package com.cafeteria.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cafeteria.model.Pedidos;

public interface PedidosRepository extends JpaRepository<Pedidos, Long>{

	Optional<Pedidos> findById(Long id);
}
