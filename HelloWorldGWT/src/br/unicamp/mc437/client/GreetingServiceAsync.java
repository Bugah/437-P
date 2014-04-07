package br.unicamp.mc437.client;

import br.unicamp.mc437.client.datatypes.Student;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * @author ©2014 gustavo waku - MC437 example
 **/

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GreetingServiceAsync {
//	void greetServer(String input, AsyncCallback<String> callback)
//			throws IllegalArgumentException;
	
	void greetServer(Student input, AsyncCallback<String> callback)
			throws IllegalArgumentException;
}
