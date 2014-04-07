package br.unicamp.mc437.promocao.impl;

import br.unicamp.mc437.promocao.spec.prov.IManager;

public class ComponentFactory {

	private static IManager manager = null;

	public static IManager createInstance(){
	
		if (manager==null)
			manager = new Manager();
		
		return manager;
	}
}



