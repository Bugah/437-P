package testesJUnit;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import br.unicamp.mc437.client.datatypes.Administrador;
import br.unicamp.mc437.client.datatypes.CarrinhoComprasElemento;
import br.unicamp.mc437.client.datatypes.Produto;
import br.unicamp.mc437.client.datatypes.SubCategoria;
import br.unicamp.mc437.server.CarrinhoComprasImpl;
import br.unicamp.mc437.sessionfake.SessionFake;

public class testeCarrinhoDeCompras {

	@Test
	public void iniciarCarrinho(){
		
		SessionFake session = new SessionFake();
		CarrinhoComprasImpl car = new CarrinhoComprasImpl();
		
		car.initCarShopTest(session);
		
		assertTrue(car.obterCarrinhoTest(session).size() == 0);	
	}
	
	@Test
	public void inserirNCarrinho(){
		SessionFake session = new SessionFake();
		CarrinhoComprasImpl car = new CarrinhoComprasImpl();
		
		car.initCarShopTest(session);
		int n = 10000;
		
		Administrador admin = new Administrador();
		admin.setId(1);
		
		for(Integer i=0; i<n; i++){
			Produto p = geraProduto("produto"+i.toString(), "desc"+i.toString(), 0.2*i, admin, i, 9*i);
			
			car.adicionarProdutoTest(p, session);
		}
		
		assertTrue(car.obterCarrinhoTest(session).size() == n);	
	}
	
	@Test
	public void removerNCarrinho1(){
		SessionFake session = new SessionFake();
		CarrinhoComprasImpl car = new CarrinhoComprasImpl();
		
		car.initCarShopTest(session);
		int n = 1000;
		
		Administrador admin = new Administrador();
		admin.setId(1);
		
		for(Integer i=0; i<n; i++){
			Produto p = geraProduto("produto"+i.toString(), "desc"+i.toString(), 0.2*i, admin, i, 9*i);
			
			car.adicionarProdutoTest(p, session);
		}
		
		for(Integer i=0; i<n/2; i++){
			Produto p = geraProduto("produto"+i.toString(), "desc"+i.toString(), 0.2*i, admin, i, 9*i);
			
			car.removerProdutoTest(p, session);
		}
		
		assertTrue(car.obterCarrinhoTest(session).size() == n/2);	
	}
	
	
	@Test
	public void removerNCarrinho2(){
		SessionFake session = new SessionFake();
		CarrinhoComprasImpl car = new CarrinhoComprasImpl();
		
		car.initCarShopTest(session);
		int n = 1000;
		
		Administrador admin = new Administrador();
		admin.setId(1);
		
		for(Integer i=0; i<n; i++){
			Produto p = geraProduto("produto"+i.toString(), "desc"+i.toString(), 0.2*i, admin, i, 9*i);
			
			car.adicionarProdutoTest(p, session);
		}
		
		for(Integer i=0; i<2*n; i++){
			Produto p = geraProduto("produto"+i.toString(), "desc"+i.toString(), 0.2*i, admin, i, 9*i);
			car.removerProdutoTest(p, session);
		}
		
		assertTrue(car.obterCarrinhoTest(session).size() == 0);	
	}
	
	@Test
	public void removeNCarrinhoIntercalado(){
		SessionFake session = new SessionFake();
		CarrinhoComprasImpl car = new CarrinhoComprasImpl();
		
		car.initCarShopTest(session);
		int n = 1000;
		
		Administrador admin = new Administrador();
		admin.setId(1);
		
		for(Integer i=0; i<2*n; i++){
			if(i%2 == 0){
				Produto p = geraProduto("produto"+i.toString(), "desc"+i.toString(), 0.2*i, admin, i, 9*i);
				
				car.adicionarProdutoTest(p, session);
			}
			else{
				Integer k = i-1;
				Produto p = geraProduto("produto"+k.toString(), "desc"+k.toString(), 0.2*k, admin, k, 9*k);
				car.removerProdutoTest(p, session);
			}
		}
		
		assertTrue(car.obterCarrinhoTest(session).size() == 0);	
	}
	
	@Test
	public void esvaziarCarrinho(){
		SessionFake session = new SessionFake();
		CarrinhoComprasImpl car = new CarrinhoComprasImpl();
		
		car.initCarShopTest(session);
		int n = 1000;
		
		Administrador admin = new Administrador();
		admin.setId(1);
		
		for(Integer i=0; i<n; i++){
			Produto p = geraProduto("produto"+i.toString(), "desc"+i.toString(), 0.2*i, admin, i, 9*i);
			
			car.adicionarProdutoTest(p, session);
		}
		
		car.esvaziarCarrinhoTest(session);
		
		assertTrue(car.obterCarrinhoTest(session).size() == 0);	
	}
		
