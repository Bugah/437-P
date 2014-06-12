package br.unicamp.mc437.server;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.tempuri.CorreioServer;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import br.unicamp.mc437.client.CarrinhoCompras;
import br.unicamp.mc437.client.datatypes.CarrinhoComprasElemento;
import br.unicamp.mc437.client.datatypes.Produto;
import br.unicamp.mc437.sessionfake.SessionFake;

@SuppressWarnings("serial")
public class CarrinhoComprasImpl extends RemoteServiceServlet implements CarrinhoCompras {

	private static final long serialVersionUID = 4456105400553118785L;
	
	@Override
	public void initCarShop(){
		HttpServletRequest httpServletRequest = getThreadLocalRequest();
        HttpSession session = httpServletRequest.getSession();
        
        List<CarrinhoComprasElemento> list = new  ArrayList<CarrinhoComprasElemento>();
        
        Object obj = session.getAttribute("carShop");
        if(obj == null){
        	session.setAttribute("carShop", list);
        }
	}
	
	@Override
	public Integer adicionarProduto(Produto produto){
		HttpServletRequest httpServletRequest = getThreadLocalRequest();
	     HttpSession session = httpServletRequest.getSession(true);
	     
	     @SuppressWarnings("unchecked")
	     List<CarrinhoComprasElemento> car =  (ArrayList<CarrinhoComprasElemento>)session.getAttribute("carShop");
	     
	     /*
		 * Bug localizado e corrigido, produto null
		 */
		if(produto == null){
			session.setAttribute("carShop", car);
			int size = 0;
			
			for(CarrinhoComprasElemento counter: car){
				size = size + counter.getQuantidade();
			}
			
			return size;
		}
		
	     
	     Integer i = findProdId(car, produto);
	     
	     if(i == -1){
	    	 CarrinhoComprasElemento elem = new CarrinhoComprasElemento();
	    	 elem.setProduto(produto);
	    	 elem.setQuantidade(1);
	    	 car.add(elem);
	     }
	     else{
	    	 car.get(i).setQuantidade(car.get(i).getQuantidade() + 1);
	     }
	     
	     session.setAttribute("carShop", car);
	     int size = 0;
			for(CarrinhoComprasElemento counter : car){
				size = size + counter.getQuantidade();
			}
	     return  size;
	}
	
	@Override
	public Integer removerProduto(Produto produto){
		HttpServletRequest httpServletRequest = getThreadLocalRequest();
        HttpSession session = httpServletRequest.getSession();
        
        @SuppressWarnings("unchecked")
		List<CarrinhoComprasElemento> car = (ArrayList<CarrinhoComprasElemento>) session.getAttribute("carShop");
        
        /*
		 * Bug localizado e corrigido, produto null
		 */
		if(produto == null){
			session.setAttribute("carShop", car);
			int size = 0;
			
			for(CarrinhoComprasElemento counter: car){
				size = size + counter.getQuantidade();
			}
			
			return size;
		}
		
		Integer indx = findProdId(car, produto);
		
		/*
		 * Bug compra negativa
		 */
		if (indx != -1){
			car.remove(car.get(indx));
			session.setAttribute("carShop", car);
		}
        
        session.setAttribute("carShop", car);
        
        int size = 0;
		
		for(CarrinhoComprasElemento counter: car){
			size = size + counter.getQuantidade();
		}
		
		return size;
	}
	
	@Override
	public List<CarrinhoComprasElemento> obterCarrinho(){
		
		HttpServletRequest httpServletRequest = getThreadLocalRequest();
        HttpSession session = httpServletRequest.getSession();
        
        @SuppressWarnings("unchecked")
        List<CarrinhoComprasElemento> carrinho =  (ArrayList<CarrinhoComprasElemento>) session.getAttribute("carShop");
        
		return carrinho;
	}
	
	@Override
	public boolean esvaziarCarrinho(){
		HttpServletRequest httpServletRequest = getThreadLocalRequest();
        HttpSession session = httpServletRequest.getSession();
        
        session.removeAttribute("carShop");
        
        initCarShop();
        
		return true;
	}
	
