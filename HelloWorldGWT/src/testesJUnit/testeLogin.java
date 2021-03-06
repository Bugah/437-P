package testesJUnit;

import static org.junit.Assert.*;

import org.junit.Test;

import br.unicamp.mc437.client.datatypes.Administrador;
import br.unicamp.mc437.client.datatypes.Cliente;
import br.unicamp.mc437.server.LoginServiceImpl;

public class testeLogin {
	
	@Test
	public void testClienteValido(){
		
		String nome = "stephane";
		String senha = "stephane";
		
		LoginServiceImpl ipi = new LoginServiceImpl();
		assertTrue(ipi.loginCliente(nome,senha));
	}
	
	@Test
	public void testClienteNomeIncorreto(){
		
		String nome = "abcd";
		String senha = "stephane";
		
		LoginServiceImpl ipi = new LoginServiceImpl();
		assertTrue(!ipi.loginCliente(nome,senha));
	}
	
	@Test
	public void testClienteSenhaIncorreta(){
		
		String nome = "stephane";
		String senha = "123";
		
		LoginServiceImpl ipi = new LoginServiceImpl();
		assertTrue(!ipi.loginCliente(nome,senha));
	}
	
	@Test
	public void testClienteStringNomeInvalido(){
		
		String nome = "123!@#$";
		String senha = "stephane";
		
		LoginServiceImpl ipi = new LoginServiceImpl();
		assertTrue(!ipi.loginCliente(nome,senha));
	}
	
	@Test
	public void testClienteStringOverflow(){
		
		String nome = "stephane";
		String senha = "stephanestephanestephanestephanestephanestephanestephane";
		
		LoginServiceImpl ipi = new LoginServiceImpl();
		assertTrue(!ipi.loginCliente(nome,senha));
	}
	
	@Test
	public void testAdminValido(){
		
		String nome = "BRYAN";
		String senha = "BRYAN";
		
		LoginServiceImpl ipi = new LoginServiceImpl();
		assertTrue(ipi.loginAdministrador(nome,senha));
	}
	
	@Test
	public void testAdminNomeIncorreto(){
		
		String nome = "BRBRBR";
		String senha = "BRIAN";
		
		LoginServiceImpl ipi = new LoginServiceImpl();
		assertTrue(!ipi.loginCliente(nome,senha));
	}
	
	@Test
	public void testAdminSenhaIncorreta(){
		
		String nome = "BRIAN";
		String senha = "123";
		
		LoginServiceImpl ipi = new LoginServiceImpl();
		assertTrue(!ipi.loginCliente(nome,senha));
	}
	
	@Test
	public void testAdminStringNomeInvalido(){
		
		String nome = "123!@#$";
		String senha = "BRIAN";
		
		LoginServiceImpl ipi = new LoginServiceImpl();
		assertTrue(!ipi.loginCliente(nome,senha));
	}
	
	@Test
	public void testAdminStringOverflow(){
		
		String nome = "BRIAN";
		String senha = "BRIANBRIANBRIANBRIANBRIANBRIANBRIANBRIANBRIANBRIANBRIAN";
		
		LoginServiceImpl ipi = new LoginServiceImpl();
		assertTrue(!ipi.loginCliente(nome,senha));
	}
	
	@Test
	public void testClienteLogado(){
		
		String nome = "stephane";
		String senha = "stephane";
		Cliente cli, cli2;
		cli = new Cliente();
		
		LoginServiceImpl ipi = new LoginServiceImpl();
		ipi.loginCliente(nome,senha);
		cli2 = ipi.getUserOn();
		
		cli.setId(1);
		cli.setNome("stephane");
		cli.setEmail("machin@bidule.fr");
		cli.setSenha("stephane");
		cli.setCpf("23425322");
		cli.setEndereco("234 rua do brasil");
		cli.setCidade("brasilopolis");
		cli.setEstado("mg");
		cli.setTelefone("7897897");
		cli.setUsername("stephane");
		
		assertTrue(cli.getId() == cli2.getId());
		assertTrue(cli.getNome().equals(cli2.getNome()));
		assertTrue(cli.getEmail().equals(cli2.getEmail()));
		assertTrue(cli.getSenha().equals(cli2.getSenha()));
		assertTrue(cli.getCpf().equals(cli2.getCpf()));
		assertTrue(cli.getEndereco().equals(cli2.getEndereco()));
		assertTrue(cli.getCidade().equals(cli2.getCidade()));
		assertTrue(cli.getEstado().equals(cli2.getEstado()));
		assertTrue(cli.getTelefone().equals(cli2.getTelefone()));
		assertTrue(cli.getUsername().equals(cli2.getUsername()));
	}
	
	@Test
	public void testAdminLogado(){
		
		String nome = "BRYAN";
		String senha = "BRYAN";
		Administrador adm, adm2;
		adm = new Administrador();
		
		LoginServiceImpl ipi = new LoginServiceImpl();
		ipi.loginCliente(nome,senha);
		adm2 = ipi.getAdminOn();
		
		adm.setId(3);
		adm.setNomeAdm("BRYAN");
		adm.setEmailAdm("BRYAN");
		adm.setSenhaAdm("BRYAN");
		
		assertTrue(adm.getId() == adm2.getId());
		assertTrue(adm.getNomeAdm().equals(adm2.getNomeAdm()));
		assertTrue(adm.getEmailAdm().equals(adm2.getEmailAdm()));
		assertTrue(adm.getSenhaAdm().equals(adm2.getSenhaAdm()));
	}
	
}