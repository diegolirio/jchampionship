package com.quartashow.jchampionship.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.quartashow.jchampionship.dao.GrupoDao;
import com.quartashow.jchampionship.model.Grupo;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath*:spring-context.xml" })
public class GrupoControllerTest {
	 
	@InjectMocks
	private GrupoController controller;

	@Mock
	private GrupoDao grupoDao;	
	
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
					.param("descricao", "A")
					.param("edicao.id", "1"))
			 .andExpect(status().isCreated())
			 .andExpect(content().contentType("application/json"));

		String location = resultActions.andReturn().getResponse().getHeader("Location");			
		mockMvc.perform(get(location)).andExpect(status().isOk());
	}		
	
	@Test
	public void testPostGrupoDescricaoInvalida() throws Exception {		
			mockMvc.perform(post("/grupo/post")
					.param("descricao", "")
					.param("edicao.id", "1"))
			 .andExpect(status().isUnauthorized())
			 .andExpect(content().contentType("application/json"));
	}	
	
	@Test
	public void testPostGrupoEdicaoInvalida() throws Exception {		
			mockMvc.perform(post("/grupo/post")
					.param("descricao", "")
					//.param("edicao.id", "1")
					)
			 .andExpect(status().isUnauthorized())
			 .andExpect(content().contentType("application/json"));
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
	
	@Test
	public void deveExcluirGrupoPorId() throws Exception {
		mockMvc.perform(delete("/grupo/delete/12")) 
			.andExpect(status().isOk());
		
	} 
	
	@Test  
	public void deveRetornarPaginaConfirmacaoExclusaoDoGrupo() throws Exception {
		
		Grupo grupo = Mockito.mock(Grupo.class);
		Mockito.when(grupoDao.get(Grupo.class, 12)).thenReturn(grupo);		
		
		mockMvc.perform(get("/grupo/delete_confirm/12"))
			.andExpect(MockMvcResultMatchers.view().name("_base_simple"))
			.andExpect(MockMvcResultMatchers.model().attributeExists("content_import", "grupo"))
			.andExpect(MockMvcResultMatchers.model().attribute("content_import", "grupo-system-confirm-delete"));
	}
	
}
