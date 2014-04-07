package mc437.software.spec.prov;
import mc437.software.spec.dt.*;

public interface IBuscarProduto{

	public Produto buscarPorNome ( String nome ); 
	public Produto buscarPorCategoria ( String categoria ); 
}