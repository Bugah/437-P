package br.unicamp.mc437.software.impl;

import br.unicamp.mc437.sistema.spec.prov.IManager;
import br.unicamp.mc437.software.spec.prov.IRealizarCompra;


class IRealizarCompraFacade implements IRealizarCompra{

	public boolean realizarCompra (  ){
		br.unicamp.mc437.sistema.spec.prov.IRealizarCompra iIRealizarCompra;
		IManager managerSistema = br.unicamp.mc437.sistema.impl.ComponentFactory.createInstance();
		iIRealizarCompra = (br.unicamp.mc437.sistema.spec.prov.IRealizarCompra)
			managerSistema.getProvidedInterface("IRealizarCompra");
		//iIRealizarCompra.realizarCompra();
		return true;
		
	} 

}