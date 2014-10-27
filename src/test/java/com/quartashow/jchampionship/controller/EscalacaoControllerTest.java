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
import com.quartashow.jchampionship.dao.JogadorDao;
import com.quartashow.jchampionship.dao.JogadorEscaladoDao;
import com.quartashow.jchampionship.dao.JogadorInfoEdicaoDao;
import com.quartashow.jchampionship.dao.JogoDao;
import com.quartashow.jchampionship.dao.TimeDao;
import com.quartashow.jchampionship.model.CollectionEventos;
import com.quartashow.jchampionship.model.Edicao;
import com.quartashow.jchampionship.model.Escalacao;
import com.quartashow.jchampionship.model.Evento;
import com.quartashow.jchampionship.model.Grupo;
import com.quartashow.jchampionship.model.Jogador;
import com.quartashow.jchampionship.model.JogadorEscalado;
import com.quartashow.jchampionship.model.Jogo;
import com.quartashow.jchampionship.model.Status;
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

	@Mock
	private JogadorInfoEdicaoDao jogadorInfoEdicaoDao;

	@Mock
	private TimeDao timeDao;

	@Mock
	private JogadorDao jogadorDao;

	@Mock
	private CollectionEventosDao collectionEventoDao;	

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}
	
	@Test
	public void testDeveAdicionarEscalacaoAoJogoDeveRetornarStatusCodeCREATE201() throws Exception {
		
		// TODO: Usar Jogo builder 
		long jogoId = 1l;
		
		Jogo jogo = Mockito.mock(Jogo.class);
		Mockito.when(this.jogoDao.get(Jogo.class, jogoId)).thenReturn(jogo);
		Mockito.when(jogo.getGrupo()).thenReturn(new Grupo(1l, new Edicao(1l)));
		
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
		
		//Mockito.when(jogadorInfoEdicaoDao.exists(jogadoresA.get(0), jogo.getGrupo().getEdicao())).thenReturn(false); 
		
		mockMvc.perform(post("/escalacao/post/jogo/"+jogoId))
			.andExpect(status().isCreated());
		
//		JogadorEscalado je = new JogadorEscalado();
//		je.setEscalacao(Mockito.mock(Escalacao.class));
//		je.setJogador(jogadoresA.get(0));
//		je.setTime(timeA);
//		Mockito.verify(jogadorEscaladoDao).save(je);		
	}
	
	@Test
	public void testDeveImpedirDeGerarEscalacaoPoisTimeAEstaSemJogadores() throws Exception {
		
		// TODO: Mover jogo para um Builder
		Jogo jogo = new Jogo();
		jogo.setId(1l);
		jogo.setTimeA(new Time(1l, "Corinthians"));
		jogo.setTimeB(new Time(2l, "Santos"));
		jogo.setGrupo(new Grupo(1l, new Edicao(1l)));
		jogo.setStatus(new Status(1l));		
		jogo.getTimeA().setJogadores(new ArrayList<Jogador>());		
		//ResultActions resultActions = 
				mockMvc.perform(post("/escalacao/post/jogo/"+jogo.getId()))
					.andExpect(status().isInternalServerError());		
		// TODO: descomentar e terminar test...
		//String message = resultActions.andReturn().getResponse().getContentAsString();		
		// System.out.println(message);
		// Assert.assertEquals(jogo.getTimeA().getNome() + " nao ha jogadores cadastrados, por favor cadastre ao menos Um.", "");
	}
	
	// TODO: Criar test p/ testDeveImpedirDeGerarEscalacaoPoisTimeBEstaSemJogadores

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
	
	@Test
	public void testDeveRetornarPageSimpleFormAddJogadorEscaladoParaJogo() throws Exception {
		Time time = new Time(1l, "Corinthians");
		
		Jogo jogo = new Jogo();
		jogo.setId(1l);
		
		Escalacao escalacao = new Escalacao();
		escalacao.setId(1l);
		escalacao.setJogo(jogo);
		
		
		Mockito.when(this.timeDao.get(Time.class, time.getId())).thenReturn(time);
		Mockito.when(this.escalacaoDao.get(Escalacao.class, escalacao.getId())).thenReturn(escalacao);
		
		List<Jogador> jogadoresEscalados = new ArrayList<Jogador>();
		Mockito.when(this.jogadorDao.getJogadoresNaoEscalados(escalacao.getJogo())).thenReturn(jogadoresEscalados );
		
		mockMvc.perform(get("/escalacao/"+escalacao.getId()+"/add/jogador/time/"+time.getId()))
			.andExpect(status().isOk())
			.andExpect(view().name("_base_simple"))
			.andExpect(model().attributeExists("content_import", "time", "escalacao"));
	}
	
	@Test
	public void testDeveSalvarJogadorEscalado() throws Exception {
		Time time = new Time(1l, "Corinthians");		
		
		Escalacao escalacao = new Escalacao();
		escalacao.setId(1l);
		
		mockMvc.perform(post("/escalacao/"+escalacao.getId()+"/add/jogador/time/"+time.getId())
				.param("jogadorId", "1"))
			.andExpect(status().isCreated());
	}	
	
	@Test
	public void testDeveRetornarPageSimpleExcluirJogadorEscalado() throws Exception {
		long jogadorEscaladoId = 1l;
		JogadorEscalado jogadorEscalado = Mockito.mock(JogadorEscalado.class);
		Mockito.when(this.jogadorEscaladoDao.get(JogadorEscalado.class, jogadorEscaladoId)).thenReturn(jogadorEscalado );
		
		mockMvc.perform(get("/escalacao/jogadorEscalado/delete/"+jogadorEscaladoId))
			.andExpect(status().isOk())
			.andExpect(view().name("_base_simple"))
			.andExpect(model().attributeExists("content_import", "jogadorEscalado"))
			.andExpect(model().attribute("content_import", "escalacao-jogadorescalado-delete"));
	}
	
	@Test
	public void testDeveExcluirJogadorEscalado() throws Exception {
		long jogadorEscaladoId = 1l;
		JogadorEscalado jogadorEscalado = Mockito.mock(JogadorEscalado.class);
		Mockito.when(this.jogadorEscaladoDao.get(JogadorEscalado.class, jogadorEscaladoId)).thenReturn(jogadorEscalado );
		
		mockMvc.perform(post("/escalacao/jogadorEscalado/delete/"+jogadorEscaladoId))
			.andExpect(status().isOk());
	}	
	
	@Test
	public void testDeveRetornarPageSimpleExcluirEventosJogadorEscalado() throws Exception {
		long jogadorEscaladoId = 1l;
		JogadorEscalado jogadorEscalado = Mockito.mock(JogadorEscalado.class);
		Mockito.when(this.jogadorEscaladoDao.get(JogadorEscalado.class, jogadorEscaladoId)).thenReturn(jogadorEscalado );		
		mockMvc.perform(get("/escalacao/jogadorEscalado/"+jogadorEscaladoId+"/eventos/delete"))
			.andExpect(status().isOk())
			.andExpect(view().name("_base_simple"))
			.andExpect(model().attributeExists("content_import", "jogadorEscalado"));
	}
	
	@Test
	public void testDeveExcluirEventoDoJogadorEscalado() throws Exception {
		long jogadorEscaladoId = 1l;
		long collectionEventoId = 1; 
		JogadorEscalado jogadorEscalado = Mockito.mock(JogadorEscalado.class);
		Mockito.when(this.jogadorEscaladoDao.get(JogadorEscalado.class, jogadorEscaladoId)).thenReturn(jogadorEscalado );
		
		CollectionEventos collectionEvento = Mockito.mock(CollectionEventos.class);
		Evento evento = Mockito.mock(Evento.class);
		Mockito.when(collectionEvento.getEvento()).thenReturn(evento);  
		Mockito.when(this.collectionEventoDao.get(CollectionEventos.class, collectionEventoId)).thenReturn(collectionEvento );
		
		mockMvc.perform(post("/escalacao/jogadorEscalado/"+jogadorEscaladoId+"/eventos/delete")
				.param("collectionEventoId", collectionEventoId+""))
			.andExpect(status().isOk());
	}		
	
}
