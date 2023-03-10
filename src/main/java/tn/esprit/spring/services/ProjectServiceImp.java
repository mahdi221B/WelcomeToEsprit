package tn.esprit.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.spring.entity.*;
import tn.esprit.spring.repositories.*;

import java.util.Date;
import java.util.List;

@Service
public class ProjectServiceImp implements  ProjectService {

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    UserRepository userRepository ;

    @Autowired
    TeamRepository teamRepository  ;
    @Autowired
    FileSystemRepository  fileSystemRepository ;



    @Autowired
    NoteRepository noteRepository;
    @Autowired
    AppEventRepository appEventRepository;

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

    @Override
    public String addvideoproject(MultipartFile file, String desc , Long id ) throws Exception {

        String msg = null;
        if (new Date().before(appEventRepository.findAll().get(0).getStartDate()) || (new Date().after(appEventRepository.findAll().get(0).getEndDate()))) {
            msg=("you can't upload a video now  ");

        } else {
            Team t = teamRepository.findById(id).get();
            Project p = new Project();
            p.setVideo(t.getName() + " " + new Date());
            p.setDescription(desc);
            //p.setPresentation(file.getResource().toString());
            fileSystemRepository.save(file);
            p.setSubmitDate(new Date());
            projectRepository.save(p);
            p.setTeam(t);
            teamRepository.save(t);
            msg=("project added successfully ");
        }
            // find video by id

        return msg;
    }

}
