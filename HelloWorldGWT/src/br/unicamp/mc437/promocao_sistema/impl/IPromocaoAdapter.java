   
package br.unicamp.mc437.promocao_sistema.impl;

import br.unicamp.mc437.sistema.spec.dt.*;
import br.unicamp.mc437.sistema.spec.req.IPromocao;

class IPromocaoAdapter implements IPromocao{

	public boolean cadastraProm ( Produto p, String promocao ){
		return true;
		
	} 
	public boolean alteraProm ( Produto p ){
		return true;
		
	} 

}