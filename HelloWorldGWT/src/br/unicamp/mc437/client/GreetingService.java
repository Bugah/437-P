package br.unicamp.mc437.client;

import java.util.ArrayList;
import java.util.Map;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import br.unicamp.mc437.client.datatypes.Produto;
import br.unicamp.mc437.client.datatypes.SubCategoria;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface GreetingService extends RemoteService {
//	String greetServer(String name) throws IllegalArgumentException;
	

	ArrayList<Produto> greetServer(Produto p, String where, Map<Integer, String> imagens_resultado) throws IllegalArgumentException;


	ArrayList<Produto> todosProdutos(Produto p, int id_categoria) throws IllegalArgumentException;
	
	ArrayList<SubCategoria> todasSubCategorias(SubCategoria p) throws IllegalArgumentException;
	
	Produto byID(int id_produto) throws IllegalArgumentException;
}
