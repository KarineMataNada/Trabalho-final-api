package com.cafeteria.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafeteria.exception.ResourceNotFoundException;
import com.cafeteria.model.Usuario;
import com.cafeteria.repository.UsuarioRepository;



@Service
public class UsuarioService {
	


	@Autowired
	private UsuarioRepository repositorioUsuario;
	

	public List<Usuario> obterTodos() {
		return repositorioUsuario.findAll();
	}
	
	
	public Optional<Usuario> obterPorId(Long id) {
		 Optional<Usuario> usuario = repositorioUsuario.findById(id);	 
		
		 if(usuario.isEmpty()) {
			throw new ResourceNotFoundException("Usuario não encontrado!");
		}
		 
		return usuario;
	}
	
	

	public List<Usuario> obterPorNome(String nome) {
		List<Usuario> usuario = repositorioUsuario.findByNomeContaining(nome);	
		
		if(usuario.isEmpty()) {
			throw new ResourceNotFoundException("Usuario não encontrado!");
		}
		
		return usuario;
	}
	
	public Usuario adicionar(Usuario usuario) {
		usuario.setId(null);
		
//		if(repositorioUsuario.findByUsername(usuario.getUsername()).isPresent()) {
//			
//		}
		
//		String senha = passwordEnconder.encode(usuario.getSenha());
//		usuario.setSenha(senha);
//		
		return repositorioUsuario.save(usuario);
		
	}
	
	 public Usuario atualizar(Usuario usuario, Long id) {
		 Optional<Usuario> usuarioAtualizado = repositorioUsuario.findById(id);
		 
		if(usuarioAtualizado.isEmpty()) {
			throw new ResourceNotFoundException("Usuario não encontrado por id");
		}
		usuario.setId(id);		
		return repositorioUsuario.save(usuario);
		
	}


	public void deletar(Long id) {
	    Optional<Usuario> deletarUsuario = repositorioUsuario.findById(id);
		
	    if(deletarUsuario.isEmpty()) {
			throw new ResourceNotFoundException("Usuario não encontrado por id");
		}
	    
		repositorioUsuario.deleteById(id);	 
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
//		var usuario = repositorioUsuario.findByUsername(username);
//		
//		return new LoginResponse(token, usuario.get());
//	}
//	

}
