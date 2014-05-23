package br.unicamp.mc437.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.unicamp.mc437.client.LoginService;
import br.unicamp.mc437.client.datatypes.Cliente;
import br.unicamp.mc437.client.datatypes.Administrador;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.sun.xml.internal.bind.v2.TODO;

@SuppressWarnings("serial")
public class LoginServiceImpl  extends RemoteServiceServlet implements LoginService{
	
	private static final long serialVersionUID = 4456105400553118785L;
	
	private Cliente loggedclient = null;
	private Administrador loggedadmin = null;

	@Override
	public boolean fazerCadastro(String nome, String username, int cpf,
			String senha) {
		//Implementar
		return false;
	}

	@Override
	public boolean loginCliente(String username, String senha) {
		
		Cliente client = checkPasswordClient(username, senha);
		
		if(client != null){
			HttpServletRequest httpServletRequest = getThreadLocalRequest();
		    HttpSession session = httpServletRequest.getSession(true);
		    session.setAttribute("user", client);
		    
		    loggedadmin=null;
		    loggedclient=client;
		    return true;
		}
		else{
			
			loggedclient=null;
			return false;
		}
	}
	
	@Override
	public boolean loginAdministrador(String username, String senha) {
		
		Administrador admin = checkPasswordAdmin(username, senha);
		
		if(admin != null){
			HttpServletRequest httpServletRequest = getThreadLocalRequest();
		    HttpSession session = httpServletRequest.getSession(true);
		    session.setAttribute("user", admin);
		    session.setAttribute("adm", true);
		    
		    
		    loggedadmin=admin;
		    loggedclient=null;
		    return true;
		}
		else{
			
			loggedadmin=null;
			return false;		
		}
		
	}


	@Override
	public boolean logoff() {
		
		HttpServletRequest httpServletRequest = getThreadLocalRequest();
	    HttpSession session = httpServletRequest.getSession(true);
	    session.removeAttribute("user");
		
	    loggedadmin=null;
	    loggedclient=null;
		return true;
	}
	
	@Override
	public Cliente getUserOn(){
		//HttpServletRequest httpServletRequest = getThreadLocalRequest();
	    //HttpSession session = httpServletRequest.getSession(true);
	    
	    //return (Cliente) session.getAttribute("user");
		return loggedclient;
	}
	
	@Override
	public Administrador getAdminOn(){
		//HttpServletRequest httpServletRequest = getThreadLocalRequest();
	    //HttpSession session = httpServletRequest.getSession(true);
	    
	    //return (Cliente) session.getAttribute("user");
		return loggedadmin;
	}
	
	private Administrador checkPasswordAdmin(String nome, String senha) throws IllegalArgumentException {
		
		Connection connection = null;
		ResultSet rs = null;
		int counter = 0;
		Administrador adm = new Administrador();
		
		// making a connection
		try {
			Class.forName("org.hsqldb.jdbcDriver");
			connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/mydb", "sa", "");
			
			// query from the db
			rs = connection.prepareStatement("select * from ADMINISTRADOR where NOME_ADM='"+nome+"' AND SENHA_ADM='"+senha+"';").executeQuery();
			
			while(rs.next()){
				adm.setId(rs.getInt("ID_ADMIN"));
				adm.setNomeAdm(rs.getString("NOME_ADM"));
				adm.setEmailAdm(rs.getString("EMAIL_ADM"));
				adm.setSenhaAdm(rs.getString("SENHA_ADM"));
				counter = counter + 1;
			}
		
			
		} catch (SQLException e2) {
			e2.printStackTrace();
			System.out.println("ERROOOOOOOOOOOOORR");
		} catch (ClassNotFoundException e2) {
			e2.printStackTrace();
			System.out.println("ERROOOOOOOOOOOOORR");
		}
		
		if(counter == 1)
			return adm;
		else
			return null;
	}
	
	public Cliente checkPasswordClient(String nome, String senha) throws IllegalArgumentException {
		
		Connection connection = null;
		ResultSet rs = null;
		int counter = 0;
		Cliente cli = new Cliente();
		
		// making a connection
		try {
			Class.forName("org.hsqldb.jdbcDriver");
			connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/mydb", "sa", "");
			
			// query from the db
			rs = connection.prepareStatement("select * from CLIENTES where USERNAME='"+nome+"' AND SENHA='"+senha+"';").executeQuery();
			
			while(rs.next()){
				cli.setId(rs.getInt("ID_CLIENTE"));
				cli.setNome(rs.getString("NOME"));
				cli.setEmail(rs.getString("EMAIL"));
				cli.setSenha(rs.getString("SENHA"));
				cli.setCpf(rs.getString("CPF"));
				cli.setEndereco(rs.getString("ENDERECO"));
				cli.setCidade(rs.getString("CIDADE"));
				cli.setEstado(rs.getString("ESTADO"));
				cli.setTelefone(rs.getString("TELEFONE"));
				cli.setUsername(rs.getString("USERNAME"));
				counter = counter + 1;
			}
		
			
		} catch (SQLException e2) {
			e2.printStackTrace();
			System.out.println("ERROOOOOOOOOOOOORR");
		} catch (ClassNotFoundException e2) {
			e2.printStackTrace();
			System.out.println("ERROOOOOOOOOOOOORR");
		}

		if(counter == 1)
			return cli;
		else
			return null;
	}
	
	@Override
	public int getIdConnectedClient(){
		int resultado=0;
		if(loggedclient!=null){
		resultado = loggedclient.getId();}
		return resultado;
		
	}


}
