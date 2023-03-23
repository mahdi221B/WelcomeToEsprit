package tn.esprit.spring.controllers;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entity.Project;
import tn.esprit.spring.services.ProjectService;
import tn.esprit.spring.services.ProjectService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/Project")
@Api
public class ProjectController {
    private final ProjectService projectservice;
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
}
