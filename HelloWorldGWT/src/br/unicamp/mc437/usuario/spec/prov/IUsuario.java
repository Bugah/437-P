package br.unicamp.mc437.usuario.spec.prov;
import br.unicamp.mc437.usuario.spec.dt.*;

public interface IUsuario{

	public Usuario fazerCadastro ( String nome, String username, int cpf, String senha ); 
	public Usuario login ( String username, String senha ); 
}