package br.unicamp.mc437.client;

import br.unicamp.mc437.client.datatypes.Administrador;
import br.unicamp.mc437.client.datatypes.Cliente;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>LoginService</code>.
 */
public interface LoginServiceAsync {
	
	void loginAdmin(String noem, String senha, AsyncCallback<Administrador> callback);
	void loginCliente(AsyncCallback<Cliente> callback);
}
