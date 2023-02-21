package tn.esprit.spring.services;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entity.Post;
import tn.esprit.spring.repositories.PostRepository;
import tn.esprit.spring.repositories.UserRepository;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
@Slf4j
@Service
@AllArgsConstructor
public class ServicePostImp implements IServicePost{
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    ObjectMapper objectMapper;

    @Override
    public List<Post> retrieveAllPosts() {
        return postRepository.findAll();
    }
    @Override
    public void deletePost(Integer id) {
        postRepository.delete(postRepository.findById(id).get());
    }
    @Override
    public Post retrievePostById(Integer id) {
        return postRepository.findById(id).get();
    }

    public Post addPost(Post post) {
        return postRepository.save(post);
    }

    public Post updatePost(JsonNode postJson, Integer postId) throws IOException {
        Post post = postRepository.findById(postId).orElseThrow(() -> new EntityNotFoundException("Post not found"));
        return postRepository.save(objectMapper.readerForUpdating(post).readValue(postJson));
    }

    @Override
    public List<Post> getPostByUser(Integer id) {
        return userRepository.findById(id).get().getPosts();
    }

    @Transactional
    public void add(Post post, Integer id) {
        addPost(post).setUser(userRepository.findById(id).get());
    }



}
