package br.unicamp.mc437.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import br.unicamp.mc437.client.datatypes.Administrador;
import br.unicamp.mc437.client.datatypes.Cliente;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("login")
public interface LoginService extends RemoteService {
	
	Administrador loginAdmin(String nome, String senha) throws IllegalArgumentException;
	Cliente loginCliente(String nome, String senha) throws IllegalArgumentException;
}
