package com.quartashow.jchampionship.controller;

import java.io.IOException;
import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.quartashow.jchampionship.controller.common.ValidationResponse;
import com.quartashow.jchampionship.dao.ClassificacaoDao;
import com.quartashow.jchampionship.dao.ClassificacaoHistDao;
import com.quartashow.jchampionship.dao.EdicaoDao;
import com.quartashow.jchampionship.dao.EscalacaoDao;
import com.quartashow.jchampionship.dao.GrupoDao;
import com.quartashow.jchampionship.dao.HarbitoDao;
import com.quartashow.jchampionship.dao.JogadorEscaladoDao;
import com.quartashow.jchampionship.dao.JogadorInfoEdicaoDao;
import com.quartashow.jchampionship.dao.JogoDao;
import com.quartashow.jchampionship.dao.LocalDao;
import com.quartashow.jchampionship.dao.TimeDao;
import com.quartashow.jchampionship.helper.ValidationResponseHelper;
import com.quartashow.jchampionship.model.Classificacao;
import com.quartashow.jchampionship.model.ClassificacaoHist;
import com.quartashow.jchampionship.model.CollectionEventos;
import com.quartashow.jchampionship.model.Edicao;
import com.quartashow.jchampionship.model.Escalacao;
import com.quartashow.jchampionship.model.Grupo;
import com.quartashow.jchampionship.model.Harbito;
import com.quartashow.jchampionship.model.JogadorEscalado;
import com.quartashow.jchampionship.model.JogadorInfoEdicao;
import com.quartashow.jchampionship.model.Jogo;
import com.quartashow.jchampionship.model.Local;
import com.quartashow.jchampionship.model.Status;

@Controller
@RequestMapping("/jogo")
public class JogoController {

	@Autowired
	private JogoDao jogoDao;
	
	@Autowired
	private EscalacaoDao escalacaoDao;

	@Autowired
	private JogadorEscaladoDao jogadorEscaladoDao;

	@Autowired
	private ClassificacaoDao classificacaoDao;

	@Autowired
	private JogadorInfoEdicaoDao jogadorInfoEdicaoDao;

	@Autowired
	private EdicaoDao edicaoDao;

	@Autowired
	private ClassificacaoHistDao classificacaoHistDao;

	@Autowired
	private GrupoDao grupoDao;

	@Autowired
	private HarbitoDao harbitoDao;

	@Autowired
	private LocalDao localDao;

	@Autowired
	private TimeDao timeDao;

