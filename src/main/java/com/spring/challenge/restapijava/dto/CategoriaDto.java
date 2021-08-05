package com.spring.challenge.restapijava.dto;

import com.spring.challenge.restapijava.model.Categoria;
import org.springframework.data.domain.Page;

public class CategoriaDto {
    private Long id;
    private String titulo;
    private String cor;

    public CategoriaDto(Categoria categoria) {
        this.id = categoria.getId();
        this.titulo = categoria.getTitulo();
        this.cor = categoria.getTitulo();
    }

    public static Page<CategoriaDto> converter(Page<Categoria> categorias) {
        return categorias.map(CategoriaDto::new);
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

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }
}
