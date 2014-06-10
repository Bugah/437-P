package testesJUnit;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import br.unicamp.mc437.client.datatypes.Administrador;
import br.unicamp.mc437.client.datatypes.Produto;
import br.unicamp.mc437.client.datatypes.SubCategoria;
import br.unicamp.mc437.server.InserirProdutoImpl;

public class testeInserirProduto {
	

	

	/*@Override
	public String getModuleName() {
		return "br.unicamp.mc437.HelloWorldGWT";
	}
	*/
		@Test
		public void testNormalInsert(){
			
			Produto prod = new Produto();
			
			Administrador admin = new Administrador();
			admin.setId(1);
			prod.setAdmin(admin);
		
			prod.setDeletado(0);
			prod.setDescricao("blabla");
			prod.setEstoque(4);
			prod.setNome("testJMusclo!");
			prod.setPreco(6.56);
			prod.setPrecoPromocional(6);
			prod.setUrlImagemUnica("http://www.avisproteine.com/wp-content/uploads/2010/11/reflex-instant-whey.jpg");
			
			ArrayList<SubCategoria> subCats = new ArrayList<SubCategoria>();
			for(int i=1;i<4;i++){
				SubCategoria subCat = new SubCategoria();
				subCat.setId(i);
				subCats.add(subCat);
			}
			prod.setSubCat(subCats);
			InserirProdutoImpl ipi = new InserirProdutoImpl();
	assertTrue(ipi.inserirProdutoServer(prod));
			
		}

		@Test
		public void testNegativePrice(){
			
			Produto prod = new Produto();
			
			Administrador admin = new Administrador();
			admin.setId(1);
			prod.setAdmin(admin);
		
			prod.setDeletado(0);
			prod.setDescricao("blabla");
			prod.setEstoque(4);
			prod.setNome("testJMusclo!");
			prod.setPreco(-6.56);
			prod.setPrecoPromocional(6);
			prod.setUrlImagemUnica("http://www.avisproteine.com/wp-content/uploads/2010/11/reflex-instant-whey.jpg");
			
			ArrayList<SubCategoria> subCats = new ArrayList<SubCategoria>();
			for(int i=1;i<4;i++){
				SubCategoria subCat = new SubCategoria();
				subCat.setId(i);
				subCats.add(subCat);
			}
			prod.setSubCat(subCats);
			InserirProdutoImpl ipi = new InserirProdutoImpl();
	assertTrue(!ipi.inserirProdutoServer(prod));
			
		}

		@Test
		public void testZeroPrice(){
			
			Produto prod = new Produto();
			
			Administrador admin = new Administrador();
			admin.setId(1);
			prod.setAdmin(admin);
		
			prod.setDeletado(0);
			prod.setDescricao("blabla");
			prod.setEstoque(4);
			prod.setNome("testJMusclo!");
			prod.setPreco(0);
			prod.setPrecoPromocional(6);
			prod.setUrlImagemUnica("http://www.avisproteine.com/wp-content/uploads/2010/11/reflex-instant-whey.jpg");
			
			ArrayList<SubCategoria> subCats = new ArrayList<SubCategoria>();
			for(int i=1;i<4;i++){
				SubCategoria subCat = new SubCategoria();
				subCat.setId(i);
				subCats.add(subCat);
			}
			prod.setSubCat(subCats);
			InserirProdutoImpl ipi = new InserirProdutoImpl();
	assertTrue(ipi.inserirProdutoServer(prod));
			
		}
		
		@Test
		public void testNegativePromoPrice(){
			
			Produto prod = new Produto();
			
			Administrador admin = new Administrador();
			admin.setId(1);
			prod.setAdmin(admin);
		
			prod.setDeletado(0);
			prod.setDescricao("blabla");
			prod.setEstoque(4);
			prod.setNome("testJMusclo!");
			prod.setPreco(6.56);
			prod.setPrecoPromocional(-6);
			prod.setUrlImagemUnica("http://www.avisproteine.com/wp-content/uploads/2010/11/reflex-instant-whey.jpg");
			
			ArrayList<SubCategoria> subCats = new ArrayList<SubCategoria>();
			for(int i=1;i<4;i++){
				SubCategoria subCat = new SubCategoria();
				subCat.setId(i);
				subCats.add(subCat);
			}
			prod.setSubCat(subCats);
			InserirProdutoImpl ipi = new InserirProdutoImpl();
	assertTrue(!ipi.inserirProdutoServer(prod));
			
		}

		
		@Test
		public void testZeroPromoPrice(){
			
			Produto prod = new Produto();
			
			Administrador admin = new Administrador();
			admin.setId(1);
			prod.setAdmin(admin);
		
			prod.setDeletado(0);
			prod.setDescricao("blabla");
			prod.setEstoque(4);
			prod.setNome("testJMusclo!");
			prod.setPreco(6.56);
			prod.setPrecoPromocional(0);
			prod.setUrlImagemUnica("http://www.avisproteine.com/wp-content/uploads/2010/11/reflex-instant-whey.jpg");
			
			ArrayList<SubCategoria> subCats = new ArrayList<SubCategoria>();
			for(int i=1;i<4;i++){
				SubCategoria subCat = new SubCategoria();
				subCat.setId(i);
				subCats.add(subCat);
			}
			prod.setSubCat(subCats);
			InserirProdutoImpl ipi = new InserirProdutoImpl();
	assertTrue(ipi.inserirProdutoServer(prod));
			
		}
		
