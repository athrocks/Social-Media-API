package com.atharva.SocialMediaAPI.service;

import com.atharva.SocialMediaAPI.model.AppUser;
import com.atharva.SocialMediaAPI.repository.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserService {

    private final UserRepo userRepo;

    @Autowired
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public List<AppUser> searchUserByKeyword(String keyword) {
        return userRepo.searchByKeyword(keyword);
    }

    public AppUser createUser(AppUser user) {

        if (user.getFirstName() == null || user.getFirstName().isEmpty()) {
            throw new IllegalArgumentException("firstName is required");
        }

        if (user.getLastName() == null || user.getLastName().isEmpty()) {
            throw new IllegalArgumentException("lastName is required");
        }

        if (user.getUserName() == null || user.getUserName().isEmpty()) {
            throw new IllegalArgumentException("userName is required");
        }

        return userRepo.save(user);
    }


    public List<AppUser> getAllUsers() {
        return userRepo.findAll();
    }

    public AppUser findById(String id) {
        return userRepo.findById(id).orElse(null);
    }

    public AppUser updateUser(AppUser updatedUser,AppUser existingUser) {

        // we can do this because we added @Builder in Model
        existingUser = existingUser.builder()
                .userID(existingUser.getUserID())
                .address(updatedUser.getAddress())
                .gender(updatedUser.getGender())
                .dob(updatedUser.getDob())
                .avatarURL(updatedUser.getAvatarURL())
                .firstName(updatedUser.getFirstName())
                .lastName(updatedUser.getLastName())
                .userName(updatedUser.getUserName())
                .build();

        // or you could have done imperatively
        /*
        appUser.setUserName(user.getUserName());
        appUser.setGender(user.getGender());
        And so on .... for all
        */

        return userRepo.save(existingUser);
    }

    public AppUser deleteUser(String id) {
        AppUser user = userRepo.findById(id).orElse(null);
        if (user != null) {
            userRepo.delete(user);
            return user;
        }else {
            log.error("User Not Found");
            return null;
        }
    }
}
