   
package br.unicamp.mc437.compra_sistema.impl;

import br.unicamp.mc437.sistema.spec.dt.*;
import br.unicamp.mc437.sistema.spec.req.ICompra;

class ICompraAdapter implements ICompra{
	
	public int criarCompra ( Usuario u, Carrinho c ){
		return 0;
		
	} 
	public boolean validarPagamento ( int id_compra ){
		return true;
		
	} 

}