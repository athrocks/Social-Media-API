package com.atharva.SocialMediaAPI.controller;

import com.atharva.SocialMediaAPI.model.Follow;
import com.atharva.SocialMediaAPI.service.FollowService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/follows")
public class FollowController {

    private final FollowService followService;
    public FollowController(FollowService followService) {
        this.followService = followService;
    }

    @PostMapping("/")
    public Follow createFollow(@RequestBody Follow follow) {
        return followService.createFollow(follow);
    }

    @GetMapping("/")
    public List<Follow> getAllFollows() {
        return followService.getAllFollows();
    }

    // Get a specific follow relationship by its followID
    @GetMapping("/{id}")
    public Follow getFollow(@PathVariable Long id) {
        return followService.getFollow(id);
    }

    // Delete a follow relationship using its followID.
    @DeleteMapping("/{id}")
    public void deleteFollow(@PathVariable Long id) {
        followService.deleteFollow(id);
    }

    // Get a list of all users followed by the specified user.
    @GetMapping("/follower/{userId}")
    public List<Follow> getAllUserTargets(@PathVariable String userId) {
        return followService.getAllUserTargets(userId);
    }

    // Get a list of all followers of a specific user.
    @GetMapping("/target/{userId}")
    public List<Follow> getUserFollowers(@PathVariable String userId) {
        return followService.getUserFollowers(userId);
    }


}
