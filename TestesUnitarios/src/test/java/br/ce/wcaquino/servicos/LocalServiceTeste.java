package br.ce.wcaquino.servicos;

import java.util.Date;

import org.hamcrest.CoreMatchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.utils.DataUtils;

public class LocalServiceTeste {
	
	@Rule
	public ErrorCollector error = new ErrorCollector();

	@Test
	public void testeQualquer() throws Exception {
	//cenario
	LocacaoService service = new LocacaoService();
	
	Usuario usuario = new Usuario("Usuario 1");
	Filme filme = new Filme("Filme 1", 2, 5.0);
	
	//acao
	
	Locacao locacao = service.alugarFilme(usuario, filme);
	
	//verificacao
	
	error.checkThat(locacao.getValor(), CoreMatchers.is(5.0));
	error.checkThat(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()),  CoreMatchers.is(true));
	error.checkThat(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)), CoreMatchers.is(true));
	}
	
	@Test
	public void testeLocacao_filmeSemEstoque() throws Exception {
		LocacaoService service = new LocacaoService();
		
		Usuario usuario = new Usuario("Usuario 1");
		Filme filme = new Filme("Filme 1", 1, 5.0);
		
		service.alugarFilme(usuario, filme);
	}
}
