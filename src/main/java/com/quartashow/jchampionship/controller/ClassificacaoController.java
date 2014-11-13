package com.quartashow.jchampionship.controller;

import java.io.IOException;
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
import com.quartashow.jchampionship.dao.GrupoDao;
import com.quartashow.jchampionship.helper.ValidationResponseHelper;
import com.quartashow.jchampionship.model.Classificacao;
import com.quartashow.jchampionship.model.Grupo;

@Controller
@RequestMapping("classificacao")
public class ClassificacaoController {

	@Autowired
	private ClassificacaoDao classficacaoDao;
	@Autowired
	private GrupoDao grupoDao;

	@RequestMapping(value="/post", method=RequestMethod.POST, produces="application/json")
	public ResponseEntity<String> post(@Valid Classificacao classificacao, BindingResult result) {
		if(result.hasErrors()) {
			ValidationResponse validationResponse = new ValidationResponseHelper().fieldsErrorsToValidationResponse(result);
			return new ResponseEntity<String>(new Gson().toJson(validationResponse), HttpStatus.UNAUTHORIZED);
		}
		String body = "{}";
		HttpHeaders headers = new HttpHeaders();
		try {
			this.classficacaoDao.save(classificacao);
			headers.setLocation(URI.create("/classificacao/get/"+classificacao.getId()));
			classificacao = this.classficacaoDao.get(Classificacao.class, classificacao.getId());
			//String json = new Gson().toJson(classificacao);
			body = new ObjectMapper().writeValueAsString(classificacao);
		} catch (IOException e) {
			body = "{\"mesage\":\""+e.getMessage()+"\"}";
		}		
		return new ResponseEntity<String>(body, headers , HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/get/{id}", method=RequestMethod.GET, produces="application/json")
	public ResponseEntity<String> get(@PathVariable("id") long id) {
		Classificacao classificacao = this.classficacaoDao.get(Classificacao.class, id);
		return new ResponseEntity<String>(new Gson().toJson(classificacao ), HttpStatus.OK);
	}
	
	@RequestMapping(value="/delete_confirm/{id}", method=RequestMethod.GET)
	public ModelAndView pageConfirmDelete(@PathVariable("id") long id) {
		ModelAndView mv = new ModelAndView("_base_simple");
		mv.addObject("content_import", "classificacao-system-confirm-delete");
		mv.addObject("classificacao", this.classficacaoDao.get(Classificacao.class, id));
		return mv;
	}		
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<String> delete(@PathVariable("id") long id) {
		this.classficacaoDao.delete(Classificacao.class, id);
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	@RequestMapping(value="/get/list/lider/by/grupo/{grupoId}", method=RequestMethod.GET, produces="application/json")
	public ResponseEntity<String> getListLider(@PathVariable("grupoId") long grupoId) {
		try {
			Grupo grupo = this.grupoDao.get(Grupo.class, grupoId);
			List<Classificacao> classificacoes = this.classficacaoDao.getLideres(grupo);
			return new ResponseEntity<String>(new ObjectMapper().writeValueAsString(classificacoes), HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}	
	
}
 