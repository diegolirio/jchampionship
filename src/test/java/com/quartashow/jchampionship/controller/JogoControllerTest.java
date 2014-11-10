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
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.quartashow.jchampionship.dao.ClassificacaoDao;
import com.quartashow.jchampionship.dao.ClassificacaoHistDao;
import com.quartashow.jchampionship.dao.EdicaoDao;
import com.quartashow.jchampionship.dao.EscalacaoDao;
import com.quartashow.jchampionship.dao.JogadorInfoEdicaoDao;
import com.quartashow.jchampionship.dao.JogoDao;
import com.quartashow.jchampionship.model.Classificacao;
import com.quartashow.jchampionship.model.CollectionEventos;
import com.quartashow.jchampionship.model.Edicao;
import com.quartashow.jchampionship.model.Escalacao;
import com.quartashow.jchampionship.model.Evento;
import com.quartashow.jchampionship.model.Grupo;
import com.quartashow.jchampionship.model.Jogador;
import com.quartashow.jchampionship.model.JogadorEscalado;
import com.quartashow.jchampionship.model.JogadorInfoEdicao;
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

	@Mock
	private JogadorInfoEdicaoDao jogadorInfoEdicaoDao;

	@Mock
	private EdicaoDao edicaoDao;

	@Mock
	private ClassificacaoHistDao classificacaoHistDao;

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
				.param("timeB.id", "2")
				.param("harbito.id", "1")
				//.param("rodada", ""+rodada)
				)
			.andExpect(MockMvcResultMatchers.status().isCreated());
	}
	
	// TODO: criar test para validar time contra ele mesmo !!!
	
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
	public void testDeveFinalizarJogoECalcularClassificacaoEAtulizarJogadorInfoEdicao() throws Exception {
		
		// TODO: Criar Build da Edicao vinculado com Grupo, jogo , jogadores info e escalado, com eventos 
		
		Edicao edicao = new Edicao(1l);
		
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
		jogo.setGrupo(new Grupo(1l, edicao, classificacoes));
		jogo.setResultadoA(3);
		jogo.setResultadoB(1);
		jogo.setStatus(new Status(2l, "Em andamento"));
		Mockito.when(this.jogoDao.get(Jogo.class, 1l)).thenReturn(jogo);
		Mockito.when(this.classificacaoDao.getClassificacoesByGrupo(jogo.getGrupo())).thenReturn(classificacoes);		
		
		Mockito.when(this.classificacaoDao.get(Classificacao.class, 1l)).thenReturn(classificacaoA);
		
		// Evento
		Evento gol = new Evento(1l);
		Evento ca = new Evento(1l);
		Evento cv = new Evento(1l);
		
		// Escalacao 
		Escalacao escalacao = new Escalacao();
		escalacao.setJogo(jogo);
		escalacao.setId(1l);
		
		List<JogadorEscalado> jogadoresEscalados = new ArrayList<JogadorEscalado>();
		
		// jogador Escalado 1
		JogadorEscalado jogadorEscalado1 = new JogadorEscalado();
		jogadorEscalado1.setId(1l);		
		Jogador jogador1 = new Jogador(1l, "Diego");
		jogadorEscalado1.setJogador(jogador1);
		jogadorEscalado1.setTime(timeA);
		List<CollectionEventos> eventos = new ArrayList<CollectionEventos>();
		
		CollectionEventos golDiego = new CollectionEventos();
		golDiego.setId(1l);
		golDiego.setEvento(gol);
		eventos.add(golDiego);

		CollectionEventos golDiego2 = new CollectionEventos();
		golDiego2.setId(2l);
		golDiego2.setEvento(gol);
		eventos.add(golDiego2);		
		
		CollectionEventos golDiego3 = new CollectionEventos();
		golDiego3.setId(3l);
		golDiego3.setEvento(gol);
		eventos.add(golDiego3);		
		
		CollectionEventos cvDiego = new CollectionEventos();
		cvDiego.setId(4l);
		cvDiego.setEvento(cv);
		eventos.add(cvDiego);			
		
		jogadorEscalado1.setEventos(eventos);
		jogadoresEscalados.add(jogadorEscalado1);
		
		// jogador escalado 2
		JogadorEscalado jogadorEscalado2 = new JogadorEscalado();
		jogadorEscalado2.setId(1l);		
		Jogador jogador2 = new Jogador(2l, "Jonh");
		jogadorEscalado2.setJogador(jogador2);
		jogadorEscalado2.setTime(timeA);
		List<CollectionEventos> eventosJonh = new ArrayList<CollectionEventos>();
		
		CollectionEventos golJonh = new CollectionEventos();
		golJonh.setId(5l);
		golJonh.setEvento(gol);
		eventosJonh.add(golJonh);	
		
		CollectionEventos cvJonh = new CollectionEventos();
		cvJonh.setId(6l);
		cvJonh.setEvento(ca);
		eventosJonh.add(cvJonh);			
		
		jogadorEscalado2.setEventos(eventosJonh);
		
		jogadoresEscalados.add(jogadorEscalado2);		
		
		escalacao.setJogadoresEscalados(jogadoresEscalados);
		
		// Jogadores Info da Edicao		
		List<JogadorInfoEdicao> jogadoresInfoEdicao = new ArrayList<JogadorInfoEdicao>();
		
		// jogador info da Edicao 1
		JogadorInfoEdicao jogadorInfoEdicao1 = new JogadorInfoEdicao(jogador1, edicao);
		jogadoresInfoEdicao.add(jogadorInfoEdicao1);

		// jogador info da Edicao 2
		JogadorInfoEdicao jogadorInfoEdicao2 = new JogadorInfoEdicao(jogador2, edicao);
		jogadoresInfoEdicao.add(jogadorInfoEdicao2);
		
		edicao.setJogadoresInfoEdicao(jogadoresInfoEdicao);
		
		Mockito.when(jogadorInfoEdicaoDao.getList(JogadorInfoEdicao.class)).thenReturn(jogadoresInfoEdicao);
		
		Mockito.when(escalacaoDao.get(jogo)).thenReturn(escalacao);
		
		Mockito.when(classificacaoHistDao.existHist(classificacaoA.getTime(), classificacaoA.getGrupo(), jogo.getRodada())).thenReturn(true);
		
		ResultActions resultActions = 
				mockMvc.perform(post("/jogo/finalizar/1"))
					.andExpect(status().isCreated());
		
		String json = resultActions.andReturn().getResponse().getContentAsString();
		System.out.println(json);
		
		String location = resultActions.andReturn().getResponse().getHeader("Location");
		System.out.println(location);
		
		// TODO: terminar test para verificar se classificacao foi calculado de modo correto
		//        Ambos os Times (A e B): verificar colocacao, vitorias, derrotas, empates, GP, GC, SG, pontos
		
		//mockMvc.perform(get(location))
		//	.andExpect(status().isOk());
		
//		@SuppressWarnings("unchecked")
//		List<Classificacao> classificacoesCalc = new ObjectMapper().convertValue(resultActions.andReturn().getResponse().getContentAsString(), List.class);
//		for (Classificacao classCalc : classificacoesCalc) {
//			if(classCalc.getTime().getId() == timeA.getId()) {
//				Assert.assertTrue(classCalc.getColocacao() == 1);
//				Assert.assertTrue(classCalc.getGolsPro() == 3);
//				Assert.assertTrue(classCalc.getGolsContra() == 1);
//				Assert.assertTrue(classCalc.getDerrotas() == 0);
//				Assert.assertTrue(classCalc.getVitorias() == 1);
//				Assert.assertTrue(classCalc.getPontos() == 3);
//				Assert.assertTrue(classCalc.getEmpates() == 0);
//			} else 
//				if(classCalc.getTime().getId() == timeB.getId()) {
//					Assert.assertTrue(classCalc.getColocacao() == 2);
//					Assert.assertTrue(classCalc.getGolsPro() == 1);
//					Assert.assertTrue(classCalc.getGolsContra() == 3);
//					Assert.assertTrue(classCalc.getDerrotas() == 1);
//					Assert.assertTrue(classCalc.getVitorias() == 0);
//					Assert.assertTrue(classCalc.getPontos() == 0);
//					Assert.assertTrue(classCalc.getEmpates() == 0);
//				}
//		}
	}

	// TODO: testParaVerificarCalculoComJogoVencedorFoiTimeA
	
	// TODO: testParaVerificarCalculoDeJogoComEmpate
	
	// TODO: testParaVerificarCalculoComJogoVencedorFoiTimeB
	
	// TODO: testParaVerificarAtualizacaoJogadorInfoEdicaoDosEventosDoJogo
	
	// TODO: test da ordenacao da classificacao (Colocacao)
	
	@Test
	public void testDeveRetornarStatusDoJogoDeFinalizadoParaEmAndamentoECalculo() throws Exception {
		
		// TODO: Criar Build da Edicao vinculado com Grupo, jogo , jogadores info e escalado, com eventos 
		
		Edicao edicao = new Edicao(1l);
		
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
		jogo.setGrupo(new Grupo(1l, edicao, classificacoes));
		jogo.setResultadoA(3);
		jogo.setResultadoB(1);
		jogo.setStatus(new Status(2l, "Em andamento"));
		Mockito.when(this.jogoDao.get(Jogo.class, 1l)).thenReturn(jogo);
		Mockito.when(this.classificacaoDao.getClassificacoesByGrupo(jogo.getGrupo())).thenReturn(classificacoes);		
		
		Mockito.when(this.classificacaoDao.get(Classificacao.class, 1l)).thenReturn(classificacaoA);
		
		// Evento
		Evento gol = new Evento(1l);
		Evento ca = new Evento(1l);
		Evento cv = new Evento(1l);
		
		// Escalacao 
		Escalacao escalacao = new Escalacao();
		escalacao.setJogo(jogo);
		escalacao.setId(1l);
		
		List<JogadorEscalado> jogadoresEscalados = new ArrayList<JogadorEscalado>();
		
		// jogador Escalado 1
		JogadorEscalado jogadorEscalado1 = new JogadorEscalado();
		jogadorEscalado1.setId(1l);		
		Jogador jogador1 = new Jogador(1l, "Diego");
		jogadorEscalado1.setJogador(jogador1);
		jogadorEscalado1.setTime(timeA);
		List<CollectionEventos> eventos = new ArrayList<CollectionEventos>();
		
		CollectionEventos golDiego = new CollectionEventos();
		golDiego.setId(1l);
		golDiego.setEvento(gol);
		eventos.add(golDiego);

		CollectionEventos golDiego2 = new CollectionEventos();
		golDiego2.setId(2l);
		golDiego2.setEvento(gol);
		eventos.add(golDiego2);		
		
		CollectionEventos golDiego3 = new CollectionEventos();
		golDiego3.setId(3l);
		golDiego3.setEvento(gol);
		eventos.add(golDiego3);		
		
		CollectionEventos cvDiego = new CollectionEventos();
		cvDiego.setId(4l);
		cvDiego.setEvento(cv);
		eventos.add(cvDiego);			
		
		jogadorEscalado1.setEventos(eventos);
		jogadoresEscalados.add(jogadorEscalado1);
		
		// jogador escalado 2
		JogadorEscalado jogadorEscalado2 = new JogadorEscalado();
		jogadorEscalado2.setId(1l);		
		Jogador jogador2 = new Jogador(2l, "Jonh");
		jogadorEscalado2.setJogador(jogador2);
		jogadorEscalado2.setTime(timeA);
		List<CollectionEventos> eventosJonh = new ArrayList<CollectionEventos>();
		
		CollectionEventos golJonh = new CollectionEventos();
		golJonh.setId(5l);
		golJonh.setEvento(gol);
		eventosJonh.add(golJonh);	
		
		CollectionEventos cvJonh = new CollectionEventos();
		cvJonh.setId(6l);
		cvJonh.setEvento(ca);
		eventosJonh.add(cvJonh);			
		
		jogadorEscalado2.setEventos(eventosJonh);
		
		jogadoresEscalados.add(jogadorEscalado2);		
		
		escalacao.setJogadoresEscalados(jogadoresEscalados);
		
		// Jogadores Info da Edicao		
		List<JogadorInfoEdicao> jogadoresInfoEdicao = new ArrayList<JogadorInfoEdicao>();
		
		// jogador info da Edicao 1
		JogadorInfoEdicao jogadorInfoEdicao1 = new JogadorInfoEdicao(jogador1, edicao);
		jogadoresInfoEdicao.add(jogadorInfoEdicao1);

		// jogador info da Edicao 2
		JogadorInfoEdicao jogadorInfoEdicao2 = new JogadorInfoEdicao(jogador2, edicao);
		jogadoresInfoEdicao.add(jogadorInfoEdicao2);
		
		edicao.setJogadoresInfoEdicao(jogadoresInfoEdicao);
		
		Mockito.when(jogadorInfoEdicaoDao.getList(JogadorInfoEdicao.class)).thenReturn(jogadoresInfoEdicao);
		
		Mockito.when(escalacaoDao.get(jogo)).thenReturn(escalacao);
		
		mockMvc.perform(post("/jogo/"+jogo.getId()+"/retornStatus"))
			.andExpect(status().isOk());
	}
}
