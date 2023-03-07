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
import tn.esprit.spring.entity.Hashtag;
import tn.esprit.spring.entity.Post;
import tn.esprit.spring.services.IServiceHashtag;
import tn.esprit.spring.services.IServicePost;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.util.Set;

import tn.esprit.spring.services.IServiceFilesStorage;


import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/post")
public class PostController {
    private final IServicePost iServicePost;
    private final IServiceFilesStorage iServiceFilesStorage;
    private final IServiceHashtag iServiceHashtag;

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
    public ResponseEntity<?> add(@Valid @RequestBody Post post,@PathVariable("id") Integer id) throws IOException {
        iServicePost.simpleAdd(post,id);
        return ResponseEntity.ok(post);
    }
    @PostMapping(value = "/addWFiles/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE} )
    public ResponseEntity<String> addWFiles(@PathVariable("id") Integer id, @Valid @RequestPart("post") String post, @RequestPart("files") List<MultipartFile> files ) throws IOException {
        iServicePost.complexAdd(id,post,files);
        return ResponseEntity.ok(post);
    }
    @GetMapping("/files/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName) throws MalformedURLException {
        Resource resource = iServiceFilesStorage.load(fileName);
        if (resource != null) {
            //The HttpHeaders in the ResponseEntity specify that the file should be downloaded as an
            //attachment, rather than displayed in the browser.
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);
        } else {
            return null;
        }
    }

    @GetMapping("/top")
    public List<Hashtag> getTopHashtags(@RequestParam(defaultValue = "10") int limit) {
        return iServiceHashtag.getTopHashtags(limit);
    }
}