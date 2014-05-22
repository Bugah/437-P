   
package br.unicamp.mc437.correio.impl;

import java.math.BigDecimal;
import java.rmi.RemoteException;

import org.tempuri.CResultado;
import org.tempuri.CalcPrecoPrazoWSSoapProxy;

import br.unicamp.mc437.correio.spec.prov.ICorreio;


class ICorreioFacade implements ICorreio{

	
	public double valorFrete() {
		return 0;
	} 
	public String obterProtocolo ( int id_compra ){
		return null;
		
	} 
	public String acompanharStatus ( String protocolo ){
		return null;
		
	} 

}