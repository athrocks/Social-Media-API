package com.atharva.SocialMediaAPI.service;

import com.atharva.SocialMediaAPI.model.AppUser;
import com.atharva.SocialMediaAPI.model.Comment;
import com.atharva.SocialMediaAPI.model.Post;
import com.atharva.SocialMediaAPI.repository.CommentRepo;
import com.atharva.SocialMediaAPI.repository.PostRepo;
import com.atharva.SocialMediaAPI.repository.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CommentService {
    private final CommentRepo commentRepo;
    private final PostRepo postRepo;
    private final UserRepo userRepo;
    @Autowired
    public CommentService(CommentRepo commentRepo,PostRepo postRepo,UserRepo userRepo) {
        this.commentRepo = commentRepo;
        this.postRepo = postRepo;
        this.userRepo = userRepo;
    }


    public Comment addComment(Comment comment) {

        if (comment.getPost() == null) {
            log.error("Post is null in the comment");
            return null;
        }

        if (comment.getUser() == null) {
            log.error("User is null in the comment");
            return null;
        }

        Post post = postRepo.findById(comment.getPost().getPostID()).orElse(null);
        AppUser user = userRepo.findById(comment.getUser().getUserID()).orElse(null);

        if (post == null) {
            log.error("Post not found");
            return null;
        }
        if (user == null) {
            log.error("User not found");
            return null;
        }

        log.info("User found to comment on Post which was also found");
        log.info("User is: {}", user);
        log.info("Post is: {}", post);

        try {
            comment.setUser(user);
            comment.setPost(post);
            return commentRepo.save(comment);
        } catch (Exception e) {
            log.error("Comment can not be added",e);
        }
        return null;
    }

    public List<Comment> getAllComments() {
        return commentRepo.findAll();
    }

    public List<Comment> getCommentByPostId(Long postId) {
        List<Comment> commentList = commentRepo.findAll();
        log.info("Comments are: {}", commentList);
        List<Comment> commentsOnPostList = new ArrayList<>();
        log.info("Comments on Posts are: {}", commentsOnPostList);

        try {
            for (Comment comment : commentList) {
                if(comment.getPost().getPostID().equals(postId)) {
                    commentsOnPostList.add(comment);
                }
            }
            log.info("Comments on Post: {}",commentsOnPostList.toString());
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
        return commentsOnPostList;
    }

    public List<Comment> getCommentByUserId(String userId) {
        List<Comment> commentList = commentRepo.findAll();
        List<Comment> commentsByUserList = new ArrayList<>();

        try {
            for (Comment comment : commentList) {
                if(comment.getUser().getUserID().equals(userId)) {
                    commentsByUserList.add(comment);
                }
            }
            log.info("Comments By User: {}",commentsByUserList.toString());
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
        return commentsByUserList;
    }

    public void deleteCommentById(Long id) {
        commentRepo.deleteById(id);
    }
}
