package com.socialMedia.socialMedia.service;

import com.socialMedia.socialMedia.Models.SocialUser;
import com.socialMedia.socialMedia.repositories.SocialUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SocialService {


    @Autowired
    SocialUserRepository socialUserRepository;

    public List<SocialUser> getAllUsers() {

        return socialUserRepository.findAll();
    }

    public SocialUser saveUser(SocialUser socialUser) {

           return socialUserRepository.save(socialUser);

    }

    public SocialUser deleteSocialUser(Long id) {
        SocialUser socialUser = socialUserRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Social User Not found."));
         socialUserRepository.delete(socialUser);

         return socialUser;
    }
}
