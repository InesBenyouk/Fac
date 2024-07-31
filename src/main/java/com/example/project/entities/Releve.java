package com.example.project.entities;

import com.example.project.repositories.DetailsFactureRepo;
import com.example.project.repositories.EnteteFactureRepo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Releve {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.DATE)
    private Date dateRelevé;
    private float ancienneLecture;
    private float nouvelleLecture;
    private Long portId;
    @Temporal(TemporalType.DATE)
    private Date dateDebut;
    @Temporal(TemporalType.DATE)
    private Date dateFin;
    private float quantiteConsommee;

    @ManyToOne(fetch = FetchType.EAGER)
    private Police police;
    @Transient
    private float consommation;
    @Transient
    private float tarif;


    public void setQuantiteConsommee() {
        int compteur5Chiffres = police.getCompteur5Chiffres();
        float difference = nouvelleLecture - ancienneLecture;
        if (difference < 0) {
            difference += Math.pow(10, compteur5Chiffres);
        }
        this.quantiteConsommee = difference;
    }

    public EnteteFacture genererFacture(Client client, float tauxTVA, float tauxTTR) {
        EnteteFacture facture = new EnteteFacture();
        facture.setClient(client);
        facture.setDateEmission(new Date());

        // Exemple de calcul du montant HT basé sur la quantité consommée
        float montantHT = this.quantiteConsommee * 100; // Ajustez le calcul selon vos besoins
        facture.setMontantHT(montantHT);

        // Calcul des montants TVA, TTR et TTC
        float montantTVA = montantHT * tauxTVA;
        float montantTTR = montantHT * tauxTTR;
        float montantTTC = montantHT + montantTVA + montantTTR;

        facture.setMontantTVA(montantTVA);
        facture.setMontantTTR(montantTTR);
        facture.setMontantTTC(montantTTC);

        // Créer et ajouter les détails de la facture
        DetailsFacture details = new DetailsFacture();
        details.setNumLigne(1);
        details.setHTligne(montantHT);
        details.setTVAligne(montantTVA);
        details.setTRligne(montantTTR);
        details.setTTCligne(montantTTC);
        details.setLibellePrestation("Consommation");
        details.setEnteteFacture(facture);

        facture.getDetailsFactures().add(details);

        return facture;

    }



    public void genererFacture(Client client, float tauxTVA, float tauxTTR, EnteteFactureRepo enteteFactureRepo, DetailsFactureRepo detailsFactureRepo) {
        // Calculer le montant HT
//        this.consommation = (nouvelleLecture - ancienneLecture) * 100;
        float consommation =0;
        float tarif=1;
        float montantHT = consommation * tarif;
        if (consommation <= 0 || tarif <= 0) {
            throw new IllegalArgumentException("Consommation et tarif doivent être des valeurs positives.");
        }
        // Calcul des montants TVA, TTR, et TTC
        float montantTVA = montantHT * tauxTVA;
        float montantTTR = montantHT * tauxTTR;
        float montantTTC = montantHT + montantTVA + montantTTR;

        // Création de l'entête de facture
        EnteteFacture facture = new EnteteFacture();
        facture.setClient(client);
        facture.setMontantHT(montantHT);
        facture.setMontantTVA(montantTVA);
        facture.setMontantTTR(montantTTR);
        facture.setMontantTTC(montantTTC);
        // Ajoutez d'autres propriétés si nécessaire

        // Sauvegarde de l'entête de facture
        enteteFactureRepo.save(facture);
    }

//   public int getConsommation() {
//
//        return Consommation;
//    }
//
//    public int getTarif() {
//    }
}


