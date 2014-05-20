package br.unicamp.mc437.server;

import br.unicamp.mc437.client.GreetingService;
import br.unicamp.mc437.client.datatypes.Produto;
import br.unicamp.mc437.client.datatypes.SubCategoria;

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
public class GreetingServiceImpl extends RemoteServiceServlet implements
		GreetingService {

	public ArrayList<Produto> greetServer(Produto p, String where,
			Map<Integer, String> imagens_resultados)
			throws IllegalArgumentException {

		// Creating a database and deleting it.
		Connection connection = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		String html = null;
		ArrayList<Produto> resultados = new ArrayList<Produto>();
		/*
		 * html="<table>"; html=html+"<tr>"; html=html+"<td>ID</td>";
		 * html=html+"<td>Nome</td>"; html=html+"<td>Pre�o</td>";
		 * html=html+"<td>IMG</td>";
		 * 
		 * html=html+"</tr>";
		 */
		String nome = null;
		int counter = 0;
		// making a connection
		try {
			Class.forName("org.hsqldb.jdbcDriver");
			connection = DriverManager.getConnection(
					"jdbc:hsqldb:hsql://localhost/mydb", "sa", "");
			html = html + "<tr>";
			/*
			 * PreparedStatement ps =
			 * connection.prepareStatement("select * from PRODUTOS where NOME = ?;"
			 * ); ps.setString(1, p.getNome()); ps.execute(); ResultSet Results
			 * = ps.getResultSet();
			 */

			// query from the db
			rs = connection.prepareStatement(
					"select * from produtos where " + where + " like '%_"
							+ p.getNome().substring(1) + "%' order by preco;")
					.executeQuery();
			// rs.setString(p,nome);
			// WHERE NOME='"+p.getNome()+"'
			nome = null;

			while (rs.next()) {
				// .out.println(String.format("ID: %1d, Nome: %1s",
				// rs.getInt(1), rs.getString(2)));
				// name_stored = "stored:" + rs.getString("ID");
				// html=html+"<td>"+ rs.getInt("ID_PRODUTO")+"</td>";
				// html=html+"<td>"+ rs.getString ("NOME")+"</td>";
				Produto n = new Produto();
				n.setId(rs.getInt("ID_PRODUTO"));
				n.setNome(rs.getString("NOME"));
				n.setDescricao(rs.getString("DESCRICAO"));
				n.setPreco(rs.getDouble("PRECO"));
				n.setPrecoPromocional(rs.getDouble("PRECO_PROMOCIONAL"));
				n.setUrlImagemUnica(rs.getString("URL_IMAGEM_UNICA"));

				resultados.add(n);
				// resultados[counter].setAdmin(rs.getInt("ID_ADMIN"));
				// ID ADMIN -> NOME ADMIN ? Verificar

				/*
				 * rs2 = connection.prepareStatement(
				 * "SELECT * FROM IMAGENS_PRODUTO pi RIGHT JOIN IMAGEM i ON (pi.ID_IMAGEM=i.ID_IMAGEM) WHERE pi.ID_PRODUTO='"
				 * +Integer.toString(n.getId())+"';").executeQuery(); // FAVOR
				 * VERIFICAR A QUERY
				 * 
				 * //html=html+"<table>"; while(rs2.next()){
				 * n.setUrlImagemUnica(rs2.getString("NOME_ARQUIVO")); break;
				 * //imagens_resultados.put(n.getId(),
				 * rs2.getString("NOME_ARQUIVO"));
				 * //System.out.println(Integer.toString
				 * (n.getId())+":"+imagens_resultados.get(n.getId())); }
				 */
				// html=html+"</table>";
				// html=html+"</tr>";
				counter = counter + 1;
			}
			// rs.close();
			// rs2.close();
			// html=html+"</table>";

		} catch (SQLException e2) {
			e2.printStackTrace();
			System.out.println("ERROOOOOOOOOOOOORR");
		} catch (ClassNotFoundException e2) {
			e2.printStackTrace();
			System.out.println("ERROOOOOOOOOOOOORR");
		}

		String serverInfo = getServletContext().getServerInfo();
		String userAgent = getThreadLocalRequest().getHeader("User-Agent");

		// Escape data from the client to avoid cross-site script
		// vulnerabilities.

		userAgent = escapeHtml(userAgent);

		// return "Produto encontrado: " + name_stored +
		// ".<br><br>I am running " + serverInfo
		// + ".<br><br>It looks like you are using:<br>" + userAgent;
		return resultados;
		// return html;

	}

	/**
	 * s Escape an html string. Escaping data received from the client helps to
	 * prevent cross-site script vulnerabilities.
	 * 
	 * @param html
	 *            the html string to escape
	 * @return the escaped string
	 */
	private String escapeHtml(String html) {
		if (html == null) {
			return null;
		}
		return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;")
				.replaceAll(">", "&gt;");
	}

	public ArrayList<SubCategoria> todasSubCategorias(SubCategoria p)
			throws IllegalArgumentException {
		Connection connection = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		String html = null;
		ArrayList<SubCategoria> resultados = new ArrayList<SubCategoria>();

		String nome = null;
		int counter = 0;
		// making a connection
		try {
			Class.forName("org.hsqldb.jdbcDriver");
			connection = DriverManager.getConnection(
					"jdbc:hsqldb:hsql://localhost/mydb", "sa", "");
			html = html + "<tr>";

			rs = connection.prepareStatement(
					"select * from sub_categorias where deletado=0 order by subcategoria asc ;")
					.executeQuery();

			// rs.setString(p,nome);
			// WHERE NOME='"+p.getNome()+"'
			nome = null;

			while (rs.next()) {

				SubCategoria n = new SubCategoria();
				n.setId(rs.getInt("ID_SUB_CATEGORIA"));
				n.setCategoria(rs.getString("SUBCATEGORIA"));
				n.setDescricao(rs.getString("DESCRICAO"));


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

		// Escape data from the client to avoid cross-site script
		// vulnerabilities.

		userAgent = escapeHtml(userAgent);

		// return "Produto encontrado: " + name_stored +
		// ".<br><br>I am running " + serverInfo
		// + ".<br><br>It looks like you are using:<br>" + userAgent;
		return resultados;
		// return html;

	}
	
	public Produto byID( int id_produto)
			throws IllegalArgumentException {

		// Creating a database and deleting it.
		Connection connection = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		String html = null;
		
		Produto n = new Produto();
		String nome = null;
		int counter = 0;
		// making a connection
		try {
			Class.forName("org.hsqldb.jdbcDriver");
			connection = DriverManager.getConnection(
					"jdbc:hsqldb:hsql://localhost/mydb", "sa", "");
		

				rs = connection.prepareStatement(
						"select * from produtos where id_produto='"+id_produto+"' order by id_produto asc;")
						.executeQuery();

			nome = null;
			
			while (rs.next()) {
				n.setId(rs.getInt("ID_PRODUTO"));
				n.setNome(rs.getString("NOME"));
				n.setDescricao(rs.getString("DESCRICAO"));
				n.setPreco(rs.getDouble("PRECO"));
				n.setPrecoPromocional(rs.getDouble("PRECO_PROMOCIONAL"));
				n.setUrlImagemUnica(rs.getString("URL_IMAGEM_UNICA"));
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

		userAgent = escapeHtml(userAgent);
		return n;

	}

	
	
	public ArrayList<Produto> todosProdutos(Produto p, int id_categoria)
			throws IllegalArgumentException {

		// Creating a database and deleting it.
		Connection connection = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		String html = null;
		ArrayList<Produto> resultados = new ArrayList<Produto>();

		String nome = null;
		int counter = 0;
		// making a connection
		try {
			Class.forName("org.hsqldb.jdbcDriver");
			connection = DriverManager.getConnection(
					"jdbc:hsqldb:hsql://localhost/mydb", "sa", "");
			html = html + "<tr>";

			// query from the db
			if (id_categoria == 0)
				rs = connection.prepareStatement(
						"select * from produtos order by id_produto asc;")
						.executeQuery();
			else
				rs = connection
						.prepareStatement(
								"SELECT  * FROM  produtos p  "
										+ "INNER JOIN   produtos_subcategorias ps ON  p.id_produto=ps.id_produto "
										+ "LEFT JOIN  sub_categorias  c  ON c.id_sub_categoria=ps.id_sub_categoria "
										+ "WHERE c.id_sub_categoria="
										+ id_categoria).executeQuery();

			// rs.setString(p,nome);
			// WHERE NOME='"+p.getNome()+"'
			nome = null;

			while (rs.next()) {

				Produto n = new Produto();
				n.setId(rs.getInt("ID_PRODUTO"));
				n.setNome(rs.getString("NOME"));
				n.setDescricao(rs.getString("DESCRICAO"));
				n.setPreco(rs.getDouble("PRECO"));
				n.setPrecoPromocional(rs.getDouble("PRECO_PROMOCIONAL"));
				n.setUrlImagemUnica(rs.getString("URL_IMAGEM_UNICA"));

				resultados.add(n);
				// resultados[counter].setAdmin(rs.getInt("ID_ADMIN"));
				// ID ADMIN -> NOME ADMIN ? Verificar

				/*
				 * rs2 = connection.prepareStatement(
				 * "SELECT * FROM IMAGENS_PRODUTO pi RIGHT JOIN IMAGEM i ON (pi.ID_IMAGEM=i.ID_IMAGEM) WHERE pi.ID_PRODUTO='"
				 * +Integer.toString(n.getId())+"';").executeQuery(); // FAVOR
				 * VERIFICAR A QUERY
				 * 
				 * //html=html+"<table>"; while(rs2.next()){
				 * n.setUrlImagemUnica(rs2.getString("NOME_ARQUIVO")); break;
				 * //imagens_resultados.put(n.getId(),
				 * rs2.getString("NOME_ARQUIVO"));
				 * //System.out.println(Integer.toString
				 * (n.getId())+":"+imagens_resultados.get(n.getId())); }
				 */
				// html=html+"</table>";
				// html=html+"</tr>";
				counter = counter + 1;
			}
			// rs.close();
			// rs2.close();
			// html=html+"</table>";

		} catch (SQLException e2) {
			e2.printStackTrace();
			System.out.println("ERROOOOOOOOOOOOORR");
		} catch (ClassNotFoundException e2) {
			e2.printStackTrace();
			System.out.println("ERROOOOOOOOOOOOORR");
		}

		String serverInfo = getServletContext().getServerInfo();
		String userAgent = getThreadLocalRequest().getHeader("User-Agent");

		// Escape data from the client to avoid cross-site script
		// vulnerabilities.

		userAgent = escapeHtml(userAgent);

		// return "Produto encontrado: " + name_stored +
		// ".<br><br>I am running " + serverInfo
		// + ".<br><br>It looks like you are using:<br>" + userAgent;
		return resultados;
		// return html;

	}

	/**
	 * s Escape an html string. Escaping data received from the client helps to
	 * prevent cross-site script vulnerabilities.
	 * 
	 * @param html
	 *            the html string to escape
	 * @return the escaped string
	 */

}
