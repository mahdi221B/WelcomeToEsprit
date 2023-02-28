package tn.esprit.spring.services;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entity.Conversation;
import tn.esprit.spring.entity.Message;
import tn.esprit.spring.repositories.ConversationRepository;
import tn.esprit.spring.repositories.MessageRepository;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class ServiceMessageImp implements IServiceMessage{
    private final MessageRepository messageRepository;
    private final ConversationRepository conversationRepository;

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

    @Scheduled(cron = "0 0 0 * * ?")
    //@Scheduled(fixedDelay = 5000)
    public void deleteOldRecords() {
        Date oneYearAgo =  Date.from(LocalDate.now().minusYears(1).atStartOfDay(ZoneId.systemDefault()).toInstant());
        List<Message> recordsToDelete = messageRepository.findMessageByDeletedIAndCreatedAt(oneYearAgo);
        System.out.println(recordsToDelete.size());
        messageRepository.deleteAll(recordsToDelete);
    }
}
