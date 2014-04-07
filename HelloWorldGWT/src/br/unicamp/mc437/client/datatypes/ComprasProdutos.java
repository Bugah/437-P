package br.unicamp.mc437.client.datatypes;

import java.io.Serializable;

public class ComprasProdutos  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private Compras compra;
	private Produto produto;
	private int qtd;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Compras getCompra() {
		return compra;
	}
	public void setCompra(Compras compra) {
		this.compra = compra;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public int getQtd() {
		return qtd;
	}
	public void setQtd(int qtd) {
		this.qtd = qtd;
	}
	
	
}
