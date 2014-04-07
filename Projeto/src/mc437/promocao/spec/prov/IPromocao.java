package mc437.promocao.spec.prov;
import mc437.promocao.spec.dt.*;

public interface IPromocao{

	public boolean cadastraProm ( Produto p, String promocao ); 
	public boolean alteraProm ( Produto p ); 
}