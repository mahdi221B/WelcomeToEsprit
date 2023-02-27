package tn.esprit.spring.services;

import tn.esprit.spring.entity.Comment;
import tn.esprit.spring.entity.Post;
import tn.esprit.spring.entity.User;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

public interface IServiceComment {
    public List<Comment> retrieveAllComments();
    public void deleteComment(Integer id);
    public Comment retrieveCommentById(Integer id);
    public Comment updateComment(Comment comment,Integer id);
    public Comment assignCommentToPost(Comment comment, Integer idPost,Integer idUser) throws IOException;
    public List<Comment> retrieveCommentsByUserId(Integer idUser);
    public List<Post> getRecommendedPosts(Integer userId, Duration timeFilter);
}