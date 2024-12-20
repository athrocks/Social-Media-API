package com.atharva.SocialMediaAPI.controller;

import com.atharva.SocialMediaAPI.model.AppUser;
import com.atharva.SocialMediaAPI.model.Comment;
import com.atharva.SocialMediaAPI.model.Post;
import com.atharva.SocialMediaAPI.repository.PostRepo;
import com.atharva.SocialMediaAPI.repository.UserRepo;
import com.atharva.SocialMediaAPI.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
@Slf4j
public class CommentController {
    private final CommentService commentService;

    private final PostRepo postRepo;

    private final UserRepo userRepo;

    @Autowired
    public CommentController(CommentService commentService,PostRepo postRepo,UserRepo userRepo) {
        this.commentService = commentService;
        this.postRepo = postRepo;
        this.userRepo = userRepo;
    }

    // save
    @PostMapping("/")
    public Comment addComment(@RequestBody Comment comment) {
        log.info("Adding comment {}", comment);
        log.info("Commenting on  Post {}", comment.getPost());
        log.info("PostId {}", comment.getPost().getPostID());
        log.info("Comment by User {}", comment.getUser());
        log.info("Comment by UserId: {}", comment.getUser().getUserID());

        return commentService.addComment(comment);
    }

    // findAllComments
    @GetMapping("/")
    public List<Comment> getAllComments() {
        return commentService.getAllComments();
    }

    // findCommentByPostId
    @GetMapping("/posts/{postId}")
    public List<Comment> getCommentByPostId(@PathVariable Long postId) {
        return commentService.getCommentByPostId(postId);
    }

    // findCommentByUserId
    @GetMapping("/users/{userId}")
    public List<Comment> getCommentByUserId(@PathVariable String userId) {
        return commentService.getCommentByUserId(userId);
    }

    // deleteCommentByUserId
    @DeleteMapping("/{id}")
    public void deleteCommentById(@PathVariable Long id) {
        commentService.deleteCommentById(id);
    }

    // add update method in the future.

}

