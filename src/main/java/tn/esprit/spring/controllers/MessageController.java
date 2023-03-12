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
    @PostMapping("/createOrGetConversation/{user1Id}/{user2Id}")
    public void createOrGetConversation(@PathVariable("user1Id") Integer user1Id, @PathVariable("user2Id") Integer user2Id){
        iServiceMessage.createOrGetConversation(user1Id,user2Id);
    }
}