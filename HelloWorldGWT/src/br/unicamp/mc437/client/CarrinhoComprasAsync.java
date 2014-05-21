package br.unicamp.mc437.client;

import java.util.HashMap;
import java.util.List;

import br.unicamp.mc437.client.datatypes.CarrinhoComprasElemento;
import br.unicamp.mc437.client.datatypes.Produto;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface CarrinhoComprasAsync {

	void adicionarProduto(Produto produto, AsyncCallback<Integer> callback);

	void removerProduto(Produto produto, AsyncCallback<Integer> callback);

	void obterCarrinho(AsyncCallback<List<CarrinhoComprasElemento>> callback);

	void esvaziarCarrinho(AsyncCallback<Boolean> callback);

	void initCarShop(AsyncCallback<Void> callback);

}
