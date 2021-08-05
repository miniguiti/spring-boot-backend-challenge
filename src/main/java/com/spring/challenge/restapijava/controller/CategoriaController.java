package com.spring.challenge.restapijava.controller;

import com.spring.challenge.restapijava.controller.form.CategoriaForm;
import com.spring.challenge.restapijava.dto.CategoriaDto;
import com.spring.challenge.restapijava.dto.DetalhesCategoriaDto;
import com.spring.challenge.restapijava.model.Categoria;
import com.spring.challenge.restapijava.repository.CategoriaRepository;
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
import java.util.Optional;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    CategoriaRepository categoriaRepository;

    @GetMapping
    public Page<CategoriaDto> listar(@PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10) Pageable pageable){
        Page<Categoria> categorias = categoriaRepository.findAll(pageable);
        return CategoriaDto.converter(categorias);
    }

    @GetMapping("{id}")
    public ResponseEntity<CategoriaDto> buscarPorId(@PathVariable Long id){
        Optional<Categoria> categoriaOptional = categoriaRepository.findById(id);
        if (categoriaOptional.isPresent()){
            return ResponseEntity.ok(new CategoriaDto(categoriaOptional.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody @Valid CategoriaForm categoriaForm, UriComponentsBuilder uriComponentsBuilder){
        Categoria categoria = categoriaForm.converter();
        categoriaRepository.save(categoria);

        URI uri = uriComponentsBuilder.path("/categorias/{id}").buildAndExpand(categoria.getId()).toUri();
        return ResponseEntity.created(uri).body(new CategoriaDto(categoria));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<CategoriaDto> atualizarVideo(@RequestBody @Valid CategoriaForm categoriaForm,
                                                   @PathVariable Long id){
        Optional<Categoria> categoriaOptional = categoriaRepository.findById(id);
        if(categoriaOptional.isPresent()){
            Categoria categoria = categoriaForm.atualizar(id, categoriaRepository);
            return ResponseEntity.ok(new CategoriaDto(categoria));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id){
        Optional<Categoria> categoria = categoriaRepository.findById(id);

        if (categoria.isPresent()){
            categoriaRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("{id}/videos")
    public ResponseEntity<DetalhesCategoriaDto> buscarVideosPorCategoria(@PathVariable Long id){
        Optional<Categoria> categoriaOptional = categoriaRepository.findById(id);
        System.out.println("categoria " + categoriaOptional.get().getVideos());
        if (categoriaOptional.isPresent()){
            return ResponseEntity.ok(new DetalhesCategoriaDto(categoriaOptional.get()));
        }
        return ResponseEntity.notFound().build();
    }
}
