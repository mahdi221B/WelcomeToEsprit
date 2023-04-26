package tn.esprit.spring.controllers;

import lombok.AllArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.spring.entity.Project;
import tn.esprit.spring.services.FileSystemRepository;
import tn.esprit.spring.services.ProjectService;
import tn.esprit.spring.services.ProjectService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/Project")
public class ProjectController {
    private final ProjectService projectservice;
    private final    FileSystemRepository  fileSystemRepository ;

    @PostMapping("/add")
    @ResponseBody
    public Project addProject(@RequestBody Project project){
        return projectservice.AddProject(project);
    }
    @PutMapping("/update/{id}")
    @ResponseBody
    public Project updateProject(@RequestBody Project project, @PathVariable("id") Long id){
        return projectservice.UpdateProject(project,id);
    }
    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public void deleteProject(@PathVariable("id") Long id){
        projectservice.DeleteProject(id);
    }
    @GetMapping("/get/{id}")
    @ResponseBody
    public Project getProjectById(@PathVariable("id") Long id){
        return projectservice.RetrieveProjectById(id);
    }
    @GetMapping("/getall")
    @ResponseBody
    public List<Project> getAllProject(){
        return projectservice.RetrieveAllProject();
    }



    @PostMapping("/addvideo/{id}/{iduser}")
    @ResponseBody
    public String  createProject(@RequestParam("file") MultipartFile project ,@RequestParam("desc") String desc ,@PathVariable("id") Long id ,@PathVariable("iduser") int iduser  ) throws Exception {
        return projectservice.addvideoproject(project,desc, id,iduser);
    }


     @GetMapping("/media/{id}")
    @ResponseBody
    public ResponseEntity<FileSystemResource> getmedia (@PathVariable("id") Long id  ){
         Project p = projectservice.RetrieveProjectById(id);

        if (p!=null){
            try {
                FileSystemResource fileSystemResource = fileSystemRepository.findInFileSystem(p.getLocation());
                MediaType mediaType = MediaTypeFactory.getMediaType(fileSystemResource).orElse(MediaType.APPLICATION_OCTET_STREAM);
                return ResponseEntity.ok().contentType(mediaType).body(fileSystemResource);
            } catch (Exception e) {
                return ResponseEntity.notFound().build();
            }
        }
return ResponseEntity.notFound().build();

     }

    }



