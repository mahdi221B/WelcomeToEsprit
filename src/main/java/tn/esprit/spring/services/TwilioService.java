package tn.esprit.spring.services;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.stereotype.Service;

@Service
public class TwilioService {
    public void sendSMS(String Code) {
        Twilio.init("ACb029f1b89a78f2693f76a395b02c7427", "14022d95c08fb018355f2c5989bd284d");
        Message.creator(new PhoneNumber("+21692119882"), new PhoneNumber("+15747667644"), (Code)).create();
    }
}