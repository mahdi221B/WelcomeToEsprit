package tn.esprit.spring.services;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.spring.entity.Post;
import tn.esprit.spring.entity.PostMedia;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.util.List;

public interface IServicePost {
    public List<Post> retrieveAllPosts();
    public void deletePost(Integer id);
    public Post retrievePostById(Integer id);
    public Post updatePost(JsonNode postJson, Integer id) throws IOException;
    public List<Post> getPostByUser(Integer id);
    public void add(Post post,Integer id);
}
