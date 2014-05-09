package br.unicamp.mc437.server;

import br.unicamp.mc437.client.LoginService;
import br.unicamp.mc437.client.datatypes.Administrador;
import br.unicamp.mc437.client.datatypes.Cliente;
import br.unicamp.mc437.client.datatypes.Produto;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.gwt.user.client.rpc.AsyncCallback;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import java.io.Serializable;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class LoginServiceImpl extends RemoteServiceServlet implements
		LoginService {

	public Administrador loginAdmin(String nome, String senha) throws IllegalArgumentException {
		
		Connection connection = null;
		ResultSet rs = null;
		String html = null;
		int counter = 0;
		Administrador adm = new Administrador();
		
		// making a connection
		try {
			Class.forName("org.hsqldb.jdbcDriver");
			connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/mydb", "sa", ""); 
			html=html+"<tr>";
			
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
	
		String serverInfo = getServletContext().getServerInfo();
		String userAgent = getThreadLocalRequest().getHeader("User-Agent");

		// Escape data from the client to avoid cross-site script vulnerabilities.		
		userAgent = escapeHtml(userAgent);
		
		if(counter == 1)
			return adm;
		else
			return null;
	}
	
public Cliente loginCliente(String nome, String senha) throws IllegalArgumentException {
		
		Connection connection = null;
		ResultSet rs = null;
		String html = null;
		int counter = 0;
		Cliente cli = new Cliente();
		
		// making a connection
		try {
			Class.forName("org.hsqldb.jdbcDriver");
			connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/mydb", "sa", ""); 
			html=html+"<tr>";
			
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
	
		String serverInfo = getServletContext().getServerInfo();
		String userAgent = getThreadLocalRequest().getHeader("User-Agent");

		// Escape data from the client to avoid cross-site script vulnerabilities.		
		userAgent = escapeHtml(userAgent);
		
		if(counter == 1)
			return cli;
		else
			return null;
	}
	
	/**s
	 * Escape an html string. Escaping data received from the client helps to
	 * prevent cross-site script vulnerabilities.
	 * 
	 * @param html the html string to escape
	 * @return the escaped string
	 */
	private String escapeHtml(String html) {
		if (html == null) {
			return null;
		}
		return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;")
				.replaceAll(">", "&gt;");
	}


}
