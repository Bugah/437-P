package br.unicamp.mc437.sistema.spec.req;


public interface ICorreio{

	public float valorFrete (  ); 
	public String obterProtocolo ( int id_compra ); 
	public String acompanharStatus ( String protocolo ); 
}