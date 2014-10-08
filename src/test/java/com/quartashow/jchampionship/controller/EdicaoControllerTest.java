package com.quartashow.jchampionship.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.quartashow.jchampionship.dao.EdicaoDao;
import com.quartashow.jchampionship.dao.GrupoDao;
import com.quartashow.jchampionship.dao.HarbitoDao;
import com.quartashow.jchampionship.dao.JogoDao;
import com.quartashow.jchampionship.model.Edicao;
import com.quartashow.jchampionship.model.Jogo;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath*:spring-context.xml" })
public class EdicaoControllerTest {
 
	@InjectMocks
	private EdicaoController controller;

	@Mock
	private EdicaoDao edicaoDao;	
	
	@Mock
	private GrupoDao grupoDao;

	@Mock
	private JogoDao jogoDao;
	
	@Mock
	private HarbitoDao harbitoDao;
	
	private MockMvc mockMvc;

	private Edicao edicao;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
		
		edicao = Mockito.mock(Edicao.class);
		Mockito.when(edicaoDao.get(Edicao.class, 1)).thenReturn(edicao);			
	}
	
	@Test
	public void paginaEdicoesPendetesDeveConterViewsAtributos() throws Exception {
		mockMvc.perform(get("/edicao/system"))
			.andExpect(status().is(200))
			.andExpect(view().name("_base"))
			.andExpect(model().attributeExists("content_import"))
			.andExpect(model().attribute("content_import", "edicao-system-pendentes"));
	}		
	
	@Test
	public void paginaCadastrarNovaEdicaoDeveConterViewsAtributos() throws Exception {
		mockMvc.perform(get("/edicao/system/nova"))
			.andExpect(status().is(200))
			.andExpect(view().name("_base"))
			.andExpect(model().attributeExists("content_import"))
			.andExpect(model().attribute("content_import", "edicao-system-form"))
			.andExpect(model().attributeExists("edicao"));
	}	
	
	@Test
	public void testPostEdicaoRestFull() throws Exception {		
		ResultActions resultActions = 
			mockMvc.perform(post("/edicao/system/nova")
					.param("descricao", "2014")
					.param("campeonato.id", "1"))
			 .andExpect(status().isCreated())
			 .andExpect(content().contentType("application/json"));

		String location = resultActions.andReturn().getResponse().getHeader("Location");			
		mockMvc.perform(get(location)).andExpect(status().isOk());
	}		
	
	@Test
	public void testPostEdicaoRestDescricaoInvalido() throws Exception {		
		mockMvc.perform(post("/edicao/system/nova")
					.param("campeonato.id", "1"))
			 .andExpect(status().isUnauthorized())
			 .andExpect(content().contentType("application/json"))
			 .andExpect(content().string("{\"status\":\"ERROR\",\"errorMessages\":{\"descricao\":\"may not be null\"}}"));
	}		
	
	@Test
	public void testPostEdicaoRestCampeonadoIdInvalido() throws Exception {		
		mockMvc.perform(post("/edicao/system/nova")
					.param("descricao", "2014"))
			 .andExpect(status().isUnauthorized())
			 .andExpect(content().contentType("application/json"))
			 .andExpect(content().string("{\"status\":\"ERROR\",\"errorMessages\":{\"campeonato\":\"may not be null\"}}"));
	}			

	@Test
	public void buscaListaDeEdicoesPendentes() throws Exception {
		mockMvc.perform(get("/edicao/get/list/by/status/1"))
			.andExpect(status().isOk())
			.andExpect(content().contentType("application/json"));
	}
	
	
	/*
	 * Form Wizard Edicao (novo)
	 */
	@Test
	public void paginaEditarGruposDeUmaEdicaoDeveConterViewsAtributos() throws Exception {			
		mockMvc.perform(get("/edicao/system/1/grupos"))
			.andExpect(status().is(200))
			.andExpect(view().name("_base"))
			.andExpect(model().attributeExists("content_import"))
			.andExpect(model().attribute("content_import", "edicao-system-grupos"))
			.andExpect(model().attributeExists("edicao"));
	}		
	
	@Test
	public void deveConterViewModelParaCadastrarJogos() throws Exception {
		
		@SuppressWarnings("unchecked")
		List<Jogo> jogos = Mockito.mock(List.class);
		Mockito.when(this.jogoDao.getJogosByEdicao(edicao)).thenReturn(jogos);
		
		mockMvc.perform(get("/edicao/system/1/jogos")) 
			.andExpect(status().isOk())
			.andExpect(MockMvcResultMatchers.view().name("_base"))
			.andExpect(MockMvcResultMatchers.model().attributeExists("content_import", "edicao", "jogos", "grupos", "harbitos"))
			.andExpect(MockMvcResultMatchers.model().attribute("content_import", "edicao-system-jogos"));
	}
	
}
