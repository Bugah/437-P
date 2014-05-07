package br.unicamp.mc437.server;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import br.unicamp.mc437.client.CarrinhoCompras;
import br.unicamp.mc437.client.datatypes.CarrinhoComprasElemento;
import br.unicamp.mc437.client.datatypes.Produto;

@SuppressWarnings("serial")
public class CarrinhoComprasImpl extends RemoteServiceServlet implements CarrinhoCompras {

	private static final long serialVersionUID = 4456105400553118785L;
	
	@Override
	public boolean adicionarProduto(Produto produto){
		HttpServletRequest httpServletRequest = getThreadLocalRequest();
        HttpSession session = httpServletRequest.getSession();
        
        Object obj = session.getAttribute(produto.getNome());
		if(obj == null)
			storeProd(produto);
		else
			icrementProd(produto);
		
		return true;
	}
	
	@Override
	public boolean removerProduto(Produto produto){
		return deleteProd(produto);
	}
	
	@Override
	public List<CarrinhoComprasElemento> obterCarrinho(){
		
		List<CarrinhoComprasElemento> carrinho = new ArrayList<>();
		
		HttpServletRequest httpServletRequest = getThreadLocalRequest();
        HttpSession session = httpServletRequest.getSession();
        
        Enumeration<String> list =  session.getAttributeNames();
        
        while(list.hasMoreElements()){
        	String element = list.nextElement();
        	if(!element.contains("-Quantidade")){
        		CarrinhoComprasElemento carrinhoElement = new CarrinhoComprasElemento();
        		carrinhoElement.setProduto(getProd(element));
        		carrinhoElement.setQuantidade(getQuant(element));
        		carrinho.add(carrinhoElement);
        	}
        }
        
		return carrinho;
	}
	
	@Override
	public boolean esvaziarCarrinho(){
		HttpServletRequest httpServletRequest = getThreadLocalRequest();
        HttpSession session = httpServletRequest.getSession();
        
        Enumeration<String> list =  session.getAttributeNames();
        
        while(list.hasMoreElements()){
        	session.removeAttribute(list.nextElement());
        }
        
		return true;
	}

	
	private void storeProd(Produto product){
		
		 HttpServletRequest httpServletRequest = getThreadLocalRequest();
	     HttpSession session = httpServletRequest.getSession(true);
	     session.setAttribute(product.getNome(), product);
	     session.setAttribute(product.getNome()+"-Quantidade", 1);
	}
	
	private void icrementProd(Produto product){
		HttpServletRequest httpServletRequest = getThreadLocalRequest();
	    HttpSession session = httpServletRequest.getSession(true);
	    Integer quant = (Integer) session.getAttribute(product.getNome()+"-Quantidade");
	    
	    session.setAttribute(product.getNome()+"-Quantidade", quant+1);
	}
	
	private boolean deleteProd(Produto product){
		HttpServletRequest httpServletRequest = getThreadLocalRequest();
        HttpSession session = httpServletRequest.getSession();
        session.removeAttribute(product.getNome());
        session.removeAttribute(product.getNome()+"-Quantidade");
        
        return true;
	}
	
	private Produto getProd(String product){
		HttpServletRequest httpServletRequest = getThreadLocalRequest();
        HttpSession session = httpServletRequest.getSession();
        return (Produto) session.getAttribute(product);
	}
	
	private Integer getQuant(String product){
		HttpServletRequest httpServletRequest = getThreadLocalRequest();
        HttpSession session = httpServletRequest.getSession();
        return (Integer) session.getAttribute(product+"-Quantidade");
	}
	
	
}
