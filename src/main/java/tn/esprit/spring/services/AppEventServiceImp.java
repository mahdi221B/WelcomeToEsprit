package tn.esprit.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entity.AppEvent;
import tn.esprit.spring.entity.Intrest;
import tn.esprit.spring.entity.Team;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.repositories.AppEventRepository;
import tn.esprit.spring.repositories.TeamRepository;
import tn.esprit.spring.repositories.UserRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppEventServiceImp implements  AppEventService {


    @Autowired
    AppEventRepository appEventRepository;
    @Autowired
    UserRepository userRepository;

    @Autowired
    TeamRepository teamRepository;

    @Override
    public List<AppEvent> RetrieveAllAppEvent() {
        return appEventRepository.findAll();
    }

    @Override
    public void DeleteAppEvent(Long id) {
        appEventRepository.delete(appEventRepository.findById(id).get());

    }

    @Override
    public AppEvent RetrieveAppEventById(Long id) {
        return appEventRepository.findById(id).get();
    }

    @Override
    public AppEvent AddAppEvent(AppEvent appEvent) {
        return appEventRepository.save(appEvent);

    }

    @Override
    public AppEvent UpdateAppEvent(AppEvent appEvent, Long id) {
        appEvent.setId(id);
        return appEventRepository.save(appEvent);
    }

    @Override
    public void affectuserstoteam() {
        if ((new Date().before(appEventRepository.findAll().get(0).getStartDate())))
        {System.out.println ("event didn't started yet");}

        else {
            List<User> allusers = userRepository.findAll();
            List<User> listit = new ArrayList<>();
            List<User> listmeca = new ArrayList<>();
            List<User> listelec = new ArrayList<>();
            List<User> listmulti = new ArrayList<>();
            List<User> listinnov = new ArrayList<>();
            listit = allusers.stream().filter(u -> u.getProfil().getIntrest().equals(Intrest.it)).collect(Collectors.toList());
            listmeca = allusers.stream().filter(u -> u.getProfil().getIntrest().equals(Intrest.mecanic)).collect(Collectors.toList());
            listelec = allusers.stream().filter(u -> u.getProfil().getIntrest().equals(Intrest.electric)).collect(Collectors.toList());
            listmulti = allusers.stream().filter(u -> u.getProfil().getIntrest().equals(Intrest.multimedia)).collect(Collectors.toList());
            listinnov = allusers.stream().filter(u -> u.getProfil().getIntrest().equals(Intrest.innovation)).collect(Collectors.toList());
            List<Team> listeamit = new ArrayList<>();
            int it = 0;
            if (listit.size() % 4 == 0) {
                for (int i = 0; i < (listit.size()) / 4; i++) {
//creation de team,  selon le nbre de  user /5
                    Team t = new Team();
                    t.setName("teamit" + (i + 1));
                    t.setBudget(500.0);
                    teamRepository.save(t);
                    listeamit.add(t);
                    for (int j = 0; j < 4; j++) {
                        User u = listit.get(i * 4 + j);
                        //u.setTeam(t;
                        u.setTeam(listeamit.get(i));
                        userRepository.save(u);
                        listit.get(i * 4).getProfil().setTeamcaptain(true);
                    }
                }


            }
            else if (listit.size() % 4 == 3) {
                for (int j = 0; j < (listit.size() - 3) / 4; j++) {
                    Team t = new Team();
                    t.setName("teamit" + (j + 1));
                    t.setBudget(500.0);
                    teamRepository.save(t);
                    listeamit.add(t);
                    it++;
                    for (int x = 0; x < 4; x++) {
                        User u = listit.get(j * 4 + x);
                        u.setTeam(listeamit.get(j));
                        userRepository.save(u);
                        listit.get(j * 4).getProfil().setTeamcaptain(true);
                    }
                }
                Team t = new Team();
                t.setName("teamit" + (it + 1));
                t.setBudget(500.0);
                teamRepository.save(t);
                listeamit.add(t);
                List<User> listwithoutteam = listit.subList(listit.size() - 3, listit.size());
                for (int i = 0; i < 3; i++) {
                    User u = listwithoutteam.get(i);
                    u.setTeam(listeamit.get(it));
                    userRepository.save(u);
                }
            }
            else if (listit.size() == 6) {
                for (int i = 0; i < (listit.size()) / 3; i++) {
                    Team t = new Team();
                    t.setName("teamit" + (i + 1));
                    t.setBudget(500.0);
                    teamRepository.save(t);
                    listeamit.add(t);

                    // i0 1
                    //j 0 ,1,2
                    //0,1,2, 3,4,5
                    for (int j = 0; j < 3; j++) {
                        User u = listit.get(i * 3 + j);
                        u.setTeam(listeamit.get(i));
                        userRepository.save(u);
                        //listit.get(i * 4).getProfil().setTeamcaptain(true);
                    }
                }

            }
            else if (listit.size() % 4 == 1) {
                for (int j = 0; j < (listit.size() - 1) / 4; j++) {
                    Team t = new Team();
                    t.setName("teamit" + (j + 1));
                    t.setBudget(500.0);
                    teamRepository.save(t);
                    listeamit.add(t);
                    it++;
                    for (int x = 0; x < 4; x++) {
                        User u = listit.get(j * 4 + x);
                        u.setTeam(listeamit.get(j));
                        userRepository.save(u);
                        listit.get(j * 4).getProfil().setTeamcaptain(true);
                    }
                }
                List<User> listwithoutteam = listit.subList(listit.size() - 1, listit.size());
                for (int i = 0; i < 1; i++) {
                    User u = listwithoutteam.get(i);
                    u.setTeam(listeamit.get(0));
                    userRepository.save(u);
                }
            }
// 5 ; 2, 1//52
            for (Team t : listeamit) {

                t.setEvent(appEventRepository.findAll().get(0));
                teamRepository.save(t);
            }

            //return listeamit;

        }
    }

}