	private Produto getProd(Produto product){
		HttpServletRequest httpServletRequest = getThreadLocalRequest();
        HttpSession session = httpServletRequest.getSession();
        
        @SuppressWarnings("unchecked")
		List<CarrinhoComprasElemento> car = (ArrayList<CarrinhoComprasElemento>) session.getAttribute("carShop");
        
        return car.get(findProdId(car, product)).getProduto();
        
        
	}
	
	private Integer getQuant(Produto product){
		HttpServletRequest httpServletRequest = getThreadLocalRequest();
        HttpSession session = httpServletRequest.getSession();
        
        @SuppressWarnings("unchecked")
		List<CarrinhoComprasElemento> car = (ArrayList<CarrinhoComprasElemento>) session.getAttribute("carShop");
        
        return car.get(findProdId(car, product)).getQuantidade();
	}
	
	private Integer findProdId(List<CarrinhoComprasElemento> car, Produto prod){
		
		for(Integer i=0; i < car.size(); i++){
			if(car.get(i).getProduto().getNome().equals(prod.getNome()))
				return i;
		}
		
		return -1;
	}
	/*
	 * Metodos para realizar os teste com session.
	 */
	public int removerProdutoTest(Produto produto, SessionFake session) {
		@SuppressWarnings("unchecked")
		List<CarrinhoComprasElemento> car = (ArrayList<CarrinhoComprasElemento>) session.getAttribute("carShop");
		
		/*
		 * Bug localizado e corrigido, produto null
		 */
		if(produto == null){
			session.setAttribute("carShop", car);
			int size = 0;
			
			for(CarrinhoComprasElemento counter: car){
				size = size + counter.getQuantidade();
			}
			
			return size;
		}
		
		Integer indx = findProdId(car, produto);
		
		/*
		 * Bug compra negativa
		 */
		if (indx != -1){
			car.remove(car.get(indx));
			session.setAttribute("carShop", car);
		}

		session.setAttribute("carShop", car);
		int size = 0;
		
		for(CarrinhoComprasElemento counter: car){
			size = size + counter.getQuantidade();
		}
		
		return size;
	}

	public void initCarShopTest(SessionFake session) {
		List<CarrinhoComprasElemento> list = new ArrayList<CarrinhoComprasElemento>();
		
		Object obj = session.getAttribute("carShop");
		
		if(obj == null){
			session.setAttribute("carShop", list);
		}
		
	}

	public List<CarrinhoComprasElemento> obterCarrinhoTest(
			SessionFake session) {
		@SuppressWarnings("unchecked")
        List<CarrinhoComprasElemento> carrinho =  (ArrayList<CarrinhoComprasElemento>) session.getAttribute("carShop");
        
		return carrinho;
	}

	public boolean esvaziarCarrinhoTest(SessionFake session) {
		if(session.getAttribute("carShop") == null){
        	return false;
        }
		
		session.removeAttribute("carShop");
		
        initCarShopTest(session);
        
		return true;
		
	}

	public int adicionarProdutoTest(Produto produto, SessionFake session) {
		@SuppressWarnings("unchecked")
		List<CarrinhoComprasElemento> car = (ArrayList<CarrinhoComprasElemento>) session.getAttribute("carShop");
		
		/*
		 * Bug localizado e corrigido, produto null
		 */
		if(produto == null){
			session.setAttribute("carShop", car);
			int size = 0;
			
			for(CarrinhoComprasElemento counter: car){
				size = size + counter.getQuantidade();
			}
			
			return size;
		}
		
		Integer i = findProdId(car, produto);
		
		if(i == -1){
			CarrinhoComprasElemento elem = new CarrinhoComprasElemento();
			elem.setProduto(produto);
			elem.setQuantidade(1);
			car.add(elem);
		}
		else{
			car.get(i).setQuantidade(car.get(i).getQuantidade() + 1);
		}
		
		session.setAttribute("carShop", car);
		int size = 0;
		
		for(CarrinhoComprasElemento counter: car){
			size = size + counter.getQuantidade();
		}
		
		return size;
	}
	
	
}

