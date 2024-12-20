package com.atharva.SocialMediaAPI.repository;

import com.atharva.SocialMediaAPI.model.LikePost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikePostRepo extends JpaRepository<LikePost, Long> {
}
