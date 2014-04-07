   
package br.unicamp.mc437.compra.impl;

import br.unicamp.mc437.compra.spec.dt.*;
import br.unicamp.mc437.compra.spec.prov.ICompra;

class ICompraFacade implements ICompra{

	public int criarCompra ( Usuario u, Carrinho c ){
		return 0;
		
	} 
	public boolean validarPagamento ( int id_compra ){
		return true;
		
	} 

}