package br.unicamp.mc437.client;

import br.unicamp.mc437.client.datatypes.Cliente;
import br.unicamp.mc437.client.datatypes.Administrador;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("usuario")
public interface LoginService extends RemoteService{
	
	boolean fazerCadastro(String nome, String username, int cpf, String senha);
	
	boolean loginCliente(String username, String senha);
	boolean loginAdministrador(String username, String senha);
	
	boolean logoff();

	Cliente getUserOn();
	Administrador getAdminOn();

}
