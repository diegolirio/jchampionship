package com.quartashow.jchampionship.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.quartashow.jchampionship.dao.CollectionEventosDao;
import com.quartashow.jchampionship.dao.EscalacaoDao;
import com.quartashow.jchampionship.dao.EventoDao;
import com.quartashow.jchampionship.dao.JogadorDao;
import com.quartashow.jchampionship.dao.JogadorEscaladoDao;
import com.quartashow.jchampionship.dao.JogadorInfoEdicaoDao;
import com.quartashow.jchampionship.dao.JogoDao;
import com.quartashow.jchampionship.dao.TimeDao;
import com.quartashow.jchampionship.model.CollectionEventos;
import com.quartashow.jchampionship.model.Escalacao;
import com.quartashow.jchampionship.model.Evento;
import com.quartashow.jchampionship.model.Jogador;
import com.quartashow.jchampionship.model.JogadorEscalado;
import com.quartashow.jchampionship.model.Jogo;
import com.quartashow.jchampionship.model.Status;
import com.quartashow.jchampionship.model.Time;

@Controller
@RequestMapping("/escalacao")
public class EscalacaoController {

	@Autowired
	private JogoDao jogoDao;
	
	@Autowired
	private EscalacaoDao escalacaoDao;

	@Autowired
	private JogadorEscaladoDao jogadorEscaladoDao;

	@Autowired
	private EventoDao eventoDao;

	@Autowired
	private CollectionEventosDao collectionEventoDao;

	@Autowired
	private JogadorInfoEdicaoDao jogadorInfoEdicaoDao;

	@Autowired
	private TimeDao timeDao;

	@Autowired
	private JogadorDao jogadorDao;

	@RequestMapping(value="/post/jogo/{jogoId}", method=RequestMethod.POST)
	public ResponseEntity<String> addEscalacao(@PathVariable("jogoId") long jogoId) {
		try {
			Jogo jogo = this.jogoDao.get(Jogo.class, jogoId);
			
			if(jogo.getTimeA().getJogadores().size() < 1) {
				throw new RuntimeException(jogo.getTimeA().getNome() + " nao ha jogadores cadastrados, por favor cadastre ao menos Um.");
			}
			if(jogo.getTimeB().getJogadores().size() < 1) {
				throw new RuntimeException(jogo.getTimeB().getNome() + " nao ha jogadores cadastrados, por favor cadastre ao menos Um.");
			}

			Escalacao escalacaoExclui = this.escalacaoDao.get(jogo);
			if(escalacaoExclui != null) {
				for (JogadorEscalado je : escalacaoExclui.getJogadoresEscalados()) {
					this.jogadorEscaladoDao.delete(JogadorEscalado.class, je.getId());
				}
				this.escalacaoDao.delete(Escalacao.class, escalacaoExclui.getId());
			}
			
			Escalacao escalacao = new Escalacao();
			escalacao.setJogo(jogo);
			this.escalacaoDao.save(escalacao);
			
			for (Jogador j : jogo.getTimeA().getJogadores()) {
				JogadorEscalado jeA = new JogadorEscalado();
				jeA.setEscalacao(escalacao);			
				jeA.setTime(jogo.getTimeA());
				jeA.setJogador(j);
				this.jogadorEscaladoDao.save(jeA); 
			}
	
			for (Jogador j : jogo.getTimeB().getJogadores()) {
				JogadorEscalado jeB = new JogadorEscalado();
				jeB.setEscalacao(escalacao);			
				jeB.setTime(jogo.getTimeB());
				jeB.setJogador(j);
				this.jogadorEscaladoDao.save(jeB);
			}		
			jogo.setStatus(new Status(2)); // set em andamento
			this.jogoDao.update(jogo);
			
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(URI.create("/jogo/"+jogo .getId()));
			return new ResponseEntity<String>(headers, HttpStatus.CREATED);
		} catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}	
	
	@RequestMapping(value="/system/{jogoId}/add/evento/{eventoId}", method=RequestMethod.GET)
	public ModelAndView pagePopupAddEvento(@PathVariable("jogoId") long jogoId, @PathVariable("eventoId") long eventoId) {
		ModelAndView mv = new ModelAndView("_base_simple");
		mv.addObject("content_import", "escalacao-evento");
		Jogo jogo = this.jogoDao.get(Jogo.class, jogoId);
		mv.addObject("jogo", jogo);
		mv.addObject("evento", this.eventoDao.get(Evento.class, eventoId));
		Escalacao escalacao = this.escalacaoDao.get(jogo);
		mv.addObject("jogadoresEscalados", escalacao.getJogadoresEscalados());
		return mv;
	}
	
	@RequestMapping(value="/add/evento/{eventoId}", method=RequestMethod.POST)
	public ResponseEntity<String> addEventoJogadorEscalado(long jogadorEscaladoId, @PathVariable("eventoId") long eventoId) {
		JogadorEscalado jogadorEscalado = this.jogadorEscaladoDao.get(JogadorEscalado.class, jogadorEscaladoId);
		Evento evento = this.eventoDao.get(Evento.class, eventoId);
		
		CollectionEventos eventos = new CollectionEventos();
		eventos.setEvento(evento);
		eventos.setJogadorEscalado(jogadorEscalado);
		this.collectionEventoDao.save(eventos);
		//this.jogadorEscaladoDao.update(jogadorEscalado);
		Jogo jogo = this.jogoDao.get(Jogo.class, jogadorEscalado.getEscalacao().getJogo().getId());
		if(eventoId == 1) {
			if(jogadorEscalado.getTime().getId() == jogo.getTimeA().getId()) {
				jogo.setResultadoA(jogo.getResultadoA()+1);
			} else if(jogadorEscalado.getTime().getId() == jogo.getTimeB().getId()) {
				jogo.setResultadoB(jogo.getResultadoB()+1);
			}
		}
		this.jogoDao.update(jogo);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(URI.create("/jogo/system/"+jogadorEscalado.getEscalacao().getJogo().getId()));
		return new ResponseEntity<String>(headers , HttpStatus.CREATED);
	}
	
