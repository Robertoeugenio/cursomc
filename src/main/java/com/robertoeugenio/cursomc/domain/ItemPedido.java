package com.robertoeugenio.cursomc.domain;
//essa classe nao tem id porque ela é uma classe associativa 

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ItemPedido implements Serializable {
	
	private static final long serialVersionUID = 1L;

	

	@JsonIgnore // vai ser ignorado tudo que bem do pedido pk
	@EmbeddedId //chave composta
	private ItemPedidoPk id = new ItemPedidoPk(); // instanciando esse id é composto
	private Double desconto;
	private Integer quantidade;
	private Double preco;

	
	public ItemPedido() {
	}

	public ItemPedido(Pedido pedido, Produto produto, Double desconto, Integer quantidade, Double preco) {
		super();
		id.setPedido(pedido);// id dentro do pedido
		id.setProduto(produto); // id dentro do produto
		this.desconto = desconto;
		this.quantidade = quantidade;
		this.preco = preco;
	}
     public double getSubTotal() {
		
		return(preco - desconto) * quantidade;
     }
	@JsonIgnore
	public Pedido getPedido() { // para ter acesso direto ao conteudo fora da classe
		return id.getPedido();
	}

	public void setPedido(Pedido pedido) {
		id.setPedido(pedido);
	}
	
	public Produto getProduto() { // para ter acesso direto ao conteudo fora da classe
		return id.getProduto();
	}
	
	public void setProduto(Produto produto) {
		id.setProduto(produto);
	}

	public ItemPedidoPk getId() {
		return id;
	}

	public void setId(ItemPedidoPk id) {
		this.id = id;
	}

	public Double getDesconto() {
		return desconto;
	}

	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemPedido other = (ItemPedido) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
