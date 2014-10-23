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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.quartashow.jchampionship.dao.CollectionEventosDao;
import com.quartashow.jchampionship.dao.EscalacaoDao;
import com.quartashow.jchampionship.dao.EventoDao;
import com.quartashow.jchampionship.dao.JogadorEscaladoDao;
import com.quartashow.jchampionship.dao.JogoDao;
import com.quartashow.jchampionship.model.Escalacao;
import com.quartashow.jchampionship.model.Evento;
import com.quartashow.jchampionship.model.Jogador;
import com.quartashow.jchampionship.model.JogadorEscalado;
import com.quartashow.jchampionship.model.Jogo;
import com.quartashow.jchampionship.model.Time;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath*:spring-context.xml" })
public class EscalacaoControllerTest {
	
	private MockMvc mockMvc;
	
	@InjectMocks
	private EscalacaoController controller;

	@Mock
	private JogoDao jogoDao;
	
	@Mock
	private EscalacaoDao escalacaoDao;

	@Mock
	private JogadorEscaladoDao jogadorEscaladoDao;

	@Mock
	private EventoDao eventoDao;
	
	@Mock
	private CollectionEventosDao collectionEventosDao;	

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
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
		
		List<Jogador> jogadoresB = new ArrayList<Jogador>();
		jogadoresB.add(new Jogador(2, "Guarrincha"));		
		Mockito.when(jogo.getTimeB().getJogadores()).thenReturn(jogadoresB);		
		
		mockMvc.perform(post("/escalacao/post/jogo/"+jogoId))
			.andExpect(status().isCreated());
		
//		JogadorEscalado je = new JogadorEscalado();
//		je.setEscalacao(Mockito.mock(Escalacao.class));
//		je.setJogador(jogadoresA.get(0));
//		je.setTime(timeA);
//		Mockito.verify(jogadorEscaladoDao).save(je);		
	}

	@Test
	public void testDeveRetornarViewEModelsParaAddEventos() throws Exception {
		long jogoId = 1l;
		
		Jogo jogo = Mockito.mock(Jogo.class);
		Mockito.when(this.jogoDao.get(Jogo.class, jogoId)).thenReturn(jogo);
		
		Evento evento = Mockito.mock(Evento.class);
		Mockito.when(this.eventoDao.get(Evento.class, 3)).thenReturn(evento);

		Escalacao escalacao = Mockito.mock(Escalacao.class);
		Mockito.when(this.escalacaoDao.get(jogo)).thenReturn(escalacao);
		
		mockMvc.perform(get("/escalacao/system/"+jogoId +"/add/evento/3"))
			.andExpect(status().isOk())
			.andExpect(view().name("_base_simple"))
			.andExpect(model().attributeExists("content_import", "jogo", "evento", "jogadoresEscalados"))
			.andExpect(model().attribute("content_import", "escalacao-evento"));
	}
	
	@Test
	public void testDeveSalvarEventoDoJogadorEscalado() throws Exception {
		
		Evento evento = new Evento();
		evento.setDescricao("GOL");
		evento.setId(1l);
		
		JogadorEscalado jogadorEscaladoAtach = Mockito.mock(JogadorEscalado.class); 
		Mockito.when(this.jogadorEscaladoDao.get(JogadorEscalado.class, 1l)).thenReturn(jogadorEscaladoAtach);
	
		//List<Evento> eventos = new ArrayList<Evento>();
		//Mockito.when(jogadorEscaladoAtach.getEventos()).thenReturn(eventos);
		
		Escalacao escalacao = Mockito.mock(Escalacao.class); 
		Mockito.when(jogadorEscaladoAtach.getEscalacao()).thenReturn(escalacao);
		
		Jogo jogo = Mockito.mock(Jogo.class);
		Mockito.when(jogadorEscaladoAtach.getEscalacao().getJogo()).thenReturn(jogo);
		Mockito.when(this.jogoDao.get(Jogo.class, jogadorEscaladoAtach.getEscalacao().getJogo().getId())).thenReturn(jogo);
		
		Time time = Mockito.mock(Time.class);
		Mockito.when(jogadorEscaladoAtach.getTime()).thenReturn(time);

		Time timeA = Mockito.mock(Time.class);
		Mockito.when(jogo.getTimeA()).thenReturn(timeA);		

		Time timeB = Mockito.mock(Time.class);
		Mockito.when(jogo.getTimeB()).thenReturn(timeB);		
		
		mockMvc.perform(post("/escalacao/add/evento/"+evento.getId())
				.param("jogadorEscaladoId", "1"))
			.andExpect(status().isCreated());
	}
	
}
