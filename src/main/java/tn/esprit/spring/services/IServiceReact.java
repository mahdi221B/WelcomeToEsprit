package tn.esprit.spring.services;

import tn.esprit.spring.entity.React;

import java.io.IOException;
import java.util.List;

public interface IServiceReact {
    public List<React> retrieveAllReacts();
    public List<React> retrieveAllPostReacts(Integer postId);
    public void deleteReact(Integer id);
    public React retrieveReactById(Integer id);
    public React retrieveReactByUserIdAndPostId(Integer userId, Integer postId);
    public String userReactions(Integer userId, Integer postId);
    public React addOrUpdateAndAssignReactToPost(String reactSTRING, Integer idUser, Integer idPost) ;
}