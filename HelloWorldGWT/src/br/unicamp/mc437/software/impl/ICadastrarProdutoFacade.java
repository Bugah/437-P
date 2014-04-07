package br.unicamp.mc437.software.impl;

import br.unicamp.mc437.sistema.spec.prov.IManager;
import br.unicamp.mc437.software.spec.dt.*;
import br.unicamp.mc437.software.spec.prov.ICadastrarProduto;

class ICadastrarProdutoFacade implements ICadastrarProduto{

	public Boolean cadastrarProduto ( Produto p ){
		br.unicamp.mc437.sistema.spec.prov.ICadastrarProduto iICadastrarProduto;
		IManager managerSistema = br.unicamp.mc437.sistema.impl.ComponentFactory.createInstance();
		iICadastrarProduto = (br.unicamp.mc437.sistema.spec.prov.ICadastrarProduto)
			managerSistema.getProvidedInterface("ICadastrarProduto");
		//iICadastrarProduto.cadastrarProduto();
		return null;
		
	} 

}