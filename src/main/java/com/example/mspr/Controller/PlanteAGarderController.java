package com.example.mspr.Controller;

import com.example.mspr.Repository.ContratRepository;
import com.example.mspr.Repository.PlanteAGarderRepository;
import com.example.mspr.Repository.PlanteRepository;
import com.example.mspr.Service.PhotoService;
import com.example.mspr.bo.Contrat;
import com.example.mspr.bo.Plante;
import com.example.mspr.bo.PlanteAGarder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/planteAGarder")
public class PlanteAGarderController {

    @Autowired
    PlanteAGarderRepository planteAGarderRepository;

    @Autowired
    PlanteRepository planteRepository;

    @Autowired
    ContratRepository contratRepository;

    @PostMapping("/")
    public ResponseEntity<?> newPlanteAGarder(
            @RequestAttribute("idContrat") Integer idContrat,
            @RequestAttribute("idPlante") Integer idPlante,
            @RequestAttribute("quantite") Integer quantite
    ) {
        Optional<Contrat> oContrat = contratRepository.findById(idContrat);
        Optional<Plante> oPlante = planteRepository.findById(idPlante);

        if(oPlante.isPresent() && oContrat.isPresent()) {

            PlanteAGarder planteAGarder = new PlanteAGarder();
            planteAGarder.setContrat(oContrat.get());
            planteAGarder.setPlante(oPlante.get());
            planteAGarder.setQuantite(quantite);

            planteAGarderRepository.save(planteAGarder);

            return ResponseEntity.ok("SUCCESS");
        }

        String body = oContrat.isEmpty() ? "Aucun contrat trouvé pour cet ID\n" : "";
        body += oPlante.isEmpty() ? "Aucune plante trouvé pour cet ID" : "";
        return ResponseEntity.unprocessableEntity().body(body);
    }


    @GetMapping("/{idClient}")
    public ResponseEntity<List<Plante>> getPlanteAGarderForClient(@PathVariable Integer idClient){

        List<PlanteAGarder> plantesAGarder = planteAGarderRepository.findByContrat_Client_Id(idClient);

        List<Plante> plantes = new ArrayList<>();

        plantesAGarder.forEach((PlanteAGarder planteAGarder) -> {
            Optional<Plante> oPlante = planteRepository.findById(planteAGarder.getPlante().getId());
            if(oPlante.isPresent() && !plantes.contains(oPlante.get())){
                plantes.add(oPlante.get());
            }
        });
        return new ResponseEntity<>(plantes, HttpStatus.OK);
    }
}
