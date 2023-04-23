package tn.esprit.spring.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.spring.entity.Picture;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.exception.EntityNotFoundException;
import tn.esprit.spring.exception.ErrorCodes;
import tn.esprit.spring.repositories.PictureRepository;
import tn.esprit.spring.repositories.UserRepository;
import tn.esprit.spring.services.PictureService;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class PictureServiceImpl implements PictureService {

    private PictureRepository pictureRepository;
    private UserRepository userRepository;
    @Autowired
    public PictureServiceImpl(PictureRepository pictureRepository,UserRepository userRepository){
        this.pictureRepository=pictureRepository;
        this.userRepository=userRepository;
    }
    @Override
    public void savePicture(MultipartFile file, Integer id) throws IOException {
        User user=userRepository.findById(id).orElseThrow(
                ()-> new EntityNotFoundException("No User with this ID in DB", ErrorCodes.USER_NOT_FOUND)
        );

        String filename= UUID.randomUUID().toString() +"_"+ file.getOriginalFilename();
        File savedFile= new File("C:\\Users\\Souro\\IdeaProjects\\WelcomeToEsprit\\src\\main\\resources\\Pictures\\" + filename);
        file.transferTo(savedFile);

        Picture picture=new Picture();
        picture.setFilename(filename);
        picture.setFilePath(savedFile.getAbsolutePath());
        picture.setUser(user);
        //pictureRepository.save(picture);
        user.setPicture(pictureRepository.save(picture));
        userRepository.save(user);
    }
}
