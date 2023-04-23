package tn.esprit.spring.controllers.api;

import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static tn.esprit.spring.utils.Constants.APP_ROOT;

@Api(APP_ROOT + "/pictures")
public interface PictureApi {
    @PostMapping(value = APP_ROOT + "/pictures/upload/{idUser}")
    public ResponseEntity<String> uploadPicture(@RequestParam("file") MultipartFile file, @PathVariable("idUser") Integer id) throws IOException;

}
