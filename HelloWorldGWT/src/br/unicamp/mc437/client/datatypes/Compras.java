package br.unicamp.mc437.client.datatypes;

import java.io.Serializable;
import java.util.Date;

public class Compras  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private Cliente cliente;
	private double total;
	private TipoStatusCompra statusCompra;
	private Date data;
	private String codigoRastreio;
	private String protocoloMoip;
	private Double valorFrete;
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
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public TipoStatusCompra getStatusCompra() {
		return statusCompra;
	}
	public void setStatusCompra(TipoStatusCompra statusCompra) {
		this.statusCompra = statusCompra;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public String getCodigoRastreio() {
		return codigoRastreio;
	}
	public void setCodigoRastreio(String codigoRastreio) {
		this.codigoRastreio = codigoRastreio;
	}
	public String getProtocoloMoip() {
		return protocoloMoip;
	}
	public void setProtocoloMoip(String protocoloMoip) {
		this.protocoloMoip = protocoloMoip;
	}
	public Double getValorFrete() {
		return valorFrete;
	}
	public void setValorFrete(Double valorFrete) {
		this.valorFrete = valorFrete;
	}
		
}
