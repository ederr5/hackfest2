package teste;

import models.Evento;
import models.Pessoa;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import models.Sistema;


public class hackTest {
	private Sistema sistema;
	private Evento evento1;
	private Pessoa pessoa1;
	
	
	@Before
	public void iniciar(){
		evento1 = new Evento("Dados abertos", "Esse evento tem o objetivo de realizar atividades com dados abertos	", "10/08/2014", "eu", "eu@eu");
		try {
			pessoa1 = new Pessoa("eu@eu", "123","eu");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	 * Os testes abaixo sao relativos ao evento
	 */
	
	
	@Test
	public void testAddMesmaPessoaNoEvento(){
		sistema.addEvento(evento1);
		sistema.addPessoaNoEvento(evento1, pessoa1);
		sistema.addPessoaNoEvento(evento1, pessoa1);
		Assert.assertTrue(evento1.numDePessoasQueConfirmaram() == 1 );
	}
	
	@Test
	public void testAddPessoaNoEvento(){
		sistema.addEvento(evento1);
		sistema.addPessoaNoEvento(evento1, pessoa1);
		Assert.assertTrue(evento1.numDePessoasQueConfirmaram() == 1 );
		
	}
	
	@Test
	public void testAdicionarMesmoEvento(){
		int anterior = sistema.getEventos().size();
		sistema.addEvento(evento1);
		sistema.addEvento(evento1);
		Assert.assertTrue(sistema.getEventos().size() == anterior + 1);
	}
}
