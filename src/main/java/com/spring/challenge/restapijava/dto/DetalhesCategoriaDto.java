package com.spring.challenge.restapijava.dto;

import com.spring.challenge.restapijava.model.Categoria;
import com.spring.challenge.restapijava.model.Video;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DetalhesCategoriaDto {
    private Long id;
    private String titulo;
    private String cor;
    private List<VideoDto> videos;

    public DetalhesCategoriaDto(Categoria categoria) {
        this.id = categoria.getId();
        this.titulo = categoria.getTitulo();
        this.cor = categoria.getCor();
        this.videos = new ArrayList<>();
        this.videos.addAll(categoria.getVideos().stream().map(VideoDto::new).collect(Collectors.toList()));
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getCor() {
        return cor;
    }

    public List<VideoDto> getVideos() {
        return videos;
    }
}
