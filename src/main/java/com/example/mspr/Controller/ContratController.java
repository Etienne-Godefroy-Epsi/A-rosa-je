package com.example.mspr.Controller;

import com.example.mspr.Enum.EtatContrat;
import com.example.mspr.Repository.BotanisteRepository;
import com.example.mspr.Repository.ContratRepository;
import com.example.mspr.bo.Botaniste;
import com.example.mspr.bo.Contrat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/contrats")
public class ContratController {

    @Autowired
    private ContratRepository contratRepository;

    @Autowired
    private BotanisteRepository botanisteRepository;

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

    @PutMapping("{id}/ajoutBotaniste")
    public ResponseEntity<?> addBotaniste(@PathVariable("id") Integer idContrat, @RequestParam("idBotaniste") Integer idBotaniste) {

        Contrat contrat;
        Optional<Contrat> oContrat = contratRepository.findById(idContrat);
        Optional<Botaniste> oBotaniste = botanisteRepository.findById(idBotaniste);

        if (oContrat.isPresent() && oBotaniste.isPresent()) {
            contrat = oContrat.get();
            contrat.setBotaniste(oBotaniste.get());

            contratRepository.save(contrat);

            return ResponseEntity.ok().body("SUCCESS");
        } else {
            String body = oContrat.isEmpty() ? "Aucun contrat trouvé avec cet id \n" : "";
            body += oBotaniste.isEmpty() ? "Aucun botaniste trouvé avec cet id" : "";

            return ResponseEntity.unprocessableEntity().body(body);
        }
    }

    public Collection<Character> getEtatSansBotaniste() {
        Collection<Character> etats = new ArrayList<>();
        etats.add(EtatContrat.SANSBOTANISTE.getValue());
        etats.add(EtatContrat.URGENCE.getValue());

        return etats;
    }

}
