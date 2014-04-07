   
package br.unicamp.mc437.moip_sistema.impl;

import br.unicamp.mc437.sistema.spec.req.IMoip;


class IMoipAdapter implements IMoip{

	public boolean confirmarPagamento ( int id_compra, String tipoPagamento, float valor ){
		return true;
		
	} 

}