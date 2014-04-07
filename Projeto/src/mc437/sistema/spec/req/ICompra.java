package mc437.sistema.spec.req;
import mc437.sistema.spec.dt.*;

public interface ICompra{

	public int criarCompra ( Usuario u, Carrinho c ); 
	public boolean validarPagamento ( int id_compra ); 
}