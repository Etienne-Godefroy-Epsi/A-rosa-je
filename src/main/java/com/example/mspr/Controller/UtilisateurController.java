package com.example.mspr.Controller;

import com.example.mspr.Enum.EtatClient;
import com.example.mspr.Repository.BotanisteRepository;
import com.example.mspr.Repository.ClientRepository;
import com.example.mspr.Repository.UtilisateurRepository;
import com.example.mspr.bo.Botaniste;
import com.example.mspr.bo.Client;
import com.example.mspr.bo.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UtilisateurController {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private BotanisteRepository botanisteRepository;
//
//    @GetMapping("/clients")
//    public ResponseEntity<List<Client>> getAllClients() {
//
//    }

    @PostMapping("/client/inscription")
    public ResponseEntity<String> insciptionClient(
            @RequestParam("nom") String nom,
            @RequestParam("prenom") String prenom,
            @RequestParam("mdp") String mdp,
            @RequestParam("adresse") String adresse,
            @RequestParam("email") String email,
            @RequestParam("description") String descritpion
    ) {
        Client client = new Client();
        client.setNom(nom);
        client.setPrenom(prenom);
        client.setMdp(mdp);
        client.setAdresse(adresse);
        client.setEmail(email);
        client.setDescription(descritpion);
        client.setEtat(EtatClient.DISPONIBLE.getValue());

        clientRepository.save(client);

        return ResponseEntity.ok().body("SUCCESS");
    }

    @PostMapping("/botaniste/inscription")
    public ResponseEntity<String> insciptionBotaniste(
            @RequestParam("nom") String nom,
            @RequestParam("prenom") String prenom,
            @RequestParam("mdp") String mdp,
            @RequestParam("adresse") String adresse,
            @RequestParam("email") String email,
            @RequestParam("description") String descritpion
    ) {
        Botaniste botaniste = new Botaniste();
        botaniste.setNom(nom);
        botaniste.setPrenom(prenom);
        botaniste.setMdp(mdp);
        botaniste.setAdresse(adresse);
        botaniste.setEmail(email);
        botaniste.setDescription(descritpion);

        botanisteRepository.save(botaniste);

        return ResponseEntity.ok().body("SUCCESS");
    }

    @PostMapping("/connexion")
    public ResponseEntity<?> connexion(
            @RequestParam("email") String email,
            @RequestParam("mdp") String mdp
    ) {
        Optional<Utilisateur> oUtilisateur = utilisateurRepository.findByEmailAndMdp(email, mdp);

        if (oUtilisateur.isPresent()) {
            Utilisateur utilisateur = oUtilisateur.get();
            if (utilisateur instanceof Client client) {
                return new ResponseEntity<>(client, HttpStatus.OK);
            } else if (utilisateur instanceof Botaniste botaniste) {
                return new ResponseEntity<>(botaniste, HttpStatus.OK);
            }
        }

        return new ResponseEntity<>("Aucun utilisateur trouvé pour ses identifiant", HttpStatus.NOT_FOUND);
    }

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

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(
            @PathVariable("id") Integer idUtilisateur,
            @RequestParam("nom") String nom,
            @RequestParam("prenom") String prenom,
            @RequestParam("mdp") String mdp,
            @RequestParam("adresse") String adresse,
            @RequestParam("email") String email,
            @RequestParam("description") String description) {

        Optional<Utilisateur> oUtilisateur = utilisateurRepository.findById(idUtilisateur);

        if (oUtilisateur.isPresent()) {
            Utilisateur utilisateur = oUtilisateur.get();

            utilisateur.setNom(nom);
            utilisateur.setPrenom(prenom);
            utilisateur.setMdp(mdp);
            utilisateur.setAdresse(adresse);
            utilisateur.setEmail(email);
            utilisateur.setDescription(description);

            utilisateurRepository.save(utilisateur);

            return ResponseEntity.ok().body("SUCCESS");
        } else {
            return ResponseEntity.unprocessableEntity().body("Aucun utilisateur trouvé pour cet id");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUtilisateur(@PathVariable("id") Integer idUtilisateur) {
        try {
            utilisateurRepository.deleteById(idUtilisateur);
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity().body("Aucun contrat trouvé pour cet id");
        }

        return ResponseEntity.ok().body("SUCCESS");
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

        List<Client> clientList = clientRepository.findByEtat(EtatClient.DISPONIBLE.getValue());

        return new ResponseEntity<>(clientList, HttpStatus.OK);
    }

    @GetMapping("/gardien/indisponible")
    public ResponseEntity<List<Client>> getGardienIndispo() {

        List<Client> clientList = clientRepository.findByEtat(EtatClient.INDISPONIBLE.getValue());

        return new ResponseEntity<>(clientList, HttpStatus.OK);
    }

    @PutMapping("/client/{id}/disponibilite")
    public ResponseEntity<?> updateDispoClient(
            @PathVariable("id") Integer idClient,
            @RequestParam("dispo") Character dispo) {

        Optional<Client> oClient = clientRepository.findById(idClient);

        dispo = Character.toUpperCase(dispo);

        if (oClient.isPresent() && EtatClient.isEtatClient(dispo)) {
            Client client = oClient.get();

            client.setEtat(dispo);

            clientRepository.save(client);

            return ResponseEntity.ok().body("SUCCESS");
        } else {
            String body = oClient.isEmpty() ? "Aucun client trouvé pour cet id\n" : "";
            body += !EtatClient.isEtatClient(dispo) ? "Etat incorrect" : "";
            return ResponseEntity.unprocessableEntity().body(body);
        }
    }

}
