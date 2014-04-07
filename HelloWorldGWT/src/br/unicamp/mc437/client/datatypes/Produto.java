package br.unicamp.mc437.client.datatypes;

import java.io.Serializable;

public class Produto  implements Serializable{


	private static final long serialVersionUID = 1L;
	
	private int id;
	private String nome;
	private String descricao;
	private double preco;
	private int estoque;
	private double precoPromocional;
	private Administrador admin;
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	public int getEstoque() {
		return estoque;
	}
	public void setEstoque(int estoque) {
		this.estoque = estoque;
	}
	public double getPrecoPromocional() {
		return precoPromocional;
	}
	public void setPrecoPromocional(double precoPromocional) {
		this.precoPromocional = precoPromocional;
	}
	public Administrador getAdmin() {
		return admin;
	}
	public void setAdmin(Administrador admin) {
		this.admin = admin;
	}
	
	

}
