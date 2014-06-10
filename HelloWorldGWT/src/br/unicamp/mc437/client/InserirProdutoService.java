package br.unicamp.mc437.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import br.unicamp.mc437.client.datatypes.Produto;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("inserirProduto")
public interface InserirProdutoService extends RemoteService {
//	String greetServer(String name) throws IllegalArgumentException;
	
	Boolean inserirProdutoServer(Produto p) throws IllegalArgumentException;
}
