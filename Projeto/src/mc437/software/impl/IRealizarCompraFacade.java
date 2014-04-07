package mc437.software.impl;

import mc437.software.spec.prov.IRealizarCompra;
import mc437.sistema.spec.prov.IManager;


class IRealizarCompraFacade implements IRealizarCompra{

	public boolean realizarCompra (  ){
		mc437.sistema.spec.prov.IRealizarCompra iIRealizarCompra;
		IManager managerSistema = mc437.sistema.impl.ComponentFactory.createInstance();
		iIRealizarCompra = (mc437.sistema.spec.prov.IRealizarCompra)
			managerSistema.getProvidedInterface("IRealizarCompra");
		//iIRealizarCompra.realizarCompra();
		return true;
		
	} 

}