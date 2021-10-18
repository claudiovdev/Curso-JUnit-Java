package br.ce.wcaquino.servicos;

import static org.hamcrest.CoreMatchers.is;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.execptions.FilmeSemEstoqueExcpetion;
import br.ce.wcaquino.execptions.LocadoraException;
import br.ce.wcaquino.utils.DataUtils;

public class LocalServiceTeste {
	
	private LocacaoService service;
	
	
	
	@Rule
	public ErrorCollector error = new ErrorCollector();
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Before
	public void setup() {
		 service = new LocacaoService();
	}
	
	
	
	
	@Test
	public void testeLocacao() throws Exception {
	//cenario
	
	Usuario usuario = new Usuario("Usuario 1");
	List<Filme> filmes = Arrays.asList(new Filme("Filme 1", 2, 5.0));
	
	
	
	//acao
	
	Locacao locacao = service.alugarFilme(usuario, filmes);
		
		error.checkThat(locacao.getValor(), CoreMatchers.is(5.0));
		error.checkThat(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()),  CoreMatchers.is(true));
		error.checkThat(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)), CoreMatchers.is(true));
	
	
	//verificacao
	}
	
	// exeção elegante
	@Test(expected = FilmeSemEstoqueExcpetion.class)
	public void testeLocacao_filmeSemEstoque() throws Exception {
		
		Usuario usuario = new Usuario("Usuario 1");
		List<Filme> filmes = Arrays.asList(new Filme("Filme 1", 0, 5.0));
		
		service.alugarFilme(usuario, filmes);
	}
	
	@Test
	public void testeLocacao_usuarioVazio() throws FilmeSemEstoqueExcpetion {
		List<Filme> filmes = Arrays.asList(new Filme("Filme 1", 2, 5.0));
		
		
			try {
				service.alugarFilme(null, filmes);
				Assert.fail();
			} catch (LocadoraException e) {
				Assert.assertThat(e.getMessage(), is("Usuario vazio"));
			}
		
	}
	
	@Test
	public void testeLocacao_filmeVazio() throws FilmeSemEstoqueExcpetion, LocadoraException {
		Usuario usuario = new Usuario("Usuario 1");
		
		exception.expect(LocadoraException.class);
		exception.expectMessage("Filme vazio");
		
		service.alugarFilme(usuario, null);
		
		
		
	}
}
