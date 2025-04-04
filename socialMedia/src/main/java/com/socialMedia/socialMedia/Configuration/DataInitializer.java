package com.socialMedia.socialMedia.Configuration;


import com.socialMedia.socialMedia.Models.Post;
import com.socialMedia.socialMedia.Models.Profile;
import com.socialMedia.socialMedia.Models.SocialGroup;
import com.socialMedia.socialMedia.Models.SocialUser;
import com.socialMedia.socialMedia.repositories.SocialGroupRepository;
import com.socialMedia.socialMedia.repositories.SocialPostRepository;
import com.socialMedia.socialMedia.repositories.SocialProfileRepository;
import com.socialMedia.socialMedia.repositories.SocialUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    private final SocialUserRepository socialUserRepository;
    private final SocialGroupRepository socialGroupRepository;
    private final SocialPostRepository socialPostRepository;
    private final SocialProfileRepository socialProfileRepository;

    public DataInitializer(SocialUserRepository socialUserRepository, SocialGroupRepository socialGroupRepository,
                           SocialPostRepository socialPostRepository,
                           SocialProfileRepository socialProfileRepository) {
        this.socialUserRepository = socialUserRepository;
        this.socialGroupRepository = socialGroupRepository;
        this.socialPostRepository = socialPostRepository;
        this.socialProfileRepository = socialProfileRepository;
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            SocialUser socialUser1 = new SocialUser();
            SocialUser socialUser2 = new SocialUser();
            SocialUser socialUser3 = new SocialUser();
            SocialUser socialUser4 = new SocialUser();

            //save users
            socialUserRepository.save(socialUser1);
            socialUserRepository.save(socialUser2);
            socialUserRepository.save(socialUser3);
            socialUserRepository.save(socialUser4);

            //create groups
            SocialGroup socialGroup1 = new SocialGroup();
            SocialGroup socialGroup2 = new SocialGroup();

            //add users to the group
            socialGroup1.getSocialUsers().add(socialUser1);
            socialGroup1.getSocialUsers().add(socialUser2);


            socialGroup2.getSocialUsers().add(socialUser3);
            socialGroup2.getSocialUsers().add(socialUser4);

            socialGroupRepository.save(socialGroup1);
            socialGroupRepository.save(socialGroup2);

            //associate users to groups
            socialUser1.getUserGroup().add(socialGroup1);
            socialUser2.getUserGroup().add(socialGroup1);
            socialUser3.getUserGroup().add(socialGroup2);
            socialUser4.getUserGroup().add(socialGroup2);

            //save users back to db to update associations
            socialUserRepository.save(socialUser1);
            socialUserRepository.save(socialUser2);
            socialUserRepository.save(socialUser3);
            socialUserRepository.save(socialUser4);



            //create some posts
            Post post1 = new Post();
            Post post2 = new Post();
            Post post3 = new Post();
            Post post4 = new Post();

            post1.setSocialuser(socialUser1);
            post2.setSocialuser(socialUser2);
            post3.setSocialuser(socialUser3);
            post4.setSocialuser(socialUser4);


            socialPostRepository.save(post1);
            socialPostRepository.save(post2);
            socialPostRepository.save(post3);
            socialPostRepository.save(post4);


            //associate profiles with users
            Profile profile1 = new Profile();
            Profile profile2 = new Profile();
            Profile profile3 = new Profile();
            Profile profile4 = new Profile();

            profile1.setSocialUser(socialUser1);
            profile2.setSocialUser(socialUser2);
            profile3.setSocialUser(socialUser3);
            profile4.setSocialUser(socialUser4);

            socialProfileRepository.save(profile1);
            socialProfileRepository.save(profile2);
            socialProfileRepository.save(profile3);
            socialProfileRepository.save(profile4);


        };
    }

}
