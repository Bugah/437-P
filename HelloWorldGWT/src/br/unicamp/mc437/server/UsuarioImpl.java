package br.unicamp.mc437.server;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.unicamp.mc437.client.Usuario;
import br.unicamp.mc437.client.datatypes.Cliente;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.sun.xml.internal.bind.v2.TODO;

@SuppressWarnings("serial")
public class UsuarioImpl  extends RemoteServiceServlet implements Usuario{
	
	private static final long serialVersionUID = 4456105400553118785L;

	@Override
	public boolean fazerCadastro(String nome, String username, int cpf,
			String senha) {
		//Implementar
		return false;
	}

	@Override
	public boolean login(String username, String senha) {
		
		Cliente client = checkPassword(username, senha);
		
		if(client != null){
			HttpServletRequest httpServletRequest = getThreadLocalRequest();
		    HttpSession session = httpServletRequest.getSession(true);
		    session.setAttribute("user", client);
		    
		    return true;
		}
		
		return false;
	}

	@Override
	public boolean logoff() {
		
		HttpServletRequest httpServletRequest = getThreadLocalRequest();
	    HttpSession session = httpServletRequest.getSession(true);
	    session.removeAttribute("user");
		
		return true;
	}
	
	@Override
	public Cliente getUserOn(){
		HttpServletRequest httpServletRequest = getThreadLocalRequest();
	    HttpSession session = httpServletRequest.getSession(true);
	    
	    return (Cliente) session.getAttribute("user");
		
	}
	
	private Cliente checkPassword(String username, String senha) {
		
		//Fazer parte do banco
		
		return null;
	}


}
