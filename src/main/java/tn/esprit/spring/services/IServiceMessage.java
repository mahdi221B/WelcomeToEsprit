package tn.esprit.spring.services;

import tn.esprit.spring.entity.Message;

import java.util.List;

public interface IServiceMessage {
    public List<Message> retrieveAllMessages();
    public void deleteMessage(Integer id);
    public void simpleAdd(Message message,Integer id);
}
