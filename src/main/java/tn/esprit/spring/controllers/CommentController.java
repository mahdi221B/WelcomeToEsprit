package tn.esprit.spring.controllers;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entity.Comment;
import tn.esprit.spring.entity.Post;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.services.IServiceComment;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/comment")
public class CommentController {
    private final IServiceComment iserviceComment;

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public void deleteComment(@PathVariable("id") Integer id){
        iserviceComment.deleteComment(id);
    }
    @PutMapping("/update/{id}")
    @ResponseBody
    public Comment updateComment(@RequestBody Comment post, @PathVariable("id") Integer id){
        return iserviceComment.updateComment(post,id);
    }

    @GetMapping("/retrieveCommentsByUserId/{idUser}")
    @ResponseBody
    public List<Comment> retrieveCommentsByUserId(@PathVariable("idUser") Integer idUser){
        return iserviceComment.retrieveCommentsByUserId(idUser);
    }
    @GetMapping("/retrieveCommentsByPostId/{idPost}")
    @ResponseBody
    public List<Comment> retrieveCommentsByPostId(@PathVariable("idPost") Integer idPost){
        return iserviceComment.retrieveCommentsByPostId(idPost);
    }
    @GetMapping("/get/{id}")
    @ResponseBody
    public Comment getCommentById(@PathVariable("id") Integer id){
        return iserviceComment.retrieveCommentById(id);
    }
    @GetMapping("/getRecommendedPosts/{userId}/{timeFilter}")
    @ResponseBody
    public List<Post> getRecommendedPosts(@PathVariable("userId") Integer userId, @PathVariable("timeFilter") String timeFilter) {
        Duration duration = Duration.parse(timeFilter);
        return iserviceComment.getRecommendedPosts(userId, duration);
    }

    @GetMapping("/getall")
    @ResponseBody
    public List<Comment> getAllComment(){
        return iserviceComment.retrieveAllComments();
    }
    @PutMapping("/assignCommentToPost/{idPost}/{idUser}")
    @ResponseBody
    public Comment assignCommentToPost(@RequestBody Comment comment, @PathVariable("idPost") Integer idPost, @PathVariable("idUser") Integer idUser) throws IOException {
        return iserviceComment.assignCommentToPost(comment,idPost,idUser);
    }
}