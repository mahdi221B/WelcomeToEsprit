package tn.esprit.spring.controllers;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entity.Post;
import tn.esprit.spring.services.IServicePost;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/post")
@Api
public class PostController {
    private final IServicePost iServicePost;
    @PostMapping("/add")
    @ResponseBody
    public Post addPost(@RequestBody Post post){
        return iServicePost.addPost(post);
    }
    @PutMapping("/update/{id}")
    @ResponseBody
    public Post updatePost(@RequestBody Post post, @PathVariable("id") Integer id){
        return iServicePost.updatePost(post,id);
    }
    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public void deletePost(@PathVariable("id") Integer id){
        iServicePost.deletePost(id);
    }
    @GetMapping("/get/{id}")
    @ResponseBody
    public Post getPostById(@PathVariable("id") Integer id){
        return iServicePost.retrievePostById(id);
    }
    @GetMapping("/getall")
    @ResponseBody
    public List<Post> getAllPost(){
        return iServicePost.retrieveAllPosts();
    }
}