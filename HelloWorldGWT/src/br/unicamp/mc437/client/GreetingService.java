package br.unicamp.mc437.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface GreetingService extends RemoteService {
//	String greetServer(String name) throws IllegalArgumentException;
	
	String greetServer(String p) throws IllegalArgumentException;
}
