package com.example.project;

import com.example.project.entities.Client;
import com.example.project.entities.EnteteFacture;
import com.example.project.entities.TypeClient;
import com.example.project.repositories.ClientRepo;
import com.example.project.services.EnteteFactureService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.text.SimpleDateFormat;
import java.util.Date;


@SpringBootApplication
public class ProjectApplication {



	public static void main(String[] args) {

		SpringApplication.run(ProjectApplication.class, args);

	}

	@Bean
	CommandLineRunner start(ClientRepo clientRepo , EnteteFactureService enteteFactureService){

       return  args -> {
		   Client client=new Client();
		   client.setAdresse("hhkk qsffsqfqfs");
		   client.setCIN("sfssqqsf");
		   client.setTypeClient(TypeClient.CIN);
		   client.setNom("yassine");
		   clientRepo.save(client);


		   // Create and save an EnteteFacture
		   EnteteFacture enteteFacture = new EnteteFacture();
		   enteteFacture.setMontantTTC(100.0f);
		   enteteFacture.setMontantHT(80.0f);
		   enteteFacture.setMontantTVA(20.0f);
		   enteteFacture.setMontantTTR(5.0f);
		   SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		   Date dateEmission = dateFormat.parse("2023-01-01");
		   Date datePaiement = dateFormat.parse("2023-01-15");
		   enteteFacture.setDateEmission(dateEmission);
		   enteteFacture.setDatePaiement(datePaiement);
		   enteteFacture.setClient(client);
		   enteteFactureService.save(enteteFacture);
	   };
	}
}
