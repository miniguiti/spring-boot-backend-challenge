package com.spring.challenge.restapijava.dto;

import com.spring.challenge.restapijava.model.Video;
import org.springframework.data.domain.Page;

import java.util.stream.Collectors;

public class VideoDto {
    private Long id;
    private String titulo;
    private String descricao;
    private String url;

    public VideoDto(Video video) {
        this.id = video.getId();
        this.titulo = video.getTitulo();
        this.descricao = video.getDescricao();
        this.url = video.getUrl();
    }

    public static Page<VideoDto> converter(Page<Video> videos) {
        return videos.map(VideoDto::new);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
