package com.cafeteria.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cafeteria.model.Usuario;
import com.cafeteria.service.UsuarioService;


//@CrossOrigin(origins = "*")
//@Api(value = "API REST Caffee Shop 42")
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

	
	@Autowired
	private UsuarioService servicoUsuario;
		
	//@ApiOperation(value = "Retorna toda lista de Usuarios")
	@GetMapping
	public List<Usuario> obterTodos() {
		return servicoUsuario.obterTodos();
	}
	
	//@ApiOperation(value = "Retorna lista de Usuarios por id")
	@GetMapping("/{id}")
	public Optional<Usuario> obterPorId(@PathVariable("id")Long id){
		return servicoUsuario.obterPorId(id);
	}
	
	//@ApiOperation(value = "Retorna lista de Usuarios por nome")
	@GetMapping("/nome/{nome}")
	public List<Usuario> obterPorNome(@PathVariable("nome")String nome){
		return servicoUsuario.obterPorNome(nome);
	}
	
	//@ApiOperation(value = "Adiciona usuarios na lista")
	@PostMapping
	public Usuario adicionar(@RequestBody Usuario usuario) {		
		return servicoUsuario.adicionar(usuario);
	}
	
	//@ApiOperation(value = "Atualiza usuario na lista por id")
	@PutMapping("/{id}")
	public Usuario atualizar(@PathVariable(value = "id")Long id, @RequestBody Usuario usuario){
		return servicoUsuario.atualizar(usuario, id);
	}
	
	//@ApiOperation(value = "Deleta usuario por id")
	@DeleteMapping("/{id}")
	public void deletar(@PathVariable(value = "id")Long id) {
		servicoUsuario.deletar(id);
	}
		
}
