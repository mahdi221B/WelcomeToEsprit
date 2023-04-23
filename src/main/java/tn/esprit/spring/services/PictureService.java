package tn.esprit.spring.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface PictureService {

    public void savePicture(MultipartFile file, Integer id) throws IOException;

}
