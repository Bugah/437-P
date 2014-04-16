package br.unicamp.mc437.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import br.unicamp.mc437.client.SubCategoriaService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class SubCategoriasImpl extends RemoteServiceServlet implements
		SubCategoriaService {

	public SubCategoriasImpl(){}
	
//	public String greetServer(String input) throws IllegalArgumentException {
//		// Verify that the input is valid. 
//		if (!FieldVerifier.isValidName(input)) {
//			// If the input is not valid, throw an IllegalArgumentException back to
//			// the client.
//			throw new IllegalArgumentException(
//					"Name must be at least 4 characters long");
//		}
//		
//		//Creating a database and deleting it.
//		Connection connection = null;
//		ResultSet rs = null;
//		
//		String name_stored = input;
//		// making a connection
//		try {
//			Class.forName("org.hsqldb.jdbcDriver");
//			connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/mydb", "sa", ""); // can through sql exception
//			
//			//drops the table if exists, and create the schema
//			//put this on ant file when it starts the database...
//			connection.prepareStatement("drop table user if exists;").execute();
//			connection.prepareStatement("create table user (id integer, nome varchar(20) not null);").execute();
//			
//			PreparedStatement ps = connection.prepareStatement("insert into user (id, nome)" 
//					+ "values ( ?, ?);");
//			ps.setInt(1, 1);
//			ps.setString(2, name_stored);
//			ps.execute();
//			
//			// query from the db
//			rs = connection.prepareStatement("select id, nome from user;").executeQuery();
//			rs.next();
//			System.out.println(String.format("ID: %1d, Nome: %1s", rs.getInt(1), rs.getString(2)));
//			name_stored = rs.getString(2);
//			rs.close();
//			
//		} catch (SQLException e2) {
//			e2.printStackTrace();
//		} catch (ClassNotFoundException e2) {
//			e2.printStackTrace();
//		}
//	
//		String serverInfo = getServletContext().getServerInfo();
//		String userAgent = getThreadLocalRequest().getHeader("User-Agent");
//
//		// Escape data from the client to avoid cross-site script vulnerabilities.
//		input = escapeHtml(input);
//		userAgent = escapeHtml(userAgent);
//		
//		return "Congrats!!, " + name_stored + "! I stored your name and deleted it <br><br>I am running " + serverInfo
//				+ ".<br><br>It looks like you are using:<br>" + userAgent;
//		
//	}
	
	

	/**
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

@Override
public ArrayList<HashMap<String,String>> getSubCategorias() throws IllegalArgumentException {
	Connection connection = null;
	ResultSet rs = null;
	String html = null;
	ArrayList<HashMap<String,String>> arrayList = new ArrayList<HashMap<String,String>>();
	// making a connection
	try {
		Class.forName("org.hsqldb.jdbcDriver");
		connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/mydb", "sa", ""); 
	//	PreparedStatement ps = connection.prepareStatement("insert into user (id, nome)" 
		//		+ "values ( ?, ?);");
		

		//Agora vamos encher a tabela Produtos_subCategorias
		
		//recuperacao do id do produto, seja o id do ultimo
		//produto entrado no banco de dado.
		rs = connection.prepareStatement("SELECT SUB_CATEGORIAs.ID_SUB_CATEGORIA as id, SUB_CATEGORIAs.SUBCATEGORIA as nome, CATEGORIAS.ID_CATEGORIA as idCat, CATEGORIAS.CATEGORIA as nomeCat FROM SUB_CATEGORIAs, CATEGORIAS_SUBCATEGORIAS, CATEGORIAS WHERE CATEGORIAS_SUBCATEGORIAS.ID_SUB_CATEGORIA = SUB_CATEGORIAs.ID_SUB_CATEGORIA AND CATEGORIAS_SUBCATEGORIAS.ID_CATEGORIA = CATEGORIAS.ID_CATEGORIA AND SUB_CATEGORIAS.DELETADO=0 ORDER BY nomeCat, nome, id;").executeQuery();
		while(rs.next()){
			//html+=rs.getString(1)+ " "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4)+"\n";
			HashMap<String, String> hashMap = new HashMap<String, String>();
			hashMap.put("id", rs.getString(1));
			hashMap.put("name", rs.getString(2));
			hashMap.put("catName",rs.getString(4));
			arrayList.add(hashMap);
		}
		
	
	} catch (SQLException e2) {
		e2.printStackTrace();
	} catch (ClassNotFoundException e2) {
		e2.printStackTrace();

	}
	return arrayList;
}
}
