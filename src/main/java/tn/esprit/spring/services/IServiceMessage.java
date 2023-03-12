package tn.esprit.spring.services;

import tn.esprit.spring.entity.Conversation;
import tn.esprit.spring.entity.Message;

import java.util.List;

public interface IServiceMessage {
    public List<Message> retrieveAllMessages();
    public void deleteMessage(Integer id);
    public void simpleAdd(Message message,Integer id);
    public void affectUserToConversation(Integer userId, Integer conversationId);
    public void affectUsersToConversation(List<Integer> usersids,Integer conversationId);
    public void sendMessageToConversatiob(Message message, Integer conversationId);
    public List<Message> getMessagesByUserAndConversationId(Integer userId, Integer conversationId);
    public List<Message> getMessagesByOtherUsersAndConversationId(Integer userId, Integer conversationId);

}