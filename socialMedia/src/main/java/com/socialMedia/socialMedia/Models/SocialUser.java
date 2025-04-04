package com.socialMedia.socialMedia.Models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Entity(name = "SocialUser")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SocialUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long socialID;


    @OneToOne(mappedBy = "socialUser",  cascade = CascadeType.ALL)
    private Profile profile;

    @OneToMany(mappedBy = "socialuser")
    private List<Post> posts =  new ArrayList<>();


    @ManyToMany
    @JoinTable(
            name = "userGroup",
            joinColumns = @JoinColumn(name  = "userID"),
            inverseJoinColumns = @JoinColumn(name = "groupID")
    )//allows you to map the associaties and define many to many relationships
    private Set<SocialGroup> userGroup = new HashSet<>();

    @Override
    public int hashCode(){
        return Objects.hash(socialID);
    }

    public void setSocialProfile(Profile profile) {
        Profile.setUser(this);
        this.profile = profile;
    }

}
