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


    @OneToOne(mappedBy = "socialUser", cascade = {CascadeType.REMOVE,CascadeType.PERSIST, CascadeType.MERGE})
    //@JoinColumn(name = "profileID")
    private Profile profile;

    @OneToMany(mappedBy = "socialuser",cascade =  {CascadeType.PERSIST,CascadeType.MERGE})//to indicate owner of the relationship we use mapped by
    private List<Post> posts =  new ArrayList<>();


    @ManyToMany(fetch = FetchType.LAZY)//default FetchType.LAZY,use eager when it is needed
    @JoinTable(
            name = "userGroup",
            joinColumns = @JoinColumn(name  = "userID"),//used to define the foreign key
            inverseJoinColumns = @JoinColumn(name = "groupID")//foreign key in table group id
    )//allows you to map the associaties and define many to many relationships
    private Set<SocialGroup> userGroup = new HashSet<>();

    @Override
    public int hashCode(){
        return Objects.hash(socialID);
    }

    public void setSocialProfile(Profile profile) {
        profile.setSocialUser(this);
        this.profile = profile;
    }

}
