package com.example.mspr.Controller;

import com.example.mspr.Repository.PhotoRepository;
import com.example.mspr.Repository.PlanteAGarderRepository;
import com.example.mspr.Service.PhotoService;
import com.example.mspr.bo.PhotoJournaliere;
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

@RestController
@RequestMapping("/photo")
public class PhotoController {

    @Autowired
    private PhotoRepository photoRepository;

    @Autowired
    private PlanteAGarderRepository planteAGarderRepository;

    @PostMapping("/uploadFile")
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

    @GetMapping("/downloadFile/{fileCode}")
    public ResponseEntity<?> downloadFile(@PathVariable("fileCode") String fileCode) {

        PhotoService photoService = new PhotoService();

        Resource resource;

        try {
            resource = photoService.downloadingPhoto(fileCode);
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
}
