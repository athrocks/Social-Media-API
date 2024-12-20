package com.atharva.SocialMediaAPI.repository;

import com.atharva.SocialMediaAPI.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepo extends JpaRepository<Post, Long> {

}
