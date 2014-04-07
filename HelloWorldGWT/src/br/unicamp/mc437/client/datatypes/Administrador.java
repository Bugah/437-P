package br.unicamp.mc437.client.datatypes;

import java.io.Serializable;

public class Administrador  implements Serializable {


	private static final long serialVersionUID = 1L;
	
	private int id;
	private String nomeAdm;
	private String emailAdm;
	private String senhaAdm;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNomeAdm() {
		return nomeAdm;
	}
	public void setNomeAdm(String nomeAdm) {
		this.nomeAdm = nomeAdm;
	}
	public String getEmailAdm() {
		return emailAdm;
	}
	public void setEmailAdm(String emailAdm) {
		this.emailAdm = emailAdm;
	}
	public String getSenhaAdm() {
		return senhaAdm;
	}
	public void setSenhaAdm(String senhaAdm) {
		this.senhaAdm = senhaAdm;
	}
	
	

}
