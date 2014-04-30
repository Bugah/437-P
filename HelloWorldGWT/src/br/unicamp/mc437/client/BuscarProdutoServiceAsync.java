package br.unicamp.mc437.client;

import java.util.ArrayList;
import java.util.Map;

import br.unicamp.mc437.client.datatypes.Produto;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>BuscarProdutoService</code>.
 */
public interface BuscarProdutoServiceAsync {
//	void greetServer(String input, AsyncCallback<String> callback)
//			throws IllegalArgumentException;
	
	void greetServer(Produto p, String where, Map<Integer, String> imagens_resultado, AsyncCallback<ArrayList<Produto>> callback);
}
