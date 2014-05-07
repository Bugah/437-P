package br.unicamp.mc437.client;

import br.unicamp.mc437.client.datatypes.Cliente;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("usuario")
public interface Usuario extends RemoteService{
	
	boolean fazerCadastro(String nome, String username, int cpf, String senha );
	
	boolean login( String username, String senha);
	
	boolean logoff();

	Cliente getUserOn();

}
