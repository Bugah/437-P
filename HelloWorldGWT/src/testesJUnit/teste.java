package testesJUnit;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import br.unicamp.mc437.client.datatypes.Administrador;
import br.unicamp.mc437.client.datatypes.Produto;
import br.unicamp.mc437.client.datatypes.SubCategoria;
import br.unicamp.mc437.server.InserirProdutoImpl;

public class teste {
	@Test
	public void test1() {
		Produto p= new Produto();
		Administrador admin = new Administrador();
		admin.setId(1);
		p.setAdmin(admin);
		p.setDeletado(0);
		p.setDescricao("wouhou");
		p.setEstoque(8000);
		p.setId(0);
		p.setNome("waka waka");
		p.setPreco(30.00);
		p.setPrecoPromocional(29.00);
		ArrayList<SubCategoria> subCats = new ArrayList<SubCategoria>();
		for(int i=1;i<4;i++){
			SubCategoria subCat = new SubCategoria();
			subCat.setId(i);
			subCats.add(subCat);
		}
		p.setSubCat(subCats);
		
		InserirProdutoImpl insPr = new InserirProdutoImpl();
		insPr.inserirProdutoServer(p);
		
		
		assertTrue(250 == 250);
		//esse caso de teste está correto?
		//esse caso de teste exercita bem a lógica do sistema?
		//DICA: o testador deve validar a lógica do negócio (requisitos)
	}
}
