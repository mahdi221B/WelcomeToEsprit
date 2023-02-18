package tn.esprit.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entity.Project;
import tn.esprit.spring.repositories.ProjectRepository;

import java.util.List;

@Service
public class ProjectServiceImp implements  ProjectService {

    @Autowired
    ProjectRepository projectRepository;

    @Override
    public List<Project> RetrieveAllProject() {
        return projectRepository.findAll() ;

    }

    @Override
    public void DeleteProject(Long id) {
        projectRepository.delete(projectRepository.findById(id).get());

    }

    @Override
    public Project RetrieveProjectById(Long id) {
        return  projectRepository.findById(id).get();

    }

    @Override
    public Project AddProject(Project project) {
        return projectRepository.save(project);

    }

    @Override
    public Project UpdateProject(Project project, Long id) {
        project.setId(id);
        return projectRepository.save(project);
    }

}
