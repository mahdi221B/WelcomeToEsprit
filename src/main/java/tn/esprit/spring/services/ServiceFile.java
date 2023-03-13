package tn.esprit.spring.services;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.*;
import lombok.extern.slf4j.Slf4j;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;
import java.util.Objects;

@Service
@Slf4j
public class ServiceFile implements IServiceFile{
    @Value("${app.firebase-configuration-file}")
    //la configuration mil firebase.json
    private String firebaseConfigPath;
    private final String BUCKETNAME = "welcometoesprit-cfe90.appspot.com";
    //fichier de stockage des doc
    private StorageOptions storageOptions;
    //les méthode prédifini mil cloud.storage
    private final String PROJECTID = "welcometoesprit-cfe90";
    //affichage du contenu des images
    @Autowired
    private Tesseract tesseract;
    private StorageOptions getStorageOptions() throws IOException {
        StorageOptions storageOptions;

        storageOptions = StorageOptions.newBuilder()
                .setProjectId(PROJECTID)
                .setCredentials(GoogleCredentials.fromStream(new ClassPathResource(firebaseConfigPath).getInputStream())).build();
        return storageOptions;
    }
    public String uploadFile(MultipartFile multipartFile) throws IOException {
        //Check if the file uploaded is image type
        try (InputStream input = multipartFile.getInputStream()) {
            try {
                String contentType = multipartFile.getContentType();
                if (!contentType.equals("image/jpeg") && !contentType.equals("image/png") &&
                        !contentType.equals("application/pdf") && !contentType.equals("application/msword") &&
                        !contentType.equals("application/vnd.openxmlformats-officedocument.wordprocessingml.document")) {
                    return ("File uploaded must be of type image, PDF or Word only");
                }
                File file = convertMultiPartToFile(multipartFile);
                Path filePath = file.toPath();

                String fileName = generateFileName(multipartFile);
                System.out.println("************************\n"+fileName);


                Storage storage = getStorageOptions().getService();

                BlobId blobId = BlobId.of(BUCKETNAME, fileName);
                BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType(contentType).build();
                Blob blob = storage.create(blobInfo, Files.readAllBytes(filePath));
                log.info("File " + filePath + " uploaded to bucket " + BUCKETNAME + " as " + fileName + " blobId " + blobId);
                return fileName;
            } catch (Exception e) {

                return ("File uploaded must be of type image, PDF or Word only");

            }
        }
    }
    public String getFileOCR(String fileName) throws IOException, TesseractException {
        Storage storage = getStorageOptions().getService();

        BlobId blobId = BlobId.of(BUCKETNAME, fileName);
        Blob b=storage.get(blobId);
        System.out.println(b);
        byte[] filecontent=b.getContent();
        BufferedImage image = ImageIO.read(new ByteArrayInputStream(filecontent));
        tesseract.setDatapath("C:\\Program Files\\Tesseract-OCR\\tessdata");
        return tesseract.doOCR(image);
    }
    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convertedFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
        FileOutputStream fos = new FileOutputStream(convertedFile);
        fos.write(file.getBytes());
        fos.close();
        return convertedFile;
    }

    private String generateFileName(MultipartFile multiPart) {
        return new Date().getTime() + "-" + Objects.requireNonNull(multiPart.getOriginalFilename()).replace(" ", "_");
    }

}
