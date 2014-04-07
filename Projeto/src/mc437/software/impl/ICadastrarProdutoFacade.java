package mc437.software.impl;

import mc437.software.spec.prov.ICadastrarProduto;
import mc437.sistema.spec.prov.IManager;
import mc437.software.spec.dt.*;

class ICadastrarProdutoFacade implements ICadastrarProduto{

	public Boolean cadastrarProduto ( Produto p ){
		mc437.sistema.spec.prov.ICadastrarProduto iICadastrarProduto;
		IManager managerSistema = mc437.sistema.impl.ComponentFactory.createInstance();
		iICadastrarProduto = (mc437.sistema.spec.prov.ICadastrarProduto)
			managerSistema.getProvidedInterface("ICadastrarProduto");
		//iICadastrarProduto.cadastrarProduto();
		return null;
		
	} 

}