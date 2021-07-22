package com.spring.challenge.restapijava.controller.form;

import com.spring.challenge.restapijava.model.Video;
import com.spring.challenge.restapijava.repository.VideoRepository;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class VideoForm {
    @NotEmpty @NotNull
    private String titulo;

    @NotEmpty @NotNull
    private String descricao;

    @NotEmpty @NotNull
    private String url;

    public Video converter() {
        return new Video(titulo, descricao, url);
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getUrl() {
        return url;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Video atualizar(Long id, VideoRepository videoRepository) {
        Video video = videoRepository.findById(id).get();
        video.setTitulo(titulo);
        video.setDescricao(descricao);
        video.setUrl(url);

        return video;
    }
}
