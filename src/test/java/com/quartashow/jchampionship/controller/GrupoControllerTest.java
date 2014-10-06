package com.quartashow.jchampionship.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.quartashow.jchampionship.dao.GrupoDao;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath*:spring-context.xml" })
public class GrupoControllerTest {
	 
	@InjectMocks
	private GrupoController controller;

	@Mock
	private GrupoDao edicaoDao;	
	
	private MockMvc mockMvc;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}
	
	@Test
	public void testPostGrupoRestFull() throws Exception {		
		ResultActions resultActions = 
			mockMvc.perform(post("/grupo/post")
					.param("descricao", "A"))
			 .andExpect(status().isCreated())
			 .andExpect(content().contentType("application/json"));

		String location = resultActions.andReturn().getResponse().getHeader("Location");			
		mockMvc.perform(get(location)).andExpect(status().isOk());
	}		
	
	@Test
	public void deveRetornarGrupoEmJSON() throws Exception {
		mockMvc.perform(get("/grupo/get/1"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}
	
	@Test
	public void deveBuscarListaDeGruposPorEdicao() throws Exception {
		long edicao = 1;
		mockMvc.perform(get("/grupo/get/list/by/edicao/"+edicao))
			.andExpect(status().isOk())
			.andExpect(content().contentType("application/json"));
	}	
	
}
