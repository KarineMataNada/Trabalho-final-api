package com.cafeteria.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafeteria.exception.ResourceNotFoundException;
import com.cafeteria.model.Categoria;
import com.cafeteria.repository.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repositorioCategoria;
	

	public List<Categoria> obterTodos() {
		return repositorioCategoria.findAll();
	}
	
	
	public Optional<Categoria> obterPorId(Long id) {
		 Optional<Categoria> Categoria = repositorioCategoria.findById(id);	 
		
		 if(Categoria.isEmpty()) {
			throw new ResourceNotFoundException("Categoria não encontrado!");
		}
		 
		return Categoria;
	}
	
	public List<Categoria> obterPorNome(String nome) {
		List<Categoria> categoria = repositorioCategoria.findByNomeContaining(nome);	
		
		if(categoria.isEmpty()) {
			throw new ResourceNotFoundException("Usuario não encontrado!");
		}
		
		return categoria;
	}
	
	public Categoria adicionar(Categoria Categoria) {
		Categoria.setId(null);
			
		return repositorioCategoria.save(Categoria);
		
	}
	
	 public Categoria atualizar(Categoria Categoria, Long id) {
		 Optional<Categoria> CategoriaAtualizado = repositorioCategoria.findById(id);
		 
		if(CategoriaAtualizado.isEmpty()) {
			throw new ResourceNotFoundException("Categoria não encontrado por id");
		}
		Categoria.setId(id);		
		return repositorioCategoria.save(Categoria);
		
	}


	public void deletar(Long id) {
	    Optional<Categoria> deletarCategoria = repositorioCategoria.findById(id);
		
	    if(deletarCategoria.isEmpty()) {
			throw new ResourceNotFoundException("Categoria não encontrado por id");
		}
	    
		repositorioCategoria.deleteById(id);	 
	}

}