	@Test
	public void inserirNull(){
		SessionFake session = new SessionFake();
		CarrinhoComprasImpl car = new CarrinhoComprasImpl();
		
		car.initCarShopTest(session);
		int n = 1000;
				
		car.adicionarProdutoTest(null, session);
		
		assertTrue(car.obterCarrinhoTest(session).size() == 0);	
	}
	
	@Test
	public void removerNull(){
		SessionFake session = new SessionFake();
		CarrinhoComprasImpl car = new CarrinhoComprasImpl();
		
		car.initCarShopTest(session);
		int n = 1000;
		
		car.adicionarProdutoTest(null, session);
		car.removerProdutoTest(null, session);
		
		assertTrue(car.obterCarrinhoTest(session).size() == 0);	
	}
	
	@Test
	public void returnFncAdd(){
		SessionFake session = new SessionFake();
		CarrinhoComprasImpl car = new CarrinhoComprasImpl();
		
		car.initCarShopTest(session);
		int res = 0, n=10;
		
		Produto p = geraProduto("produto1", "desc", 2.3, null, 0, 10000);
		
		for(int i=0; i<n; i++){
			res = car.adicionarProdutoTest(p, session);
		}
		
		p = geraProduto("produto2", "desc2", 6.3, null, 1, 100);
		
		for(int i=0; i<n; i++){
			res = car.adicionarProdutoTest(p, session);
		}
		
		assertTrue(res==2*n);
		
	}
	
	@Test
	public void returnFncRes(){
		SessionFake session = new SessionFake();
		CarrinhoComprasImpl car = new CarrinhoComprasImpl();
		
		car.initCarShopTest(session);
		int res = 0, n=10;
		
		Produto p1 = geraProduto("produto1", "desc", 2.3, null, 0, 10000);
		Produto p2 = geraProduto("produto2", "desc2", 6.3, null, 1, 100);
		
		for(int i=0; i<n; i++){
			car.adicionarProdutoTest(p1, session);
		}

		res = car.removerProdutoTest(p2, session);
		
		for(int i=0; i<n; i++){
			car.adicionarProdutoTest(p2, session);
		}
		
		res = car.removerProdutoTest(p2, session);
		
		assertTrue(res==n);
		
	}
	
	@Test
	public void returnFncObterCarrinho(){
		SessionFake session = new SessionFake();
		CarrinhoComprasImpl car = new CarrinhoComprasImpl();
		
		car.initCarShopTest(session);
		int n1=10, n2=20;
		
		Produto p1 = geraProduto("produto1", "desc", 2.3, null, 0, 10000);
		Produto p2 = geraProduto("produto2", "desc2", 6.3, null, 1, 100);

		for(int i=0; i<n1; i++){
			car.adicionarProdutoTest(p1, session);
		}

		for(int i=0; i<n2; i++){
			car.adicionarProdutoTest(p2, session);
		}
		
		ArrayList<CarrinhoComprasElemento> res = (ArrayList<CarrinhoComprasElemento>) car.obterCarrinhoTest(session);
		
		
		assertTrue((res.size()==2)&&
				(res.get(0).getQuantidade() == n1)&&
				(res.get(1).getQuantidade() == n2)&&
				(compareProduto(res.get(0).getProduto(), p1))&&
				(compareProduto(res.get(1).getProduto(), p2)));
	}
	
	public Produto geraProduto(String nome, String desc, Double preço, Administrador admin, int id, int estoque){
		Produto p= new Produto();
		p.setAdmin(admin);
		p.setDeletado(0);
		p.setDescricao(desc);
		p.setEstoque(estoque);
		p.setId(id);
		p.setNome(nome);
		p.setPreco(preço);
		p.setPrecoPromocional(29.00);
		ArrayList<SubCategoria> subCats = new ArrayList<SubCategoria>();
		for(int j=1;j<4;j++){
			SubCategoria subCat = new SubCategoria();
			subCat.setId(j);
			subCats.add(subCat);
		}
		p.setSubCat(subCats);
		
		return p;
	}

	public boolean compareProduto(Produto p1, Produto p2){
		
		if(p1.getDeletado() != p2.getDeletado()) return false;
		if(p1.getDescricao() != p2.getDescricao()) return false;
		if(p1.getEstoque() != p2.getEstoque()) return false;
		if(p1.getId() != p2.getId()) return false;
		if(p1.getNome() != p2.getNome()) return false;
		if(p1.getPreco() != p2.getPreco()) return false;
		if(p1.getPrecoPromocional() != p2.getPrecoPromocional()) return false;
		
		return true;
	}
	
}
