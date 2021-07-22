package com.spring.challenge.restapijava.repository;

import com.spring.challenge.restapijava.model.Video;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoRepository extends JpaRepository<Video, Long> {
}
