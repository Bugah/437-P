   
package mc437.compra_sistema.impl;

import mc437.sistema.spec.req.ICompra;
import mc437.sistema.spec.dt.*;

class ICompraAdapter implements ICompra{
	
	public int criarCompra ( Usuario u, Carrinho c ){
		return 0;
		
	} 
	public boolean validarPagamento ( int id_compra ){
		return true;
		
	} 

}