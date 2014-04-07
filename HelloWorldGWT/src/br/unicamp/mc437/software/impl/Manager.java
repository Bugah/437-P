package br.unicamp.mc437.software.impl;

import java.util.*;
import br.unicamp.mc437.software.spec.prov.*;

class Manager implements IManager{

	Map reqInterfaceMap = new HashMap();

	Manager(){
	
		// Instantiate Components and Connectors
		
		br.unicamp.mc437.carrinhocompras.spec.prov.IManager managerCarrinhoCompras =
		br.unicamp.mc437.carrinhocompras.impl.ComponentFactory.createInstance();
		
		br.unicamp.mc437.carrinhocompras_sistema.impl.IManager managerCarrinhoCompras_Sistema =
		br.unicamp.mc437.carrinhocompras_sistema.impl.ComponentFactory.createInstance();
		
		br.unicamp.mc437.compra.spec.prov.IManager managerCompra =
		br.unicamp.mc437.compra.impl.ComponentFactory.createInstance();
		
		br.unicamp.mc437.compra_sistema.impl.IManager managerCompra_Sistema =
		br.unicamp.mc437.compra_sistema.impl.ComponentFactory.createInstance();
		
		br.unicamp.mc437.correio.spec.prov.IManager managerCorreio =
		br.unicamp.mc437.correio.impl.ComponentFactory.createInstance();
		
		br.unicamp.mc437.correio_sistema.impl.IManager managerCorreio_Sistema =
		br.unicamp.mc437.correio_sistema.impl.ComponentFactory.createInstance();
		
		br.unicamp.br.unicamp.mc437.moip.spec.prov.IManager managerMoip =
		br.unicamp.mc437.moip.impl.ComponentFactory.createInstance();
		
		br.unicamp.mc437.moip_sistema.impl.IManager managerMoip_Sistema =
		br.unicamp.mc437.moip_sistema.impl.ComponentFactory.createInstance();
		
		br.unicamp.mc437.produto.spec.prov.IManager managerProduto =
		br.unicamp.mc437.produto.impl.ComponentFactory.createInstance();
		
		br.unicamp.mc437.produto_sistema.impl.IManager managerProduto_Sistema =
		br.unicamp.mc437.produto_sistema.impl.ComponentFactory.createInstance();
		
		br.unicamp.mc437.promocao.spec.prov.IManager managerPromocao =
		br.unicamp.mc437.promocao.impl.ComponentFactory.createInstance();
		
		br.unicamp.mc437.promocao_sistema.impl.IManager managerPromocao_Sistema =
		br.unicamp.mc437.promocao_sistema.impl.ComponentFactory.createInstance();
		
		br.unicamp.mc437.sistema.spec.prov.IManager managerSistema =
		br.unicamp.mc437.sistema.impl.ComponentFactory.createInstance();
		
		br.unicamp.mc437.usuario.spec.prov.IManager managerUsuario =
		br.unicamp.mc437.usuario.impl.ComponentFactory.createInstance();
		
		br.unicamp.mc437.usuario_sistema.impl.IManager managerUsuario_Sistema =
		br.unicamp.mc437.usuario_sistema.impl.ComponentFactory.createInstance();
		
	
		// Connect Interfaces
		
		br.unicamp.mc437.sistema.spec.req.ICompra compra_sistema_ICompra = 
			(br.unicamp.mc437.sistema.spec.req.ICompra)
			managerCompra_Sistema.getProvidedInterface("ICompra");
			managerSistema.setRequiredInterface("ICompra",compra_sistema_ICompra);
		
		br.unicamp.mc437.compra.spec.prov.ICompra compra_ICompra = 
			(br.unicamp.mc437.compra.spec.prov.ICompra)
			managerCompra.getProvidedInterface("ICompra");
			managerCompra_Sistema.setRequiredInterface("ICompra",compra_ICompra);
		
		br.unicamp.mc437.usuario.spec.prov.IUsuario usuario_IUsuario = 
			(br.unicamp.mc437.usuario.spec.prov.IUsuario)
			managerUsuario.getProvidedInterface("IUsuario");
			managerUsuario_Sistema.setRequiredInterface("IUsuario",usuario_IUsuario);
		
		br.unicamp.mc437.sistema.spec.req.IUsuario usuario_sistema_IUsuario = 
			(br.unicamp.mc437.sistema.spec.req.IUsuario)
			managerUsuario_Sistema.getProvidedInterface("IUsuario");
			managerSistema.setRequiredInterface("IUsuario",usuario_sistema_IUsuario);
		
		br.unicamp.mc437.sistema.spec.req.IPromocao promocao_sistema_IPromocao = 
			(br.unicamp.mc437.sistema.spec.req.IPromocao)
			managerPromocao_Sistema.getProvidedInterface("IPromocao");
			managerSistema.setRequiredInterface("IPromocao",promocao_sistema_IPromocao);
		
		br.unicamp.mc437.promocao.spec.prov.IPromocao promocao_IPromocao = 
			(br.unicamp.mc437.promocao.spec.prov.IPromocao)
			managerPromocao.getProvidedInterface("IPromocao");
			managerPromocao_Sistema.setRequiredInterface("IPromocao",promocao_IPromocao);
		
		br.unicamp.mc437.sistema.spec.req.IProduto produto_sistema_IProduto = 
			(br.unicamp.mc437.sistema.spec.req.IProduto)
			managerProduto_Sistema.getProvidedInterface("IProduto");
			managerSistema.setRequiredInterface("IProduto",produto_sistema_IProduto);
		
		br.unicamp.mc437.produto.spec.prov.IProduto produto_IProduto = 
			(br.unicamp.mc437.produto.spec.prov.IProduto)
			managerProduto.getProvidedInterface("IProduto");
			managerProduto_Sistema.setRequiredInterface("IProduto",produto_IProduto);
		
		br.unicamp.br.unicamp.mc437.moip.spec.prov.IMoip moip_IMoip = 
			(br.unicamp.br.unicamp.mc437.moip.spec.prov.IMoip)
			managerMoip.getProvidedInterface("IMoip");
			managerMoip_Sistema.setRequiredInterface("IMoip",moip_IMoip);
		
		br.unicamp.mc437.sistema.spec.req.IMoip moip_sistema_IMoip = 
			(br.unicamp.mc437.sistema.spec.req.IMoip)
			managerMoip_Sistema.getProvidedInterface("IMoip");
			managerSistema.setRequiredInterface("IMoip",moip_sistema_IMoip);
		
		br.unicamp.mc437.sistema.spec.req.ICorreio correio_sistema_ICorreio = 
			(br.unicamp.mc437.sistema.spec.req.ICorreio)
			managerCorreio_Sistema.getProvidedInterface("ICorreio");
			managerSistema.setRequiredInterface("ICorreio",correio_sistema_ICorreio);
		
		br.unicamp.mc437.correio.spec.prov.ICorreio correio_ICorreio = 
			(br.unicamp.mc437.correio.spec.prov.ICorreio)
			managerCorreio.getProvidedInterface("ICorreio");
			managerCorreio_Sistema.setRequiredInterface("ICorreio",correio_ICorreio);
		
		br.unicamp.mc437.carrinhocompras.spec.prov.ICarrinhoCompras carrinhocompras_ICarrinhoCompras = 
			(br.unicamp.mc437.carrinhocompras.spec.prov.ICarrinhoCompras)
			managerCarrinhoCompras.getProvidedInterface("ICarrinhoCompras");
			managerCarrinhoCompras_Sistema.setRequiredInterface("ICarrinhoCompras",carrinhocompras_ICarrinhoCompras);
		
		br.unicamp.mc437.sistema.spec.req.ICarrinhoCompras carrinhocompras_sistema_ICarrinhoCompras = 
			(br.unicamp.mc437.sistema.spec.req.ICarrinhoCompras)
			managerCarrinhoCompras_Sistema.getProvidedInterface("ICarrinhoCompras");
			managerSistema.setRequiredInterface("ICarrinhoCompras",carrinhocompras_sistema_ICarrinhoCompras);
		
		
		// Set required maps
		
	}

