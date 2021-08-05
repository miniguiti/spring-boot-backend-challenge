package com.spring.challenge.restapijava.dto;

import com.spring.challenge.restapijava.model.Categoria;
import com.spring.challenge.restapijava.model.Video;


public class DetalhesVideoDto {
    private Long id;
    private String titulo;
    private String descricao;
    private String url;
    private CategoriaDto categoria;

    public DetalhesVideoDto(Video video) {
        this.id = video.getId();
        this.categoria = new CategoriaDto(video.getCategoria());
        this.titulo = video.getTitulo();
        this.descricao = video.getDescricao();
        this.url = video.getUrl();
    }

    public Long getId() {
        return id;
    }

    public CategoriaDto getCategoria() {
        return categoria;
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
}
