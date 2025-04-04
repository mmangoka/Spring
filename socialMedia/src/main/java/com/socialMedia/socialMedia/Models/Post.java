package com.socialMedia.socialMedia.Models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "Post")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postID;


    @ManyToOne
    @JoinColumn(name = "Userid")
    @JsonIgnore
    private SocialUser socialuser;
}
