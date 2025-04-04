package com.socialMedia.socialMedia.Models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity(name = "SocialGroup")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SocialGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long groupID;

    @ManyToMany(mappedBy = "userGroup")
    @JsonIgnore
    private Set<SocialUser> socialUsers =  new HashSet<>();

    @Override
    public int hashCode(){
        return Objects.hash(groupID);
    }

}
