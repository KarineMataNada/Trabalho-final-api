package com.cafeteria.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafeteria.exception.ResourceForbiddenException;
import com.cafeteria.exception.ResourceNotFoundException;
import com.cafeteria.model.Pedidos;
import com.cafeteria.repository.PedidosRepository;



@Service
public class PedidosService {
	


	@Autowired
	private PedidosRepository repositorioPedidos;
	

	public List<Pedidos> obterTodos() {
		return repositorioPedidos.findAll();
	}
	
	
	public Optional<Pedidos> obterPorId(Long id) {
		 Optional<Pedidos> Pedidos = repositorioPedidos.findById(id);	 
		
		 if(Pedidos.isEmpty()) {
			throw new ResourceNotFoundException("Pedidos não encontrado!");
		}
		 
		return Pedidos;
	}
	
	
	public Pedidos adicionar(Pedidos Pedidos) {
		Pedidos.setId(null);

		return repositorioPedidos.save(Pedidos);
		
	}
	
	 public Pedidos atualizar(Pedidos pedidos, Long id) {
		 Optional<Pedidos> pedidosAtualizado = repositorioPedidos.findById(id);
		 
		if(pedidosAtualizado.isEmpty()) {
			throw new ResourceNotFoundException("Pedidos não encontrado por id");
		} if (pedidosAtualizado.get().getStatusFinalizado() == true) {
			throw new ResourceForbiddenException("Acesso negado! Esse pedido ja foi finalizado e não pode mais ser alterado.");
		}
		
		pedidos.setId(id);		
		return repositorioPedidos.save(pedidos);
		
	}


	public void deletar(Long id) {
	    Optional<Pedidos> deletarPedidos = repositorioPedidos.findById(id);
		
	    if(deletarPedidos.isEmpty()) {
			throw new ResourceNotFoundException("Pedidos não encontrado por id");
		}
	    
		repositorioPedidos.deleteById(id);	 
}

}
