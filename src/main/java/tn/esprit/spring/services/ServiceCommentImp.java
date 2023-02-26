package tn.esprit.spring.services;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.pipeline.StanfordCoreNLPClient;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.util.CoreMap;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.spring.configuration.Pipeline;
import tn.esprit.spring.entity.Comment;
import tn.esprit.spring.entity.Post;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.repositories.CommentRepository;
import tn.esprit.spring.repositories.PostRepository;
import javax.transaction.Transactional;
import java.sql.Date;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.util.CoreMap;
import tn.esprit.spring.repositories.UserRepository;

import java.util.Properties;


@Slf4j
@Service
@AllArgsConstructor
public class ServiceCommentImp implements IServiceComment{
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    @Override
    public List<Comment> retrieveAllComments() {
        return commentRepository.findAll();
    }
    @Override
    public void deleteComment(Integer id) {
        commentRepository.delete(commentRepository.findById(id).get());
    }
    @Override
    public Comment retrieveCommentById(Integer id) {
        return commentRepository.findById(id).get();
    }
    @Override
    public List<Comment> retrieveCommentsByUserId(Integer idUser) {
        return userRepository.findById(idUser).get().getComments();
    }
    @Override
    public Comment updateComment(Comment comment, Integer id) {
        comment.setId(id);
        return commentRepository.save(comment);
    }
    @Transactional
    public Comment assignCommentToPost(Comment comment, Integer idPost,Integer idUser) {
        User user = userRepository.findById(idUser).get();
        Post post = postRepository.findById(idPost).get();
        comment.setUser(user);
        comment.setPost(post);
        comment.setCreatedAt(LocalDateTime.now());
        Annotation annotation = getSentimentAnalysis(comment.getContent());
        comment.setSentiment(getSentimentScore(annotation));
        return commentRepository.save(comment);
    }
    public static Annotation getSentimentAnalysis(String text) {
        StanfordCoreNLP stanfordCoreNLP =  Pipeline.getInstance();
        Annotation annotation = new Annotation(text);
        stanfordCoreNLP.annotate(annotation);
        return annotation;
    }
    public static int getSentimentScore(Annotation annotation) {
        int sentimentScore = 0;
        int numOfSentences = 0;
        for (CoreMap sentence : annotation.get(CoreAnnotations.SentencesAnnotation.class)) {
            String sentiment = sentence.get(SentimentCoreAnnotations.SentimentClass.class);
            sentimentScore += getSentimentValue(sentiment);
            numOfSentences++;
        }
        if (numOfSentences > 0) {
            sentimentScore /= numOfSentences;
        }
        return sentimentScore;
    }
    public static int getSentimentValue(String sentiment) {
        switch (sentiment) {
            case "Very negative":
            case "Negative":
                return 0;
            case "Neutral":
                return 2;
            case "Positive":
            case "Very positive":
                return 4;
            default:
                return 2;
        }
    }
    public List<Post> getRecommendedPosts(Integer userId, Duration timeFilter) {
        List<Post> recommendedPosts = new ArrayList<>();
        List<Post> similarPosts;
        List<Post> userPosts = postRepository.findPostsByUser(userRepository.findById(userId).get());
        List<Comment> userComments = commentRepository.findCommentsByUser(userRepository.findById(userId).get());
        int totalSentimentScore = 0;
        int numInteractions = 0;
        for (Post post : userPosts) {
            totalSentimentScore += post.getSentimentScore();
            numInteractions++;
        }
        for (Comment comment : userComments) {
            totalSentimentScore += comment.getSentiment();
            numInteractions++;
        }
        int avgSentimentScore = numInteractions > 0 ? totalSentimentScore / numInteractions : 2;
        LocalDateTime oldestAllowedDate = LocalDateTime.now().minus(timeFilter);
        if (avgSentimentScore < 2) {
            similarPosts = postRepository.findBySentimentScoreBetweenAndCreatedAtAfter(0, 2);
        } else if (avgSentimentScore == 2) {
            similarPosts = postRepository.findBySentimentScoreBetweenAndCreatedAtAfter(2, 2);
        } else {
            similarPosts = postRepository.findBySentimentScoreBetweenAndCreatedAtAfter(2, 4);
        }
        for (Post post : similarPosts) {
            if (!userPosts.contains(post) && !userComments.contains(post)) {
                recommendedPosts.add(post);
            }
        }
        recommendedPosts.sort(Comparator.comparingInt(Post::getSentimentScore).thenComparing(Post::getCreatedAt).reversed());
        return recommendedPosts;
    }

}
    //@Scheduled(cron = "0 0 */12 * * *")
    /*public void countPostContentLength() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime sixHoursAgo = now.minusHours(6);
        List<User> users = userRepository.findAll();
        //List<Post> posts = postRepository.findPostByUserIDAndCreatedAtBetween(userId, sixHoursAgo, now);
        int totalLength = posts.stream().mapToInt(post -> post.getContent().length()).sum();
        System.out.println("Total length of posts for user " + userId + " in the last 6 hours: " + totalLength);
    }*/