package mc437.sistema.spec.prov;
import mc437.sistema.spec.dt.*;

public interface IBuscarProduto{

	public Produto buscarPorNome ( String nome ); 
	public Produto buscarPorCategoria ( String categoria ); 
}