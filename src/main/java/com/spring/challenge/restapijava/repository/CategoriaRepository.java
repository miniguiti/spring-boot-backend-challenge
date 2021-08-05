package com.spring.challenge.restapijava.repository;

import com.spring.challenge.restapijava.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
