package com.cafeteria.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafeteria.exception.ResourceNotFoundException;
import com.cafeteria.exception.ResourceUnprocessableEntityException;
import com.cafeteria.model.Produto;
import com.cafeteria.repository.ProdutoRepository;

@Service
public class ProdutoService {


	@Autowired
	private ProdutoRepository repositorioProduto;


	public List<Produto> obterTodos() {
		return repositorioProduto.findAll();
	}

	public Optional<Produto> obterPorId(Long id) {
		Optional<Produto> produto = repositorioProduto.findById(id);

		if (produto.isEmpty()) {
			throw new ResourceNotFoundException("Produto não encontrado!");
		}

		return produto;
	}

	public List<Produto> obterPorNome(String nome) {
	List<Produto> produto = repositorioProduto.findByNomeContaining(nome);
		if (produto.isEmpty()) {
			throw new ResourceNotFoundException("Produto não encontrado!");
		}

		return produto;
	}
	

	public Produto adicionar(Produto produto) {
		produto.setId(null);
		
		if(produto.getPreco() < -1) {
			throw new ResourceUnprocessableEntityException("Produto não pode ser negativo");
		}
		
		return repositorioProduto.save(produto);

	}

	public Produto atualizar(Produto Produto, Long id) {
		Optional<Produto> produtoAtt = repositorioProduto.findById(id);

		if (produtoAtt.isEmpty()) {
			throw new ResourceNotFoundException("Produto não encontrado por id");
		}
		produtoAtt.get().setId(id);
		return repositorioProduto.save(Produto);

	}

	public void deletar(Long id) {
		Optional<Produto> deletarProduto = repositorioProduto.findById(id);

		if (deletarProduto.isEmpty()) {
			throw new ResourceNotFoundException("Produto não encontrado por id");
		}

		repositorioProduto.deleteById(id);
	}

}