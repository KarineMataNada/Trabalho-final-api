package com.cafeteria.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cafeteria.model.Produto;
import com.cafeteria.service.ProdutoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@Api(value = "API REST Caffee Shop 42")
@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

	
	@Autowired
	private ProdutoService servicoProduto;
		
	@ApiOperation(value = "Retorna toda lista de Produtos")
	@GetMapping
	public List<Produto> obterTodos() {
		return servicoProduto.obterTodos();
	}
	
	@ApiOperation(value = "Retorna lista de Produtos por id")
	@GetMapping("/{id}")
	public Optional<Produto> obterPorId(@PathVariable(value = "id")Long id){
		return servicoProduto.obterPorId(id);
	}
	
	@ApiOperation(value = "Retorna lista de Produtos por nome")
	@GetMapping("nome/{nome}")
	public List<Produto> obterPorNome(@PathVariable(value = "nome")String nome){
		return servicoProduto.obterPorNome(nome);
	}
	
	@ApiOperation(value = "Adiciona Produtos na lista")
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public Produto adicionar(@RequestBody Produto Produto) {		
		return servicoProduto.adicionar(Produto);
	}
	
	@ApiOperation(value = "Atualiza Produto na lista por id")
	@PutMapping("/{id}")
	public Produto atualizar(@PathVariable(value = "id")Long id, @RequestBody Produto Produto){
		return servicoProduto.atualizar(Produto, id);
	}
	
	@ApiOperation(value = "Deleta Produto por id")
	@DeleteMapping("/{id}")
	public void deletar(@PathVariable(value = "id")Long id) {
		servicoProduto.deletar(id);
	}
		
}
