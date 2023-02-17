package tn.esprit.spring.services;

import tn.esprit.spring.entity.Comment;

import java.util.List;

public interface IServiceComment {
    public List<Comment> retrieveAllComments();
    public void deleteComment(Integer id);
    public Comment retrieveCommentById(Integer id);
    public Comment addComment(Comment comment);
    public Comment updateComment(Comment comment,Integer id);
    public void assignCommentToPost(Comment comment,Integer id);
}
