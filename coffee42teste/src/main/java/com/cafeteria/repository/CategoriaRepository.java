package com.cafeteria.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cafeteria.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

	Optional<Categoria> findById(Long id);
}
