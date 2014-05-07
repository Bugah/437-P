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
		
		
		Connection connection = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		String html = null;
		ArrayList<Produto> resultados = new ArrayList<Produto>();
		String nome=null;
		int counter = 0;
		
		
		// making a connection
		try {
			Class.forName("org.hsqldb.jdbcDriver");
			connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/mydb", "sa", ""); 
			html=html+"<tr>";
			
			// query from the db
			rs = connection.prepareStatement("select * from produtos where "+where+" like '%_"+p.getNome().substring(1)+"%' order by preco;").executeQuery();
			nome=null;
			
			while(rs.next()){
				Produto n = new Produto();
				n.setId(rs.getInt("ID_PRODUTO"));
				n.setNome(rs.getString("NOME"));
				n.setDescricao(rs.getString("DESCRICAO"));
				n.setPreco(rs.getDouble("PRECO"));
				n.setPrecoPromocional(rs.getDouble("PRECO_PROMOCIONAL"));
				n.setUrlImagemUnica(rs.getString("URL_IMAGEM_UNICA"));				
				resultados.add(n);
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
		
		return resultados;
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
