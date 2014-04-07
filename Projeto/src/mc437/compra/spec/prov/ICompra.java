package mc437.compra.spec.prov;
import mc437.compra.spec.dt.*;

public interface ICompra{

	public int criarCompra ( Usuario u, Carrinho c ); 
	public boolean validarPagamento ( int id_compra ); 
}