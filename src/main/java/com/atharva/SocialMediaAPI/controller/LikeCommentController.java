package com.atharva.SocialMediaAPI.controller;

import com.atharva.SocialMediaAPI.model.LikeComment;
import com.atharva.SocialMediaAPI.service.LikeCommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/likecomment")
public class LikeCommentController {
    private final LikeCommentService likeCommentService;
    public LikeCommentController(LikeCommentService likeCommentService) {
        this.likeCommentService = likeCommentService;
    }


    // Create a new like for a comment. you provide commentId and userID in body
    @PostMapping("/")
    public LikeComment createLikeComment(@RequestBody LikeComment likeComment) {
        return likeCommentService.createLikeComment(likeComment);
    }

    @GetMapping("/")
    public List<LikeComment> getAllLikeComment() {
        return likeCommentService.getAllLikeComment();
    }

    // Get a specific like by likeCommentID.
    @GetMapping("/{likeCommentID}")
    public LikeComment getLikeByLikeCommentById(@PathVariable Long likeCommentID) {
        return likeCommentService.getLikeByLikeCommentById(likeCommentID);
    }

    // Delete a specific like by likeCommentID.
    @DeleteMapping("/{likeCommentID}")
    public void deleteLikeComment(@PathVariable Long likeCommentID) {
        likeCommentService.deleteLikeComment(likeCommentID);
    }

    // Get all likes for a specific comment.
    @GetMapping("/comment/{commentID}")
    public List<LikeComment> getLikeCommentsByCommentId(@PathVariable Long commentID) {
        return likeCommentService.getLikeCommentsByCommentId(commentID);
    }

    // Get all comments liked by a specific user.
    @GetMapping("/users/{userId}")
    public List<LikeComment> getLikeCommentsByUserId(@PathVariable String userId) {
        return likeCommentService.getLikeCommentsByUserId(userId);
    }

    // Total Likes on Comment
    @GetMapping("/totalLikes/{commentID}")
    public int getTotalLikes(@PathVariable Long commentID) {
        return likeCommentService.getTotalLikes(commentID);
    }


}
