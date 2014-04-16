package br.unicamp.mc437.client;

import java.util.ArrayList;
import java.util.HashMap;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface SubCategoriaServiceAsync {
//	void greetServer(String input, AsyncCallback<String> callback)
//			throws IllegalArgumentException;
	
	void getSubCategorias(
			AsyncCallback<ArrayList<HashMap<String, String>>> callback);
}
