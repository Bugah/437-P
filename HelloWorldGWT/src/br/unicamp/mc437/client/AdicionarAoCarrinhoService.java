package br.unicamp.mc437.client;

import java.util.ArrayList;
import java.util.Map;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import br.unicamp.mc437.client.datatypes.Produto;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("adicionarAoCarrinho")
public interface AdicionarAoCarrinhoService extends RemoteService {
	
	Boolean adicionar() throws IllegalArgumentException;
}
