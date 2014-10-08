package com.quartashow.jchampionship.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.quartashow.jchampionship.dao.JogadorDao;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath*:spring-context.xml" })
public class JogadorControllerTest {

	@InjectMocks
	private JogadorController controller;

	private MockMvc mockMvc;
	
	@Mock
	private JogadorDao timeDao;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void pageSimpleJogadorParaCadastroRapidoDeveConterViewModels() throws Exception {
		mockMvc.perform(get("/jogador/page/simple"))
			.andExpect(status().isOk())
			.andExpect(view().name("_base_simple"))
			.andExpect(model().attributeExists("content_import"))
			.andExpect(model().attribute("content_import", "jogador-system-form"));
	}
	
	@Test
	public void jogadorPost() throws Exception {
		ResultActions resultActions = 
			mockMvc.perform(post("/jogador/post")
				.param("nome", "Diego Lirio"))
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
	
}