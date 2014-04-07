package br.unicamp.mc437.client.datatypes;

import java.io.Serializable;

public class TipoStatusCompra  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String statusCompra;
	private String descricao;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStatusCompra() {
		return statusCompra;
	}
	public void setStatusCompra(String statusCompra) {
		this.statusCompra = statusCompra;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
}
