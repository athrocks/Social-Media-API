package com.atharva.SocialMediaAPI.service;

import com.atharva.SocialMediaAPI.model.AppUser;
import com.atharva.SocialMediaAPI.model.City;
import com.atharva.SocialMediaAPI.model.Post;
import com.atharva.SocialMediaAPI.repository.CityRepo;
import com.atharva.SocialMediaAPI.repository.PostRepo;
import com.atharva.SocialMediaAPI.repository.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class PostService {

    private final PostRepo postRepo;
    private final CityRepo cityRepo;
    private final UserRepo userRepo;
    @Autowired
    public PostService(PostRepo postRepo,CityRepo cityRepo,UserRepo userRepo) {
        this.postRepo = postRepo;
        this.cityRepo = cityRepo;
        this.userRepo = userRepo;
    }

    public Post createPost(Post post) {
        AppUser user = userRepo.findById(post.getUser().getUserID()).orElse(null);
        City city = cityRepo.findById(post.getCity().getCityID()).orElse(null);

        if (user == null) {
            log.error("User not found");
            return null;
        }
        if (city == null) {
            log.error("City not found");
            return null;
        }

        log.info("User and City Found");

        post.setUser(user);
        post.setCity(city);

        try{
            post = postRepo.save(post);
            log.info("Post created: {}", post);
        }catch (Exception e){
            log.error(e.getMessage());
            return null;
        }
        return post;
    }

    public List<Post> getAllPosts() {
        List<Post> posts = postRepo.findAll();
        log.info("Posts found: {}", posts);
        return posts;
    }

    public Post getPostById(Long id) {
        Optional<Post> postOptional = postRepo.findById(id);
        if (postOptional.isEmpty()) {
            log.info("Post not found");
            return null;
        }

        Post post = postOptional.get();
        log.info("Post found: {}", post);
        return post;
    }

    public Post updatePost(Long id,Post updatedPost) {
        Optional<Post> existingPostOptional = postRepo.findById(id);

        if (existingPostOptional.isEmpty()) {
            log.info("Post not found for id: {}", id);
            return null;
        }

        Post existingPost = existingPostOptional.get();
        log.info("Post found for id: {} and are: {}", id , existingPost);

        City city = cityRepo.findById(existingPost.getCity().getCityID()).orElse(null);
        if (city == null) {
            log.error("City not found to update");
            return null;
        }

        try{
            existingPost = Post.builder()
                            .postID(existingPost.getPostID())
                            .user(existingPost.getUser()) // user dont update its own userId in post
                            .city(city)
                            .imageURL(updatedPost.getImageURL())
                            .content(updatedPost.getContent())
                            .createDay(updatedPost.getCreateDay())
                            .build();

            log.info("Post updated: {}", updatedPost);

            return postRepo.save(existingPost);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    public void deletePost(Long id) {
        Optional<Post> postOptional = postRepo.findById(id);
        if (postOptional.isEmpty()) {
            log.info("Post not found for postId: {}",id);
            return;
        }

        Post post = postOptional.get();
        log.info("Post found for postId: {} and are: {} ", id ,post);
        postRepo.delete(post);
    }

    public List<Post> getPostsByUser(String userId) {
        List<Post> posts = postRepo.findAll();
        List<Post> userPosts = new ArrayList<>();

        try {
            for (Post post : posts) {
                if(post.getUser().getUserID().equals(userId)){
                    userPosts.add(post);
                }
            }
            log.info("Posts are: {} for UserId: {}", userPosts, userId);
            return userPosts;
        } catch (Exception e) {
            log.error("Posts Not Found",e);
        }
        return null;
    }

    public List<Post> getAllPhotosPost(){
        List<Post> posts = postRepo.findAll();
        List<Post> photoPosts = new ArrayList<>();

        try {
            for (Post post : posts) {
                if (post.getImageURL() != null && !post.getImageURL().isEmpty()) {
                    photoPosts.add(post);
                }
            }
            log.info("Posts containing photos found: {}", photoPosts);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
        return photoPosts;
    }

    public List<Post> getUserPhotosPostsByUserID(String userId) {
        List<Post> posts = postRepo.findAll();
        List<Post> userPhotosPosts = new ArrayList<>();

        try {
            for (Post post : posts) {
                if(post.getUser().getUserID().equals(userId) && post.getImageURL() != null && !post.getImageURL().isEmpty()) {
                    userPhotosPosts.add(post);
                }
            }
            log.info("Users Posts containing Photos found: {}", userPhotosPosts);
        } catch (Exception e) {
            log.error("User Photos Posts not found");
            return null;
        }
        return userPhotosPosts;
    }

    //    getRecommendPosts
    //    getRecommendPostsByUsers
}
