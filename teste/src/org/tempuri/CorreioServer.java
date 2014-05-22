package org.tempuri;

import java.math.BigDecimal;
import java.rmi.RemoteException;

public class CorreioServer {
	String cepOrigem ;
	String cepDestino;
	String codServico;
	
	// Devemos mudar aqui se quisermos redimensionar a embalagem 
	BigDecimal x = BigDecimal.valueOf(20);
	
	public void setCepOrigem(String c){
		this.cepOrigem = c;	
	}
	
	public String getCepOrigem(String c){
		return this.cepOrigem;	
	}
	
	public void setCepDestino(String c){
		this.cepDestino = c;
	}
	
	public String getCepDestino(String c){
		return this.cepDestino;
	}
	
	public void setCodServico(String c){
		this.codServico = c;
	}
	
	public String getCodServico(String c){
		return this.codServico;
	}
	
	
	public double valorFrete() {
		CalcPrecoPrazoWSSoapProxy wc = new CalcPrecoPrazoWSSoapProxy();
		CResultado r = new CResultado();
		
		try {
			// Calcula Frete
			r = wc.calcPreco("", "", codServico, cepOrigem, cepDestino, "1" /*Peso em Kg*/, 1, x, x, x, x, "N", BigDecimal.ZERO, "N");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String preco = r.getServicos()[0].getValor();
		preco = preco.replace(',', '.');
		return new Float(preco).doubleValue();
	} 
}
