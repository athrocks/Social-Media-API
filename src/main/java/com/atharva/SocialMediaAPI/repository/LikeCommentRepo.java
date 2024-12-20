package com.atharva.SocialMediaAPI.repository;

import com.atharva.SocialMediaAPI.model.LikeComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeCommentRepo extends JpaRepository<LikeComment, Long> {

}
