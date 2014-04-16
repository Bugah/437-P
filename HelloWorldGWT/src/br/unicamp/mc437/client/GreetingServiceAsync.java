package br.unicamp.mc437.client;

import java.util.ArrayList;

import br.unicamp.mc437.client.datatypes.Produto;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GreetingServiceAsync {
//	void greetServer(String input, AsyncCallback<String> callback)
//			throws IllegalArgumentException;
	
	void greetServer(Produto p, String where, String[][] imagens_resultado, AsyncCallback<ArrayList<Produto>> callback);
}
