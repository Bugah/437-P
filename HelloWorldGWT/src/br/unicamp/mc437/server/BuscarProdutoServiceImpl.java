package br.unicamp.mc437.server;

import br.unicamp.mc437.client.BuscarProdutoService;
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
public class BuscarProdutoServiceImpl extends RemoteServiceServlet implements
		BuscarProdutoService {

	public ArrayList<Produto> greetServer(Produto p,String where, Map<Integer, String> imagens_resultados) throws IllegalArgumentException {
		
		
		//Creating a database and deleting it.
		Connection connection = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		String html = null;
		ArrayList<Produto> resultados = new ArrayList<Produto>();
		/*
		html="<table>";
		html=html+"<tr>";
		html=html+"<td>ID</td>";
		html=html+"<td>Nome</td>";
		html=html+"<td>Pre�o</td>";
		html=html+"<td>IMG</td>";
	
		html=html+"</tr>";
		*/
		String nome=null;
		int counter = 0;
		// making a connection
		try {
			Class.forName("org.hsqldb.jdbcDriver");
			connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/mydb", "sa", ""); 
			html=html+"<tr>";
			/*PreparedStatement ps = connection.prepareStatement("select * from PRODUTOS where NOME = ?;");
			ps.setString(1, p.getNome());
			ps.execute();
			ResultSet Results = ps.getResultSet();
			*/
			
			// query from the db
			rs = connection.prepareStatement("select * from produtos where "+where+" like '%_"+p.getNome().substring(1)+"%' order by preco;").executeQuery();
			//rs.setString(p,nome);
			//WHERE NOME='"+p.getNome()+"'
			nome=null;
			
			while(rs.next()){
				//.out.println(String.format("ID: %1d, Nome: %1s", rs.getInt(1), rs.getString(2)));
				//name_stored = "stored:" + rs.getString("ID");
				//html=html+"<td>"+ rs.getInt("ID_PRODUTO")+"</td>";
				//html=html+"<td>"+ rs.getString ("NOME")+"</td>";
				Produto n = new Produto();
				n.setId(rs.getInt("ID_PRODUTO"));
				n.setNome(rs.getString("NOME"));
				n.setDescricao(rs.getString("DESCRICAO"));
				n.setPreco(rs.getDouble("PRECO"));
				n.setPrecoPromocional(rs.getDouble("PRECO_PROMOCIONAL"));
				n.setUrlImagemUnica(rs.getString("URL_IMAGEM_UNICA"));
				
				resultados.add(n);
				//resultados[counter].setAdmin(rs.getInt("ID_ADMIN"));
				// ID ADMIN -> NOME ADMIN ? Verificar
				
				/*rs2 = connection.prepareStatement("SELECT * FROM IMAGENS_PRODUTO pi RIGHT JOIN IMAGEM i ON (pi.ID_IMAGEM=i.ID_IMAGEM) WHERE pi.ID_PRODUTO='"+Integer.toString(n.getId())+"';").executeQuery();
				// FAVOR VERIFICAR A QUERY
				
				//html=html+"<table>";
				while(rs2.next()){
					n.setUrlImagemUnica(rs2.getString("NOME_ARQUIVO"));
					break;
					//imagens_resultados.put(n.getId(), rs2.getString("NOME_ARQUIVO"));
					//System.out.println(Integer.toString(n.getId())+":"+imagens_resultados.get(n.getId()));
				} */
				//html=html+"</table>";
				//html=html+"</tr>";
				counter = counter + 1;
			}
			//rs.close();
			//rs2.close();
			//html=html+"</table>";
		
			
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
		
//		return "Produto encontrado: " + name_stored + ".<br><br>I am running " + serverInfo
//				+ ".<br><br>It looks like you are using:<br>" + userAgent;
		return resultados;
		//return html;
		
	}
	
	
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
