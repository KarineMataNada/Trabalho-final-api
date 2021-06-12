package com.cafeteria.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		
//		if(repositorioPedidos.findByUsername(Pedidos.getUsername()).isPresent()) {
//			
//		}
		
//		String senha = passwordEnconder.encode(Pedidos.getSenha());
//		Pedidos.setSenha(senha);
//		
		return repositorioPedidos.save(Pedidos);
		
	}
	
	 public Pedidos atualizar(Pedidos Pedidos, Long id) {
		 Optional<Pedidos> PedidosAtualizado = repositorioPedidos.findById(id);
		 
		if(PedidosAtualizado.isEmpty()) {
			throw new ResourceNotFoundException("Pedidos não encontrado por id");
		}
		Pedidos.setId(id);		
		return repositorioPedidos.save(Pedidos);
		
	}


	public void deletar(Long id) {
	    Optional<Pedidos> deletarPedidos = repositorioPedidos.findById(id);
		
	    if(deletarPedidos.isEmpty()) {
			throw new ResourceNotFoundException("Pedidos não encontrado por id");
		}
	    
		repositorioPedidos.deleteById(id);	 
}
	
//	public LoginResponse logar(String username, String senha) {
//		
//		Authentication autenticacao = authenticationManager.authenticate(
//				new UsernamePasswordAuthenticationToken(username, senha, Collections.emptyList()));
//		
//		SecurityContextHolder.getContext().setAuthentication(autenticacao);
//		
//		String token = headerPrefix + jwtService.gerarToken(autenticacao);
//		
//		var Pedidos = repositorioPedidos.findByUsername(username);
//		
//		return new LoginResponse(token, Pedidos.get());
//	}
//	

}
