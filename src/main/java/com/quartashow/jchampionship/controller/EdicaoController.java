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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.quartashow.jchampionship.controller.common.ValidationResponse;
import com.quartashow.jchampionship.dao.EdicaoDao;
import com.quartashow.jchampionship.dao.GrupoDao;
import com.quartashow.jchampionship.dao.HarbitoDao;
import com.quartashow.jchampionship.dao.JogoDao;
import com.quartashow.jchampionship.dao.LocalDao;
import com.quartashow.jchampionship.dao.TimeDao;
import com.quartashow.jchampionship.helper.ValidationResponseHelper;
import com.quartashow.jchampionship.model.Edicao;
import com.quartashow.jchampionship.model.Harbito;
import com.quartashow.jchampionship.model.Local;
import com.quartashow.jchampionship.model.Status;
import com.quartashow.jchampionship.model.Time;

@Controller
@RequestMapping("/edicao")
public class EdicaoController { 
	
	@Autowired
	private EdicaoDao edicaoDao;
	
	@Autowired
	private GrupoDao grupoDao;

	@Autowired
	private JogoDao jogoDao;

	@Autowired
	private HarbitoDao harbitoDao;

	@Autowired
	private LocalDao localDao;

	@Autowired
	private TimeDao timeDao;	
	
	@RequestMapping(value="/system", method=RequestMethod.GET)
	public ModelAndView pageEdicoesPendentes() {
		ModelAndView mv = new ModelAndView("_base");
		mv.addObject("content_import", "edicao-system-pendentes");
		return mv;
	}	
	
	@RequestMapping(value="/system/nova", method=RequestMethod.GET)
	public ModelAndView pageNovaEdicao(Edicao edicao) {
		ModelAndView mv = new ModelAndView("_base");
		mv.addObject("content_import", "edicao-system-form");
		mv.addObject("edicao", edicao);
		System.out.println(edicao);
		return mv;
	}
	
	@RequestMapping(value="/system/nova", method=RequestMethod.POST, produces="application/json")
    public ResponseEntity<String> save(@Valid Edicao edicao, BindingResult result) {
	   
	   if(result.hasErrors() == true) {
		   ValidationResponse validationResponse = new ValidationResponseHelper().fieldsErrorsToValidationResponse(result);		
	       String json = new Gson().toJson(validationResponse);
		   return new ResponseEntity<String>(json, HttpStatus.UNAUTHORIZED);
	   }
	   edicao.setStatus(new Status(1, "Pendente"));
	   this.edicaoDao.save(edicao);
	   URI location = URI.create("/edicao/system/"+edicao.getId()+"/grupos");
	   HttpHeaders responseHeaders = new HttpHeaders();
	   responseHeaders.setLocation(location);
	   responseHeaders.set("id", String.valueOf(edicao.getId()));
	   ResponseEntity<String> responseEntity = new ResponseEntity<String>("OK", responseHeaders, HttpStatus.CREATED);
	   return responseEntity;
	 }
	

	@RequestMapping(value="/get/list/by/status/{idStatus}", method=RequestMethod.GET,produces="application/json")
	public @ResponseBody List<Edicao> getListByStatus(@PathVariable("idStatus") long idStatus) {
		return this.edicaoDao.getListByStatus(new Status(idStatus));
	}		
	
	@RequestMapping(value="/system/{idEdicao}/grupos", method=RequestMethod.GET)
	public ModelAndView pageAddGrupos(@PathVariable("idEdicao") long idEdicao) {
		ModelAndView mv = new ModelAndView("_base");
		mv.addObject("content_import", "edicao-system-grupos");
		Edicao edicao = this.edicaoDao.get(Edicao.class, idEdicao);
		mv.addObject("edicao", edicao);
		mv.addObject("grupos", grupoDao.getGruposByEdicao(edicao));
		return mv;
	}	
	
	@RequestMapping(value="/system/{idEdicao}/times", method=RequestMethod.GET)
	public ModelAndView pageAddTimes(@PathVariable("idEdicao") long idEdicao) {
		ModelAndView mv = new ModelAndView("_base");
		mv.addObject("content_import", "edicao-system-times");
		Edicao edicao = this.edicaoDao.get(Edicao.class, idEdicao);
		mv.addObject("edicao", edicao);
		mv.addObject("times", this.timeDao.getList(Time.class));
		mv.addObject("timesClassificacao", null);
		return mv;
	}		
	
	@RequestMapping(value="/system/{idEdicao}/jogos", method=RequestMethod.GET) 
	public ModelAndView pageAddJogos(@PathVariable("idEdicao") long idEdicao) {
		ModelAndView mv = new ModelAndView("_base");
		mv.addObject("content_import", "edicao-system-jogos");
		Edicao edicao = this.edicaoDao.get(Edicao.class, idEdicao);
		mv.addObject("edicao", edicao);
		mv.addObject("jogos", this.jogoDao.getJogosByEdicao(edicao));
		mv.addObject("grupos", this.grupoDao.getGruposByEdicao(edicao));
		mv.addObject("harbitos", this.harbitoDao.getList(Harbito.class));
		mv.addObject("locais", this.localDao.getList(Local.class));
		return mv;
	}
}
