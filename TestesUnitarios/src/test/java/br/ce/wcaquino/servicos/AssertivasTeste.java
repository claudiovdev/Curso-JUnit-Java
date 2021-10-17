package br.ce.wcaquino.servicos;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Assert;
import org.junit.Test;

import br.ce.wcaquino.entidades.Usuario;

public class AssertivasTeste {

	@Test
	public void teste() {
		Assert.assertTrue(true);// Recebe como valor um Boolean
		Assert.assertFalse(false);// recebe como um parametro um boolean que se for false o teste passa
		
		Assert.assertEquals(1, 1);// Ele recebe 2 parametros e compara se são iguais..
		Assert.assertEquals(0.51, 0.51, 0.01);// o 0.01 é a margem de erro para comparação
		Assert.assertEquals(Math.PI, 3.14, 0.01);// um exemplo valido.
		
		int i = 0;
		Integer i2 = 0;
		Assert.assertEquals(Integer.valueOf(i), i2);
		Assert.assertEquals(i, i2.intValue());// Mesmo as duas variaveis sendo inteiras, são de primitivos diferentes
		
		// Testando String
		Assert.assertEquals("Oi", "Oi");
		Assert.assertNotEquals("Oi", "Bye");
		Assert.assertTrue("Oi".equalsIgnoreCase("oi"));
		
		// Comprando Objetos
		
		Usuario u1 = new Usuario("Vinicius");
		Usuario u2 = new Usuario("Vinicius");
		Usuario u3 = null;
		
		
		
		Assert.assertEquals(u1, u2);
		Assert.assertSame(u2, u2); // faz comparação as referencias;
		Assert.assertNotSame(u1, u2); // faz comparação as referencias;
		Assert.assertTrue(u3 == null);
		assertNull(u3);
		assertNotNull(u2);
		
		
	}
}
