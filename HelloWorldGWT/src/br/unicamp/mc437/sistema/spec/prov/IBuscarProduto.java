package br.unicamp.mc437.sistema.spec.prov;
import br.unicamp.mc437.sistema.spec.dt.*;

public interface IBuscarProduto{

	public Produto buscarPorNome ( String nome ); 
	public Produto buscarPorCategoria ( String categoria ); 
}