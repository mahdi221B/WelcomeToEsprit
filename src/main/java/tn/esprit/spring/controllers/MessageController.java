package tn.esprit.spring.controllers;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entity.Message;
import tn.esprit.spring.entity.Post;
import tn.esprit.spring.services.IServiceMessage;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/message")
public class MessageController {
    private final IServiceMessage iServiceMessage;
    @DeleteMapping("/delete/{id}")
    public void deletePost(@PathVariable("id") Integer id){
        iServiceMessage.deleteMessage(id);
    }
    @GetMapping("/getall")
    @ResponseBody
    public List<Message> getAllMessage(){
        return iServiceMessage.retrieveAllMessages();
    }
    @PostMapping("/add/{id}")
    public void add(@RequestBody Message message, @PathVariable("id") Integer id){
        iServiceMessage.simpleAdd(message,id);
    }
    @PostMapping("/affectUserToConversation/{userId}/{conversationId}")
    public void affectUserToConversation(@PathVariable("userId") Integer userId, @PathVariable("conversationId") Integer conversationId){
        iServiceMessage.affectUserToConversation(userId,conversationId);
    }

    @PostMapping("/affectUsersToConversation/{usersids}/{conversationId}")
    public void affectUsersToConversation(@PathVariable("usersids")List<Integer> usersids, @PathVariable("conversationId") Integer conversationId) {
        iServiceMessage.affectUsersToConversation(usersids, conversationId);
    }
    @PostMapping("/sendMessageToConversation/{conversationId}/{userId}")
    public void sendMessageToConversation(@RequestBody Message message, @PathVariable("conversationId") Integer conversationId, @PathVariable("userId") Integer userId) {
        iServiceMessage.sendMessageToConversatiob(message, conversationId,userId);
    }
    @GetMapping("/getMessagesByConversationIdAndUserId/{conversationId}/{userId}")
    public List<Message> getMessagesByConversationIdAndUserId(@PathVariable Integer conversationId, @PathVariable Integer userId) {
        return iServiceMessage.getMessagesByUserAndConversationId(conversationId, userId);
    }
    @GetMapping("/getMessagesByOtherUsersAndConversationId/{conversationId}/{userId}")
    public List<Message> getMessagesByOtherUsersAndConversationId(@PathVariable Integer conversationId, @PathVariable Integer userId) {
        return iServiceMessage.getMessagesByOtherUsersAndConversationId(conversationId, userId);
    }

}