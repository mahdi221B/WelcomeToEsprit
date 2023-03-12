package tn.esprit.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import tn.esprit.spring.entity.Notification;

@Controller
public class NotificationController {
    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;
    @MessageMapping("/application")
    @SendTo("/all/messages")
    public Notification send(final Notification notification){
        return notification;
    }
    @MessageMapping("/private")
    public void sendToSpecefic(@Payload Notification notification){
        simpMessagingTemplate.convertAndSendToUser(notification.getTo(),"/specific",notification);
    }
    @MessageMapping("/notification")
    public void sendNotification(@Payload Notification notification){
        Notification studentNotification=new Notification();
        studentNotification.setText("New notification for students: "+notification.getText());
        studentNotification.setTo("student");

        Notification teacherNotification=new Notification();
        teacherNotification.setText("New notification for teacher: "+notification.getText());
        teacherNotification.setTo("teacher");

        simpMessagingTemplate.convertAndSendToUser(studentNotification.getTo(),"/specific",studentNotification);
        simpMessagingTemplate.convertAndSendToUser(teacherNotification.getTo(),"/specific",teacherNotification);

    }
}
