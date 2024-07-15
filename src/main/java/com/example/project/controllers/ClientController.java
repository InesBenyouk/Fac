package com.example.project.controllers;

import com.example.project.entities.Client;
import com.example.project.entities.TypeClient;
import com.example.project.services.ClientService;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class ClientController {

    private ClientService clientService;
    private ClientService clientRepository;

    @PostMapping
    public Client createClient(@RequestBody Client client) {
        return (Client) clientService.saveClient(client);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable Long id, @RequestBody Client newClient) {
        return clientService.updateClient(id, newClient)
                .map(client -> ResponseEntity.ok(client))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    // Autres méthodes

    @GetMapping("/type/{typeClient}")
    public ResponseEntity<List<Client>> getClientsByType(@PathVariable TypeClient typeClient) {
        List<Client> clients = clientService.findByTypeClient(typeClient);
        return clients.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(clients);
    }
    @DeleteMapping("/conditional/{id}")
    public ResponseEntity<Void> conditionalDeleteClient(@PathVariable Long id) {
        Optional<Client> client = clientRepository.findById(id);
        if (client.isPresent() && client.get().getFactures().isEmpty()) {
            clientRepository.delete(client.get());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).build(); // ou tout autre code approprié
    }


}

