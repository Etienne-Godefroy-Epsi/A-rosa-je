package com.example.mspr.Controller;

import com.example.mspr.Repository.PlanteRepository;
import com.example.mspr.Service.PhotoService;
import com.example.mspr.bo.Plante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/plantes")
public class PlanteController {

    @Autowired
    private PlanteRepository planteRepository;

    @PostMapping("/")
    public ResponseEntity<?> newPlante(
            @RequestParam("nom") String nom,
            @RequestParam("photo") MultipartFile photo,
            @RequestParam("type") String type) throws IOException {


        PhotoService photoService = new PhotoService();
        String lienPhoto = photoService.uploadingPhoto(photo);

        Plante plante = new Plante();
        plante.setNom(nom);
        plante.setType(type);
        plante.setPhoto(lienPhoto);

        planteRepository.save(plante);

        return ResponseEntity.ok().body("SUCCESS");
    }

    @GetMapping("/")
    public ResponseEntity<List<Plante>> getPlantes() {
        return new ResponseEntity<>(planteRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Plante> getPlanteById(@PathVariable("id") Integer idPlante) {
        Plante plante;
        Optional<Plante> oPlante = planteRepository.findById(idPlante);

        if (oPlante.isPresent()) {
            plante = oPlante.get();
            return new ResponseEntity<>(plante, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updatePlante(
            @PathVariable("id") Integer idPlante,
            @RequestParam("nom") String nom,
            @RequestParam("photo") MultipartFile photo,
            @RequestParam("type") String type) throws IOException {

        PhotoService photoService = new PhotoService();
        String lienPhoto = photoService.uploadingPhoto(photo);

        Optional<Plante> oPlante = planteRepository.findById(idPlante);

        if (oPlante.isPresent()) {
            Plante plante = new Plante();
            plante.setNom(nom);
            plante.setType(type);
            plante.setPhoto(lienPhoto);

            planteRepository.save(plante);

            return ResponseEntity.ok().body("SUCCESS");
        } else {
            return ResponseEntity.unprocessableEntity().body("Auncune plante trouvé");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePlante(@PathVariable("id") Integer idPlante) {

        planteRepository.deleteById(idPlante);

        return ResponseEntity.ok().body("SUCCESS");
    }
}
