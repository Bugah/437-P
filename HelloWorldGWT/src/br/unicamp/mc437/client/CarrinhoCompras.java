package br.unicamp.mc437.client;

import java.util.List;

import br.unicamp.mc437.client.datatypes.CarrinhoComprasElemento;
import br.unicamp.mc437.client.datatypes.Produto;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("carrinhoCompras")
public interface CarrinhoCompras extends RemoteService{
	
	boolean adicionarProduto(Produto produto);
	List<CarrinhoComprasElemento> obterCarrinho();
	boolean esvaziarCarrinho();
	boolean removerProduto(Produto produto);

}
