package br.unicamp.mc437.client.datatypes;

import java.io.Serializable;

public class CarrinhoComprasElemento implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Produto produto;
	private Integer quantidade;
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	
	
}
