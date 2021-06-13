package com.cafeteria.model;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table
@SequenceGenerator(name = "generator_produto", sequenceName = "sequence_produto", initialValue = 1, allocationSize = 1)
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator_usuario")
	private Long id;
	
	@Column(nullable = false, length = 60)
	private String nome;
	
	@Column(nullable = false, length = 255)
	private String descricao;
	
	@Column(nullable = false, length = 10)
	private Double preco;
	
	@Column(nullable = false, length = 10)
	private Integer quantidade;
	
	//@Column(nullable = false)
	//private Date dataCadastro;
	
	//@Column(nullable = false)
	//private MultipartFile imagem;
	
	//@Column(nullable = false)
	//private Categoria categoria_id;
	
	public Produto() {}
	
	public Produto(Long id, String nome, String descricao, Double preco, Integer quantidade) {

		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
		this.quantidade = quantidade;
	//	this.dataCadastro = dataCadastro;
		//this.imagem = imagem;
		//this.categoria_id = categoria_id;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public Double getPreco() {
		return preco;
	}
	
	public void setPreco(Double preco) {
		this.preco = preco;
	}
	
	public Integer getQuantidade() {
		return quantidade;
	}
	
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	
//	public Date getDataCadastro() {
//		return dataCadastro;
//	}
//	
//	public void setDataCadastro(Date dataCadastro) {
//		this.dataCadastro = dataCadastro;
//	}
//	
//	public MultipartFile getImagem() {
//		return imagem;
//	}
//	
//	public void setImagem(MultipartFile imagem) {
//		this.imagem = imagem;
//	}
//	
////	public Categoria getCategoria_id() {
//		return categoria_id;
//	}
//	
//	public void setCategoria_id(Categoria categoria_id) {
//		this.categoria_id = categoria_id;
//	}
	
	
	
}

