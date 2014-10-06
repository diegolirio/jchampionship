package com.quartashow.jchampionship.controller;

import java.net.URI;
import java.util.List;

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
import com.quartashow.jchampionship.dao.GrupoDao;
import com.quartashow.jchampionship.helper.ValidationResponseHelper;
import com.quartashow.jchampionship.model.Edicao;
import com.quartashow.jchampionship.model.Grupo;

@Controller
@RequestMapping("/grupo")
public class GrupoController {

	@Autowired
	private GrupoDao grupoDao;
	
	private Gson gson;
	
	public GrupoController() {
		this.gson = new Gson();
	}

	@RequestMapping(value="/post", method=RequestMethod.POST, produces="application/json")
	public ResponseEntity<String> save(@Valid Grupo grupo, BindingResult result) {
		if(result.hasErrors()) {
			ValidationResponse validationResponse = new ValidationResponseHelper().fieldsErrorsToValidationResponse(result);
			return new ResponseEntity<String>(this.gson.toJson(validationResponse), HttpStatus.UNAUTHORIZED);
		}
		this.grupoDao.save(grupo);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(URI.create("/grupo/get/"+grupo.getId()));
		headers.set("id", String.valueOf(grupo.getId()));
		return new ResponseEntity<String>(this.gson.toJson(grupo), headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/get/{id}", method=RequestMethod.GET, produces="application/json")
	public  ResponseEntity<String> get(@PathVariable("id") long id) {
		Grupo grupo = this.grupoDao.get(Grupo.class, id); 
		return new ResponseEntity<String>(this.gson.toJson(grupo), HttpStatus.OK);
	}
	
	@RequestMapping(value="/get/list/by/edicao/{edicaoId}", method=RequestMethod.GET, produces="application/json")
	public ResponseEntity<String> getGruposByEdicao(@PathVariable("edicaoId") long edicaoId) {
		List<Grupo> list = this.grupoDao.getGruposByEdicao(new Edicao(edicaoId)); 
		return new ResponseEntity<String>(this.gson.toJson(list), HttpStatus.OK);
	}
	
	@RequestMapping(value="/delete_confirm/{id}", method=RequestMethod.GET)
	public ModelAndView deleteConfirm(@PathVariable("id") long id) {
		ModelAndView mv = new ModelAndView("_base_simple");
		mv.addObject("content", "grupo-system-confirm-delete");
		Grupo grupo = this.grupoDao.get(Grupo.class, id);
		mv.addObject("grupo", grupo); 
		return mv; 
	}
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<String> delete(@PathVariable("id") long id) {
		this.grupoDao.delete(Grupo.class, id);
		return new ResponseEntity<String>(HttpStatus.OK);
	}
}
