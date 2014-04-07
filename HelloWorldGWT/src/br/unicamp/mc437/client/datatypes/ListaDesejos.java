package br.unicamp.mc437.client.datatypes;

import java.io.Serializable;

public class ListaDesejos  implements Serializable{
	

	private static final long serialVersionUID = 1L;
	
	private int id;
	private Cliente cliente;
	private Produto produto;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	
	

}
