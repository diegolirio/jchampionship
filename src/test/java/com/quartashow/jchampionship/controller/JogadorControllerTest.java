package com.quartashow.jchampionship.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.quartashow.jchampionship.dao.EdicaoDao;
import com.quartashow.jchampionship.dao.JogadorDao;
import com.quartashow.jchampionship.dao.PosicaoDao;
import com.quartashow.jchampionship.model.Edicao;
import com.quartashow.jchampionship.model.Posicao;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath*:spring-context.xml" })
public class JogadorControllerTest {

	@InjectMocks
	private JogadorController controller;

	private MockMvc mockMvc;
	
	@Mock
	private JogadorDao timeDao;

	@Mock
	private PosicaoDao posicaoDao;

	@Mock
	private EdicaoDao edicaoDao;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void pageSimpleJogadorParaCadastroRapidoDeveConterViewModels() throws Exception {
		
		@SuppressWarnings("unchecked")
		List<Posicao> posicoes = Mockito.mock(List.class);
		Mockito.when(this.posicaoDao.getList(Posicao.class)).thenReturn(posicoes);
		
		mockMvc.perform(get("/jogador/page/simple"))
			.andExpect(status().isOk())
			.andExpect(view().name("_base_simple"))
			.andExpect(model().attributeExists("content_import", "posicoes"))
			.andExpect(model().attribute("content_import", "jogador-system-form"));
	}
	
	@Test
	public void jogadorPost() throws Exception {
		ResultActions resultActions = 
			mockMvc.perform(post("/jogador/post")
				.param("nome", "Diego Lirio")
				.param("posicao.id", "1"))
			 .andExpect(status().isCreated());
		String uri = resultActions.andReturn().getResponse().getHeader("Location");		
		mockMvc.perform(get(uri)).andExpect(status().isOk());
	}
	
	@Test
	public void jogadorPostNomeInvalido() throws Exception {
			mockMvc.perform(post("/jogador/post")
				.param("nome", ""))
			 .andExpect(status().isUnauthorized());
	}	

	@Test
	public void testDeveRetornarPaginaDeJogadoresPorEdicao() throws Exception {
		Edicao edicao = new Edicao(1l);
		Mockito.when(edicaoDao.get(Edicao.class, edicao.getId())).thenReturn(edicao);
		mockMvc.perform(get("/jogador/by/edicao/"+edicao.getId()))
			.andExpect(status().isOk())
			.andExpect(view().name("_base2"))
			.andExpect(model().attributeExists("content_import", "edicao", "jogadores"))
			.andExpect(model().attribute("content_import", "jogador-page"));
	}
	
	@Test
	public void testDeveRetornarPaginaDeCadastroDeJogador() throws Exception {
		mockMvc.perform(get("/jogador/system/form/0"))
			.andExpect(status().isOk())
			.andExpect(view().name("_base2"))
			.andExpect(model().attributeExists("content_import"))
			.andExpect(model().attribute("content_import", "jogador-system-form"));
	}
}
