package br.unicamp.mc437.client;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import br.unicamp.mc437.client.datatypes.Produto;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface GreetingService extends RemoteService {
//	String greetServer(String name) throws IllegalArgumentException;
	
	ArrayList<Produto> greetServer(Produto p, String [][] imagens_resultado) throws IllegalArgumentException;
}