		@Test
		public void testNegativeStorage(){
			
			Produto prod = new Produto();
			
			Administrador admin = new Administrador();
			admin.setId(1);
			prod.setAdmin(admin);
		
			prod.setDeletado(0);
			prod.setDescricao("blabla");
			prod.setEstoque(-4);
			prod.setNome("testJMusclo!");
			prod.setPreco(6.56);
			prod.setPrecoPromocional(6);
			prod.setUrlImagemUnica("http://www.avisproteine.com/wp-content/uploads/2010/11/reflex-instant-whey.jpg");
			
			ArrayList<SubCategoria> subCats = new ArrayList<SubCategoria>();
			for(int i=1;i<4;i++){
				SubCategoria subCat = new SubCategoria();
				subCat.setId(i);
				subCats.add(subCat);
			}
			prod.setSubCat(subCats);
			InserirProdutoImpl ipi = new InserirProdutoImpl();
	assertTrue(!ipi.inserirProdutoServer(prod));
			
		}
		
		@Test
		public void testZeroStorage(){
			
			Produto prod = new Produto();
			
			Administrador admin = new Administrador();
			admin.setId(1);
			prod.setAdmin(admin);
		
			prod.setDeletado(0);
			prod.setDescricao("blabla");
			prod.setEstoque(0);
			prod.setNome("testJMusclo!");
			prod.setPreco(6.56);
			prod.setPrecoPromocional(6);
			prod.setUrlImagemUnica("http://www.avisproteine.com/wp-content/uploads/2010/11/reflex-instant-whey.jpg");
			
			ArrayList<SubCategoria> subCats = new ArrayList<SubCategoria>();
			for(int i=1;i<4;i++){
				SubCategoria subCat = new SubCategoria();
				subCat.setId(i);
				subCats.add(subCat);
			}
			prod.setSubCat(subCats);
			InserirProdutoImpl ipi = new InserirProdutoImpl();
	assertTrue(ipi.inserirProdutoServer(prod));
			
		}
		
		@Test
		public void testAbsenceCat(){
			
			Produto prod = new Produto();
			
			Administrador admin = new Administrador();
			admin.setId(1);
			prod.setAdmin(admin);
		
			prod.setDeletado(0);
			prod.setDescricao("blabla");
			prod.setEstoque(4);
			prod.setNome("testJMusclo!");
			prod.setPreco(6.56);
			prod.setPrecoPromocional(6);
			prod.setUrlImagemUnica("http://www.avisproteine.com/wp-content/uploads/2010/11/reflex-instant-whey.jpg");
			
			ArrayList<SubCategoria> subCats = new ArrayList<SubCategoria>();
			prod.setSubCat(subCats);
			InserirProdutoImpl ipi = new InserirProdutoImpl();
	assertTrue(!ipi.inserirProdutoServer(prod));
			
		}
		
		@Test
		public void testAbsenceName(){
			
			Produto prod = new Produto();
			
			Administrador admin = new Administrador();
			admin.setId(1);
			prod.setAdmin(admin);
		
			prod.setDeletado(0);
			prod.setDescricao("blabla");
			prod.setEstoque(4);
			prod.setNome("");
			prod.setPreco(6.56);
			prod.setPrecoPromocional(6);
			prod.setUrlImagemUnica("http://www.avisproteine.com/wp-content/uploads/2010/11/reflex-instant-whey.jpg");
			
			ArrayList<SubCategoria> subCats = new ArrayList<SubCategoria>();
			for(int i=1;i<4;i++){
				SubCategoria subCat = new SubCategoria();
				subCat.setId(i);
				subCats.add(subCat);
			}
			prod.setSubCat(subCats);
			InserirProdutoImpl ipi = new InserirProdutoImpl();
	assertTrue(!ipi.inserirProdutoServer(prod));
			
		}
		
}
