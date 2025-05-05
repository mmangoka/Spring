package com.socialMedia.socialMedia.Models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "profiles")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long profileID;


    @OneToOne
    @JoinColumn(name = "socialID")
    @JsonIgnore
    private SocialUser socialUser;


    private String description;

    public void setSocialUser(SocialUser socialUser) {
        this.socialUser = socialUser;

        if (socialUser.getProfile() != this) {
            socialUser.setProfile(this);
        }
    }
}
