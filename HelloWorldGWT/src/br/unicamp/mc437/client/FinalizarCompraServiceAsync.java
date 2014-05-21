package br.unicamp.mc437.client;

import java.util.ArrayList;
import java.util.HashMap;

import br.unicamp.mc437.client.datatypes.Produto;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>FinalizarCompraService</code>.
 */
public interface FinalizarCompraServiceAsync {
	
	void finalizarCompra(AsyncCallback<Boolean> callback);

	void getAtualCarrinho(
			AsyncCallback<ArrayList<HashMap<String, String>>> callback);

	void simularCompra(AsyncCallback<Void> callback);

	void clienteId1(int idCliente,
			AsyncCallback<HashMap<String, String>> callback);
}
