package tn.esprit.spring.services;

import tn.esprit.spring.entity.Hashtag;

import java.util.List;

public interface IServiceHashtag {
    public void updateHashtags(String content);
    public List<Hashtag> getTopHashtags(int limit);
}