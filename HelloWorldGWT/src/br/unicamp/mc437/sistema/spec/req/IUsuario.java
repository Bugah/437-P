package br.unicamp.mc437.sistema.spec.req;
import br.unicamp.mc437.sistema.spec.dt.*;

public interface IUsuario{

	public Usuario fazerCadastro ( String nome, String username, int cpf, String senha ); 
	public Usuario login ( String username, String senha ); 
}