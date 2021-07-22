package com.spring.challenge.restapijava.controller;

import com.spring.challenge.restapijava.controller.form.VideoForm;
import com.spring.challenge.restapijava.dto.VideoDto;
import com.spring.challenge.restapijava.model.Video;
import com.spring.challenge.restapijava.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.net.URI;
import java.util.List;
import java.util.Optional;

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

    @GetMapping("/{id}")
    public ResponseEntity<VideoDto> buscarVideo(@PathVariable Long id){
        Optional<Video> video = videoRepository.findById(id);
        if (video.isPresent()) {
            return ResponseEntity.ok(new VideoDto(video.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<VideoDto> cadastrar(@RequestBody @Valid VideoForm videoForm,
                                              UriComponentsBuilder uriComponentsBuilder){
        Video video =  videoForm.converter();
        videoRepository.save(video);

        URI uri = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(video.getId()).toUri();
        return ResponseEntity.created(uri).body(new VideoDto(video));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<VideoDto> atualizarVideo(@RequestBody @Valid VideoForm videoForm,
                                                   @PathVariable Long id){
        Optional<Video> videoOptional = videoRepository.findById(id);
        if(videoOptional.isPresent()){
            Video video = videoForm.atualizar(id, videoRepository);
            return ResponseEntity.ok(new VideoDto(video));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id){
        Optional<Video> video = videoRepository.findById(id);

        if (video.isPresent()){
            videoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
