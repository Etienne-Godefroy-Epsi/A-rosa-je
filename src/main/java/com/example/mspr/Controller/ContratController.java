package com.example.mspr.Controller;

import com.example.mspr.Enum.EtatContrat;
import com.example.mspr.Repository.BotanisteRepository;
import com.example.mspr.Repository.ClientRepository;
import com.example.mspr.Repository.ContratRepository;
import com.example.mspr.bo.Botaniste;
import com.example.mspr.bo.Client;
import com.example.mspr.bo.Contrat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/contrats")
public class ContratController {

    @Autowired
    private ContratRepository contratRepository;

    @Autowired
    private BotanisteRepository botanisteRepository;

    @Autowired
    private ClientRepository clientRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Contrat> getContratById(@PathVariable("id") Integer idContrat) {

        Contrat contrat;
        Optional<Contrat> oContrat = contratRepository.findById(idContrat);

        if (oContrat.isPresent()) {
            contrat = oContrat.get();
            return new ResponseEntity<>(contrat, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/sansBotaniste")
    public ResponseEntity<List<Contrat>> getContratsSansBotaniste() {

        Collection<Character> etats = getEtatSansBotaniste();

        List<Contrat> contratList = contratRepository.findByEtatIn(etats);

        return new ResponseEntity<>(contratList, HttpStatus.OK);
    }

    @GetMapping("/avecBotaniste")
    public ResponseEntity<List<Contrat>> getContratsAvecBotaniste() {

        List<Contrat> contratList = contratRepository.findByEtat(EtatContrat.AVECBOTANISTE.getValue());

        return new ResponseEntity<>(contratList, HttpStatus.OK);
    }

    @GetMapping("/termine")
    public ResponseEntity<List<Contrat>> getContratsTermine() {

        List<Contrat> contratList = contratRepository.findByEtat(EtatContrat.TERMINE.getValue());

        return new ResponseEntity<>(contratList, HttpStatus.OK);
    }

    @GetMapping("/forClient/{id}")
    public ResponseEntity<List<Contrat>> getContratsForClient(@PathVariable("id") Integer idClient) {

        List<Contrat> contratList = contratRepository.findByClient_Id(idClient);

        return new ResponseEntity<>(contratList, HttpStatus.OK);
    }

    @GetMapping("/sansBotaniste/forClient/{id}")
    public ResponseEntity<List<Contrat>> getContratsSansBotanisteForClient(@PathVariable("id") Integer idClient) {

        Collection<Character> etats = getEtatSansBotaniste();

        List<Contrat> contratList = contratRepository.findByClient_IdAndEtatIn(idClient, etats);

        return new ResponseEntity<>(contratList, HttpStatus.OK);
    }

    @GetMapping("/avecBotaniste/forClient/{id}")
    public ResponseEntity<List<Contrat>> getContratsAvecBotanisteForClient(@PathVariable("id") Integer idClient) {

        List<Contrat> contratList = contratRepository.findByClient_IdAndEtat(idClient, EtatContrat.AVECBOTANISTE.getValue());

        return new ResponseEntity<>(contratList, HttpStatus.OK);
    }

    @GetMapping("/termine/forClient/{id}")
    public ResponseEntity<List<Contrat>> getContratsTermineForClient(@PathVariable("id") Integer idClient) {

        List<Contrat> contratList = contratRepository.findByClient_IdAndEtat(idClient, EtatContrat.TERMINE.getValue());

        return new ResponseEntity<>(contratList, HttpStatus.OK);
    }

    @GetMapping("/forGardien/{id}")
    public ResponseEntity<List<Contrat>> getContratsForGardien(@PathVariable("id") Integer idGardien) {

        List<Contrat> contratList = contratRepository.findByGardien_Id(idGardien);

        return new ResponseEntity<>(contratList, HttpStatus.OK);
    }

    @GetMapping("/sansBotaniste/forGardien/{id}")
    public ResponseEntity<List<Contrat>> getContratsSansBotanisteForGardien(@PathVariable("id") Integer idGardien) {

        Collection<Character> etats = getEtatSansBotaniste();

        List<Contrat> contratList = contratRepository.findByGardien_IdAndEtatIn(idGardien, etats);

        return new ResponseEntity<>(contratList, HttpStatus.OK);
    }

    @GetMapping("/avecBotaniste/forGardien/{id}")
    public ResponseEntity<List<Contrat>> getContratsAvecBotanisteForGardien(@PathVariable("id") Integer idGardien) {

        List<Contrat> contratList = contratRepository.findByGardien_IdAndEtat(idGardien, EtatContrat.AVECBOTANISTE.getValue());

        return new ResponseEntity<>(contratList, HttpStatus.OK);
    }

    @GetMapping("/termine/forGardien/{id}")
    public ResponseEntity<List<Contrat>> getContratsTermineForGardien(@PathVariable("id") Integer idGardien) {

        List<Contrat> contratList = contratRepository.findByGardien_IdAndEtat(idGardien, EtatContrat.TERMINE.getValue());

        return new ResponseEntity<>(contratList, HttpStatus.OK);
    }

    @GetMapping("/forBotaniste/{id}")
    public ResponseEntity<List<Contrat>> getContratsForBotaniste(@PathVariable("id") Integer idBotaniste) {

        List<Contrat> contratList = contratRepository.findByBotaniste_Id(idBotaniste);

        return new ResponseEntity<>(contratList, HttpStatus.OK);
    }

    @GetMapping("/sansBotaniste/forBotaniste/{id}")
    public ResponseEntity<List<Contrat>> getContratsSansBotanisteForBotaniste(@PathVariable("id") Integer idBotaniste) {

        Collection<Character> etats = getEtatSansBotaniste();

        List<Contrat> contratList = contratRepository.findByBotaniste_IdAndEtatIn(idBotaniste, etats);

        return new ResponseEntity<>(contratList, HttpStatus.OK);
    }

    @GetMapping("/avecBotaniste/forBotaniste/{id}")
    public ResponseEntity<List<Contrat>> getContratsAvecBotanisteForBotaniste(@PathVariable("id") Integer idBotaniste) {

        List<Contrat> contratList = contratRepository.findByBotaniste_IdAndEtat(idBotaniste, EtatContrat.AVECBOTANISTE.getValue());

        return new ResponseEntity<>(contratList, HttpStatus.OK);
    }

    @GetMapping("/termine/forBotaniste/{id}")
    public ResponseEntity<List<Contrat>> getContratsTermineForBotaniste(@PathVariable("id") Integer idBotaniste) {

        List<Contrat> contratList = contratRepository.findByBotaniste_IdAndEtat(idBotaniste, EtatContrat.TERMINE.getValue());

        return new ResponseEntity<>(contratList, HttpStatus.OK);
    }

    @PostMapping("/demande")
    public ResponseEntity<?> newContrat(
            @RequestParam("dateDebut") LocalDate dateDebut,
            @RequestParam("dateFin") LocalDate dateFin,
            @RequestParam("idClient") Integer idClient,
            @RequestParam("idGardien") Integer idGardien) {

        Optional<Client> oClient = clientRepository.findById(idClient);
        Optional<Client> oGardien = clientRepository.findById(idGardien);

        if (oClient.isPresent() && oGardien.isPresent()) {
            Contrat contrat = new Contrat();

            contrat.setDatedebut(dateDebut);
            contrat.setDatefin(dateFin);
            contrat.setClient(oClient.get());
            contrat.setGardien(oGardien.get());
            contrat.setEtat(EtatContrat.DEMANDE.getValue());

            contratRepository.save(contrat);

            return ResponseEntity.ok().body("SUCCESS");
        } else {
            String body = oClient.isEmpty() ? "Aucun client trouvé pour cet id \n" : "";
            body += oGardien.isEmpty() ? "Aucun gardien trouvé pour cet id \n" : "";

            return ResponseEntity.unprocessableEntity().body(body);
        }
    }

    @PutMapping("/demande/accepter/{idContrat}")
    public ResponseEntity<String> accepteContrat(@PathVariable Integer idContrat) {
        Optional<Contrat> oContrat = contratRepository.findById(idContrat);

        if (oContrat.isPresent()) {
            Contrat contratAccepte = oContrat.get();
            contratAccepte.setEtat(EtatContrat.SANSBOTANISTE.getValue());

            List<Contrat> contratsASupp = contratRepository.findByClient_IdAndDatedebutAndDatefin(contratAccepte.getClient().getId(), contratAccepte.getDatedebut(), contratAccepte.getDatefin());
            contratRepository.deleteAll(contratsASupp);

            return ResponseEntity.ok().body("SUCCESS");
        }

        return ResponseEntity.unprocessableEntity().body("Aucun contrat trouvé avec cet ID");
    }

    @GetMapping("/demande/forClient/{idClient}")
    public ResponseEntity<List<Contrat>> getDemandeByIdClient(@PathVariable Integer idClient) {
        List<Contrat> contrats = contratRepository.findByClient_IdAndEtat(idClient, EtatContrat.DEMANDE.getValue());

        return new ResponseEntity<>(contrats, HttpStatus.OK);
    }

    @GetMapping("/demande/forGardien/{idGardien}")
    public ResponseEntity<List<Contrat>> getDemandeByIdGardien(@PathVariable Integer idGardien) {
        List<Contrat> contrats = contratRepository.findByGardien_IdAndEtat(idGardien, EtatContrat.DEMANDE.getValue());

        return new ResponseEntity<>(contrats, HttpStatus.OK);
    }

    @PutMapping("{id}/ajoutBotaniste")
    public ResponseEntity<?> addBotaniste(@PathVariable("id") Integer idContrat, @RequestParam("idBotaniste") Integer idBotaniste) {

        Optional<Contrat> oContrat = contratRepository.findById(idContrat);
        Optional<Botaniste> oBotaniste = botanisteRepository.findById(idBotaniste);

        if (oContrat.isPresent() && oBotaniste.isPresent()) {
            Contrat contrat = oContrat.get();
            contrat.setBotaniste(oBotaniste.get());

            contratRepository.save(contrat);

            return ResponseEntity.ok().body("SUCCESS");
        } else {
            String body = oContrat.isEmpty() ? "Aucun contrat trouvé avec cet id \n" : "";
            body += oBotaniste.isEmpty() ? "Aucun botaniste trouvé avec cet id" : "";

            return ResponseEntity.unprocessableEntity().body(body);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteContrat(@PathVariable("id") Integer idContrat) {

        try {
            contratRepository.deleteById(idContrat);
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity().body("Aucun contrat trouvé pour cet id");
        }

        return ResponseEntity.ok().body("SUCCESS");
    }

    public Collection<Character> getEtatSansBotaniste() {
        Collection<Character> etats = new ArrayList<>();
        etats.add(EtatContrat.SANSBOTANISTE.getValue());
        etats.add(EtatContrat.URGENCE.getValue());

        return etats;
    }

}
