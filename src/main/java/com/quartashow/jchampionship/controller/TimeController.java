package com.quartashow.jchampionship.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

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

import com.google.gson.Gson;
import com.quartashow.jchampionship.controller.common.ValidationResponse;
import com.quartashow.jchampionship.dao.ClassificacaoDao;
import com.quartashow.jchampionship.dao.EdicaoDao;
import com.quartashow.jchampionship.dao.GrupoDao;
import com.quartashow.jchampionship.dao.JogadorDao;
import com.quartashow.jchampionship.dao.TimeDao;
import com.quartashow.jchampionship.helper.ValidationResponseHelper;
import com.quartashow.jchampionship.model.Classificacao;
import com.quartashow.jchampionship.model.Edicao;
import com.quartashow.jchampionship.model.Grupo;
import com.quartashow.jchampionship.model.Jogador;
import com.quartashow.jchampionship.model.Time;

@Controller
@RequestMapping("time")
public class TimeController {

	@Autowired
	private TimeDao timeDao;
	
	@Autowired
	private JogadorDao jogadorDao;

	@Autowired
	private EdicaoDao edicaoDao;

	@Autowired
	private ClassificacaoDao classificacaoDao;

	@Autowired
	private GrupoDao grupoDao;

	@RequestMapping(value="/page/simple")
	public ModelAndView pageSimple(Time time) {
		ModelAndView mv = new ModelAndView("_base_simple");
		mv.addObject("content_import", "time-system-form");
		mv.addObject("time", time);
		return mv ;
	}
	
	@RequestMapping(value="/post", method=RequestMethod.POST, produces="application/json")
	public ResponseEntity<String> post(@Valid Time time, BindingResult result) {
		if(result.hasErrors()) {
			ValidationResponse validationResponse = new ValidationResponseHelper().fieldsErrorsToValidationResponse(result);
			return new ResponseEntity<String>(new Gson().toJson(validationResponse), HttpStatus.UNAUTHORIZED);
		}
		if(time.getId() <= 0)
			this.timeDao.save(time);
		else
			this.timeDao.update(time);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(URI.create("/time/get/"+time.getId()));
		return new ResponseEntity<String>(new Gson().toJson(time), headers , HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/get/{id}", method=RequestMethod.GET, produces="application/json")
	public ResponseEntity<String> get(@PathVariable("id") long id) {
		Time time = this.timeDao.get(Time.class, id);
		return new ResponseEntity<String>(new Gson().toJson(time), HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}")
	public ModelAndView pageTime(@PathVariable("id") long id) {
		ModelAndView mv = new ModelAndView("_base2");
		mv.addObject("content_import", "time-page");
		Time time = this.timeDao.get(Time.class, id);
		mv.addObject("time", time);
		return mv;
	}
	
	@RequestMapping(value="/system/{id}")
	public ModelAndView pageTimeSystem(@PathVariable("id") long id) {
		ModelAndView mv = new ModelAndView("_base2");
		mv.addObject("content_import", "time-form");
		Time time = this.timeDao.get(Time.class, id);
		mv.addObject("time", time);
		mv.addObject("jogadoresAll", this.jogadorDao.getList(Jogador.class));
		return mv;
	}
	
	@RequestMapping(value="/system/{timeId}/post/add/jogador/{jogadorId}", method=RequestMethod.POST, produces="application/json")
	public ResponseEntity<String> addJogadorTime(@PathVariable("timeId") long timeId, @PathVariable("jogadorId") long jogadorId) {
		try {
			Time time = this.timeDao.get(Time.class, timeId);
			Jogador jogador = this.jogadorDao.get(Jogador.class, jogadorId);
			time.getJogadores().add(jogador);
			this.timeDao.update(time);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(URI.create("/time/system/"+jogador.getId()));			
			return new ResponseEntity<String>(new ObjectMapper().writeValueAsString(jogador), headers , HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value="/system/{timeId}/remove/jogador/{jogadorId}", method=RequestMethod.POST, produces="application/json")
	public ResponseEntity<String> removeJogadorTime(@PathVariable("timeId") long timeId, @PathVariable("jogadorId") long jogadorId) {
		try {
			Time time = this.timeDao.get(Time.class, timeId);
			Jogador jogador = this.jogadorDao.get(Jogador.class, jogadorId);
			for (int i = 0; i <= time.getJogadores().size()-1; i++) {
				if(time.getJogadores().get(i).getId() == jogador.getId()) {
					System.out.println("REMOVE: " + i);
					System.out.println("QTDE: " + time.getJogadores().size());
					time.getJogadores().remove(i);
					System.out.println("QTDE: " + time.getJogadores().size());
				}
			}			
			this.timeDao.update(time);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(URI.create("/time/system/"+jogador.getId()));			
			return new ResponseEntity<String>(headers , HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
		
	@RequestMapping(value="/by/edicao/{edicaoId}", method=RequestMethod.GET)
	public ModelAndView pageTimeByEdicao(@PathVariable("edicaoId") long edicaoId) {
		ModelAndView mv = new ModelAndView("_base2");
		mv.addObject("content_import", "time-list");
		Edicao edicao = this.edicaoDao.get(Edicao.class, edicaoId);
		mv.addObject("edicao", edicao);
		List<Time> times = this.timeDao.getTimesByEdicaoClassificacao(edicao);
		mv.addObject("times", times);
		return mv;
	}
	
	@RequestMapping(value="/{id}/edicao/{edicaoId}", method=RequestMethod.GET)
	public ModelAndView pageTime(@PathVariable("id") long id, @PathVariable("edicaoId") long edicaoId) {
		if(edicaoId <= 0) 
			return new ModelAndView("redirect:/time/"+id);			
		ModelAndView mv = new ModelAndView("_base2");
		mv.addObject("content_import", "time-page");
		Time time = this.timeDao.get(Time.class, id);
		Edicao edicao = edicaoDao.get(Edicao.class, edicaoId);
		mv.addObject("time", time);
		mv.addObject("edicao", edicao);
		Classificacao classificacao = null;
		for(Grupo g : this.grupoDao.getGruposByEdicao(edicao)) {
			for(Classificacao c : this.classificacaoDao.getClassificacoesByGrupo(g)) {
				if(c.getTime().getId() == time.getId()) {
					classificacao = c;
					break;
				}
			}
		}
		mv.addObject("classificacao", classificacao);
		return mv;
	}	
}
