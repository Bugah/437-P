package mc437.produto.spec.prov;
import mc437.produto.spec.dt.*;

public interface IProduto{

	public Produto buscarProduto ( String chave ); 
	public Produto buscarPorCategoria ( String categoria ); 
	public boolean cadastrarProduto ( Produto p ); 
}