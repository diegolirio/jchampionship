package com.quartashow.jchampionship.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.quartashow.jchampionship.dao.ClassificacaoDao;
import com.quartashow.jchampionship.model.Classificacao;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath*:spring-context.xml" })
public class ClassificacaoControllerTest {

	@InjectMocks
	private ClassificacaoController controller;

	private MockMvc mockMvc;
	
	@Mock
	private ClassificacaoDao classificacaoDao;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}
	
	@Test
	public void classificacaoPost() throws Exception {
		ResultActions resultActions = 
			mockMvc.perform(post("/classificacao/post")
					.param("time.id", "1")
					.param("grupo.id", "1"))
			 .andExpect(status().isCreated());
		String uri = resultActions.andReturn().getResponse().getHeader("Location");		
		mockMvc.perform(get(uri)).andExpect(status().isOk());
	}
	
	@Test
	public void classificacaoPostNomeInvalido() throws Exception {
			mockMvc.perform(post("/classificacao/post")
				.param("nome", ""))
			 .andExpect(status().isUnauthorized());
	}	

	@Test
	public void testModelAndViewPageDeleteTimeConfirm() throws Exception {		
		Classificacao classificacao = Mockito.mock(Classificacao.class);
		Mockito.when(classificacaoDao.get(Classificacao.class, 1)).thenReturn(classificacao);		
		
		mockMvc.perform(get("/classificacao/delete_confirm/1"))
			.andExpect(status().isOk())
			.andExpect(view().name("_base_simple"))
			.andExpect(model().attributeExists("content_import", "classificacao"));
	}	
	
	@Test
	public void testDeveExcluirTimeDaClassificacao() throws Exception {
		mockMvc.perform(delete("/classificacao/delete/1"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}
	
}
