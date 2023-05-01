package tn.esprit.spring.controllers;

import lombok.AllArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.spring.entity.*;
import tn.esprit.spring.repositories.ConversationRepository;
import tn.esprit.spring.repositories.MessageRepository;
import tn.esprit.spring.repositories.UserRepository;
import tn.esprit.spring.services.IServiceMessage;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Controller
@AllArgsConstructor
public class WebSocketController {

    private final SimpMessagingTemplate simpMessagingTemplate;
    private final IServiceMessage iServiceMessage;

    //When a message is sent to /resume, the chat method will be invoked on the server side.
    //the chat method returns a string message, which is sent to the destination /  start/initial

    @MessageMapping("/resume/{to}")
    public void sendMessage(@DestinationVariable String to , Message message) {
        message.setCreatedAt(LocalDateTime.now());
        iServiceMessage.sendMessageToConversatiob(message,Integer.parseInt(to),message.getSender().getId());
        simpMessagingTemplate.convertAndSend("/start/initial/" + to, message);
    }





}