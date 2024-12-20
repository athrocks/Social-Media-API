package com.atharva.SocialMediaAPI.controller;

import com.atharva.SocialMediaAPI.model.Post;
import com.atharva.SocialMediaAPI.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;
    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/")
    public Post createPost(@RequestBody Post post) {
        return postService.createPost(post);
    }

    @GetMapping("/")
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/{id}")
    public Post getPostById(@PathVariable Long id) {
        return postService.getPostById(id);
    }

    @PutMapping("/{id}")
    public Post updatePost(@PathVariable Long id, @RequestBody Post post) {
        return postService.updatePost(id, post);
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id) {
        postService.deletePost(id);
    }


    // find all posts from users
    @GetMapping("/users/{userId}")
    public List<Post> getPostsByUser(@PathVariable String userId) {
        return postService.getPostsByUser(userId);
    }

    // findAllPhotoPost: Retrieves all photos that are posts
    @GetMapping("/photos")
    public List<Post> getAllPhotosPost() {
        return postService.getAllPhotosPost();
    }


    // findAllPhotoPostByUserID: Fetches all photo posts created by a user, identified by userID
    @GetMapping("/users/photos/{userId}")
    public List<Post> getUserPhotosPostsByUserID(@PathVariable String userId) {
        return postService.getUserPhotosPostsByUserID(userId);
    }

    // getRecommendPosts: Returns a list of recommended posts based on a given range of start and pageSize.

    //getRecommendPostsByUsers: Returns recommended posts for a specific user,
    //                          with pagination based on start, pageSize, and userID.


}
