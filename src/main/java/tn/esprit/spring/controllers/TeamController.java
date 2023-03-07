package tn.esprit.spring.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entity.Note;
import tn.esprit.spring.entity.Team;
import tn.esprit.spring.entity.Team;
import tn.esprit.spring.services.TeamService;
import tn.esprit.spring.services.TeamService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/Team")
public class TeamController {
    private final TeamService teamService;

    @PostMapping("/add")
    @ResponseBody
    public Team addTeam(@RequestBody Team team) {
        return teamService.AddTeam(team);
    }

    @PutMapping("/update/{id}")
    @ResponseBody
    public Team updateTeam(@RequestBody Team team, @PathVariable("id") Long id) {
        return teamService.UpdateTeam(team, id);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public void deleteTeam(@PathVariable("id") Long id) {
        teamService.DeleteTeam(id);
    }

    @GetMapping("/get/{id}")
    @ResponseBody
    public Team getTeamById(@PathVariable("id") Long id) {
        return teamService.RetrieveTeamById(id);
    }

    @GetMapping("/getall")
    @ResponseBody
    public List<Team> getAllTeam() {
        return teamService.RetrieveAllTeam();
    }

    @GetMapping("/noteteam/{id}")
    public double hardskils(@PathVariable("id") Long id) {
        return    teamService.calculnote(id);
    }
}