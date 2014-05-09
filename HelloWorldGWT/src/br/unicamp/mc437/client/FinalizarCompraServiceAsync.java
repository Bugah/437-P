package br.unicamp.mc437.client;

import br.unicamp.mc437.client.datatypes.Produto;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>FinalizarCompraService</code>.
 */
public interface FinalizarCompraServiceAsync {
	
	void finalizarCompra(AsyncCallback<Boolean> callback);
}
