package br.unicamp.mc437.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.tempuri.CorreioServer;

import br.unicamp.mc437.client.FinalizarCompraService;
import br.unicamp.mc437.client.datatypes.Produto;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class FinalizarCompraImpl  extends RemoteServiceServlet implements FinalizarCompraService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	@Override
	public Boolean finalizarCompra() throws IllegalArgumentException {
		// TODO Auto-generated method stub
		//: ???????????????
		return null;
	}
	
	@Override
	public void simularCompra(){
		
		HttpServletRequest httpServletRequest = getThreadLocalRequest();
		 HttpSession session = httpServletRequest.getSession(true);
        ArrayList<HashMap<String,Integer>> simul = new ArrayList<HashMap<String,Integer>>();
        HashMap<String,Integer> hashMap;
        
        hashMap = new HashMap<String,Integer>();
        hashMap.put("idProd", 1);
        hashMap.put("quantity", 1);
        simul.add(hashMap);
        
        hashMap = new HashMap<String,Integer>();
        hashMap.put("idProd", 2);
        hashMap.put("quantity", 4);
        simul.add(hashMap);
        
        hashMap = new HashMap<String,Integer>();
        hashMap.put("idProd", 14);
        hashMap.put("quantity", 3);
        simul.add(hashMap);
        
        session.setAttribute("carrinho", simul);
        
        
        
		
	}

	@Override
	public ArrayList<HashMap<String, String>> getAtualCarrinho() {
		
		ArrayList<HashMap<String, String>> resultado = new ArrayList<HashMap<String, String>>();
		HttpServletRequest httpServletRequest = getThreadLocalRequest();
        HttpSession session = httpServletRequest.getSession();
        ArrayList<HashMap<String,Integer>> listProducts = (ArrayList<HashMap<String,Integer>>) session.getAttribute("carrinho");
        ResultSet rs = null;
        for(int i=0;i<listProducts.size();i++){
        	Connection connection = null;

    		// making a connection
    			try {
					Class.forName("org.hsqldb.jdbcDriver");
						connection = DriverManager.getConnection(
								"jdbc:hsqldb:hsql://localhost/mydb", "sa", "");
						rs = connection.prepareStatement(
								"select id_produto as idProd, nome, preco from produtos where id_produto="+listProducts.get(i).get("idProd"))
								.executeQuery();
						rs.next();
						
						HashMap<String,String> hashMap = new HashMap<String, String>();
						hashMap.put("idProd", rs.getString("idProd"));
						hashMap.put("name", rs.getString("NOME"));
						hashMap.put("unitPrice", Double.toString(rs.getDouble("PRECO")));
						hashMap.put("quantity",listProducts.get(i).get("quantity").toString());
						double precoTot = (rs.getDouble("PRECO")*listProducts.get(i).get("quantity"));
						hashMap.put("totalPrice",  Double.toString(precoTot)  );
						
					
						
						resultado.add(hashMap);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();}
    			catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
    			
        	
        	
        }
        
		
		return resultado;
	}
	
	//a trocar com o cliente do login
	//eh so para o exemplo
	//pegar getUserOn de LoginService qdo estara funcionando
	@Override
	public
	HashMap<String,String> clienteId1(int idCliente){
		
	ResultSet rs = null;
        	Connection connection = null;
			HashMap<String,String> hashMap = new HashMap<String, String>();

    		// making a connection
    			try {
					Class.forName("org.hsqldb.jdbcDriver");
						connection = DriverManager.getConnection(
								"jdbc:hsqldb:hsql://localhost/mydb", "sa", "");
						rs = connection.prepareStatement(
								"SELECT id_cliente as id, nome, endereco, cidade, estado, cep from clientes where id_cliente="+idCliente+";")
								.executeQuery();
						rs.next();
						
						hashMap.put("idClient", rs.getString("id"));
						hashMap.put("name", rs.getString("nome"));
						hashMap.put("adress", rs.getString("endereco"));
						hashMap.put("city", rs.getString("cidade"));
						hashMap.put("state", rs.getString("estado"));
						hashMap.put("cep", rs.getString("cep"));
						
						// Calculo de frete para um cep 
						CorreioServer frete = new CorreioServer();
						frete.setCepDestino("05625100");
						frete.setCepOrigem("13083755");
						// Mudar conforme servico desejado //
						frete.setCodServico("40010");
						hashMap.put("frete", Double.toString(frete.valorFrete()));
						
						
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();}
    			catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
		
	return hashMap;
	
	}
}
