   
package br.unicamp.mc437.correio_sistema.impl;

import br.unicamp.mc437.sistema.spec.req.ICorreio;


class ICorreioAdapter implements ICorreio{

	public float valorFrete (  ){
		return 0;
		
	} 
	public String obterProtocolo ( int id_compra ){
		return null;
		
	} 
	public String acompanharStatus ( String protocolo ){
		return null;
		
	} 

}