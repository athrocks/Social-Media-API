package com.atharva.SocialMediaAPI.repository;

import com.atharva.SocialMediaAPI.model.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowRepo extends JpaRepository<Follow, Long> {

}
