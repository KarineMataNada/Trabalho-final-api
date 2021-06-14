package com.cafeteria.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cafeteria.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
		
	Optional<Produto> findById(Long id);
	List<Produto> findByNomeContaining(String nome);
}
