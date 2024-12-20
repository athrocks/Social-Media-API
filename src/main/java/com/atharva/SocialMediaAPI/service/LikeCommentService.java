package com.atharva.SocialMediaAPI.service;

import com.atharva.SocialMediaAPI.model.*;
import com.atharva.SocialMediaAPI.repository.CommentRepo;
import com.atharva.SocialMediaAPI.repository.LikeCommentRepo;
import com.atharva.SocialMediaAPI.repository.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class LikeCommentService {
    private final LikeCommentRepo likeCommentRepo;
    private final CommentRepo commentRepo;
    private final UserRepo userRepo;

    @Autowired
    public LikeCommentService(LikeCommentRepo likeCommentRepo,CommentRepo commentRepo,UserRepo userRepo) {
        this.likeCommentRepo = likeCommentRepo;
        this.commentRepo = commentRepo;
        this.userRepo = userRepo;
    }


    public LikeComment createLikeComment(LikeComment likeComment) {
        Comment comment = commentRepo.findById(likeComment.getComment().getCommentID()).orElse(null);
        if(comment == null) {
            log.error("Comment id {} not found", likeComment.getComment().getCommentID());
            return null;
        }
        AppUser user = userRepo.findById(likeComment.getUser().getUserID()).orElse(null);
        if(user == null) {
            log.error("User id {} not found", likeComment.getUser().getUserID());
            return null;
        }

        likeComment.setUser(user);
        likeComment.setComment(comment);

        try {
            return likeCommentRepo.save(likeComment);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;

    }

    public List<LikeComment> getAllLikeComment() {
        return likeCommentRepo.findAll();
    }

    public LikeComment getLikeByLikeCommentById(Long likeCommentID) {
        LikeComment likeComment = likeCommentRepo.findById(likeCommentID).orElse(null);
        if(likeComment == null) {
            log.error("Comment id {} not found", likeCommentID);
        }
        return likeComment;
    }

    public void deleteLikeComment(Long likeCommentID) {
        likeCommentRepo.deleteById(likeCommentID);
    }

    public List<LikeComment> getLikeCommentsByCommentId(Long commentID) {
        List<LikeComment> likeCommentList = likeCommentRepo.findAll();

        if(likeCommentList.isEmpty()) {
            log.error("Likes not found");
            return null;
        }

        try {
            List<LikeComment> likesOnComments = new ArrayList<>();
            for (LikeComment likeComment : likeCommentList) {
                if(likeComment.getComment().getCommentID().equals(commentID)) {
                    likesOnComments.add(likeComment);
                }
            }
            log.info("Likes on CommentID {} found", commentID);
            return likesOnComments;
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    public List<LikeComment> getLikeCommentsByUserId(String userId) {
        List<LikeComment> likeCommentList = likeCommentRepo.findAll();

        if(likeCommentList.isEmpty()) {
            log.error("Likes on Comment not found");
            return null;
        }

        try {
            List<LikeComment> userLikesList = new ArrayList<>();
            for (LikeComment likeComment : likeCommentList) {
                if(likeComment.getUser().getUserID().equals(userId)) {
                    userLikesList.add(likeComment);
                }
            }
            log.info("Likes by UserId {} found", userId);
            return userLikesList;
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    public int getTotalLikes(Long commentID) {
        List<LikeComment> likeCommentList = likeCommentRepo.findAll();

        if(likeCommentList.isEmpty()) {
            log.error("Likes not found");
            return 0;
        }

        try {
            List<LikeComment> likesOnComments = new ArrayList<>();
            for (LikeComment likeComment : likeCommentList) {
                if(likeComment.getComment().getCommentID().equals(commentID)) {
                    likesOnComments.add(likeComment);
                }
            }
            log.info("Likes on CommentID {} found", commentID);
            return likesOnComments.size();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return 0;
    }
}
