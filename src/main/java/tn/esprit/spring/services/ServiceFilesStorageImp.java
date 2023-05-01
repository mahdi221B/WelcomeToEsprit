package tn.esprit.spring.services;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;

import java.net.MalformedURLException;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;



@Slf4j
@Service
@AllArgsConstructor
public class ServiceFilesStorageImp implements IServiceFilesStorage{
    private final Path root = Paths.get("assets");

    @Override
    public void init() {
        try {
            Files.createDirectories(root);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize folder for upload!");
        }
    }
    public String save(String name, MultipartFile multipartFile) throws IOException,RuntimeException {
        String fileCode = RandomStringUtils.randomAlphanumeric(8);
        Path filePath = this.root.resolve(fileCode+"-"+name);//Files.copy(inputStream, filePath);
        Files.copy(multipartFile.getInputStream(), filePath);
        return name;
    }

    @Override
    public Resource load(String name) throws MalformedURLException {
        Path file = root.resolve(name);
        Resource resource = new UrlResource(file.toUri());
        if (resource.exists() || resource.isReadable()) {
            return resource;
        }
        return null;
    }
    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(root.toFile());
    }

    @Override
    public Stream<Path> loadAll() throws IOException {
        return Files.walk(this.root, 1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
    }
}