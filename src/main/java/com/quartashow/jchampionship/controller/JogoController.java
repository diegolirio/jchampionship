package com.quartashow.jchampionship.controller;

import java.io.IOException;
import java.net.URI;

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
import com.quartashow.jchampionship.dao.EscalacaoDao;
import com.quartashow.jchampionship.dao.JogadorEscaladoDao;
import com.quartashow.jchampionship.dao.JogoDao;
import com.quartashow.jchampionship.helper.ValidationResponseHelper;
import com.quartashow.jchampionship.model.Escalacao;
import com.quartashow.jchampionship.model.Jogador;
import com.quartashow.jchampionship.model.JogadorEscalado;
import com.quartashow.jchampionship.model.Jogo;

@Controller
@RequestMapping("/jogo")
public class JogoController {

	@Autowired
	private JogoDao jogoDao;
	
	@Autowired
	private EscalacaoDao escalacaoDao;

	@Autowired
	private JogadorEscaladoDao jogadorEscaladoDao;

	@RequestMapping(value="/post", method=RequestMethod.POST, produces="application/json")
	public ResponseEntity<String> post(@Valid Jogo jogo, BindingResult result) {
		HttpHeaders headers = new HttpHeaders();
		String bodyJson = "{}";
		try {
			if(result.hasErrors()) {
				ValidationResponse validationResponse = new ValidationResponseHelper().fieldsErrorsToValidationResponse(result);
				return new ResponseEntity<String>(validationResponse.toJSON(), HttpStatus.UNAUTHORIZED);
			}
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
		mv.addObject("content_import", "jogo-page");
		Jogo jogo = this.jogoDao.get(Jogo.class, id);
		mv.addObject("jogo", jogo);
		mv.addObject("escalacao", this.escalacaoDao.get(jogo));
		mv.addObject("edicao", jogo.getGrupo().getEdicao());
		return mv ;
	}
	
	@RequestMapping(value="/{jogoId}/add/escalacao", method=RequestMethod.POST)
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
	
}
