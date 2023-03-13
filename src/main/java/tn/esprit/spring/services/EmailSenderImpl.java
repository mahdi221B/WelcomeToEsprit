package tn.esprit.spring.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
@Service
public class EmailSenderImpl implements EmailSender{
    @Autowired
    private JavaMailSender emailSender;
    @Override
    public void send(String to, String subj,String msg) throws MessagingException {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("afef.zouaoui@esprit.tn");
        message.setTo(to);
        message.setSubject(subj);
        message.setText(msg);
        emailSender.send(message);
        System.out.println("email sent...");


    }
}
