package mc437.software.impl;

import mc437.software.spec.prov.IBuscarProduto;
import mc437.sistema.spec.prov.IManager;
import mc437.software.spec.dt.*;

class IBuscarProdutoFacade implements IBuscarProduto{

	public Produto buscarPorNome ( String nome ){
		mc437.sistema.spec.prov.IBuscarProduto iIBuscarProduto;
		IManager managerSistema = mc437.sistema.impl.ComponentFactory.createInstance();
		iIBuscarProduto = (mc437.sistema.spec.prov.IBuscarProduto)
			managerSistema.getProvidedInterface("IBuscarProduto");
		//iIBuscarProduto.buscarPorNome();
		return null;
		
	} 
	public Produto buscarPorCategoria ( String categoria ){
		mc437.sistema.spec.prov.IBuscarProduto iIBuscarProduto;
		IManager managerSistema = mc437.sistema.impl.ComponentFactory.createInstance();
		iIBuscarProduto = (mc437.sistema.spec.prov.IBuscarProduto)
			managerSistema.getProvidedInterface("IBuscarProduto");
		//iIBuscarProduto.buscarPorCategoria();
		return null;
		
	} 

}