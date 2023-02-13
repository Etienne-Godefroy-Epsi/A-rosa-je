package com.example.mspr.Controller;

import com.example.mspr.Repository.PhotoRepository;
import com.example.mspr.Repository.PlanteAGarderRepository;
import com.example.mspr.Repository.PlanteRepository;
import com.example.mspr.Service.PhotoService;
import com.example.mspr.bo.PhotoJournaliere;
import com.example.mspr.bo.Plante;
import com.example.mspr.bo.PlanteAGarder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/photo")
public class PhotoController {

    @Autowired
    private PhotoRepository photoRepository;

    @Autowired
    private PlanteAGarderRepository planteAGarderRepository;

    @Autowired
    private PlanteRepository planteRepository;

    @PostMapping("/uploadPhoto")
    public ResponseEntity<PhotoJournaliere> uploadPhoto(@RequestParam("file") MultipartFile multipartFile) throws IOException {

        PhotoService photoService = new PhotoService();

        String namePhoto = photoService.uploadingPhoto(multipartFile);

        PhotoJournaliere photoJournaliere = new PhotoJournaliere();
        photoJournaliere.setDatejour(LocalDate.now());
        planteAGarderRepository.findById(1).ifPresent(photoJournaliere::setPlanteAGarder);
        photoJournaliere.setLien(namePhoto);

        photoRepository.save(photoJournaliere);

        return new ResponseEntity<>(photoJournaliere, HttpStatus.OK);
    }

    @GetMapping("/downloadPhoto/journalière/{idPlanteAGarder}")
    public ResponseEntity<?> downloadFile(@PathVariable Integer idPlanteAGarder) {

        PhotoService photoService = new PhotoService();

        Resource resource;

        Optional<PlanteAGarder> oPlanteAGarder = planteAGarderRepository.findById(idPlanteAGarder);

        if (oPlanteAGarder.isPresent()) {
            String fileName = oPlanteAGarder.get().getPlante().getPhoto();

            try {
                resource = photoService.downloadingPhoto(fileName);
            } catch (IOException e) {
                return ResponseEntity.internalServerError().build();
            }

            if (resource == null) {
                return new ResponseEntity<>("File not found", HttpStatus.NOT_FOUND);
            }

            String contentType = "application/octet-stream";
            String headerValue = "attachment; filename=\"" + resource.getFilename() + "\"";

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .header(HttpHeaders.CONTENT_DISPOSITION, headerValue)
                    .body(resource);
        }

        return ResponseEntity.unprocessableEntity().body("Aucune plante à garder trouvé avec cet ID");
    }

    @GetMapping("/downloadPhoto/plante/{idPlante}")
    public ResponseEntity<?> downloadPhotoPlante(@PathVariable Integer idPlante) {

        PhotoService photoService = new PhotoService();

        Resource resource;

        Optional<Plante> oPlante = planteRepository.findById(idPlante);

        if (oPlante.isPresent()) {
            String fileName = oPlante.get().getPhoto();

            try {
                resource = photoService.downloadingPhoto(fileName);
            } catch (IOException e) {
                return ResponseEntity.internalServerError().build();
            }

            if (resource == null) {
                return new ResponseEntity<>("File not found", HttpStatus.NOT_FOUND);
            }

            String contentType = "application/octet-stream";
            String headerValue = "attachment; filename=\"" + resource.getFilename() + "\"";

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .header(HttpHeaders.CONTENT_DISPOSITION, headerValue)
                    .body(resource);
        }

        return ResponseEntity.unprocessableEntity().body("Aucune plante à garder trouvé avec cet ID");
    }


}
