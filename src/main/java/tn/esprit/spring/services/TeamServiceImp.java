package tn.esprit.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entity.Note;
import tn.esprit.spring.entity.Project;
import tn.esprit.spring.entity.Team;
import tn.esprit.spring.repositories.AppEventRepository;
import tn.esprit.spring.repositories.ProjectRepository;
import tn.esprit.spring.repositories.TeamRepository;
import com.vader.sentiment.analyzer.SentimentAnalyzer;
import com.vader.sentiment.analyzer.SentimentPolarities;

import javax.lang.model.type.NullType;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeamServiceImp implements  TeamService {

    @Autowired
    TeamRepository teamRepository;
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    AppEventRepository appEventRepository;
    @Override
    public List<Team> RetrieveAllTeam() {
        return teamRepository.findAll() ;

    }

    @Override
    public void DeleteTeam(Long id) {
        teamRepository.delete(teamRepository.findById(id).get());

    }

    @Override
    public Team RetrieveTeamById(Long id) {
        return  teamRepository.findById(id).get();

    }

    @Override
    public Team AddTeam(Team team) {
        return teamRepository.save(team);

    }

    @Override
    public Team UpdateTeam(Team team, Long id) {
        team.setId(id);
        return teamRepository.save(team);
    }

    @Override
    public Double calculnote(Long id) {
        Double nhskils ;
        Double nskils ;
        Double noteteam=0.0;
        if ((new Date().before(appEventRepository.findAll().get(0).getEndDate())))
        {System.out.println ("event didn't finish yet");}

        else {


        Team t = teamRepository.findById(id).get();
        Project p  = projectRepository.findById(id).get();
        List<Note> n = p.getNotes();
        Integer num1 = 0;
        Double sum1  = Double.valueOf(0);
        Integer num2 = 0;
        Double sum2  = Double.valueOf(0);
        Double ncoment =  Double.valueOf(0);

        for (Note n1:n) {
            sum1+= n1.getHardSkils();
            num1++;
        }

        for (Note n1:n) {

            sum2+= n1.getSoftSkils();
            num2++;
        }

        nhskils = sum1/num1;
        nskils = sum2/num2;

        for (Note n1:n) {
         SentimentPolarities sentimentPolarities = SentimentAnalyzer.getScoresFor(n1.getComment());
        //System.out.println("la resulatt est "+sentimentPolarities.getPositivePolarity());
             ncoment =  ncoment +  sentimentPolarities.getPositivePolarity();
            System.out.println(ncoment);
        }

        noteteam=  ((ncoment/ n.size()) *20 *.2) + (nhskils*.6) + (nskils*.2);

        p.getTeam().setNoteTeam(noteteam);
        teamRepository.save(t);}





    return noteteam;
    }



}
