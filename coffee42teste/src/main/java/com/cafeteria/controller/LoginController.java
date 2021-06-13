package com.cafeteria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cafeteria.dto.LoginRequest;
import com.cafeteria.dto.LoginResponse;
import com.cafeteria.service.UsuarioService;

@RestController
@RequestMapping("/api/login")
public class LoginController {

	@Autowired
	private UsuarioService servicoUsuario;
		
	@PostMapping
	public LoginResponse login (@RequestBody LoginRequest request) {		
		return servicoUsuario.logar(request.getUsername(), request.getSenha());
	}
		
}
