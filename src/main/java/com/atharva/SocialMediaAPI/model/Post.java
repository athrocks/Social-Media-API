package com.atharva.SocialMediaAPI.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "post", uniqueConstraints = {
        @UniqueConstraint(columnNames = "postID")
})
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postID;

    @ManyToOne
    @JoinColumn(name = "userID")
    private AppUser user;

    @ManyToOne
    @JoinColumn(name = "cityID")
    private City city;

    @Column(nullable = true) // allowed to contain null values
    private String imageURL;

    @Column(nullable = false)
    private String content;
    @Column(nullable = false)
    private LocalDateTime createDay;

}