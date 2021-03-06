package br.unicamp.mc437.correio_sistema.impl;

import java.util.*;

class Manager implements IManager{

	Map reqInterfaceMap = new HashMap();

	public String[] getProvidedInterfaces(){
	   List provInterfaceList = new ArrayList();
	   provInterfaceList.add("ICorreio");
	     
	   
	   return convertListToArray(provInterfaceList);
	}
	
	public String[] getRequiredInterfaces(){
	
		return convertListToArray(reqInterfaceMap.keySet());
	}
	
	public Object getProvidedInterface(String name){

	   if (name.equals("ICorreio")){
	   		return new ICorreioAdapter();
	   }
	   
	   return null;
	}
	
	public void setRequiredInterface(String name, Object adapter){
		reqInterfaceMap.put(name,adapter);
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



