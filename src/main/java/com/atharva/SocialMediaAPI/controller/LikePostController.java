package com.atharva.SocialMediaAPI.controller;

import com.atharva.SocialMediaAPI.model.LikePost;
import com.atharva.SocialMediaAPI.service.LikePostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/likepost")
public class LikePostController {

    private final LikePostService likePostService;

    public LikePostController(LikePostService likePostService) {
        this.likePostService = likePostService;
    }

    // Create a new like for a post. you provide postId and userID in body
    @PostMapping("/")
    public LikePost createLikePost(@RequestBody LikePost likePost) {
        return likePostService.createLikePost(likePost);
    }

    @GetMapping("/")
    public List<LikePost> getAllLikePosts() {
        return likePostService.getAllLikePosts();
    }

    // Get a specific like by likePostID.
    @GetMapping("/{likePostID}")
    public LikePost getLikeByLikePostById(@PathVariable Long likePostID) {
        return likePostService.getLikeByLikePostById(likePostID);
    }

    // Delete a specific like by likePostID.
    @DeleteMapping("/{likePostID}")
    public void deleteLikePost(@PathVariable Long likePostID) {
        likePostService.deleteLikePost(likePostID);
    }

    // Get all likes for a specific post.
    @GetMapping("/posts/{postId}")
    public List<LikePost> getLikePostsByPostId(@PathVariable Long postId) {
        return likePostService.getLikePostsByPostId(postId);
    }

    // Get all posts liked by a specific user.
    @GetMapping("/users/{userId}")
    public List<LikePost> getLikePostsByUserId(@PathVariable String userId) {
        return likePostService.getLikePostsByUserId(userId);
    }

}
