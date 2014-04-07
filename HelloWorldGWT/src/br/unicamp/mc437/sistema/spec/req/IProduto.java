package br.unicamp.mc437.sistema.spec.req;
import br.unicamp.mc437.sistema.spec.dt.*;

public interface IProduto{

	public Produto buscarProduto ( String chave ); 
	public Produto buscarPorCategoria ( String categoria ); 
	public boolean cadastrarProduto ( Produto p ); 
}