package com.atharva.SocialMediaAPI.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "user", uniqueConstraints = {
        @UniqueConstraint(columnNames = "userName")
})
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String userID;

    private String address;
    private String gender;
    private LocalDate dob;
    private String avatarURL;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String userName;
}
