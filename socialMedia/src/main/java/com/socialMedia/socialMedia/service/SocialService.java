package com.socialMedia.socialMedia.service;

import com.socialMedia.socialMedia.Models.SocialUser;
import com.socialMedia.socialMedia.repositories.SocialUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
