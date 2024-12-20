package com.atharva.SocialMediaAPI.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="follow", uniqueConstraints = {@UniqueConstraint(columnNames = "followID")})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Follow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long followID;

    @ManyToOne
    @JoinColumn(name = "follower_userID")
    private AppUser follower;

    @ManyToOne
    @JoinColumn(name = "target_userID")
    private AppUser userTarget;

}
