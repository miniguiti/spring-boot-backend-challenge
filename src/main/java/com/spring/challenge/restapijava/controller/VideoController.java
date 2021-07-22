package com.spring.challenge.restapijava.controller;

import com.spring.challenge.restapijava.dto.VideoDto;
import com.spring.challenge.restapijava.model.Video;
import com.spring.challenge.restapijava.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/videos")
public class VideoController {
    @Autowired
    VideoRepository videoRepository;

    @GetMapping
    public Page<VideoDto> listar(@RequestParam int pagina, @RequestParam int qtd){
        Pageable paginacao = PageRequest.of(pagina, qtd);

        Page<Video> videos = videoRepository.findAll(paginacao);
        return VideoDto.converter(videos);
    }
}
