package com.atharva.SocialMediaAPI.service;

import com.atharva.SocialMediaAPI.model.AppUser;
import com.atharva.SocialMediaAPI.model.Follow;
import com.atharva.SocialMediaAPI.repository.FollowRepo;
import com.atharva.SocialMediaAPI.repository.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class FollowService {

    private final FollowRepo followRepo;
    private final UserRepo userRepo;
    public FollowService(FollowRepo followRepo,UserRepo userRepo) {
        this.followRepo = followRepo;
        this.userRepo = userRepo;
    }


    public Follow createFollow(Follow follow) {
        log.info("Creating follow for {} by {}.", follow.getUserTarget(), follow.getFollower());

        Optional<AppUser> followerOptional = userRepo.findById(follow.getFollower().getUserID());
        Optional<AppUser> userTargetOptional = userRepo.findById(follow.getUserTarget().getUserID());

        if (followerOptional.isEmpty() || userTargetOptional.isEmpty()) {
            log.error("Either follower not exists or Target User");
        }

        if (followerOptional.isPresent() && userTargetOptional.isPresent()) {
            AppUser follower = followerOptional.get();
            AppUser targetUser = userTargetOptional.get();
            follow.setFollower(follower);
            follow.setUserTarget(targetUser);
        }

        try {
            log.info("Creating follow for {} by {}.", follow.getUserTarget(), follow.getFollower());
            return followRepo.save(follow);
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return null;
    }

    public List<Follow> getAllFollows() {
        return followRepo.findAll();
    }


    public Follow getFollow(Long id) {
        try {
            Optional<Follow> followOptional = followRepo.findById(id);
            if (followOptional.isPresent()) {
                return followOptional.get();
            } else {
                log.error("No follow found with followID: {}", id);
            }
        } catch (Exception e) {
            log.error("Error fetching follow by ID: {}", e.getMessage());
        }
        return null;
    }


    public void deleteFollow(Long id) {
        followRepo.deleteById(id);
    }

    // remember this userId is follower of various account and we need to find out those various account
    public List<Follow> getAllUserTargets(String userId) {
        Optional<AppUser> userOptional = userRepo.findById(userId);
        if (userOptional.isEmpty()) {
            log.error("No User with userID: {}", userId);
        }

        AppUser user = userOptional.get();

        List<Follow> followList = followRepo.findAll();
        List<Follow> userFollowList = new ArrayList<>();
        try {
            for (Follow follow : followList) {
                if (follow.getFollower().getUserID().equals(userId)) {
                    userFollowList.add(follow);
                }
            }
            log.info("Users Followed by Follower are : {}", userFollowList);
            return userFollowList;
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    public List<Follow> getUserFollowers(String userId) {
        Optional<AppUser> userOptional = userRepo.findById(userId);
        if (userOptional.isEmpty()) {
            log.error("No User with userID: {}", userId);
        }

        AppUser user = userOptional.get();

        List<Follow> followList = followRepo.findAll();
        List<Follow> userFollowerList = new ArrayList<>();
        try {
            for (Follow follow : followList) {
                if (follow.getUserTarget().getUserID().equals(userId)) {
                    userFollowerList.add(follow);
                }
            }
            log.info("Follower of user: {} are : {}", user, userFollowerList);
            return userFollowerList;
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }
}
