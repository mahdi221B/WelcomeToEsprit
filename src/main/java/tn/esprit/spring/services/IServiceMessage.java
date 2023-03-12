package tn.esprit.spring.services;

import tn.esprit.spring.entity.Conversation;
import tn.esprit.spring.entity.Message;

import java.util.List;

public interface IServiceMessage {
    public List<Message> retrieveAllMessages();
    public void deleteMessage(Integer id);
    public void simpleAdd(Message message,Integer id);
    public Conversation createOrGetConversation(Integer user1Id, Integer user2Id);
    }
