package tn.esprit.spring.controllers;

//import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.entity.VerificationToken;
import tn.esprit.spring.repositories.UserRepository;
import tn.esprit.spring.repositories.VerificationTokenRepository;
import tn.esprit.spring.services.UserService;
import tn.esprit.spring.services.VerificationTokenService;

import java.sql.Timestamp;

import static tn.esprit.spring.utils.Constants.APP_ROOT;

@RestController
@Api
public class AccountController {
    private UserService userService;

    private UserRepository userRepository;

    private VerificationTokenService tokenService;
    private final VerificationTokenRepository verificationTokenRepository;

    @Autowired
    public AccountController(UserService userService,
                             VerificationTokenService tokenService,
                             VerificationTokenRepository verificationTokenRepository,
                             UserRepository userRepository)
    {
        this.userService = userService;
        this.tokenService=tokenService;
        this.verificationTokenRepository = verificationTokenRepository;
        this.userRepository=userRepository;
    }

    @PostMapping(APP_ROOT + "/register")
    public  String save(@RequestBody User user, RedirectAttributes ra) throws javax.mail.MessagingException {
        ra.addFlashAttribute("message",
                "Success! A verification email has been sent to your email address.");
        userService.addUser(user);
        return "redirect:/login";
    }
    @GetMapping(APP_ROOT + "/activation")
    public String activation(@RequestParam("token")String token, Model model) throws javax.mail.MessagingException {
        //create html page activation

        VerificationToken verificationToken=tokenService.findByToken(token);
        if(verificationToken==null){
            model.addAttribute("message","Your verification token is invalid");
        }else {
            User user=verificationToken.getUser();
            //if the user account is not actived
            if (!user.isActive()){
                //get the current timestamp
                Timestamp currentTimestamp=new Timestamp(System.currentTimeMillis());
                //check if the token is expired
                if (verificationToken.getExpiryDate().before(currentTimestamp)){
                    model.addAttribute("message","Your verification token has expired");
                }else {
                    //the token is valid
                    //active the user account
                    user.setActive(true);
                    userRepository.save(user);
                    model.addAttribute("message","your account is already activated");
                }
            }
        }
        // add '/activation' to securityConfiguration
        return "activation";
    }
}
