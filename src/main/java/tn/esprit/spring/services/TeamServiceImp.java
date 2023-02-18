package tn.esprit.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entity.Team;
import tn.esprit.spring.repositories.TeamRepository;
import tn.esprit.spring.repositories.TeamRepository;

import java.util.List;

@Service
public class TeamServiceImp implements  TeamService {

    @Autowired
    TeamRepository teamRepository;

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

}