	@RequestMapping(value="{escalacaoId}/add/jogador/time/{timeId}", method=RequestMethod.GET)
	public ModelAndView pageAddJogadorEscalado(@PathVariable("escalacaoId") long escalacaoId, @PathVariable("timeId") long timeId) {
		ModelAndView mv = new ModelAndView("_base_simple");
		mv.addObject("content_import", "escalacao-add-jogodorescalado");
		mv.addObject("time", this.timeDao.get(Time.class, timeId));
		Escalacao escalacao = this.escalacaoDao.get(Escalacao.class, escalacaoId);
		mv.addObject("escalacao", escalacao);
		mv.addObject("jogadores", this.jogadorDao.getJogadoresNaoEscalados(escalacao.getJogo()));
		return mv;
	}
	
	@RequestMapping(value="{escalacaoId}/add/jogador/time/{timeId}", method=RequestMethod.POST, produces="application/json")
	public ResponseEntity<String> saveJogadorEscalado(@PathVariable("escalacaoId") long escalacaoId, @PathVariable("timeId") long timeId, long jogadorId) {
		try {
			System.out.println(jogadorId);
			JogadorEscalado jogadorEscalado = new JogadorEscalado();
			Escalacao escalacao = this.escalacaoDao.get(Escalacao.class, escalacaoId);
			jogadorEscalado.setEscalacao(escalacao);
			Time time = this.timeDao.get(Time.class, timeId);
			jogadorEscalado.setTime(time);
			Jogador jogador = this.jogadorDao.get(Jogador.class, jogadorId);
			jogadorEscalado.setJogador(jogador );
			System.out.println(jogadorEscalado);
			this.jogadorEscaladoDao.save(jogadorEscalado);
			return new ResponseEntity<String>(HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value="/jogadorEscalado/delete/{id}", method=RequestMethod.GET)
	public ModelAndView pageDeleteJogadorEscalado(@PathVariable("id") long id) {
		ModelAndView mv = new ModelAndView("_base_simple");
		mv.addObject("content_import", "escalacao-jogadorescalado-delete");
		mv.addObject("jogadorEscalado", this.jogadorEscaladoDao.get(JogadorEscalado.class, id));
		return mv;
	}
	
	@RequestMapping(value="/jogadorEscalado/delete/{id}", method=RequestMethod.POST)
	public ResponseEntity<String> deleteJogadorEscalado(@PathVariable("id") long id) {
		try {
			JogadorEscalado jogadorEscalado = this.jogadorEscaladoDao.get(JogadorEscalado.class, id);
			if(jogadorEscalado.getEventos().size() == 0) {
				this.jogadorEscaladoDao.delete(JogadorEscalado.class, id);
			} else {
				throw new RuntimeException("Existem Gols ou Cartões adicionados a este Jogador, para retira-lo da escalação, retire primeiramente esses eventos!");
			}
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}	
	
	@RequestMapping(value="/jogadorEscalado/{jogadorEscaladoId}/eventos/delete", method=RequestMethod.GET)
	public ModelAndView pageSimpleDeleteEventosJogadorEscalado(@PathVariable("jogadorEscaladoId") long jogadorEscaladoId) {
		ModelAndView mv = new ModelAndView("_base_simple");
		mv.addObject("content_import", "escalado-jogadorescalado-eventos-delete");
		mv.addObject("jogadorEscalado", this.jogadorEscaladoDao.get(JogadorEscalado.class, jogadorEscaladoId));
		return mv;
	}
	
	@RequestMapping(value="/jogadorEscalado/{jogadorEscaladoId}/eventos/delete", method=RequestMethod.POST, produces="application/json")
	public ResponseEntity<String> deleteEventosJogadorEscalado(@PathVariable("jogadorEscaladoId") long jogadorEscaladoId, long collectionEventoId) {
		CollectionEventos collectionEvento = this.collectionEventoDao.get(CollectionEventos.class, collectionEventoId);
		if(collectionEvento.getEvento().getId() == 1) { // GOL
			JogadorEscalado jogadorEscalado = this.jogadorEscaladoDao.get(JogadorEscalado.class, jogadorEscaladoId);
			Jogo jogo = jogadorEscalado.getEscalacao().getJogo();
			if(jogadorEscalado.getTime().getId() == jogadorEscalado.getEscalacao().getJogo().getTimeA().getId()) {
				jogo.setResultadoA(jogo.getResultadoA()-1);
			} else {
				jogo.setResultadoB(jogo.getResultadoB()-1);
			}
			this.jogoDao.update(jogo);
		}
		this.collectionEventoDao.delete(CollectionEventos.class, collectionEventoId);
		return new ResponseEntity<String>(HttpStatus.OK);
	}	
}
