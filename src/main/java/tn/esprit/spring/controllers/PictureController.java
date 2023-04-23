package tn.esprit.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.spring.controllers.api.PictureApi;
import tn.esprit.spring.services.PictureService;

import java.io.IOException;

@RestController
public class PictureController implements PictureApi {

    private PictureService pictureService;
    @Autowired
    public PictureController(PictureService pictureService){
        this.pictureService=pictureService;
    }
    @Override
    public ResponseEntity<String> uploadPicture(MultipartFile file, Integer id){
        try {
            pictureService.savePicture(file, id);
            return ResponseEntity.ok().body("Picture uploaded successfully");
        }catch (IOException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload picture");
        }
    }
}
