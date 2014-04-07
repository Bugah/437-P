package br.unicamp.mc437.compra.spec.prov;
import br.unicamp.mc437.compra.spec.dt.*;

public interface ICompra{

	public int criarCompra ( Usuario u, Carrinho c ); 
	public boolean validarPagamento ( int id_compra ); 
}