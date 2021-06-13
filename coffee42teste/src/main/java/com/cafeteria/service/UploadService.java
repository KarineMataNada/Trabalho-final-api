package com.cafeteria.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cafeteria.exception.ResourceNotFoundException;
import com.cafeteria.model.Produto;
import com.cafeteria.repository.ProdutoRepository;



@Service
public class UploadService {
	
	@Autowired
	private ProdutoService servicoProduto;
	@Autowired
	private ProdutoRepository repositorioProduto;

	
	@Value("${caminho.raiz}")
	private String raiz;

	public void salvar(String caminho, MultipartFile arquivo, Long id) {
		
		Optional<Produto> produto = repositorioProduto.findById(id);
		
		if(produto.isEmpty()) {
			throw new ResourceNotFoundException("Produto não encontrado!");
		}
	
		Path diretorio = Paths.get(this.raiz, caminho);
		String imagem = diretorio.toString();
		
		produto.get().setImagem(imagem);
		
		servicoProduto.atualizar(produto.get(),id);	
		
		Path arquivoPath = diretorio.resolve(arquivo.getOriginalFilename());
		
		try {
			
			Files.createDirectories(diretorio);
			arquivo.transferTo(arquivoPath.toFile());
		} catch (Exception e) {
			throw new RuntimeException("Falha ao buscar");
			
		}
	}
}