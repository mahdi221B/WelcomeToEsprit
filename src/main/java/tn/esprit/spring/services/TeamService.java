package tn.esprit.spring.services;

import tn.esprit.spring.entity.Team;
import tn.esprit.spring.entity.Team;

import java.util.List;

public interface TeamService {

    public List<Team> RetrieveAllTeam();
    public void DeleteTeam(Long id);
    public Team RetrieveTeamById(Long id);
    public Team AddTeam(Team team);
    public Team UpdateTeam(Team team,Long id);
}
