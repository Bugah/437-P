package br.unicamp.mc437.client.datatypes;

import java.io.Serializable;

/**
 * this class will be sent from the client to the server
 * */
public class Student implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String name;
	private Integer id;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
 }
