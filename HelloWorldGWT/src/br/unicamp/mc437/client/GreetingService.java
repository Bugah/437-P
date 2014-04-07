package br.unicamp.mc437.client;

import br.unicamp.mc437.client.datatypes.Student;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author ©2014 gustavo waku - MC437 example
 * */

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface GreetingService extends RemoteService {
//	String greetServer(String name) throws IllegalArgumentException;
	
	String greetServer(Student student) throws IllegalArgumentException;
}
