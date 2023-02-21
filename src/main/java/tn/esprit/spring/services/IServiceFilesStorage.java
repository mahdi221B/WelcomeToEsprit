package tn.esprit.spring.services;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.util.stream.Stream;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
public interface IServiceFilesStorage {
    public void init();

    public String save(String name, MultipartFile multipartFile)throws IOException,RuntimeException;
    public Resource load(String name) throws MalformedURLException;
    public void deleteAll();
    public Stream<Path> loadAll() throws IOException;
}
