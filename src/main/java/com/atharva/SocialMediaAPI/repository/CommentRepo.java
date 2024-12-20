package com.atharva.SocialMediaAPI.repository;

import com.atharva.SocialMediaAPI.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepo extends JpaRepository<Comment, Long> {

}
