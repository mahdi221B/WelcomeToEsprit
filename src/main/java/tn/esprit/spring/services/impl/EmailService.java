package tn.esprit.spring.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.entity.VerificationToken;
import tn.esprit.spring.services.VerificationTokenService;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService {

    private final VerificationTokenService service;
    private final TemplateEngine templateEngine;
    private final JavaMailSender javaMailSender;
    @Autowired
    public EmailService(VerificationTokenService service,
                        TemplateEngine templateEngine,
                        JavaMailSender javaMailSender)
    {
        this.service = service;
        this.templateEngine=templateEngine;
        this.javaMailSender=javaMailSender;
    }

    public void sendHtmlMail(User user) throws MessagingException {
        VerificationToken verificationToken=service.findByUser(user);
        //check if the user has token
        if (verificationToken!=null){
            String token=verificationToken.getToken();
            Context context=new Context();
            context.setVariable("title","Verify your email address");
            context.setVariable("link","http://localhost:8091/WelcomeToEsprit/v1/activation?token="+token);
            //create an HTML template and pass the variables to it
            String body=templateEngine.process("verification",context);

            //send the verification email
            MimeMessage message=javaMailSender.createMimeMessage();
            MimeMessageHelper helper=new MimeMessageHelper(message,true);
            helper.setTo(user.getEmailAddress());
            helper.setSubject("email address verification ");
            helper.setText(body,true);
            javaMailSender.send(message);
        }
    }
}
