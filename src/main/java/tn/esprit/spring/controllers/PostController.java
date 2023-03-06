package tn.esprit.spring.controllers;
import com.fasterxml.jackson.databind.JsonNode;
import edu.stanford.nlp.simple.Document;
import edu.stanford.nlp.simple.Sentence;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.spring.entity.Post;
import tn.esprit.spring.services.IServicePost;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Properties;
import java.util.stream.Collectors;

import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;

import javax.validation.Valid;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/post")
public class PostController {
    private final IServicePost iServicePost;

    @DeleteMapping("/delete/{id}")
    public void deletePost(@PathVariable("id") Integer id){
        iServicePost.deletePost(id);
    }
    @PutMapping("/update/{id}")
    @ResponseBody
    public Post updatePost(@RequestBody JsonNode post, @PathVariable("id") Integer id) throws IOException {
        return iServicePost.updatePost(post,id);
    }
    @DeleteMapping("/deletePostByUserId/{id}")
    public void deletePostByUserId(@PathVariable("id") Integer id){
        iServicePost.deletePostByUserId(id);
    }

    @GetMapping("/get/{id}")
    @ResponseBody
    public Post getPostById(@PathVariable("id") Integer id){
        return iServicePost.retrievePostById(id);
    }
    @GetMapping("/corenlp")
    @ResponseBody
    public void getkoko(){
        String text = "This is a test sentence.";
        Document doc = new Document(text);
        String classpath = System.getProperty("java.class.path");
        System.out.println("Classpath: " + classpath);
        for (Sentence sent : doc.sentences()) {
            System.out.println(sent);
            System.out.println("Tokens: " + sent.tokens());
        }
    }
    @GetMapping("/getall")
    @ResponseBody
    public List<Post> getAllPost(){
        return iServicePost.retrieveAllPosts();
    }
    @GetMapping("/getPostByUser/{id}")
    @ResponseBody
    public List<Post> getPostByUser(@PathVariable("id") Integer id){
        return iServicePost.getPostByUser(id);
    }
    @PostMapping("/add/{id}")
    public ResponseEntity<?> add(@RequestBody Post post,@PathVariable("id") Integer id,BindingResult result){
        if (result.hasErrors()) {
            List<String> errors = result.getAllErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errors);
        }
        iServicePost.simpleAdd(post,id);
        return ResponseEntity.badRequest().body(post);
    }
    @PostMapping(value = "/complexAdd/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE} )
    public ResponseEntity<String> uploadSingleFileExample1(@PathVariable("id") Integer id, @Valid @RequestPart("post") String post, @RequestPart("files") List<MultipartFile> files ) throws IOException {
        iServicePost.complexAdd(id,post,files);
        return ResponseEntity.ok(post);
    }
}