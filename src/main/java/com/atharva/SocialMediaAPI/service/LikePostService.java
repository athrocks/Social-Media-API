package com.atharva.SocialMediaAPI.service;

import com.atharva.SocialMediaAPI.model.AppUser;
import com.atharva.SocialMediaAPI.model.LikePost;
import com.atharva.SocialMediaAPI.model.Post;
import com.atharva.SocialMediaAPI.repository.LikePostRepo;
import com.atharva.SocialMediaAPI.repository.PostRepo;
import com.atharva.SocialMediaAPI.repository.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class LikePostService {

    private final LikePostRepo likePostRepo;
    private final PostRepo postRepo;
    private final UserRepo userRepo;

    public LikePostService(LikePostRepo likePostRepo,PostRepo postRepo,UserRepo userRepo) {
        this.likePostRepo = likePostRepo;
        this.postRepo = postRepo;
        this.userRepo = userRepo;
    }

    public LikePost createLikePost(LikePost likePost) {
        Post post = postRepo.findById(likePost.getPost().getPostID()).orElse(null);
        if(post == null) {
            log.error("Post id {} not found", likePost.getPost().getPostID());
            return null;
        }
        AppUser user = userRepo.findById(likePost.getUser().getUserID()).orElse(null);
        if(user == null) {
            log.error("User id {} not found", likePost.getUser().getUserID());
            return null;
        }

        likePost.setUser(user);
        likePost.setPost(post);

        try {
            return likePostRepo.save(likePost);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }


    public List<LikePost> getAllLikePosts() {
        return likePostRepo.findAll();
    }


    public LikePost getLikeByLikePostById(Long likePostID) {
        LikePost likePost = likePostRepo.findById(likePostID).orElse(null);
        if(likePost == null) {
            log.error("Post id {} not found", likePostID);
        }
        return likePost;
    }


    public void deleteLikePost(Long likePostID) {
        likePostRepo.deleteById(likePostID);
    }

    public List<LikePost> getLikePostsByPostId(Long postId) {
        List<LikePost> likePostList = likePostRepo.findAll();

        if(likePostList.isEmpty()) {
            log.error("Likes not found");
            return null;
        }

        try {
            List<LikePost> likesOnPosts = new ArrayList<>();
            for (LikePost likePost : likePostList) {
                if(likePost.getPost().getPostID().equals(postId)) {
                    likesOnPosts.add(likePost);
                }
            }
            log.info("Likes on PostId {} found", postId);
            return likesOnPosts;
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    public List<LikePost> getLikePostsByUserId(String userId) {
        List<LikePost> likePostList = likePostRepo.findAll();

        if(likePostList.isEmpty()) {
            log.error("Likes not found");
            return null;
        }

        try {
            List<LikePost> userLikesList = new ArrayList<>();
            for (LikePost likePost : likePostList) {
                if(likePost.getUser().getUserID().equals(userId)) {
                    userLikesList.add(likePost);
                }
            }
            log.info("Likes by UserId {} found", userId);
            return userLikesList;
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }
}
