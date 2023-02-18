package tn.esprit.spring.services;

import tn.esprit.spring.entity.Project;

import java.util.List;

public interface ProjectService {

    public List<Project> RetrieveAllProject();
    public void DeleteProject(Long id);
    public Project RetrieveProjectById(Long id);
    public Project AddProject(Project project);
    public Project UpdateProject(Project project,Long id);
}
