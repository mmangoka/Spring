package com.socialMedia.socialMedia.repositories;

import com.socialMedia.socialMedia.Models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SocialPostRepository extends JpaRepository<Post,Long> {
}
