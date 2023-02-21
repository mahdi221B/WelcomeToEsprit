package tn.esprit.spring.controllers;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.spring.entity.Post;
import tn.esprit.spring.entity.PostMedia;
import tn.esprit.spring.repositories.PostMediaRepository;
import tn.esprit.spring.services.IServiceFilesStorage;
import tn.esprit.spring.services.IServicePost;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/post")
public class PostController {
    private final IServicePost iServicePost;
    private final IServiceFilesStorage iServiceFilesStorage;
    private final PostMediaRepository mediaRepository;
    ObjectMapper objectMapper = new ObjectMapper();


    @PutMapping("/update/{id}")
    @ResponseBody
    public Post updatePost(@RequestBody JsonNode post, @PathVariable("id") Integer id) throws IOException {
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
    @GetMapping("/getPostByUser/{id}")
    @ResponseBody
    public List<Post> getPostByUser(@PathVariable("id") Integer id){
        return iServicePost.getPostByUser(id);
    }
    @PostMapping("/add/{id}")
    public void add(@RequestBody Post post,@PathVariable("id") Integer id){
        iServicePost.add(post,id);
    }
    @PostMapping(value = "/upload/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE} )
    public ResponseEntity<String> uploadSingleFileExample1(@PathVariable("id") Integer id,@RequestPart("post") String post,@RequestPart("files") List<MultipartFile> files ) throws IOException {
        //Transform from string to JSON function
        String f;
        Post postJSON = objectMapper.readValue(post, Post.class);
        iServicePost.add(postJSON,id);
        List<PostMedia> medias = new ArrayList<>();
        for (MultipartFile m:files) {
            PostMedia media = new PostMedia();
            media.setOriginalFilename(m.getOriginalFilename());
            media.setName(m.getName());
            media.setContentType(m.getContentType());
            media.setSize(m.getBytes());
            media.setPost(postJSON);
            medias.add(media);
            mediaRepository.save(media);
            log.info("111");
            iServiceFilesStorage.save(m.getOriginalFilename(), m);
        }
        return ResponseEntity.ok("Success and the code is :" );
    }
}