package tn.esprit.spring.services;
import javax.mail.MessagingException;

public interface EmailSender {
    public void send(String to, String subj,String msg) throws MessagingException ;
}
