package mc437.compra.impl;

import java.util.*;import mc437.compra.spec.prov.*;

class Manager implements IManager{

	Map reqInterfaceMap = new HashMap();

	public String[] getProvidedInterfaces(){
	   List provInterfaceList = new ArrayList();
	   provInterfaceList.add("ICompra");
	     
	   
	   return convertListToArray(provInterfaceList);
	}
	
	public String[] getRequiredInterfaces(){
	
		return convertListToArray(reqInterfaceMap.keySet());
	}
	
	public Object getProvidedInterface(String name){

	   if (name.equals("ICompra")){
	   		return new ICompraFacade();
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



