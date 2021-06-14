package com.cafeteria.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cafeteria.model.Endereco;
import com.cafeteria.service.CepService;
import com.cafeteria.service.EnderecoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@CrossOrigin(origins = "*")
@Api(value = "API REST Caffee Shop 42")
@RestController
@RequestMapping("/api/enderecos")
public class EnderecoController {

	@Autowired
	private CepService cepService;
	@Autowired
	private EnderecoService servicoEndereco;
	
	
	@GetMapping("/enderecos/{cep}")
	public ResponseEntity<?> obterEnderecoPorCep(@PathVariable(value = "cep") String cep){
		var endereco = cepService.obterEnderecoPorCep(cep);
		return ResponseEntity.ok(endereco);
	}
		
	@ApiOperation(value = "Retorna toda lista de Enderecos")
	@GetMapping 
	public List<Endereco> obterTodos() {
		return servicoEndereco.obterTodos();
	}
	
	@ApiOperation(value = "Retorna lista de Enderecos por id")
	@GetMapping("/{id}")
	public Optional<Endereco> obterPorId(@PathVariable("id")Long id){
		return servicoEndereco.obterPorId(id);
	}
	
	@ApiOperation(value = "Adiciona Enderecos na lista")
	@PostMapping
	public Endereco adicionar(@RequestBody Endereco Endereco) {		
		return servicoEndereco.adicionar(Endereco);
	}
	
	@ApiOperation(value = "Atualiza Endereco na lista por id")
	@PutMapping("/{id}")
	public Endereco atualizar(@PathVariable(value = "id")Long id, @RequestBody Endereco Endereco){
		return servicoEndereco.atualizar(Endereco, id);
	}
	
	
	
	@ApiOperation(value = "Deleta Endereco por id")
	@DeleteMapping("/{id}")
	public void deletar(@PathVariable(value = "id")Long id) {
		servicoEndereco.deletar(id);
	}
		
}
