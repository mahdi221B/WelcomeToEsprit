package tn.esprit.spring.controllers;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entity.Comment;
import tn.esprit.spring.services.IServiceComment;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/comment")
@Api
public class CommentController {
    private final IServiceComment iserviceComment;
    @PostMapping("/add")
    @ResponseBody
    public Comment addComment(@RequestBody Comment comment){
        return iserviceComment.addComment(comment);
    }
    @PutMapping("/update/{id}")
    @ResponseBody
    public Comment updateComment(@RequestBody Comment post, @PathVariable("id") Integer id){
        return iserviceComment.updateComment(post,id);
    }
    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public void deleteComment(@PathVariable("id") Integer id){
        iserviceComment.deleteComment(id);
    }
    @GetMapping("/get/{id}")
    @ResponseBody
    public Comment getCommentById(@PathVariable("id") Integer id){
        return iserviceComment.retrieveCommentById(id);
    }
    @GetMapping("/getall")
    @ResponseBody
    public List<Comment> getAllComment(){
        return iserviceComment.retrieveAllComments();
    }
    @PutMapping("/assignCommentToPost/{id}")
    @ResponseBody
    public void assignCommentToPost(@RequestBody Comment comment, @PathVariable("id") Integer id){
        iserviceComment.assignCommentToPost(comment,id);
    }
}
