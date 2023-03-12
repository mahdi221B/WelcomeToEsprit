package tn.esprit.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entity.*;
import tn.esprit.spring.repositories.AppEventRepository;
import tn.esprit.spring.repositories.RoleRepository;
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

    @Autowired
    RoleRepository roleRepository;


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
public String assignteacheertojury(int id) {
        String msg;
        User u =userRepository.findById(id).get() ;
    List<Role>  userrole = u.getRoles().stream().filter(r->r.getRoleName().equals("teacher")).collect(Collectors.toList());
        if (userrole.isEmpty() )
    {
            msg="only teachers can be a jury member ";
        }
        else{
            Role jury = roleRepository.findRoleByRoleNameContains("jury");
            u.getRoles().add(jury);
            userRepository.save(u);
            msg =" teacher  become a jury member ";
        }

    return msg;
}




    @Override
    public String affectuserstoteam() {
        String msg = null;
        if ((new Date().before(appEventRepository.findAll().get(0).getStartDate())))
        {
            msg = "event didn't started yet";
        }
        else {
            List<User> allusers = userRepository.findAllByRolesRoleNameContains("student");
            List<User> listit = new ArrayList<>();
            List<User> listmeca = new ArrayList<>();
            List<User> listelec = new ArrayList<>();
            List<User> listmulti = new ArrayList<>();
            listit = allusers.stream().filter(u -> u.getProfil().getIntrest().equals(Intrest.it)).collect(Collectors.toList());
            listmeca = allusers.stream().filter(u -> u.getProfil().getIntrest().equals(Intrest.mecanic)).collect(Collectors.toList());
            listelec = allusers.stream().filter(u -> u.getProfil().getIntrest().equals(Intrest.electric)).collect(Collectors.toList());
            listmulti = allusers.stream().filter(u -> u.getProfil().getIntrest().equals(Intrest.multimedia)).collect(Collectors.toList());
            List<Team> listeamit = new ArrayList<>();
            int it = 0;
            if (listit.size() % 4 == 0) {
                for (int i = 0; i < (listit.size()) / 4; i++) {
                    Team t = new Team();
                    t.setName("teamit" + (i + 1));
                    t.setBudget(500.0);
                    t.setDepartment(Department.it);

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
                    t.setDepartment(Department.it);
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
                t.setDepartment(Department.it);
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
                    t.setDepartment(Department.it);
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
                    t.setDepartment(Department.it);

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
            else if (listit.size() % 4 == 2) {
                for (int j = 0; j < (listit.size() - 2) / 4; j++) {
                    Team t = new Team();
                    t.setName("teamit" + (j + 1));
                    t.setBudget(500.0);
                    t.setDepartment(Department.it);

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
                List<User> listwithoutteam = listit.subList(listit.size() - 2, listit.size());
                for (int i = 0; i < 1; i++) {
                    User u = listwithoutteam.get(i);
                    u.setTeam(listeamit.get(i));
                    userRepository.save(u);
                }
            }

            if(listeamit.size()!=0){
            for (Team t : listeamit) {
                t.setEvent(appEventRepository.findAll().get(0));
                teamRepository.save(t);
            }}
            List<Team> listeammeca = new ArrayList<>();
            int meca = 0;
            if (listmeca.size() % 4 == 0) {
                for (int i = 0; i < (listmeca.size()) / 4; i++) {
                    Team t = new Team();
                    t.setName("teammeca" + (i + 1));
                    t.setBudget(500.0);
                    t.setDepartment(Department.mecanic);

                    teamRepository.save(t);
                    listeammeca.add(t);
                    for (int j = 0; j < 4; j++) {
                        User u = listmeca.get(i * 4 + j);
                        //u.setTeam(t;
                        u.setTeam(listeammeca.get(i));
                        userRepository.save(u);
                        listmeca.get(i * 4).getProfil().setTeamcaptain(true);
                    }
                }


            }
            else if (listmeca.size() % 4 == 3) {
                for (int j = 0; j < (listmeca.size() - 3) / 4; j++) {
                    Team t = new Team();
                    t.setName("teammeca" + (j + 1));
                    t.setBudget(500.0);
                    t.setDepartment(Department.mecanic);
                    teamRepository.save(t);
                    listeammeca.add(t);
                    meca++;
                    for (int x = 0; x < 4; x++) {
                        User u = listmeca.get(j * 4 + x);
                        u.setTeam(listeammeca.get(j));

                        userRepository.save(u);
                        listmeca.get(j * 4).getProfil().setTeamcaptain(true);
                    }
                }
                Team t = new Team();
                t.setName("teammeca" + (meca + 1));
                t.setBudget(500.0);
                t.setDepartment(Department.mecanic);
                teamRepository.save(t);
                listeammeca.add(t);
                List<User> listwithoutteam = listmeca.subList(listmeca.size() - 3, listmeca.size());
                for (int i = 0; i < 3; i++) {
                    User u = listwithoutteam.get(i);
                    u.setTeam(listeammeca.get(meca));
                    userRepository.save(u);
                }
            }
            else if (listmeca.size() == 6) {
                for (int i = 0; i < (listmeca.size()) / 3; i++) {
                    Team t = new Team();
                    t.setName("teamemca" + (i + 1));
                    t.setBudget(500.0);
                    t.setDepartment(Department.mecanic);

                    teamRepository.save(t);
                    listeammeca.add(t);

                    // i0 1
                    //j 0 ,1,2
                    //0,1,2, 3,4,5
                    for (int j = 0; j < 3; j++) {
                        User u = listmeca.get(i * 3 + j);
                        u.setTeam(listeammeca.get(i));
                        userRepository.save(u);
                        //listit.get(i * 4).getProfil().setTeamcaptain(true);
                    }
                }

            }
            else if (listmeca.size() % 4 == 1) {
                for (int j = 0; j < (listmeca.size() - 1) / 4; j++) {
                    Team t = new Team();
                    t.setName("teammeca" + (j + 1));
                    t.setBudget(500.0);
                    t.setDepartment(Department.mecanic);

                    teamRepository.save(t);
                    listeammeca.add(t);
                    meca++;
                    for (int x = 0; x < 4; x++) {
                        User u = listmeca.get(j * 4 + x);
                        u.setTeam(listeammeca.get(j));
                        userRepository.save(u);
                        listmeca.get(j * 4).getProfil().setTeamcaptain(true);
                    }
                }
                List<User> listwithoutteam = listmeca.subList(listmeca.size() - 1, listmeca.size());
                for (int i = 0; i < 1; i++) {
                    User u = listwithoutteam.get(i);
                    u.setTeam(listeammeca.get(0));
                    userRepository.save(u);
                }
            }
            else if (listmeca.size() % 4 == 2) {
                for (int j = 0; j < (listmeca.size() - 2) / 4; j++) {
                    Team t = new Team();
                    t.setName("teammeca" + (j + 1));
                    t.setBudget(500.0);
                    t.setDepartment(Department.mecanic);

                    teamRepository.save(t);
                    listeammeca.add(t);
                    meca++;
                    for (int x = 0; x < 4; x++) {
                        User u = listmeca.get(j * 4 + x);
                        u.setTeam(listeammeca.get(j));
                        userRepository.save(u);
                        listmeca.get(j * 4).getProfil().setTeamcaptain(true);
                    }
                }
                List<User> listwithoutteam = listmeca.subList(listmeca.size() - 2, listmeca.size());
                for (int i = 0; i < 2; i++) {
                    User u = listwithoutteam.get(i);
                    u.setTeam(listeammeca.get(i));
                    userRepository.save(u);
                }
            }
            if(listeammeca.size()!=0){
            for (Team t : listeammeca) {
                t.setEvent(appEventRepository.findAll().get(0));
                teamRepository.save(t);}
            }
            List<Team> listteamelec= new ArrayList<>();
            int elec = 0;
            if (listelec.size() % 4 == 0) {
                for (int i = 0; i < (listelec.size()) / 4; i++) {
                    Team t = new Team();
                    t.setName("teamelec" + (i + 1));
                    t.setBudget(500.0);
                    t.setDepartment(Department.electric);

                    teamRepository.save(t);
                    listteamelec.add(t);
                    for (int j = 0; j < 4; j++) {
                        User u = listelec.get(i * 4 + j);
                        //u.setTeam(t;
                        u.setTeam(listteamelec.get(i));
                        userRepository.save(u);
                        listelec.get(i * 4).getProfil().setTeamcaptain(true);
                    }
                }


            }
            else if (listelec.size() % 4 == 3) {
                for (int j = 0; j < (listelec.size() - 3) / 4; j++) {
                    Team t = new Team();
                    t.setName("teamelec" + (j + 1));
                    t.setBudget(500.0);
                    t.setDepartment(Department.electric);
                    teamRepository.save(t);
                    listteamelec.add(t);
                    elec++;
                    for (int x = 0; x < 4; x++) {
                        User u = listelec.get(j * 4 + x);
                        u.setTeam(listteamelec.get(j));

                        userRepository.save(u);
                        listelec.get(j * 4).getProfil().setTeamcaptain(true);
                    }
                }
                Team t = new Team();
                t.setName("teammelec" + (elec + 1));
                t.setBudget(500.0);
                t.setDepartment(Department.electric);
                teamRepository.save(t);
                listteamelec.add(t);
                List<User> listwithoutteam = listelec.subList(listelec.size() - 3, listelec.size());
                for (int i = 0; i < 3; i++) {
                    User u = listwithoutteam.get(i);
                    u.setTeam(listteamelec.get(elec));
                    userRepository.save(u);
                }
            }
            else if (listelec.size() == 6) {
                for (int i = 0; i < (listelec.size()) / 3; i++) {
                    Team t = new Team();
                    t.setName("teamelec" + (i + 1));
                    t.setBudget(500.0);
                    t.setDepartment(Department.electric);

                    teamRepository.save(t);
                    listteamelec.add(t);

                    // i0 1
                    //j 0 ,1,2
                    //0,1,2, 3,4,5
                    for (int j = 0; j < 3; j++) {
                        User u = listelec.get(i * 3 + j);
                        u.setTeam(listteamelec.get(i));
                        userRepository.save(u);
                        //listit.get(i * 4).getProfil().setTeamcaptain(true);
                    }
                }

            }
            else if (listelec.size() % 4 == 1) {
                for (int j = 0; j < (listelec.size() - 1) / 4; j++) {
                    Team t = new Team();
                    t.setName("teamelec" + (j + 1));
                    t.setBudget(500.0);
                    t.setDepartment(Department.electric);

                    teamRepository.save(t);
                    listteamelec.add(t);
                    meca++;
                    for (int x = 0; x < 4; x++) {
                        User u = listelec.get(j * 4 + x);
                        u.setTeam(listteamelec.get(j));
                        userRepository.save(u);
                        listelec.get(j * 4).getProfil().setTeamcaptain(true);
                    }
                }
                List<User> listwithoutteam = listelec.subList(listelec.size() - 1, listelec.size());
                for (int i = 0; i < 1; i++) {
                    User u = listwithoutteam.get(i);
                    u.setTeam(listteamelec.get(0));
                    userRepository.save(u);
                }
            }
            else if (listelec.size() % 4 == 2) {
                for (int j = 0; j < (listelec.size() - 2) / 4; j++) {
                    Team t = new Team();
                    t.setName("teamelec" + (j + 1));
                    t.setBudget(500.0);
                    t.setDepartment(Department.electric);

                    teamRepository.save(t);
                    listteamelec.add(t);
                    meca++;
                    for (int x = 0; x < 4; x++) {
                        User u = listelec.get(j * 4 + x);
                        u.setTeam(listteamelec.get(j));
                        userRepository.save(u);
                        listelec.get(j * 4).getProfil().setTeamcaptain(true);
                    }
                }
                List<User> listwithoutteam = listelec.subList(listelec.size() - 2, listelec.size());
                for (int i = 0; i < 1; i++) {
                    User u = listwithoutteam.get(i);
                    u.setTeam(listteamelec.get(i));
                    userRepository.save(u);
                }
            }

            if(listteamelec.size()!=0){
            for (Team t : listteamelec ) {
                t.setEvent(appEventRepository.findAll().get(0));
                teamRepository.save(t);
            }}
            List<Team> listeammulti = new ArrayList<>();
            int multi = 0;
            if (listmulti.size() % 4 == 0) {
                for (int i = 0; i < (listmulti.size()) / 4; i++) {
                    Team t = new Team();
                    t.setName("teammulti" + (i + 1));
                    t.setBudget(500.0);
                    t.setDepartment(Department.multimedia);

                    teamRepository.save(t);
                    listeammulti.add(t);
                    for (int j = 0; j < 4; j++) {
                        User u = listmulti.get(i * 4 + j);
                        //u.setTeam(t;
                        u.setTeam(listeammulti.get(i));
                        userRepository.save(u);
                        listmulti.get(i * 4).getProfil().setTeamcaptain(true);
                    }
                }


            }
            else if (listmulti.size() % 4 == 3) {
                for (int j = 0; j < (listmulti.size() - 3) / 4; j++) {
                    Team t = new Team();
                    t.setName("teammulti" + (j + 1));
                    t.setBudget(500.0);
                    t.setDepartment(Department.multimedia);
                    teamRepository.save(t);
                    listeammulti.add(t);
                    multi++;
                    for (int x = 0; x < 4; x++) {
                        User u = listmulti.get(j * 4 + x);
                        u.setTeam(listeammulti.get(j));

                        userRepository.save(u);
                        listmulti.get(j * 4).getProfil().setTeamcaptain(true);
                    }
                }
                Team t = new Team();
                t.setName("teammulti" + (multi + 1));
                t.setBudget(500.0);
                t.setDepartment(Department.multimedia);
                teamRepository.save(t);
                listeammulti.add(t);
                List<User> listwmultihoutteam = listmulti.subList(listmulti.size() - 3, listmulti.size());
                for (int i = 0; i < 3; i++) {
                    User u = listwmultihoutteam.get(i);
                    u.setTeam(listeammulti.get(multi));
                    userRepository.save(u);
                }
            }
            else if (listmulti.size() == 6) {
                for (int i = 0; i < (listmulti.size()) / 3; i++) {
                    Team t = new Team();
                    t.setName("teammulti" + (i + 1));
                    t.setBudget(500.0);
                    t.setDepartment(Department.multimedia);
                    teamRepository.save(t);
                    listeammulti.add(t);
                    // i0 1
                    //j 0 ,1,2
                    //0,1,2, 3,4,5
                    for (int j = 0; j < 3; j++) {
                        User u = listmulti.get(i * 3 + j);
                        u.setTeam(listeammulti.get(i));
                        userRepository.save(u);
                        //listmulti.get(i * 4).getProfil().setTeamcaptain(true);
                    }
                }

            }
            else if (listmulti.size() % 4 == 1) {
                for (int j = 0; j < (listmulti.size() - 1) / 4; j++) {
                    Team t = new Team();
                    t.setName("teammulti" + (j + 1));
                    t.setBudget(500.0);
                    t.setDepartment(Department.multimedia);

                    teamRepository.save(t);
                    listeammulti.add(t);
                    multi++;
                    for (int x = 0; x < 4; x++) {
                        User u = listmulti.get(j * 4 + x);
                        u.setTeam(listeammulti.get(j));
                        userRepository.save(u);
                        listmulti.get(j * 4).getProfil().setTeamcaptain(true);
                    }
                }
                List<User> listwmultihoutteam = listmulti.subList(listmulti.size() - 1, listmulti.size());
                for (int i = 0; i < 1; i++) {
                    User u = listwmultihoutteam.get(i);
                    u.setTeam(listeammulti.get(0));
                    userRepository.save(u);
                }
            }
            else if (listmulti.size() % 4 == 2) {
                for (int j = 0; j < (listmulti.size() - 2) / 4; j++) {
                    Team t = new Team();
                    t.setName("teammulti" + (j + 1));
                    t.setBudget(500.0);
                    t.setDepartment(Department.multimedia);

                    teamRepository.save(t);
                    listeammulti.add(t);
                    multi++;
                    for (int x = 0; x < 4; x++) {
                        User u = listmulti.get(j * 4 + x);
                        u.setTeam(listeammulti.get(j));
                        userRepository.save(u);
                        listmulti.get(j * 4).getProfil().setTeamcaptain(true);
                    }
                }
                List<User> listwmultihoutteam = listmulti.subList(listmulti.size() - 2, listmulti.size());
                for (int i = 0; i < 1; i++) {
                    User u = listwmultihoutteam.get(i);
                    u.setTeam(listeammulti.get(i));
                    userRepository.save(u);
                }
            }

            if(listeammulti.size()!=0){

                for (Team t : listeammulti) {
                t.setEvent(appEventRepository.findAll().get(0));
                teamRepository.save(t);
            }}
    msg= "teams crated  ";
        }

        return msg;
    }


}




