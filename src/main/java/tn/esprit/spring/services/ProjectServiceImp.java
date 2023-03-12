package tn.esprit.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.spring.entity.*;
import tn.esprit.spring.repositories.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
    public String addvideoproject(MultipartFile file, String desc , Long id ,int  iduser) throws Exception {

        String msg = null;
        List<Project> projects = projectRepository.findAll();
        User u = userRepository.findById(iduser).get();
        //Team t = teamRepository.findById(id).get();

        if (!u.getProfil().isTeamcaptain()){
            msg ="only team captain  can upload a video";
        }

        else
        {
        for (Project project : projects) {
            if (project.getTeam().getId().equals(id)) {
                return "A project already exists for this team.";
            }}
            if (new Date().before(appEventRepository.findAll().get(0).getStartDate()) || (new Date().after(appEventRepository.findAll().get(0).getEndDate()))) {
                msg = ("you can't upload a video now  ");
            } else {
                Team t = teamRepository.findById(id).get();
                Project p = new Project();
                p.setVideo(t.getName() + " " + new Date());
                p.setDescription(desc);
                fileSystemRepository.save(file);
                p.setSubmitDate(new Date());
                projectRepository.save(p);
                p.setTeam(t);
                teamRepository.save(t);
                msg = ("project added successfully ");
            }}
            return msg;
        }}

