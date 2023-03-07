package tn.esprit.spring.services;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entity.Note;
import tn.esprit.spring.entity.Project;
import tn.esprit.spring.entity.Team;
import tn.esprit.spring.repositories.AppEventRepository;
import tn.esprit.spring.repositories.NoteRepository;
import tn.esprit.spring.repositories.ProjectRepository;
import com.google.zxing.WriterException;
import tn.esprit.spring.repositories.TeamRepository;
import com.twilio.Twilio;



import java.io.IOException;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
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
    public void affectnote(Note n, Long id)  {
        if (new Date().before(appEventRepository.findAll().get(0).getStartDate()) || (new Date().after(appEventRepository.findAll().get(0).getEndDate()) ))
        {System.out.println ("you can't asign a  mark now ");}

        else {
        Project p = projectRepository.findById(id).get();
        p.getNotes().add(n);
        n.setProject(p);
        noteRepository.save(n);
        projectRepository.save(p);
        }
    }

    @Override

    public void sendSmsvalide() {
         String ACCOUNT_SID = "ACff096b193c1c973816cf724a9c445180";
          String AUTH_TOKEN = "1a05dcecbf89f071055d9ea6131946c7";
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        List<Team> t =teamRepository.findAll().stream().sorted(Comparator.comparing(Team::getNoteTeam , Comparator.reverseOrder())) .collect(Collectors.toList());
        Team t1 = t.get(0);
        Message msg = Message.creator(new PhoneNumber("+21654583665"),new PhoneNumber("+18654247150"),("congrats  for  "+ t1.getName()+" for being the best team with NOTE : "+ t1.getNoteTeam()+"" )).create();

    }
    @Override
    public byte[] genrerateqr() throws IOException, WriterException {
       List<Team> t =teamRepository.findAll().stream().sorted(Comparator.comparing(Team::getNoteTeam , Comparator.reverseOrder())) .collect(Collectors.toList());
       Team t1 = t.get(0);


        return qrCodeGenerator. generateQRCodeImage( "congrats  for  "+ t1.getName()+" for being the best team with NOTE : "+ t1.getNoteTeam()+"" ,255,255);
    }

// cond team .proejt = null => creat null project, note 0





}
