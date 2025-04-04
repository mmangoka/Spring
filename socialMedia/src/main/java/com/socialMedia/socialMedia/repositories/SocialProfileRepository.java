package com.socialMedia.socialMedia.repositories;

import com.socialMedia.socialMedia.Models.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SocialProfileRepository extends JpaRepository<Profile,Long> {
}
