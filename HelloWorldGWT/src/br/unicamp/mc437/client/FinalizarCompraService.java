package br.unicamp.mc437.client;

import java.util.ArrayList;
import java.util.HashMap;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("finalizarCompra")
public interface FinalizarCompraService extends RemoteService {
	public void simularCompra();
	
	ArrayList<HashMap<String, String>> getAtualCarrinho();
	
	Boolean finalizarCompra() throws IllegalArgumentException;

	HashMap<String, String> clienteId1(int idCliente);
}
