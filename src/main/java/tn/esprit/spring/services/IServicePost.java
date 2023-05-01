package tn.esprit.spring.services;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.spring.entity.Post;
import java.io.IOException;
import java.util.List;

public interface IServicePost {
    public List<Post> retrieveAllPosts();
    public void deletePost(Integer id);
    public void deletePostByUserId(Integer id);
    public Post retrievePostById(Integer id);
    public Post updatePost(JsonNode postJson, Integer id) throws IOException;
    public List<Post> getPostByUser(Integer id);
    public void simpleAdd(Post post,Integer id);
    public void complexAdd(Integer id,String post,List<MultipartFile> files ) throws IOException;

}