package com.quartashow.jchampionship.controller;

import java.net.URI;

import javax.validation.Valid;

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

import com.google.gson.Gson;
import com.quartashow.jchampionship.controller.common.ValidationResponse;
import com.quartashow.jchampionship.dao.EdicaoDao;
import com.quartashow.jchampionship.dao.JogadorDao;
import com.quartashow.jchampionship.dao.PosicaoDao;
import com.quartashow.jchampionship.helper.ValidationResponseHelper;
import com.quartashow.jchampionship.model.Edicao;
import com.quartashow.jchampionship.model.Jogador;
import com.quartashow.jchampionship.model.Posicao;

@Controller
@RequestMapping("jogador")
public class JogadorController {

	@Autowired
	private JogadorDao jogadorDao;
	
	@Autowired
	private PosicaoDao posicaoDao;

	@Autowired
	private EdicaoDao edicaoDao;

	@RequestMapping(value="/page/simple")
	public ModelAndView pageSimple() {
		ModelAndView mv = new ModelAndView("_base_simple");
		mv.addObject("content_import", "jogador-system-form");
		mv.addObject("posicoes", this.posicaoDao.getList(Posicao.class));
		mv.addObject("jogador", new Jogador());
		return mv ;
	}
	
	@RequestMapping(value="/post", method=RequestMethod.POST, produces="application/json")
	public ResponseEntity<String> post(@Valid Jogador jogador, BindingResult result) {
		if(result.hasErrors()) {
			ValidationResponse validationResponse = new ValidationResponseHelper().fieldsErrorsToValidationResponse(result);
			return new ResponseEntity<String>(new Gson().toJson(validationResponse), HttpStatus.UNAUTHORIZED);
		}
		if(jogador.getId() <= 0)
			this.jogadorDao.save(jogador);
		else
			this.jogadorDao.update(jogador); 
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(URI.create("/jogador/get/"+jogador.getId()));
		return new ResponseEntity<String>(new Gson().toJson(jogador), headers , HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/get/{id}", method=RequestMethod.GET, produces="application/json")
	public ResponseEntity<String> get(@PathVariable("id") long id) {
		Jogador jogador = this.jogadorDao.get(Jogador.class, id);
		return new ResponseEntity<String>(new Gson().toJson(jogador), HttpStatus.OK);
	}	
 
	@RequestMapping(value="/by/edicao/{edicaoId}", method=RequestMethod.GET)
	public ModelAndView pageJogadoresByEdicao(@PathVariable("edicaoId") long edicaoId) {
		ModelAndView mv = new ModelAndView("_base2");
		mv.addObject("content_import", "jogador-page");
		Edicao edicao = this.edicaoDao.get(Edicao.class, edicaoId);
		mv.addObject("edicao", edicao);
		mv.addObject("jogadores", this.jogadorDao.getJogadoresByEdicao(edicao));
		//mv.addObject("jogadores", this.jogadorDao.getList(Jogador.class));
		return mv;
	}
	
	@RequestMapping(value="/system/form/{id}", method=RequestMethod.GET)
	public ModelAndView pageForm(@PathVariable("id") long id) {
		ModelAndView mv = new ModelAndView("_base2");
		mv.addObject("content_import", "jogador-system-form");
		mv.addObject("jogador", this.jogadorDao.get(Jogador.class, id));
		mv.addObject("posicoes", this.posicaoDao.getList(Posicao.class));
		return mv;
	}
}
