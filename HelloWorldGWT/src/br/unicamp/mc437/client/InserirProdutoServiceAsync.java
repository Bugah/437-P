package br.unicamp.mc437.client;

import br.unicamp.mc437.client.datatypes.Produto;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface InserirProdutoServiceAsync {
//	void greetServer(String input, AsyncCallback<String> callback)
//			throws IllegalArgumentException;
	
	void inserirProdutoServer(Produto p, AsyncCallback<String> callback);
}
