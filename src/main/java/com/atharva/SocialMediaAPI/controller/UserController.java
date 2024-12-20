package com.atharva.SocialMediaAPI.controller;

import com.atharva.SocialMediaAPI.model.AppUser;
import com.atharva.SocialMediaAPI.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/")
    public AppUser createUser(@RequestBody AppUser user) {

        AppUser Appuser;

        try{
            Appuser = userService.createUser(user);
            log.info("User created: {}", user.toString());
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }

        return Appuser;
    }

    @GetMapping("/")
    public List<AppUser> getAllUsers() {
        List<AppUser> users;

        try {
            users = userService.getAllUsers();
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
        return users;
    }

    @GetMapping("/{id}")
    public AppUser getUser(@PathVariable String id) {

        AppUser user = userService.findById(id);

        if (user == null) {
            log.error("User with id {} not found", id);
            return null;
        }
        return user;
    }

    @GetMapping("/search")
    public List<AppUser> searchUserByKeyword(@RequestParam String keyword) {
        List<AppUser> users;
        if (keyword == null || keyword.isEmpty()) {
            log.info(" keyword is empty");
            return null;
        }

        users = userService.searchUserByKeyword(keyword);
        if (users == null) {
            log.info("User/Users with keyword {} not found", keyword);
            return null;
        }

        return users;
    }

    @PutMapping("/{id}")
    public AppUser updateUser(@PathVariable String id, @RequestBody AppUser updatedUser) {

        AppUser existingUser = userService.findById(id);

        if (existingUser == null) {
            log.info("User with id {} not found, add user first if you want to update.", id);
            return null;
        }

        try{
            updatedUser = userService.updateUser(updatedUser,existingUser);
            log.info("User updated: {}", updatedUser.toString());
            return updatedUser;
        }
        catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public AppUser deleteUser(@PathVariable String id) {
        AppUser user;

        try {
            user = userService.deleteUser(id);
            log.info("User deleted: {}", user.toString());
            return user;
        }
        catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }
}

/*
POST /users — Create a new user
GET /users — Get a list of all users
GET /users/{id} — Get a specific user by ID
GET /users/keyword= - search User By Keyword
PUT /users/{id} — Update user details by ID
DELETE /users/{id} — Delete a user by ID
*/