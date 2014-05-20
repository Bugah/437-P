package br.unicamp.mc437.client;

import java.util.ArrayList;
import java.util.Map;

import br.unicamp.mc437.client.datatypes.Produto;
import br.unicamp.mc437.client.datatypes.SubCategoria;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GreetingServiceAsync {
//	void greetServer(String input, AsyncCallback<String> callback)
//			throws IllegalArgumentException;
	
	void greetServer(Produto p, String where, Map<Integer, String> imagens_resultado, AsyncCallback<ArrayList<Produto>> callback);
	
	void todosProdutos(Produto p, int id_categoria, AsyncCallback<ArrayList<Produto>> callback);
	
	void todasSubCategorias(SubCategoria p, AsyncCallback<ArrayList<SubCategoria>> callback);
	
	void byID(int id_produto, AsyncCallback<Produto> callback);
	
}
