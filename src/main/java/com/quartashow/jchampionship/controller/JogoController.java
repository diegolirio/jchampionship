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
import com.quartashow.jchampionship.dao.EdicaoDao;
import com.quartashow.jchampionship.dao.EscalacaoDao;
import com.quartashow.jchampionship.dao.JogadorEscaladoDao;
import com.quartashow.jchampionship.dao.JogadorInfoEdicaoDao;
import com.quartashow.jchampionship.dao.JogoDao;
import com.quartashow.jchampionship.helper.ValidationResponseHelper;
import com.quartashow.jchampionship.model.Classificacao;
import com.quartashow.jchampionship.model.CollectionEventos;
import com.quartashow.jchampionship.model.Escalacao;
import com.quartashow.jchampionship.model.JogadorEscalado;
import com.quartashow.jchampionship.model.JogadorInfoEdicao;
import com.quartashow.jchampionship.model.Jogo;
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
			jogo.setStatus(new Status(1));
			this.jogoDao.save(jogo);
			jogo = this.jogoDao.get(Jogo.class, jogo.getId());
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
		List<Classificacao> classificacoes = this.classificacaoDao.getClassificacoesByGrupo(jogo.getGrupo());
		char vencedor = 'E';
		if(jogo.getResultadoA() > jogo.getResultadoB()) 
			vencedor = 'A';
		else if (jogo.getResultadoA() < jogo.getResultadoB())
			vencedor = 'B';
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
	
	@RequestMapping(value="/finalizar/{id}", method=RequestMethod.POST, produces="application/json")
	public ResponseEntity<String> finalizar(@PathVariable("id") long id) {
		try {
			Jogo jogo = this.jogoDao.get(Jogo.class, id);
			this.calculaClassificacao(jogo);
			List<Classificacao> classificacoes = this.ordenaClassificacao(jogo);
			this.atualizaJogadorinfoEdicao(jogo);
			jogo.setStatus(new Status(3));
			this.jogoDao.update(jogo);		
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(URI.create("/edicao/"+jogo.getGrupo().getEdicao().getId()));
			return new ResponseEntity<String>(new ObjectMapper().writeValueAsString(classificacoes), headers, HttpStatus.CREATED);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	private void atualizaJogadorinfoEdicao(Jogo jogo) {
		Escalacao escalacao = this.escalacaoDao.get(jogo);
		
		// Salva os Jogadores escalados que nao existem na Info Edicao! 
		for (JogadorEscalado jogadorEscalado : escalacao.getJogadoresEscalados()) {
			if(this.jogadorInfoEdicaoDao.exists(jogadorEscalado.getJogador(), jogo.getGrupo().getEdicao()) == false) 
				this.jogadorInfoEdicaoDao.save(new JogadorInfoEdicao(jogadorEscalado.getJogador(), jogo.getGrupo().getEdicao()));
		}
		
		// Pega os jogadores da Info Edicao e atualiza as informacoes...
		List<JogadorInfoEdicao> jogadoresInfoEdicao = this.jogadorInfoEdicaoDao.getJogadorInfoEdicaoByEdicao(jogo.getGrupo().getEdicao());
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

	@RequestMapping(value="/{id}/retornStatus", method=RequestMethod.POST, produces="application/json")
	public ResponseEntity<String> returnCalculadoStatusFinalizadoEmAndamento(@PathVariable("id") long id) {
		try {
			Jogo jogo = this.jogoDao.get(Jogo.class, id);
			this.retornaCalculoJogadorInfoEdicao(jogo);
			this.retornaCalculaClassificacao(jogo);
			this.ordenaClassificacao(jogo);
			jogo.setStatus(new Status(2));
			this.jogoDao.update(jogo);
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	private void retornaCalculoJogadorInfoEdicao(Jogo jogo) {
		Escalacao escalacao = this.escalacaoDao.get(jogo);
		List<JogadorInfoEdicao> jogadoresInfoEdicao = this.jogadorInfoEdicaoDao.getJogadorInfoEdicaoByEdicao(jogo.getGrupo().getEdicao());
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
		List<Classificacao> classificacoes = this.classificacaoDao.getClassificacoesByGrupo(jogo.getGrupo());
		char vencedor = 'E';
		if(jogo.getResultadoA() > jogo.getResultadoB()) 
			vencedor = 'A';
		else if (jogo.getResultadoA() < jogo.getResultadoB())
			vencedor = 'B';
		
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
