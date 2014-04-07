package br.unicamp.mc437.software.impl;

import br.unicamp.mc437.sistema.spec.prov.IManager;
import br.unicamp.mc437.software.spec.dt.*;
import br.unicamp.mc437.software.spec.prov.IBuscarProduto;

class IBuscarProdutoFacade implements IBuscarProduto{

	public Produto buscarPorNome ( String nome ){
		br.unicamp.mc437.sistema.spec.prov.IBuscarProduto iIBuscarProduto;
		IManager managerSistema = br.unicamp.mc437.sistema.impl.ComponentFactory.createInstance();
		iIBuscarProduto = (br.unicamp.mc437.sistema.spec.prov.IBuscarProduto)
			managerSistema.getProvidedInterface("IBuscarProduto");
		//iIBuscarProduto.buscarPorNome();
		return null;
		
	} 
	public Produto buscarPorCategoria ( String categoria ){
		br.unicamp.mc437.sistema.spec.prov.IBuscarProduto iIBuscarProduto;
		IManager managerSistema = br.unicamp.mc437.sistema.impl.ComponentFactory.createInstance();
		iIBuscarProduto = (br.unicamp.mc437.sistema.spec.prov.IBuscarProduto)
			managerSistema.getProvidedInterface("IBuscarProduto");
		//iIBuscarProduto.buscarPorCategoria();
		return null;
		
	} 

}