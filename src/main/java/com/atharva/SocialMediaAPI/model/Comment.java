package com.atharva.SocialMediaAPI.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="comment", uniqueConstraints = {@UniqueConstraint(columnNames = "commentID")})
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentID;

    @ManyToOne
    @JoinColumn(name="postID")
    private Post post;


    @ManyToOne
    @JoinColumn(name="userID")
    private AppUser user;

    private String content;
    private String imageURL;

}
