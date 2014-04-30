package br.unicamp.mc437.client;

import br.unicamp.mc437.client.datatypes.Produto;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>AdicionarAoCarrinhoService</code>.
 */
public interface AdicionarAoCarrinhoServiceAsync {
	
	void adicionar(AsyncCallback<Boolean> callback);
}
