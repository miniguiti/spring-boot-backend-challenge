package com.spring.challenge.restapijava.controller.form;

import com.spring.challenge.restapijava.model.Categoria;
import com.spring.challenge.restapijava.model.Video;
import com.spring.challenge.restapijava.repository.CategoriaRepository;
import com.spring.challenge.restapijava.repository.VideoRepository;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

public class VideoForm {
    @NotEmpty @NotNull
    private String titulo;

    @NotEmpty @NotNull
    private String descricao;

    @NotEmpty @NotNull
    private String url;

    private Long categoriaId;

    public Video converter(CategoriaRepository categoriaRepository) {
        Long idCategoria = Objects.isNull(this.categoriaId) ? 1 : this.categoriaId;
        Categoria categoria = categoriaRepository.findById(idCategoria).get();

        return new Video(this.titulo, this.descricao, this.url, categoria);
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

    public Long getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Long categoriaId) {
        this.categoriaId = categoriaId;
    }

    public Video atualizar(Long id, VideoRepository videoRepository) {
        Video video = videoRepository.findById(id).get();
        video.setTitulo(titulo);
        video.setDescricao(descricao);
        video.setUrl(url);

        return video;
    }
}
