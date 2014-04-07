package br.unicamp.mc437.client.datatypes;

import java.io.Serializable;

public class ValeCompra  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private int codigoValeCompra;
	private int status_uso;
	private Cliente cliente;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCodigoValeCompra() {
		return codigoValeCompra;
	}
	public void setCodigoValeCompra(int codigoValeCompra) {
		this.codigoValeCompra = codigoValeCompra;
	}
	public int getStatus_uso() {
		return status_uso;
	}
	public void setStatus_uso(int status_uso) {
		this.status_uso = status_uso;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	
}
