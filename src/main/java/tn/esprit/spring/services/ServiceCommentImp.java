package tn.esprit.spring.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entity.Comment;
import tn.esprit.spring.entity.Post;
import tn.esprit.spring.repositories.CommentRepository;
import tn.esprit.spring.repositories.PostRepository;

import javax.transaction.Transactional;
import java.util.List;
@Slf4j
@Service
@AllArgsConstructor
public class ServiceCommentImp implements IServiceComment{
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    @Override
    public List<Comment> retrieveAllComments() {
        return commentRepository.findAll();
    }

    @Override
    public void deleteComment(Integer id) {
        commentRepository.delete(commentRepository.findById(id).get());
    }

    @Override
    public Comment retrieveCommentById(Integer id) {
        return commentRepository.findById(id).get();
    }

    @Override
    public Comment addComment(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public Comment updateComment(Comment comment, Integer id) {
        comment.setId(id);
        return commentRepository.save(comment);
    }

    @Transactional
    public void assignCommentToPost(Comment comment, Integer id) {
        commentRepository.save(comment);
        comment.setPost(postRepository.findById(id).get());
    }
}