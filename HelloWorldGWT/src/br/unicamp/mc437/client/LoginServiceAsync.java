package br.unicamp.mc437.client;

import br.unicamp.mc437.client.datatypes.Cliente;
import br.unicamp.mc437.client.datatypes.Administrador;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface LoginServiceAsync {

	void fazerCadastro(String nome, String username, int cpf, String senha,
			AsyncCallback<Boolean> callback);

	void loginCliente(String username, String senha, AsyncCallback<Boolean> callback);
	void loginAdministrador(String username, String senha, AsyncCallback<Boolean> callback);

	void logoff(AsyncCallback<Boolean> callback);

	void getUserOn(AsyncCallback<Cliente> callback);
	void getAdminOn(AsyncCallback<Administrador> callback);

}
