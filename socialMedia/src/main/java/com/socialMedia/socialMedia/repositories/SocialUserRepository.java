package com.socialMedia.socialMedia.repositories;

import com.socialMedia.socialMedia.Models.SocialUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SocialUserRepository extends JpaRepository<SocialUser,Long> {
}
