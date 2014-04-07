package br.unicamp.mc437.correio_sistema.impl;

import br.unicamp.mc437.correio_sistema.impl.IManager;

public class ComponentFactory {

	private static IManager manager = null;

	public static IManager createInstance(){
	
		if (manager==null)
			manager = new Manager();
		
		return manager;
	}
}



