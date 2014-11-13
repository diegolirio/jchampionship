package com.quartashow.jchampionship.controller;

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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.quartashow.jchampionship.dao.ClassificacaoHistDao;
import com.quartashow.jchampionship.dao.GrupoDao;
import com.quartashow.jchampionship.model.Edicao;
import com.quartashow.jchampionship.model.Grupo;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath*:spring-context.xml" })
public class ClassificacaoHistControllerTest {

	@InjectMocks
	private ClassificacaoHistController controller;

	private MockMvc mockMvc;
	
	@Mock
	private ClassificacaoHistDao classificacaoDao;

	@Mock
	private GrupoDao grupoDao;
	
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}
	
	@Test
	public void testDeveRetornarListaDeHistoricoDaClassificacaoPorGrupo() throws Exception {
		Grupo grupo = new Grupo(1l, new Edicao(1l));
		Mockito.when(grupoDao.get(Grupo.class, grupo.getId())).thenReturn(grupo);
		mockMvc.perform(MockMvcRequestBuilders.get("/classificacaoHist/get/list/by/grupo/"+grupo .getId()+"/json"))
			.andExpect(MockMvcResultMatchers.status().isOk()); 
	}
	
}
