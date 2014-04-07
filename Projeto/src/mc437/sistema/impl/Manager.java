package mc437.sistema.impl;

import java.util.*;import mc437.sistema.spec.prov.*;

class Manager implements IManager{

	Map reqInterfaceMap = new HashMap();

	public String[] getProvidedInterfaces(){
	   List provInterfaceList = new ArrayList();
	   provInterfaceList.add("IRealizarCompra");
	     
	   provInterfaceList.add("IBuscarProduto");
	     
	   provInterfaceList.add("ICadastrarProduto");
	     
	   
	   return convertListToArray(provInterfaceList);
	}
	
	public String[] getRequiredInterfaces(){
	
		return convertListToArray(reqInterfaceMap.keySet());
	}
	
	public Object getProvidedInterface(String name){

	   if (name.equals("IRealizarCompra")){
	   		return new IRealizarCompraFacade();
	   }
	   
	   if (name.equals("IBuscarProduto")){
	   		return new IBuscarProdutoFacade();
	   }
	   
	   if (name.equals("ICadastrarProduto")){
	   		return new ICadastrarProdutoFacade();
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



