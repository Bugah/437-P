package br.unicamp.mc437.client;

import java.util.ArrayList;
import java.util.HashMap;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("subCategoria")
public interface SubCategoriaService extends RemoteService {
	
	ArrayList<HashMap<String, String>> getSubCategorias() throws IllegalArgumentException;
}
