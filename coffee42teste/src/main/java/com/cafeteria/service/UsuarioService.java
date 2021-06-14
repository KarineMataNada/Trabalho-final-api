package com.cafeteria.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.cafeteria.dto.LoginResponse;
import com.cafeteria.exception.ResourceNotAcceptableException;
//import com.cafeteria.exception.ResourceNotAcceptableException;
import com.cafeteria.exception.ResourceNotFoundException;
import com.cafeteria.model.Endereco;
import com.cafeteria.model.Usuario;
import com.cafeteria.model.email.Mailler;
import com.cafeteria.model.email.MensagemEmail;
import com.cafeteria.repository.UsuarioRepository;
import com.cafeteria.security.JWTService;



@Service
public class UsuarioService {
	

	private static final String headerPrefix = "Coffee ";

	@Autowired
	private UsuarioRepository repositorioUsuario;
	
	@Autowired
	private JWTService jwtService;
	
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEnconder;
    
    @Autowired
    private CepService cepService;
    
    @Autowired
    private WebClient webClient;
	
	@Autowired
	Mailler mailler;

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
		
		
		if(repositorioUsuario.findByUsername(usuario.getUsername()).isPresent()) {
			throw new ResourceNotAcceptableException("Usuario já existe");
	}
		
		
		Endereco endereco = cepService.obterEnderecoPorCep(usuario.getEndereco().getCep());
		usuario.setEndereco(endereco);
		String senha = passwordEnconder.encode(usuario.getSenha());
		usuario.setSenha(senha);
		
		
		//email do cadastro 
		this.repositorioUsuario.save(usuario);
		
		
		var mensagem = "<!DOCTYPE html><html lang=\"en\"><head><meta charset=\"UTF-8\"><meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\"><meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"><title>Document</title></head><body><div style=\"text-align: center;\"><h1 style=\"color:blueviolet;\">%s</h1><p style=\"font-size: 20px;\" ><em>Você foi cadastrado ;)</em></p> <a href=\"http://serratec.org/\" target=\"_blank\"> <img src=\"https://mega.ibxk.com.br/2018/07/27/cachorro-se-cocando-27124201260112.gif?ims=610x\" alt=\"Serratec\"> </a></div></body></html>";
		mensagem  = String.format(mensagem, usuario.getNome());
		
		var funcionario = "Bernard <serratecdev@gmail.com>";
				
		var email = new MensagemEmail(
				"Cadastro realizado com sucesso!", 
				mensagem,
				funcionario,
				Arrays.asList(usuario.getEmail()));
		
		mailler.enviar(email);
		
	
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
	
	public LoginResponse logar(String username, String senha) {
		
		Authentication autenticacao = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(username, senha, Collections.emptyList()));
		
		SecurityContextHolder.getContext().setAuthentication(autenticacao);
		
		String token = headerPrefix + jwtService.gerarToken(autenticacao);
		
		var usuario = repositorioUsuario.findByUsername(username);
		
		return new LoginResponse(token, usuario.get());
	}
	
}

