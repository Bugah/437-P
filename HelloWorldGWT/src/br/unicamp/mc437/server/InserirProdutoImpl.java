package br.unicamp.mc437.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import br.unicamp.mc437.client.BuscarProdutoService;
import br.unicamp.mc437.client.InserirProdutoService;
import br.unicamp.mc437.client.datatypes.Produto;
import br.unicamp.mc437.client.datatypes.SubCategoria;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class InserirProdutoImpl extends RemoteServiceServlet implements
		InserirProdutoService {
	

	
	public String inserirProdutoServer(Produto p) throws IllegalArgumentException {
		

		//Creating a database and deleting it.
		Connection connection = null;
		ResultSet rs = null;
		String html = null;

		// making a connection
		try {
			Class.forName("org.hsqldb.jdbcDriver");
			connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/mydb", "sa", ""); 
			
			PreparedStatement ps = connection.prepareStatement("INSERT INTO PRODUTOS VALUES(?,?,?,?,?,?,?,?,?)");
			ps.setNull(1, 0);
			ps.setString(2, p.getNome());
			ps.setString(3, p.getDescricao());
			ps.setDouble(4, p.getPreco());
			ps.setInt(5,p.getEstoque());
			ps.setDouble(6, p.getPrecoPromocional());
			ps.setInt(7, p.getAdmin().getId());
			ps.setInt(8,p.getDeletado());
			ps.setString(9, p.getUrlImagemUnica());
			ps.execute();
			
			//Agora vamos encher a tabela Produtos_subCategorias
			
			//recuperacao do id do produto, seja o id do ultimo
			//produto entrado no banco de dado.
			rs = connection.prepareStatement("select ID_PRODUTO from produtos order by id_produto DESC LIMIT 1;").executeQuery();
			rs.next();
			int idProd = rs.getInt(1);
			
			
			ArrayList<SubCategoria> subCats = p.getSubCat();
			for(int i=0;i<subCats.size();i++){
				ps = connection.prepareStatement("INSERT INTO PRODUTOS_SUBCATEGORIAS VALUES(?,?,?)");
				ps.setNull(1, 0);
				ps.setInt(2, subCats.get(i).getId());
				ps.setInt(3,idProd);
				ps.execute();
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		} catch (ClassNotFoundException e2) {
			e2.printStackTrace();

		}
	
		String serverInfo = getServletContext().getServerInfo();
		String userAgent = getThreadLocalRequest().getHeader("User-Agent");

		// Escape data from the client to avoid cross-site script vulnerabilities		
		userAgent = escapeHtml(userAgent);
				
		return html;
		
	}
	

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
}
