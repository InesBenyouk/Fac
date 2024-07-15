package com.example.project;

import com.example.project.entities.Client;
import com.example.project.entities.TypeClient;
import com.example.project.repositories.ClientRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class ProjectApplication {



	public static void main(String[] args) {

		SpringApplication.run(ProjectApplication.class, args);

	}

	@Bean
	CommandLineRunner start(ClientRepo clientRepo){

       return  args -> {
		   Client client=new Client();
		   client.setAdresse("hhkk qsffsqfqfs");
		   client.setCIN("sfssqqsf");
		   client.setTypeClient(TypeClient.CIN);
		   client.setNom("yassine");
		   clientRepo.save(client);
	   };
	}
}
