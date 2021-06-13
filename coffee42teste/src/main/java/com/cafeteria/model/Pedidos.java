package com.cafeteria.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "Pedidos")
@SequenceGenerator(name = "generator_pedido", sequenceName = "sequence_pedido", initialValue = 1, allocationSize = 1)
public class Pedidos {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator_pedido")
	private Long id;
	
	private String numeroDoPedido;
	
	//vai se conectar a produtos pedidos
	private String listaDeProdudoDoPedido;
	
	private String valorTotalDoPedido;
	
	//private Date dataDoPedido;
	
	private boolean statusFinalizado;
	
	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;
	
	@ManyToMany
	@JoinTable(name="Peditos_Produtos", 
			   joinColumns = @JoinColumn(name = "pedidos_id"), 
			   inverseJoinColumns = @JoinColumn(name = "produto_id"))
	private List<Produto> produtos;
	


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumeroDoPedido() {
		return numeroDoPedido;
	}

	public void setNumeroDoPedido(String numeroDoPedido) {
		this.numeroDoPedido = numeroDoPedido;
	}

	public String getListaDeProdudoDoPedido() {
		return listaDeProdudoDoPedido;
	}

	public void setListaDeProdudoDoPedido(String listaDeProdudoDoPedido) {
		this.listaDeProdudoDoPedido = listaDeProdudoDoPedido;
	}

	public String getValorTotalDoPedido() {
		return valorTotalDoPedido;
	}

	public void setValorTotalDoPedido(String valorTotalDoPedido) {
		this.valorTotalDoPedido = valorTotalDoPedido;
	}

	public boolean isStatusFinalizado() {
		return statusFinalizado;
	}

	public void setStatusFinalizado(boolean statusFinalizado) {
		this.statusFinalizado = statusFinalizado;
	}

//	public Date getDataDoPedido() {
//		return dataDoPedido;
//	}
//
//	public void setDataDoPedido(Date dataDoPedido) {
//		this.dataDoPedido = dataDoPedido;
//	}



	
	
}

