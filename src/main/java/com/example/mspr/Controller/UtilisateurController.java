package com.example.mspr.Controller;

import com.example.mspr.Enum.EtatUtilisateur;
import com.example.mspr.Repository.BotanisteRepository;
import com.example.mspr.Repository.ClientRepository;
import com.example.mspr.Repository.UtilisateurRepository;
import com.example.mspr.bo.Botaniste;
import com.example.mspr.bo.Client;
import com.example.mspr.bo.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UtilisateurController {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private BotanisteRepository botanisteRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Utilisateur> getUtilisateurById(@PathVariable("id") Integer idUtilisateur) {

        Utilisateur utilisateur;
        Optional<Utilisateur> oUtilisateur = utilisateurRepository.findById(idUtilisateur);

        if (oUtilisateur.isPresent()) {
            utilisateur = oUtilisateur.get();
            return new ResponseEntity<>(utilisateur, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/client/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable("id") Integer idClient) {

        Client client;
        Optional<Client> oClient = clientRepository.findById(idClient);

        if (oClient.isPresent()) {
            client = oClient.get();
            return new ResponseEntity<>(client, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/botaniste/{id}")
    public ResponseEntity<Botaniste> getBotanisteById(@PathVariable("id") Integer idBotaniste) {

        Botaniste botaniste;
        Optional<Botaniste> oBotaniste = botanisteRepository.findById(idBotaniste);

        if (oBotaniste.isPresent()) {
            botaniste = oBotaniste.get();
            return new ResponseEntity<>(botaniste, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/gardien/disponible")
    public ResponseEntity<List<Client>> getGardienDispo() {

        List<Client> clientList = clientRepository.findByEtat(EtatUtilisateur.DISPONIBLE.getValue());

        return new ResponseEntity<>(clientList, HttpStatus.OK);
    }

    @GetMapping("/gardien/indisponible")
    public ResponseEntity<List<Client>> getGardienIndispo() {

        List<Client> clientList = clientRepository.findByEtat(EtatUtilisateur.INDISPONIBLE.getValue());

        return new ResponseEntity<>(clientList, HttpStatus.OK);
    }


}
