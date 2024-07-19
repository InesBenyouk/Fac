package com.example.project;

import com.example.project.entities.*;
import com.example.project.repositories.ClientRepo;
import com.example.project.repositories.UtilisateurRepo;

import com.example.project.services.EnteteFactureService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.text.SimpleDateFormat;
import java.util.Date;


@SpringBootApplication
public class ProjectApplication {



	public static void main(String[] args) {

		SpringApplication.run(ProjectApplication.class, args);

	}

	@Bean
	CommandLineRunner start(ClientRepo clientRepo , EnteteFactureService enteteFactureService, UtilisateurRepo utilisateurRepo){

       return  args -> {

//		   accountService.addNewRole("USER");
//		   accountService.addNewRole("ADMIN");
//
//		   // Add users
//		   accountService.addNewUser("user1", "password1", "user1@example.com", "password1");
//		   accountService.addNewUser("admin1", "password1", "admin1@example.com", "password1");
//
//		   // Assign roles to users
//		   accountService.addRoleToUser("user1", "USER");
//		   accountService.addRoleToUser("admin1", "ADMIN");

		   Client client=new Client();
		   client.setAdresse("hhkk qsffsqfqfs");
		   client.setCIN("sfssqqsf");
		   client.setTypeClient(TypeClient.CIN);
		   client.setNom("yassine");
		   clientRepo.save(client);

		   Utilisateur utilisateur=new Utilisateur();
		   utilisateur.setEmail("mdqfq@gmail.com");
		   utilisateur.setNom("dsfsdf");
		   utilisateur.setRole(role.admin);
		   utilisateurRepo.save(utilisateur);

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
	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
}
