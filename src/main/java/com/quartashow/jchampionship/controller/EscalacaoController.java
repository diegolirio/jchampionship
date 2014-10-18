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

import com.quartashow.jchampionship.dao.EscalacaoDao;
import com.quartashow.jchampionship.dao.EventoDao;
import com.quartashow.jchampionship.dao.JogadorEscaladoDao;
import com.quartashow.jchampionship.dao.JogoDao;
import com.quartashow.jchampionship.model.Escalacao;
import com.quartashow.jchampionship.model.Evento;
import com.quartashow.jchampionship.model.Jogador;
import com.quartashow.jchampionship.model.JogadorEscalado;
import com.quartashow.jchampionship.model.Jogo;

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

	@RequestMapping(value="/post/jogo/{jogoId}", method=RequestMethod.POST)
	public ResponseEntity<String> addEscalacao(@PathVariable("jogoId") long jogoId) {
		try {
			Jogo jogo = this.jogoDao.get(Jogo.class, jogoId);
			
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
			
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(URI.create("/jogo/system/"+jogo .getId()));
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
		mv.addObject("jogo", this.jogoDao.get(Jogo.class, jogoId));
		mv.addObject("evento", this.eventoDao.get(Evento.class, eventoId));
		return mv;
	}
}