	@RequestMapping(value="/post", method=RequestMethod.POST, produces="application/json")
	public ResponseEntity<String> post(@Valid Jogo jogo, BindingResult result) {
		HttpHeaders headers = new HttpHeaders();
		String bodyJson = "{}";
		try {
			if(result.hasErrors()) {
				ValidationResponse validationResponse = new ValidationResponseHelper().fieldsErrorsToValidationResponse(result);
				return new ResponseEntity<String>(validationResponse.toJSON(), HttpStatus.UNAUTHORIZED);
			}
			if(jogo.getTimeA().getId() == jogo.getTimeB().getId()) {
				throw new RuntimeException("Nao é permitido cadastrar time contra ele mesmo time!");
			}
			if(jogo.getId() == 0) {
				jogo.setStatus(new Status(1));
				long sequencia = this.jogoDao.getCountJogosByGrupo(jogo.getGrupo())+1;
				jogo.setSequencia((int)sequencia);
				this.jogoDao.save(jogo);
				jogo = this.jogoDao.get(Jogo.class, jogo.getId());
			} else {
				this.jogoDao.update(jogo);
			}
			//headers.setLocation(URI.create("/jogo/get/"+jogo.getId()));
			bodyJson = new ObjectMapper().writeValueAsString(jogo);
		} catch (IOException e) {
			e.printStackTrace();
			return  new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<String>(bodyJson, headers , HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/get/{id}", method=RequestMethod.GET, produces="application/json")
	public ResponseEntity<String> get(@PathVariable("id") long id) throws JsonGenerationException, JsonMappingException, IOException {
		Jogo jogo = this.jogoDao.get(Jogo.class, id);
		return new ResponseEntity<String>(new ObjectMapper().writeValueAsString(jogo), HttpStatus.OK);
	}
	
	@RequestMapping(value="/delete_confirm/{id}", method=RequestMethod.GET)
	public ModelAndView pageDeleteConfirm(@PathVariable("id") long id) {
		ModelAndView mv = new ModelAndView("_base_simple");
		mv.addObject("content_import", "jogo-system-confirm-delete");
		Jogo jogo = this.jogoDao.get(Jogo.class, id);
		mv.addObject("jogo", jogo);
		return mv;
	}
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.DELETE, produces="application/json")
	public ResponseEntity<String> delete(@PathVariable("id") long id) {
		try {
			this.jogoDao.delete(Jogo.class, id);
		} catch(Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ModelAndView pageJogo(@PathVariable("id") long id) {
		ModelAndView mv = new ModelAndView("_base2");
		mv.addObject("content_import", "jogo-page-system");
		Jogo jogo = this.jogoDao.get(Jogo.class, id);
		mv.addObject("jogo", jogo);
		mv.addObject("escalacao", this.escalacaoDao.get(jogo));
		mv.addObject("edicao", jogo.getGrupo().getEdicao());
		return mv ;
	}
	
	@RequestMapping(value="/system/form/{id}", method=RequestMethod.GET) 
	public ModelAndView pageJogoForm(@PathVariable("id") long id) {
		ModelAndView mv = new ModelAndView("_base");
		mv.addObject("content_import", "jogo-form");
		Jogo jogo = this.jogoDao.get(Jogo.class, id);
		mv.addObject("jogo", jogo); 
		Edicao edicao = this.edicaoDao.get(Edicao.class, jogo.getGrupo().getEdicao().getId());
		mv.addObject("edicao", edicao);
		//mv.addObject("jogos", this.jogoDao.getJogosByEdicao(edicao));
		mv.addObject("grupos", this.grupoDao.getGruposByEdicao(edicao));
		mv.addObject("harbitos", this.harbitoDao.getList(Harbito.class));
		mv.addObject("locais", this.localDao.getList(Local.class));
		mv.addObject("times", this.timeDao.getTimesByEdicaoClassificacao(edicao));
		return mv;
	}	
	
//	@RequestMapping(value="/system/{id}", method=RequestMethod.GET)
//	public ModelAndView pageSystemJogo(@PathVariable("id") long id) {
//		ModelAndView mv = new ModelAndView("_base2");
//		mv.addObject("content_import", "jogo-page-system");
//		Jogo jogo = jogoDao.get(Jogo.class, id);
//		mv.addObject("jogo", jogo);
//		Escalacao escalacao = this.escalacaoDao.get(jogo);
//		mv.addObject("escalacao", escalacao);
//		return mv;
//	}
	
	private List<Classificacao> calculaClassificacao(Jogo jogo) {
		char vencedor = 'E';
		if(jogo.getResultadoA() > jogo.getResultadoB()) 
			vencedor = 'A';
		else if (jogo.getResultadoA() < jogo.getResultadoB())
			vencedor = 'B';
		List<Classificacao> classificacoes = this.classificacaoDao.getClassificacoesByGrupo(jogo.getGrupo());
		for (Classificacao classTime : classificacoes) {
			// calcula classificacao time A
			if(classTime.getTime().getId() == jogo.getTimeA().getId()) {
				classTime.setJogos(classTime.getJogos()+1);
				classTime.setGolsPro(classTime.getGolsPro()+jogo.getResultadoA());
				classTime.setGolsContra(classTime.getGolsContra()+jogo.getResultadoB());
				if(vencedor == 'A') {
					classTime.setVitorias(classTime.getVitorias()+1);
					classTime.setPontos(classTime.getPontos()+3);
				} 
				else if (vencedor == 'E') {
					classTime.setEmpates(classTime.getEmpates()+1);
					classTime.setPontos(classTime.getPontos()+1);
				} else if(vencedor == 'B') {
					classTime.setDerrotas(classTime.getDerrotas()+1);
				}
				this.classificacaoDao.update(classTime);
			} // Calcula classificacao Time B 
			else if(classTime.getTime().getId() == jogo.getTimeB().getId()) {
				classTime.setJogos(classTime.getJogos()+1);
				classTime.setGolsPro(classTime.getGolsPro()+jogo.getResultadoB());
				classTime.setGolsContra(classTime.getGolsContra()+jogo.getResultadoA()); 
				if(vencedor == 'A') {
					classTime.setDerrotas(classTime.getDerrotas()+1);
				} else if(vencedor == 'E') {
					classTime.setEmpates(classTime.getEmpates()+1);
					classTime.setPontos(classTime.getPontos()+1);					
				} else if(vencedor == 'B') {
					classTime.setVitorias(classTime.getVitorias()+1);
					classTime.setPontos(classTime.getPontos()+3);
				}
				this.classificacaoDao.update(classTime);
			}
		}
		return classificacoes;
	}
	
	private List<Classificacao> ordenaClassificacao(Jogo jogo) {
		List<Classificacao> classificacoes = this.classificacaoDao.getClassificacoesByGrupo(jogo.getGrupo());
		int pontosAnt = -1;
		int sg = -1;
		int posReal = 0;
		for(int posicao = 0; posicao <= classificacoes.size()-1; posicao++) {
			Classificacao cS = null;
			// seleciona 1 ainda nao seleionado
			for (Classificacao classificacao : classificacoes) {
				if(!"S".equals(classificacao.getObservacao())) {
					cS = classificacao;
					break;
				}
			}
			// Pega o com maior pontos sg 
			for (Classificacao classificacao : classificacoes) {
				if(!"S".equals(classificacao.getObservacao())) {
					if(classificacao.getPontos() > cS.getPontos()) {
						cS = classificacao;
					} else if(classificacao.getPontos() == cS.getPontos() && 
							  classificacao.getGolsPro() - classificacao.getGolsContra() > cS.getGolsPro() - cS.getGolsContra()) {
						cS = classificacao;
					}
				}
			}
			
			// pontos anterior ou Saldo de Gols for maior que pontos do proximo aumenta 1 colocacao
			if(pontosAnt > cS.getPontos() ||
			   (pontosAnt == cS.getPontos() && sg > cS.getGolsPro() - cS.getGolsContra()) || 
			   posReal == 0) {
					posReal = posicao+1;
			}
			classificacoes.get(classificacoes.indexOf(cS)).setColocacao(posReal);
			classificacoes.get(classificacoes.indexOf(cS)).setObservacao("S");
			pontosAnt = cS.getPontos();
			sg = cS.getGolsPro() - cS.getGolsContra();
		}
		for (Classificacao classificacao : classificacoes) {
			classificacao.setObservacao("N");
			classificacao.setColocacao(classificacao.getColocacao());
			this.classificacaoDao.update(classificacao);
		}
		return classificacoes;
	}
	
	/****************************************************************************************
	 * 
	 * @param id
	 * @return JSON
	 *
	 * Finaliza Jogo e gera os calculos (classificacao, jogadorInfoEdicao) e guarda 
	 *   historico da classificacao.
	 ****************************************************************************************/
	@RequestMapping(value="/finalizar/{id}", method=RequestMethod.POST, produces="application/json")
	public ResponseEntity<String> finalizar(@PathVariable("id") long id) {
		try {
			Jogo jogo = this.jogoDao.get(Jogo.class, id);
			if(jogo.getStatus().getId() != 2) {
				throw new RuntimeException("Jogo nao encontra-se em andamento.");
			}			
			// calcula classificacao
			this.calculaClassificacao(jogo);
			// ordena a classificacao calculada
			List<Classificacao> classificacoes = this.ordenaClassificacao(jogo);
			// atualiza o jogadorInfoEdicao
			this.atualizaJogadorinfoEdicao(jogo);
			// altera o jogo para status Finalizado
			jogo.setStatus(new Status(3));
			this.jogoDao.update(jogo);
			// guarda historico da classificacao se todos os jogos da rodada finalizado!
			this.saveClassificacaoHist(jogo.getGrupo(), jogo.getRodada());
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(URI.create("/edicao/"+jogo.getGrupo().getEdicao().getId()));
			return new ResponseEntity<String>(new ObjectMapper().writeValueAsString(classificacoes), headers, HttpStatus.CREATED);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	private boolean saveClassificacaoHist(Grupo grupo, int rodada) {
		List<Jogo> jogosByGrupo = jogoDao.getJogosByGrupoAndRodada(grupo, rodada);
		// verifica se todos os jogos da rodada estado finalizados...
		for (Jogo jogo : jogosByGrupo) {
			if(jogo.getStatus().getId() != 3) 
				return false;
		}
		
		// verifica se tem no historico rodada maior ou igual a mesma, sim -> nao guarda historico!
		int rodadaLastHist = this.classificacaoHistDao.getNumberHistLastRodada(grupo);
		if(rodadaLastHist >= rodada) {
			return false;
		}
		
		List<Classificacao> classificacoesByGrupo = classificacaoDao.getClassificacoesByGrupo(grupo);
		for (Classificacao classificacao : classificacoesByGrupo) {
			// se nao existe o historico guarda o mesmo!
			if(this.classificacaoHistDao.existHist(classificacao.getTime(), classificacao.getGrupo(), rodada) == false) {
				ClassificacaoHist hist = new ClassificacaoHist();
				hist.setColocacao(classificacao.getColocacao());
				hist.setDerrotas(classificacao.getDerrotas()); 
				hist.setEmpates(classificacao.getEmpates());
				hist.setGolsContra(classificacao.getGolsContra());
				hist.setGolsPro(classificacao.getGolsPro());
				hist.setGrupo(grupo);
				hist.setJogos(classificacao.getJogos());
				hist.setObservacao(classificacao.getObservacao());
				hist.setPontos(classificacao.getPontos());
				hist.setRodada(rodada);
				hist.setTime(classificacao.getTime());
				hist.setVitorias(classificacao.getVitorias());
				this.classificacaoHistDao.save(hist);
			}
		}
		return true;
	}

	private void atualizaJogadorinfoEdicao(Jogo jogo) {
		Escalacao escalacao = this.escalacaoDao.get(jogo);
		
		// Salva os Jogadores escalados que nao existem na Info Edicao! 
		for (JogadorEscalado jogadorEscalado : escalacao.getJogadoresEscalados()) {
			if(this.jogadorInfoEdicaoDao.exists(jogadorEscalado.getJogador(), jogo.getGrupo().getEdicao()) == false) 
				this.jogadorInfoEdicaoDao.save(new JogadorInfoEdicao(jogadorEscalado.getJogador(), jogo.getGrupo().getEdicao()));
		}
		
		// Pega os jogadores da Info Edicao e atualiza as informacoes...
		List<JogadorInfoEdicao> jogadoresInfoEdicao = this.jogadorInfoEdicaoDao.getJogadoresInfoEdicaoByEdicao(jogo.getGrupo().getEdicao());
		for (JogadorInfoEdicao jogadorInfoEdicao : jogadoresInfoEdicao) { // jogadores Info
		
			List<JogadorEscalado> jogadoresEscalados = escalacao.getJogadoresEscalados();
			for (JogadorEscalado jogadorEscalado : jogadoresEscalados) { // Jogadores Escalados
				
				if(jogadorEscalado.getJogador().getId() == jogadorInfoEdicao.getJogador().getId()) { // se jogadorEscalado == jogadorInfoEdicao -> atualiza
					
					jogadorInfoEdicao.setJogos(jogadorInfoEdicao.getJogos()+1);
					for (CollectionEventos evento : jogadorEscalado.getEventos()) {

						if(evento.getEvento().getId() == 1) // 1 = Gol
							jogadorInfoEdicao.setGols(jogadorInfoEdicao.getGols()+1);
						else
						if(evento.getEvento().getId() == 2) // 2 = Cartao Amarelo
							jogadorInfoEdicao.setCartaoAmarelo(jogadorInfoEdicao.getCartaoAmarelo()+1);
						else 
						if (evento.getEvento().getId() == 3) // 3 = Cartao Vermelho
							jogadorInfoEdicao.setCartaoVermelho(jogadorInfoEdicao.getCartaoVermelho()+1);
					}
					this.jogadorInfoEdicaoDao.update(jogadorInfoEdicao);					
				}
			}
			
		}
		
	}
	
	/****************************************************************************************
	 * 
	 * @param id
	 * @return JSON
	 *
	 * Retorna Jogo de Finalizado para em andamento e retorna os calculos (classificacao, 
	 *   jogadorInfoEdicao) e delete o historico da rodada caso o ultimo historico seje o mesmo
	 *   sendo calculado
	 ****************************************************************************************/	
	@RequestMapping(value="/{id}/retornStatus", method=RequestMethod.POST, produces="application/json")
	public ResponseEntity<String> returnStatusJogo(@PathVariable("id") long id) {
		try {
			Jogo jogo = this.jogoDao.get(Jogo.class, id);
			if(jogo.getStatus().getId() == 1) { // finalizado
				throw new RuntimeException("Jogo encontra-se com Status " + jogo.getStatus().getDescricao());
			} 
			else if (jogo.getStatus().getId() == 3) {
				this.retornaCalculoJogadorInfoEdicao(jogo);
				this.retornaCalculaClassificacao(jogo);
				this.ordenaClassificacao(jogo); 
				jogo.setStatus(new Status(2)); // seta jogo p/ status em andamento...
				this.jogoDao.update(jogo);
			}
			else if(jogo.getStatus().getId() == 2) {
				Escalacao escalacao = this.escalacaoDao.get(jogo);
				List<JogadorEscalado> jogadoresEscalados = escalacao.getJogadoresEscalados();
				// verifica se tem evento cadastrado
				for (JogadorEscalado jogadorEscalado : jogadoresEscalados) {
					if(jogadorEscalado.getEventos().size() > 0) {
						throw new RuntimeException("Para voltar jogo para Status Pendente, deve excluir todos os eventos(gols, cartoes) do jogo.");
					}
				}
				// exclui jogador escalado
				for (JogadorEscalado jogadorEscalado : jogadoresEscalados) {
					this.jogadorEscaladoDao.delete(JogadorEscalado.class, jogadorEscalado.getId());
				}
				// exclui escalacao
				this.escalacaoDao.delete(Escalacao.class, escalacao.getId());
				// set jogo status pendente
				jogo.setStatus(new Status(1l)); 
				jogo.setResultadoA(0);
				jogo.setResultadoB(0); 
				this.jogoDao.update(jogo);
			}
			return new ResponseEntity<String>(HttpStatus.OK);
				
		} catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	private void retornaCalculoJogadorInfoEdicao(Jogo jogo) {
		Escalacao escalacao = this.escalacaoDao.get(jogo);
		List<JogadorInfoEdicao> jogadoresInfoEdicao = this.jogadorInfoEdicaoDao.getJogadoresInfoEdicaoByEdicao(jogo.getGrupo().getEdicao());
		for (JogadorEscalado je : escalacao.getJogadoresEscalados()) {
			for(JogadorInfoEdicao jie : jogadoresInfoEdicao) {
				if(je.getJogador().getId() == jie.getJogador().getId()) {
					jie.setJogos(jie.getJogos()-1);
					for (CollectionEventos evento : je.getEventos()) {

						if(evento.getEvento().getId() == 1) // 1 = Gol
							jie.setGols(jie.getGols()-1);
						else
						if(evento.getEvento().getId() == 2) // 2 = Cartao Amarelo
							jie.setCartaoAmarelo(jie.getCartaoAmarelo()-1);
						else 
						if (evento.getEvento().getId() == 3) // 3 = Cartao Vermelho
							jie.setCartaoVermelho(jie.getCartaoVermelho()-1);
					}
					this.jogadorInfoEdicaoDao.update(jie);
				}
			}
		}
	}

	private List<Classificacao> retornaCalculaClassificacao(Jogo jogo) {
		// pega a ultima rodada guardada no historico da classificacao
		int rodadaLastHist = this.classificacaoHistDao.getNumberHistLastRodada(jogo.getGrupo());
		// se o ultimo hist da clasificacao for a mesma rodada do jogo cancelado, delete o historico... 
		if(rodadaLastHist == jogo.getRodada()) {
			List<ClassificacaoHist> histLastRodada = classificacaoHistDao.getHistListByRodada(jogo.getRodada(), jogo.getGrupo());
			for (ClassificacaoHist hist : histLastRodada) {
				this.classificacaoHistDao.delete(ClassificacaoHist.class, hist.getId());
			}			
		}
		
		char vencedor = 'E';
		if(jogo.getResultadoA() > jogo.getResultadoB()) 
			vencedor = 'A';
		else if (jogo.getResultadoA() < jogo.getResultadoB())
			vencedor = 'B';
		
		List<Classificacao> classificacoes = this.classificacaoDao.getClassificacoesByGrupo(jogo.getGrupo());
		
		// Retorna o calculo gerado da clasificacao
		for (Classificacao classTime : classificacoes) {
			// calcula classificacao time A
			if(classTime.getTime().getId() == jogo.getTimeA().getId()) {
				classTime.setJogos(classTime.getJogos()-1);
				classTime.setGolsPro(classTime.getGolsPro()-jogo.getResultadoA());
				classTime.setGolsContra(classTime.getGolsContra()-jogo.getResultadoB());
				if(vencedor == 'A') {
					classTime.setVitorias(classTime.getVitorias()-1);
					classTime.setPontos(classTime.getPontos()-3);
				} 
				else if (vencedor == 'E') {
					classTime.setEmpates(classTime.getEmpates()-1);
					classTime.setPontos(classTime.getPontos()-1);
				} else if(vencedor == 'B') {
					classTime.setDerrotas(classTime.getDerrotas()-1);
				}
				this.classificacaoDao.update(classTime);
			} // Calcula classificacao Time B 
			else if(classTime.getTime().getId() == jogo.getTimeB().getId()) {
				classTime.setJogos(classTime.getJogos()-1);
				classTime.setGolsPro(classTime.getGolsPro()-jogo.getResultadoB());
				classTime.setGolsContra(classTime.getGolsContra()-jogo.getResultadoA()); 
				if(vencedor == 'A') {
					classTime.setDerrotas(classTime.getDerrotas()-1);
				} else if(vencedor == 'E') {
					classTime.setEmpates(classTime.getEmpates()-1);
					classTime.setPontos(classTime.getPontos()-1);					
				} else if(vencedor == 'B') {
					classTime.setVitorias(classTime.getVitorias()-1);
					classTime.setPontos(classTime.getPontos()-3);
				}
				this.classificacaoDao.update(classTime);
			}
		}
		return classificacoes;
	}
	
	
}
