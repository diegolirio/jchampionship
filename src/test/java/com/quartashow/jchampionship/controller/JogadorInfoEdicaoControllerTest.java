package com.quartashow.jchampionship.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import java.util.ArrayList;
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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.quartashow.jchampionship.dao.EdicaoDao;
import com.quartashow.jchampionship.dao.JogadorInfoEdicaoDao;
import com.quartashow.jchampionship.model.Edicao;
import com.quartashow.jchampionship.model.Jogador;
import com.quartashow.jchampionship.model.JogadorInfoEdicao;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath*:spring-context.xml" })
public class JogadorInfoEdicaoControllerTest {

	@InjectMocks
	private JogadorInfoEdicaoController controller;
	 
	private MockMvc mockMvc;

	@Mock
	private JogadorInfoEdicaoDao jogadorInfoEdicaoDao;
	
	@Mock
	private EdicaoDao edicaoDao;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this); 
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void testDeveRetornarViewEModelsParaPaginaArtilharia() throws Exception {
		
		Edicao edicao = new Edicao(1l);
		Mockito.when(edicaoDao.get(Edicao.class, edicao.getId())).thenReturn(edicao);
		
		// TODO: criar jogadorInfoEdicao builder
		// TODO: criar list jogadores info tambem ao builder
		List<JogadorInfoEdicao> jogadoresInfoEdicao = new ArrayList<JogadorInfoEdicao>();
		jogadoresInfoEdicao.add(new JogadorInfoEdicao(new Jogador(1l, "Ronaldo"), edicao));
		
		Mockito.when(jogadorInfoEdicaoDao.getList(JogadorInfoEdicao.class)).thenReturn(jogadoresInfoEdicao);
		
		mockMvc.perform(get("/jogadorInfoEdicao/artilharia/by/edicao/"+edicao.getId()))
			.andExpect(status().isOk())
			.andExpect(view().name("_base2"))
			.andExpect(model().attributeExists("content_import", "edicao"))
			.andExpect(model().attribute("content_import", "jogadorInfoEdicao-page"));
	}
	
}
