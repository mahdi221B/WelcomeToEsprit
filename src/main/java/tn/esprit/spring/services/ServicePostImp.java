package tn.esprit.spring.services;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entity.Post;
import tn.esprit.spring.repositories.PostRepository;

import java.util.List;
@Slf4j
@Service
@AllArgsConstructor
public class ServicePostImp implements IServicePost{
    private final PostRepository postRepository;

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

    @Override
    public Post addPost(Post Post) {
        return postRepository.save(Post);
    }

    @Override
    public Post updatePost(Post post,Integer id) {
        post.setId(id);
        return postRepository.save(post);
    }
}
