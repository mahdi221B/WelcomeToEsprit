package tn.esprit.spring.services;

import net.sourceforge.tess4j.TesseractException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IServiceFile {
    public String uploadFile(MultipartFile multipartFile) throws IOException;
    public String getFileOCR(String fileName) throws IOException, TesseractException;
}
