package tn.esprit.spring.services;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entity.React;
import tn.esprit.spring.repositories.CommentRepository;
import tn.esprit.spring.repositories.PostRepository;
import tn.esprit.spring.repositories.ReactRepository;
import javax.transaction.Transactional;
import java.util.List;
@Slf4j
@Service
@AllArgsConstructor
public class ServiceReactImp implements IServiceReact{

    private final ReactRepository reactRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    @Override
    public List<React> retrieveAllReacts() {
        return reactRepository.findAll();
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
    public React addReact(React react) {
        return reactRepository.save(react);
    }

    @Override
    public React updateReact(React react, Integer id) {
        react.setId(id);
        return reactRepository.save(react);
    }

    @Transactional
    public void assignReactToPost(React react, Integer id) {

        react.setPost(postRepository.findById(id).orElse(null));

        reactRepository.save(react);
    }

    @Transactional
    public void assignReactToComment(React react, Integer id) {
        react.setComment(commentRepository.findById(id).orElse(null));

        reactRepository.save(react);
    }
}
