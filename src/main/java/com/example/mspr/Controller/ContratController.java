package com.example.mspr.Controller;

import com.example.mspr.Repository.ContratRepository;
import com.example.mspr.bo.Contrat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/contrat")
public class ContratController {

    @Autowired
    private ContratRepository contratRepository;

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
}
