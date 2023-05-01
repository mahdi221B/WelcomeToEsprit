package tn.esprit.spring.controllers;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entity.React;
import tn.esprit.spring.services.IServiceReact;

import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/react")
public class ReactController {
    private final IServiceReact iserviceReact;
    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public void deleteReact(@PathVariable("id") Integer id){
        iserviceReact.deleteReact(id);
    }
    @GetMapping("/get/{id}")
    @ResponseBody
    public React getReactById(@PathVariable("id") Integer id){
        return iserviceReact.retrieveReactById(id);
    }
    @GetMapping("/getall")
    @ResponseBody
    public List<React> getAllReact(){
        return iserviceReact.retrieveAllReacts();
    }
    @PostMapping("/addOrUpdate/{reactSTRING}/{idUser}/{idPost}")
    @ResponseBody
    public React addOrUpdateAndAssignReactToPost(@PathVariable("reactSTRING") String reactSTRING,@PathVariable("idUser") Integer idUser,@PathVariable("idPost") Integer idPost) {
        return iserviceReact.addOrUpdateAndAssignReactToPost(reactSTRING,idUser,idPost);
    }
    @GetMapping("/ReactByUserIdAndPostId/{userId}/{postId}")
    @ResponseBody
    public React retrieveReactByUserIdAndPostId(@PathVariable("userId") Integer userId,@PathVariable("postId") Integer postId)
    {return iserviceReact.retrieveReactByUserIdAndPostId(userId, postId);}
    @GetMapping("/retrieveAllPostReacts/{postId}")
    @ResponseBody
    public List<React> retrieveAllPostReacts(@PathVariable("postId") Integer postId){
        return iserviceReact.retrieveAllPostReacts(postId);
    }
    @GetMapping("/userReactions/{userId}/{postId}")
    @ResponseBody
    public String userReactions(@PathVariable("userId") Integer userId,@PathVariable("postId") Integer postId){
        return iserviceReact.userReactions(userId, postId);
    }
}