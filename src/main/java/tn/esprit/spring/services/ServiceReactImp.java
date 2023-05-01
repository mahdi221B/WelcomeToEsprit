package tn.esprit.spring.services;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entity.Post;
import tn.esprit.spring.entity.React;
import tn.esprit.spring.entity.ReactType;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.repositories.CommentRepository;
import tn.esprit.spring.repositories.PostRepository;
import tn.esprit.spring.repositories.ReactRepository;
import tn.esprit.spring.repositories.UserRepository;

import javax.transaction.Transactional;
import java.io.DataInput;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class ServiceReactImp implements IServiceReact{

    private final ReactRepository reactRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    @Override
    public List<React> retrieveAllReacts() {
        return reactRepository.findAll();
    }

    @Override
    public List<React> retrieveAllPostReacts(Integer postId) {
        return reactRepository.findReactsByPostId(postId);
    }

    @Override
    public void deleteReact(Integer id) {
        reactRepository.delete(reactRepository.findById(id).get());
    }

    @Override
    public React retrieveReactById(Integer id) {
        return reactRepository.findById(id).get();
    }

    @Override
    public React retrieveReactByUserIdAndPostId(Integer userId, Integer postId) {
        return  reactRepository.findReactsByUserIdAndPostId(userId,postId);
    }

    @Override
    public String userReactions(Integer userId, Integer postId) {
        return retrieveReactByUserIdAndPostId(userId, postId).getReaction().toString();
    }
    public React updateReact(React react, Integer id) {
        react.setId(id);
        return reactRepository.save(react);
    }

    @Transactional
    public void assignReactToPost(React react, Integer id) {
        reactRepository.save(react);
        react.setPost(postRepository.findById(id).get());
    }

    public React addOrUpdateAndAssignReactToPost(String reactSTRING, Integer idUser, Integer idPost) {
        User user = userRepository.findById(idUser).get();
        Post post = postRepository.findById(idPost).get();


        Map<String, ReactType> reactMap = new HashMap<>();
        reactMap.put("like", ReactType.like);
        reactMap.put("love", ReactType.love);
        reactMap.put("wow",  ReactType.wow);
        reactMap.put("haha", ReactType.haha);
        reactMap.put("sad", ReactType.sad);
        reactMap.put("angry", ReactType.angry);
        React reactJSON = new React();
        ReactType reactType = reactMap.get(reactSTRING);
        reactJSON.setReaction(reactType);

        Optional<React> react = post.getReactions().stream().filter(r -> r.getUser().equals(user)).findFirst();
        //post.getReactions().stream().anyMatch(reaction -> reaction.getUser().equals(user))
        if (react.isPresent()) {
            React existingReact = react.get();
            existingReact.setReaction(reactJSON.getReaction());
            return reactRepository.save(existingReact);
        } else {
            reactJSON.setPost(post);
            reactJSON.setUser(user);
            return reactRepository.save(reactJSON);
        }
    }
    @Transactional
    public void assignReactToComment(React react, Integer id) {
        reactRepository.save(react);
        react.setComment(commentRepository.findById(id).get());
    }
}