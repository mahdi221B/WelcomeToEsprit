package tn.esprit.spring.services;

import tn.esprit.spring.entity.Post;

import java.util.List;

public interface IServicePost {
    public List<Post> retrieveAllPosts();
    public void deletePost(Integer id);
    public Post retrievePostById(Integer id);
    public Post addPost(Post post);
    public Post updatePost(Post post,Integer id);
}
