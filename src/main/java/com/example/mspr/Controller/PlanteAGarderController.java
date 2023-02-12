package com.example.mspr.Controller;

import com.example.mspr.Repository.ContratRepository;
import com.example.mspr.Repository.PhotoRepository;
import com.example.mspr.Repository.PlanteAGarderRepository;
import com.example.mspr.Repository.PlanteRepository;
import com.example.mspr.bo.Contrat;
import com.example.mspr.bo.Plante;
import com.example.mspr.bo.PlanteAGarder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/planteAGarder")
public class PlanteAGarderController {

    @Autowired
    PlanteAGarderRepository planteAGarderRepository;

    @Autowired
    PhotoRepository photoRepository;

    @Autowired
    PlanteRepository planteRepository;

    @Autowired
    ContratRepository contratRepository;

    @GetMapping("/parContrat/{idContrat}")
    public ResponseEntity<List<PlanteAGarder>> getPlanteAGarderByIdContrat(@PathVariable Integer idContrat) {

        List<PlanteAGarder> plantesAGarder = planteAGarderRepository.findByContrat_Id(idContrat);

        return new ResponseEntity<>(plantesAGarder, HttpStatus.OK);
    }


    @PostMapping("/")
    public ResponseEntity<String> newPlanteAGarder(
            @RequestParam("idContrat") Integer idContrat,
            @RequestParam("idPlante") Integer idPlante,
            @RequestParam("quantite") Integer quantite
    ) {

        Optional<Plante> oPlante = planteRepository.findById(idPlante);
        Optional<Contrat> oContrat = contratRepository.findById(idContrat);

        if (oPlante.isPresent() && oContrat.isPresent()) {
            Plante plante = oPlante.get();
            Contrat contrat = oContrat.get();

            PlanteAGarder planteAGarder = new PlanteAGarder();
            planteAGarder.setPlante(plante);
            planteAGarder.setContrat(contrat);
            planteAGarder.setQuantite(quantite);

            planteAGarderRepository.save(planteAGarder);

            return ResponseEntity.ok("SUCCESS");
        }

        String body = oPlante.isEmpty() ? "Aucune plante trouvé pour cet ID" : "";
        body += oContrat.isEmpty() ? "Aucun contrat trouvé pour cet ID" : "";
        return ResponseEntity.unprocessableEntity().body(body);
    }


}
