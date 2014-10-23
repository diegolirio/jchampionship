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

import com.quartashow.jchampionship.dao.ClassificacaoDao;
import com.quartashow.jchampionship.dao.EscalacaoDao;
import com.quartashow.jchampionship.dao.JogoDao;
import com.quartashow.jchampionship.model.Classificacao;
import com.quartashow.jchampionship.model.Edicao;
import com.quartashow.jchampionship.model.Escalacao;
import com.quartashow.jchampionship.model.Grupo;
import com.quartashow.jchampionship.model.Jogo;
import com.quartashow.jchampionship.model.Status;
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
	private ClassificacaoDao classificacaoDao;

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
	public void testDeveFinalizarJogoECalcularClassificacao() throws Exception {
		
		Time timeA = new Time(1l, "Corinthians");
		Time timeB = new Time(2l, "Vasco");
		List<Classificacao> classificacoes = new ArrayList<Classificacao>();
		Classificacao classificacaoA = new Classificacao(1, timeA);
		Classificacao classificacaoB = new Classificacao(2, timeB);
		classificacoes.add(classificacaoA);
		classificacoes.add(classificacaoB);
		
		Jogo jogo = new Jogo();
		jogo.setId(1l);
		jogo.setTimeA(timeA);
		jogo.setTimeB(timeB);
		jogo.setGrupo(new Grupo(1l, classificacoes));
		jogo.setResultadoA(3);
		jogo.setResultadoB(1);
		jogo.setStatus(new Status(2l, "Em andamento"));
		Mockito.when(this.jogoDao.get(Jogo.class, 1l)).thenReturn(jogo);
		Mockito.when(this.classificacaoDao.getClassificacoesByGrupo(jogo.getGrupo())).thenReturn(classificacoes);		
		
		mockMvc.perform(post("/jogo/finalizar/1"))
			.andExpect(status().isCreated());
	}
	
}
