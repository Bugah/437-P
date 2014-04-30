package br.unicamp.mc437.client;

import java.util.ArrayList;
import java.util.HashMap;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>SubCategoriaService</code>.
 */
public interface SubCategoriaServiceAsync {
	
	void getSubCategorias(
			AsyncCallback<ArrayList<HashMap<String, String>>> callback);
}
