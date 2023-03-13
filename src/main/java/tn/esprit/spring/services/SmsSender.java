package tn.esprit.spring.services;

public interface SmsSender {
    public void sendSms(String to, String message);
}
