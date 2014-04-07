package br.unicamp.mc437.sistema.spec.req;
import br.unicamp.mc437.sistema.spec.dt.*;

public interface IPromocao{

	public boolean cadastraProm ( Produto p, String promocao ); 
	public boolean alteraProm ( Produto p ); 
}