package com.cafeteria.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cafeteria.model.Pedidos;
import com.cafeteria.model.Usuario;
import com.cafeteria.service.PedidosService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@Api(value = "API REST Caffee Shop 42")
@RestController
@RequestMapping("/api/pedidos")
public class PedidosController {

	
	@Autowired
	private PedidosService servicoPedidos;
		
	@ApiOperation(value = "Retorna toda lista de Pedidoss")
	@GetMapping
	public List<Pedidos> obterTodos() {
		return servicoPedidos.obterTodos();
	}
	
	@ApiOperation(value = "Retorna lista de Pedidoss por id")
	@GetMapping("/{id}")
	public Optional<Pedidos> obterPorId(@PathVariable("id")Long id){
		return servicoPedidos.obterPorId(id);
	}
	
	@ApiOperation(value = "Adiciona pedidoss na lista")
	@PostMapping
	public Pedidos adicionar(@RequestBody Pedidos pedidos, Usuario usuario) {		
		return servicoPedidos.adicionar(pedidos);
	}
	
	@ApiOperation(value = "Atualiza pedidos na lista por id")
	@PutMapping("/{id}")
	public Pedidos atualizar(@PathVariable(value = "id")Long id, @RequestBody Pedidos pedidos){
		return servicoPedidos.atualizar(pedidos, id);
	}
	
	@ApiOperation(value = "Deleta pedidos por id")
	@DeleteMapping("/{id}")
	public void deletar(@PathVariable(value = "id")Long id) {
		servicoPedidos.deletar(id);
	}
		
}
