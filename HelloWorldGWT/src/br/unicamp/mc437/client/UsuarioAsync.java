package br.unicamp.mc437.client;

import br.unicamp.mc437.client.datatypes.Cliente;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface UsuarioAsync {

	void fazerCadastro(String nome, String username, int cpf, String senha,
			AsyncCallback<Boolean> callback);

	void login(String username, String senha, AsyncCallback<Boolean> callback);

	void logoff(AsyncCallback<Boolean> callback);

	void getUserOn(AsyncCallback<Cliente> callback);

}
