package br.unicamp.mc437.server;

import br.unicamp.mc437.client.GreetingService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author ©2014 gustavo waku - MC437 example
 **/

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements
		GreetingService {

	/*public String greetServer(Student input) throws IllegalArgumentException {
		
		
		//Creating a database and deleting it.
		Connection connection = null;
		ResultSet rs = null;
		
		String name_stored = null;
		// making a connection
		try {
			Class.forName("org.hsqldb.jdbcDriver");
			connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/mydb", "sa", ""); // can through sql exception
			//Class.forName("com.mysql.jdbc.Driver").newInstance();
			//connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:81/test","root",""); 
			
			//drops the table if exists, and create the schema
			//put this on ant file when it starts the database...
			//connection.prepareStatement("drop table users if exists;").execute();

			connection.prepareStatement("create table if not exists turma (id integer, nome varchar(20) not null);").execute();
			connection.prepareStatement("create table if not exists users (id integer, nome varchar(20) not null, turma integer);").execute();
			
			//connection.prepareStatement("CREATE TABLE IF NOT EXISTS `turma` (   `id` int(11) NOT NULL,   `name` varchar(255) NOT NULL,   PRIMARY KEY (`id`) ) ENGINE=MyISAM DEFAULT CHARSET=latin1;  CREATE TABLE IF NOT EXISTS `users` (   `id` int(11) NOT NULL,   `nome` varchar(255) NOT NULL,   `turma` int(11) NOT NULL,   PRIMARY KEY (`id`),   KEY `id` (`id`,`turma`) ) ENGINE=MyISAM DEFAULT CHARSET=latin1;");
			//connection.prepareStatement("insert into turma (id, nome) values ('1', 'MC437');").execute();
			
		/*	PreparedStatement ps = connection.prepareStatement("insert into users (id, nome, turma)" 
					+ "values ( ?, ?,1);");
			ps.setInt(1, input.getId());
			ps.setString(2, input.getName());
			ps.execute();
			
			// query from the db
			rs = connection.prepareStatement("select id, nome from users;").executeQuery();
			rs.next();
			System.out.println(String.format("ID: %1d, Nome: %1s", rs.getInt(1), rs.getString(2)));
			name_stored = "stored:" + rs.getString(2);
			rs.close();
			
		} catch (SQLException e2) {
			e2.printStackTrace();
		} catch (ClassNotFoundException e2) {
			e2.printStackTrace();

		}
	
		String serverInfo = getServletContext().getServerInfo();
		String userAgent = getThreadLocalRequest().getHeader("User-Agent");

		// Escape data from the client to avoid cross-site script vulnerabilities.
		
		userAgent = escapeHtml(userAgent);
		
		return "Congrats!! using student datatype!, " + name_stored + "! I stored your name and deleted it <br><br>I am running " + serverInfo
				+ ".<br><br>It looks like you are using:<br>" + userAgent;
		
	}*/
	
	
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
}
