package br.unicamp.mc437.client.datatypes;

import java.io.Serializable;

public class ImagensProdutos  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private Imagem imagem;
	private Produto produto;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Imagem getImagem() {
		return imagem;
	}
	public void setImagem(Imagem imagem) {
		this.imagem = imagem;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	
}
