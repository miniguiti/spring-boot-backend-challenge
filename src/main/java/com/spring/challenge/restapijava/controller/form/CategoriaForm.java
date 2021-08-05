package com.spring.challenge.restapijava.controller.form;

import com.spring.challenge.restapijava.model.Categoria;
import com.spring.challenge.restapijava.repository.CategoriaRepository;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CategoriaForm {
    @NotEmpty @NotNull
    private String titulo;

    @NotEmpty @NotNull
    private String cor;

    public Categoria converter(){
        return new Categoria(titulo, cor);
    }

    public CategoriaForm(String titulo, String cor) {
        this.titulo = titulo;
        this.cor = cor;
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

    public Categoria atualizar(Long id, CategoriaRepository categoriaRepository) {
        Categoria categoria = categoriaRepository.findById(id).get();
        categoria.setTitulo(this.titulo);
        categoria.setCor(this.cor);

        return categoria;
    }
}
