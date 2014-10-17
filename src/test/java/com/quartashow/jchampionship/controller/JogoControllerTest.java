package com.quartashow.jchampionship.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.quartashow.jchampionship.dao.EscalacaoDao;
import com.quartashow.jchampionship.dao.JogadorEscaladoDao;
import com.quartashow.jchampionship.dao.JogoDao;
import com.quartashow.jchampionship.model.Edicao;
import com.quartashow.jchampionship.model.Escalacao;
import com.quartashow.jchampionship.model.Grupo;
import com.quartashow.jchampionship.model.Jogador;
import com.quartashow.jchampionship.model.Jogo;
import com.quartashow.jchampionship.model.Time;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath*:spring-context.xml" })
public class JogoControllerTest {

	@InjectMocks
	private JogoController controller;

	private MockMvc mockMvc;
	
	@Mock
	private JogoDao jogoDao;

	@Mock
	private EscalacaoDao escalacaoDao;

	@Mock
	private JogadorEscaladoDao jogadorEscaladoDao;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void testDeveSalvarJogo() throws Exception {
		
		Jogo jogo = new Jogo();
		jogo.setId(1l);
		Mockito.when(this.jogoDao.get(Jogo.class, 1l)).thenReturn(jogo);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/jogo/post")
				.param("grupo.id", "1")
				.param("local.id", "1")
				.param("timeA.id", "1")
				.param("timeB.id", "1")
				.param("harbito.id", "1"))
			.andExpect(MockMvcResultMatchers.status().isCreated());
	}
	
	@Test  
	public void deveRetornarPaginaConfirmacaoExclusaoDoJogo() throws Exception {
		
		Jogo jogo = Mockito.mock(Jogo.class);
		Mockito.when(jogoDao.get(Jogo.class, 1)).thenReturn(jogo);		
		
		mockMvc.perform(get("/jogo/delete_confirm/1"))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.view().name("_base_simple"))
			.andExpect(MockMvcResultMatchers.model().attributeExists("content_import", "jogo"))
			.andExpect(MockMvcResultMatchers.model().attribute("content_import", "jogo-system-confirm-delete"));
	}	
	
	@Test
	public void testDeveExcluirJogo() throws Exception { 
		mockMvc.perform(MockMvcRequestBuilders.delete("/jogo/delete/1"))
			.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void testDeveRetornarPaginaPublicaDoJogo() throws Exception {		
		Edicao edicao = Mockito.mock(Edicao.class);
		Grupo grupo = Mockito.mock(Grupo.class);
		Jogo jogo = Mockito.mock(Jogo.class);
		Escalacao escalacao = Mockito.mock(Escalacao.class);
		
		Mockito.when(jogo.getGrupo()).thenReturn(grupo);		
		Mockito.when(jogo.getGrupo().getEdicao()).thenReturn(edicao);		
		Mockito.when(this.jogoDao.get(Jogo.class, 1l)).thenReturn(jogo);			
		Mockito.when(this.escalacaoDao.get(jogo)).thenReturn(escalacao);
		
		
		mockMvc.perform(get("/jogo/1"))
			.andExpect(status().isOk())
			.andExpect(view().name("_base2"))
			.andExpect(model().attributeExists("content_import", "jogo", "edicao", "escalacao"));
	}
	
	@Test
	public void testDeveAdicionarEscalacaoAoJogoDeveRetornarStatusCodeCREATE201() throws Exception {
		long jogoId = 1l;
		
		Jogo jogo = Mockito.mock(Jogo.class);
		Mockito.when(this.jogoDao.get(Jogo.class, jogoId)).thenReturn(jogo);
		
		Time timeA = Mockito.mock(Time.class);
		Mockito.when(jogo.getTimeA()).thenReturn(timeA);
		
		List<Jogador> jogadoresA = new ArrayList<Jogador>();
		jogadoresA.add(new Jogador(1, "Diego"));		
		Mockito.when(jogo.getTimeA().getJogadores()).thenReturn(jogadoresA);
		
		Time timeB = Mockito.mock(Time.class);
		Mockito.when(jogo.getTimeB()).thenReturn(timeB);		
		
		mockMvc.perform(post("/jogo/"+jogoId +"/add/escalacao"))
			.andExpect(status().isCreated());
		
//		JogadorEscalado je = new JogadorEscalado();
//		je.setEscalacao(Mockito.mock(Escalacao.class));
//		je.setJogador(jogadoresA.get(0));
//		je.setTime(timeA);
//		Mockito.verify(jogadorEscaladoDao).save(je);		
	}
	
}
