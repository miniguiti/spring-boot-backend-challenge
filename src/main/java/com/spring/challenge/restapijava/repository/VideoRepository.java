package com.spring.challenge.restapijava.repository;

import com.spring.challenge.restapijava.model.Video;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoRepository extends JpaRepository<Video, Long> {
    Page<Video> findByTitulo(String nomeVideo, Pageable pageable);
}
