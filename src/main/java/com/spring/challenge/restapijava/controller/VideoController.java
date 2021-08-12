package com.spring.challenge.restapijava.controller;

import com.spring.challenge.restapijava.controller.form.VideoForm;
import com.spring.challenge.restapijava.dto.DetalhesVideoDto;
import com.spring.challenge.restapijava.dto.VideoDto;
import com.spring.challenge.restapijava.model.Categoria;
import com.spring.challenge.restapijava.model.Video;
import com.spring.challenge.restapijava.repository.CategoriaRepository;
import com.spring.challenge.restapijava.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/videos")
public class VideoController {
    @Autowired
    VideoRepository videoRepository;

    @Autowired
    CategoriaRepository categoriaRepository;

    @GetMapping
    public Page<VideoDto> listar(@RequestParam(required = false) String nomeVideo,
                                 @PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10) Pageable pageable) {
        if (nomeVideo == null) {
            Page<Video> videos = videoRepository.findAll(pageable);
            return VideoDto.converter(videos);
        } else {
            Page<Video> videos = videoRepository.findByTitulo(nomeVideo, pageable);
            return VideoDto.converter(videos);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhesVideoDto> buscarVideo(@PathVariable Long id) {
        Optional<Video> video = videoRepository.findById(id);
        if (video.isPresent()) {
            return ResponseEntity.ok(new DetalhesVideoDto(video.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/free")
    public Page<VideoDto> buscarVideosFree(@PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10) Pageable pageable) {
        Page<Video> videos = videoRepository.findByIdLessThan(3L, pageable);
        return VideoDto.converter(videos);
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody @Valid VideoForm videoForm,
                                       UriComponentsBuilder uriComponentsBuilder) {
        Video video = videoForm.converter(categoriaRepository);
        videoRepository.save(video);

        URI uri = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(video.getId()).toUri();
        return ResponseEntity.created(uri).body(new VideoDto(video));

    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<VideoDto> atualizarVideo(@RequestBody @Valid VideoForm videoForm,
                                                   @PathVariable Long id) {
        Optional<Video> videoOptional = videoRepository.findById(id);
        if (videoOptional.isPresent()) {
            Video video = videoForm.atualizar(id, videoRepository);
            return ResponseEntity.ok(new VideoDto(video));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        Optional<Video> video = videoRepository.findById(id);

        if (video.isPresent()) {
            videoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
