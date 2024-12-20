package com.atharva.SocialMediaAPI.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="likePost", uniqueConstraints = {
        @UniqueConstraint(columnNames = "likePostID")
})
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LikePost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long likePostID;

    @ManyToOne
    @JoinColumn(name = "postID")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "userID")
    private AppUser user;
}