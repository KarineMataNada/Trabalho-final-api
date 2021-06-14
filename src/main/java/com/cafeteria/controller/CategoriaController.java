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

import com.cafeteria.model.Categoria;
import com.cafeteria.service.CategoriaService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

	@Autowired
	private CategoriaService servicoCategoria;
		
	@ApiOperation(value = "Retorna toda lista de Categorias")
	@GetMapping
	public List<Categoria> obterTodos() {
		return servicoCategoria.obterTodos();
	}
	
	@ApiOperation(value = "Retorna lista de Categorias por id")
	@GetMapping("/{id}")
	public Optional<Categoria> obterPorId(@PathVariable("id")Long id){
		return servicoCategoria.obterPorId(id);
	}
	
	@ApiOperation(value = "Retorna lista de Categorias por nome")
	@GetMapping("/nome/{nome}")
	public List<Categoria> obterPorNome(@PathVariable("nome")String nome){
		return servicoCategoria.obterPorNome(nome);
	}
	
	@ApiOperation(value = "Adiciona Categorias na lista")
	@PostMapping
	public Categoria adicionar(@RequestBody Categoria Categoria) {		
		return servicoCategoria.adicionar(Categoria);
	}
	
	@ApiOperation(value = "Atualiza Categoria na lista por id")
	@PutMapping("/{id}")
	public Categoria atualizar(@PathVariable(value = "id")Long id, @RequestBody Categoria Categoria){
		return servicoCategoria.atualizar(Categoria, id);
	}
	
	@ApiOperation(value = "Deleta Categoria por id")
	@DeleteMapping("/{id}")
	public void deletar(@PathVariable(value = "id")Long id) {
		servicoCategoria.deletar(id);
	}
	
}