	public String[] getProvidedInterfaces(){
	   List provInterfaceList = new ArrayList();
	   provInterfaceList.add("ICadastrarProduto");
	     
	   provInterfaceList.add("IBuscarProduto");
	     
	   provInterfaceList.add("IRealizarCompra");
	     
	   
	   return convertListToArray(provInterfaceList);
	}
	
	public String[] getRequiredInterfaces(){
	
		return convertListToArray(reqInterfaceMap.keySet());
	}
	
	public Object getProvidedInterface(String name){

	   if (name.equals("ICadastrarProduto")){
	   		return new ICadastrarProdutoFacade();
	   }
	   
	   if (name.equals("IBuscarProduto")){
	   		return new IBuscarProdutoFacade();
	   }
	   
	   if (name.equals("IRealizarCompra")){
	   		return new IRealizarCompraFacade();
	   }
	   
	   return null;
	}
	
	public void setRequiredInterface(String name, Object facade){
		reqInterfaceMap.put(name,facade);
	}
	
	public Object getRequiredInterface(String name){
	   return reqInterfaceMap.get(name);
	}
	
	private String[] convertListToArray(Collection stringCollection){
		String[] stringArray = new String[stringCollection.size()];
		int i=0;
		for (Iterator iter=stringCollection.iterator();iter.hasNext();){
			stringArray[i] = (String)iter.next();
			i++;
		}
		return stringArray;
	}
}