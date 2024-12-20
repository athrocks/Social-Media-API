package com.atharva.SocialMediaAPI.repository;

import com.atharva.SocialMediaAPI.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<AppUser, String> {

    @Query("SELECT u FROM AppUser u WHERE u.firstName LIKE %:keyword% OR u.lastName LIKE %:keyword% OR u.userName LIKE %:keyword% OR u.address LIKE %:keyword%")
    List<AppUser> searchByKeyword(@Param("keyword") String keyword);

}
