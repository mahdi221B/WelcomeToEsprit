package tn.esprit.spring.services;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entity.Conversation;
import tn.esprit.spring.entity.Message;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.repositories.ConversationRepository;
import tn.esprit.spring.repositories.MessageRepository;
import tn.esprit.spring.repositories.UserRepository;

import javax.transaction.Transactional;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class ServiceMessageImp implements IServiceMessage{
    private final MessageRepository messageRepository;
    private final ConversationRepository conversationRepository;
    private final UserRepository userRepository;

    @Override
    public List<Message> retrieveAllMessages() {
        return messageRepository.findAll();
    }

    @Override
    public void deleteMessage(Integer id) {
        messageRepository.deleteById(id);
    }

    @Override
    public void simpleAdd(Message message, Integer id) {
        messageRepository.save(message);
    }







    @Transactional
    public void affectUserToConversation(Integer userId, Integer conversationId) {
        User user = userRepository.findById(userId).get();
        Conversation conversation = conversationRepository.findById(conversationId).get();
        conversation.getParticipants().add(user);
        conversationRepository.save(conversation);
    }
    @Transactional
    public void affectUsersToConversation(List<Integer> usersids, Integer conversationId) {
        Conversation conversation = conversationRepository.findById(conversationId).get();
        for (Integer i:usersids) {
            User user = userRepository.findById(i).get();
            conversation.getParticipants().add(user);
        }
        conversationRepository.save(conversation);
    }
    @Transactional
    public void sendMessageToConversatiob(Message message, Integer conversationId){
        Conversation conversation = conversationRepository.findById(conversationId).get();
        conversation.getMessages().add(message);
        conversationRepository.save(conversation);
    }
    public List<Message> getMessagesByUserAndConversationId(Integer userId, Integer conversationId){
        Conversation conversation = conversationRepository.findById(conversationId).orElseThrow(() -> new RuntimeException("Conversation not found"));
        List<Message> messages = new ArrayList<>();
        for (Message message : conversation.getMessages()) {
            if (message.getSender().getId().equals(userId) && !message.isDeleted()) {
                messages.add(message);
            }
        }
        return messages;
    }
    public List<Message> getMessagesByOtherUsersAndConversationId(Integer userId, Integer conversationId){
        Conversation conversation = conversationRepository.findById(conversationId).orElseThrow(() -> new RuntimeException("Conversation not found"));
        List<Message> messages = new ArrayList<>();
        for (Message message : conversation.getMessages()) {
            if (!message.getSender().getId().equals(userId) && !message.isDeleted()) {
                messages.add(message);
            }
        }
        return messages;
    }






    @Scheduled(cron = "0 0 0 * * ?")
    //@Scheduled(fixedDelay = 5000)
    public void deleteOldRecords() {
        Date oneYearAgo =  Date.from(LocalDate.now().minusYears(1).atStartOfDay(ZoneId.systemDefault()).toInstant());
        List<Message> recordsToDelete = messageRepository.findMessageByDeletedIAndCreatedAt(oneYearAgo);
        System.out.println(recordsToDelete.size());
        messageRepository.deleteAll(recordsToDelete);
    }
/**
    public Conversation createOrGetConversation(Integer user1Id, Integer user2Id) {
        User user1 = userRepository.findById(user1Id).get();
        User user2 = userRepository.findById(user2Id).get();
        Conversation conversation = conversationRepository.findByParticipants(user1, user2);
        if (conversation == null) {
            conversation = new Conversation();
            conversation.getParticipants().add(user1);
            conversation.getParticipants().add(user2);
            conversationRepository.save(conversation);
        }
        return conversation;
    }**/
}
