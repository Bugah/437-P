package br.unicamp.mc437.client;

import java.util.ArrayList;
import java.util.Map;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import br.unicamp.mc437.client.datatypes.Produto;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("buscarProduto")
public interface BuscarProdutoService extends RemoteService {
//	String greetServer(String name) throws IllegalArgumentException;
	
	ArrayList<Produto> greetServer(Produto p, String where, Map<Integer, String> imagens_resultado) throws IllegalArgumentException;
}
