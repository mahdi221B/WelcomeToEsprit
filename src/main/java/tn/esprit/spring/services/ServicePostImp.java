package tn.esprit.spring.services;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.stanford.nlp.pipeline.Annotation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.spring.entity.*;
import tn.esprit.spring.repositories.PostMediaRepository;
import tn.esprit.spring.repositories.PostRepository;
import tn.esprit.spring.repositories.UserRepository;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import static tn.esprit.spring.services.ServiceCommentImp.getSentimentAnalysis;
import static tn.esprit.spring.services.ServiceCommentImp.getSentimentScore;

@Slf4j
@Service
@AllArgsConstructor
public class ServicePostImp implements IServicePost{
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final PostMediaRepository mediaRepository;
    private final IServiceFilesStorage iServiceFilesStorage;
    private ServiceHashtagImp hashtagService;
    ObjectMapper objectMapper;


    public GeoPoint getPostLocation(String address) {
        try {
            HttpClient httpClient = HttpClientBuilder.create().build();
            //String url = "https://nominatim.openstreetmap.org/search?q=" + address + "&format=json&addressdetails=1";
            URIBuilder uriBuilder = new URIBuilder("https://nominatim.openstreetmap.org/search");
            uriBuilder.setParameter("q", address);
            uriBuilder.setParameter("format", "json");
            uriBuilder.setParameter("addressdetails", "1");
            HttpGet httpGet = new HttpGet(uriBuilder.build());
            HttpResponse response = httpClient.execute(httpGet);
            String responseBody = EntityUtils.toString(response.getEntity());
            JSONArray results = new JSONArray(responseBody);
            JSONObject firstResult = results.getJSONObject(0);
            double lat = firstResult.getDouble("lat");
            double lon = firstResult.getDouble("lon");
            return new GeoPoint(lat,lon);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }


    @Override
    public List<Post> retrieveAllPosts() {
        return postRepository.findAll();
    }
    @Override
    public void deletePost(Integer id) {
        Post post = postRepository.findById(id).get();
        postRepository.delete(post);
    }
    @Override
    public void deletePostByUserId(Integer id) {
        List<Post> posts = postRepository.findAllByUser(userRepository.findById(id).get());
        postRepository.deleteAll(posts);
    }

    @Override
    public Post retrievePostById(Integer id) {
        return postRepository.findById(id).get();
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
    public void simpleAdd(Post post, Integer id) {
        User user = userRepository.findById(id).get();
        post.setUser(user);
        Annotation annotation = getSentimentAnalysis(post.getContent());
        post.setSentimentScore(getSentimentScore(annotation));
        post.setCreatedAt(LocalDateTime.now());
        if (post.getTags() !=null){
            hashtagService.updateHashtags(post.getTags());
        }
        postRepository.save(post);
    }
    @Transactional
    public void complexAdd(Integer id,String post,List<MultipartFile> files ) throws IOException {
        Post postJSON = objectMapper.readValue(post, Post.class);
        User user = userRepository.findById(id).get();
        postJSON.setUser(user);
        Annotation annotation = getSentimentAnalysis(postJSON.getContent());
        postJSON.setSentimentScore(getSentimentScore(annotation));
        postJSON.setCreatedAt(LocalDateTime.now());
        if(postJSON.getLocationName()!=null){
            postJSON.setLocation(getPostLocation(postJSON.getLocationName()));
        }
        if (postJSON.getTags() !=null){
            hashtagService.updateHashtags(postJSON.getTags());
        }
        postRepository.save(postJSON);
        if(files!=null){
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
                iServiceFilesStorage.save(m.getOriginalFilename(), m);
            }
        }
    }
}