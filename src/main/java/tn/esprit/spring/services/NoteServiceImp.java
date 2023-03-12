package tn.esprit.spring.services;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entity.*;
import tn.esprit.spring.repositories.*;
import com.google.zxing.WriterException;
import com.twilio.Twilio;


import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class NoteServiceImp implements  NoteService {

    @Autowired
    NoteRepository noteRepository;
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    QRCodeGenerator qrCodeGenerator ;
    @Autowired
    AppEventRepository appEventRepository;


    @Autowired
    TeamRepository teamRepository;
    @Autowired
    UserRepository userRepository ;

    @Autowired
    RoleRepository roleRepository ;

    @Override
    public List<Note> RetrieveAllNote() {
        return noteRepository.findAll()  ;

    }

    @Override
    public void DeleteNote(Long id) {
        noteRepository.delete(noteRepository.findById(id).get());

    }

    @Override
    public Note RetrieveNoteById(Long id) {
        return  noteRepository.findById(id).get();
    }

    @Override
    public void AddNote(Note note) {

             noteRepository.save(note);


    }

    @Override
    public Note UpdateNote(Note note, Long id) {
        note.setId(id);
        return noteRepository.save(note);
    }



    @Override
    public String affectnote(Note n, Long id, int iduser) {
        User prof = userRepository.findById(iduser).get();
        List<Role>  userrole = prof.getRoles().stream().filter(r->r.getRoleName().equals("jury")).collect(Collectors.toList());
String msg = null;
        if (new Date().before(appEventRepository.findAll().get(0).getStartDate()) || (new Date().after(appEventRepository.findAll().get(0).getEndDate()))) {
          msg=("you can't assign a  mark now ");
        } else  if (userrole.isEmpty() ) {msg =  "you are not allowed  to give a  mark ,  only jury can assign marks";}
        else  {
             Project ptest =projectRepository.findById(id).get();

            for (Note note :ptest.getNotes())
            {
                if (note.getUser().getId()==iduser){
                    msg = "this user has already assigned a note to the project";
                    return  msg;
                }
            }
                Project p = projectRepository.findById(id).get();
                p.getNotes().add(n);
                n.setUser(userRepository.findById(iduser).get());
                n.setProject(p);
                noteRepository.save(n);
                projectRepository.save(p);
                msg = "note asgined with sucess ";
            }
        return msg ;
    }



    public void sendSmsvalide() {
         String ACCOUNT_SID = "ACff096b193c1c973816cf724a9c445180";
          String AUTH_TOKEN = "1a05dcecbf89f071055d9ea6131946c7";
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        List<Team> it = teamRepository.findAll().stream().filter(u -> u.getDepartment().equals(Department.it)).sorted(Comparator.comparing(Team::getNoteTeam, Comparator.reverseOrder())).collect(Collectors.toList());
        List<Team> meca = teamRepository.findAll().stream().filter(u -> u.getDepartment().equals(Department.mecanic)).sorted(Comparator.comparing(Team::getNoteTeam, Comparator.reverseOrder())).collect(Collectors.toList());
        List<Team> elec = teamRepository.findAll().stream().filter(u -> u.getDepartment().equals(Department.electric)).sorted(Comparator.comparing(Team::getNoteTeam, Comparator.reverseOrder())).collect(Collectors.toList());
        List<Team> multi = teamRepository.findAll().stream().filter(u -> u.getDepartment().equals(Department.multimedia)).sorted(Comparator.comparing(Team::getNoteTeam, Comparator.reverseOrder())).collect(Collectors.toList());

        Team t1 = it.get(0);
        Team t2 = meca.get(0);
        Team t3 = elec.get(0);
        Team t4 = multi.get(0);

        Message msg = Message.creator(new PhoneNumber("+21654583665"),new PhoneNumber("+18654247150"),("congrats  to  teams "+ t1.getName() +" "+ t2.getName() + " "+  t3.getName() +" "+ t4.getName()  + " for being the best team with NOTE : "+ t1.getNoteTeam() +" "+ t2.getNoteTeam()  +" "+ t3.getNoteTeam()  +" "+ t4.getNoteTeam()   +"")).create();

    }
    @Override
    public byte[] genrerateqrit() throws IOException, WriterException {
        String msg = null;

            List<Team> it = teamRepository.findAll().stream().filter(u -> u.getDepartment().equals(Department.it)).sorted(Comparator.comparing(Team::getNoteTeam, Comparator.reverseOrder())).collect(Collectors.toList());
            List<Team> meca = teamRepository.findAll().stream().filter(u -> u.getDepartment().equals(Department.mecanic)).sorted(Comparator.comparing(Team::getNoteTeam, Comparator.reverseOrder())).collect(Collectors.toList());
            List<Team> elec = teamRepository.findAll().stream().filter(u -> u.getDepartment().equals(Department.electric)).sorted(Comparator.comparing(Team::getNoteTeam, Comparator.reverseOrder())).collect(Collectors.toList());
            List<Team> multi = teamRepository.findAll().stream().filter(u -> u.getDepartment().equals(Department.multimedia)).sorted(Comparator.comparing(Team::getNoteTeam, Comparator.reverseOrder())).collect(Collectors.toList());

            Team t1 = it.get(0);
            Team t2 = meca.get(0);
            Team t3 = elec.get(0);
            Team t4 = multi.get(0);

        return qrCodeGenerator. generateQRCodeImage( "congrats  to  teams "+ t1.getName() +" "+ t2.getName() + " "+  t3.getName() +" "+ t4.getName()  + " for being the best team with NOTE : "+ t1.getNoteTeam() +" "+ t2.getNoteTeam()  +" "+ t3.getNoteTeam()  +" "+ t4.getNoteTeam()   +"" ,255,255);
    }

    @Override
    public String statistics(int filter, Department dept) {

        String msg = null;
      //  if (new Date().before(appEventRepository.findAll().get(0).getStartDate()) || (new Date().after(appEventRepository.findAll().get(0).getEndDate()))) {
        //    msg=("you can" );
        //}
        //else {

            List<Team> liste = teamRepository.findAll().stream()
                    .filter(u -> u.getDepartment().equals(dept))
                    .collect(Collectors.toList());
            Double avrageit = liste.stream().mapToDouble(Team::getNoteTeam).average().getAsDouble();
            int count = (int) liste.stream().filter(u -> u.getNoteTeam() > filter).count();
            msg = "The average of " + dept + " teams marks is " + avrageit + "\r" +
                    "The number of " + dept + " teams which marks is greater than " + filter + " is " + count + "";
       // }

            return msg;
    }
}
