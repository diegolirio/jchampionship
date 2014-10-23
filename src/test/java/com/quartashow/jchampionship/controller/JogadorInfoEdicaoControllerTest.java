package com.quartashow.jchampionship.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.quartashow.jchampionship.dao.EdicaoDao;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath*:spring-context.xml" })
public class JogadorInfoEdicaoControllerTest {

	@InjectMocks
	private JogadorInfoEdicaoController controller;
	 
	private MockMvc mockMvc;
	
	@Mock
	private EdicaoDao edicaoDao;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this); 
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void testDeveRetornarViewEModelsParaPaginaArtilharia() throws Exception {
		mockMvc.perform(get("/jogadorInfoEdicao/artilharia/by/edicao/4"))
			.andExpect(status().isOk());
	}
	
}
