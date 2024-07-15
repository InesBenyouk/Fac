package com.example.project.services;



import java.util.List;


import com.example.project.entities.Client;

import com.example.project.entities.TypeClient;
import com.example.project.repositories.ClientRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepo clientRepository;

    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    public Optional<Client> findById(Long id) {
        return clientRepository.findById(id);
    }

    public Client saveClient(Client client) {
        return clientRepository.save(client);
    }

    public Optional<Client> updateClient(Long id, Client newClient) {
        return clientRepository.findById(id).map(client -> {
            client.setNom(newClient.getNom());
            client.setAdresse(newClient.getAdresse());
            client.setTypeClient(newClient.getTypeClient());
            client.setICE(newClient.getICE());
            client.setCIN(newClient.getCIN());
            return clientRepository.save(client);
        });
    }




    public List<Client> findByTypeClient(TypeClient typeClient) {
        return clientRepository.findByTypeClient(typeClient);
    }


    @Transactional
    public void delete(Client client) {
        if (client != null && client.getId() != null && clientRepository.existsById(client.getId())) {
            clientRepository.delete(client);
        }
    }
}

