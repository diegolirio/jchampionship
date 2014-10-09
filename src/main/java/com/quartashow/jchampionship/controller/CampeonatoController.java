package com.quartashow.jchampionship.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.quartashow.jchampionship.dao.CampeonatoDao;
import com.quartashow.jchampionship.dao.StatusDao;
import com.quartashow.jchampionship.model.Campeonato;

@Controller
public class CampeonatoController {
	
	private static final Logger logger = LoggerFactory.getLogger(CampeonatoController.class);
	
	@Autowired
	private CampeonatoDao campeonatoDao;
	@Autowired
	private StatusDao statusDao;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home() {
		logger.info("");
		ModelAndView mv = new ModelAndView("_base");
		mv.addObject("content_import", "campeonato-classificacao-static");
		return mv;
	}
	
	@RequestMapping(value="/campeonato/get/list", produces="application/json")
	public @ResponseBody List<Campeonato> getList() {
		return this.campeonatoDao.getList(Campeonato.class);
	}
	
//	@RequestMapping(value="/pre_cadastro", method=RequestMethod.GET, produces="application/json")
//	public ResponseEntity<String> initValues() {
//		Status pendente = new Status("Pendente");
//		Status andamento = new Status("Em Andamento");
//		Status finalizado = new Status("Fianlizado");
//		this.statusDao.save(pendente);
//		this.statusDao.save(andamento);
//		this.statusDao.save(finalizado);
//
//		Campeonato quartashow = new Campeonato();
//		quartashow.setDescricao("Quarta Show");
//		this.campeonatoDao.save(quartashow);
//		
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("Status pendente", pendente);
//		map.put("Status andamento", andamento);
//		map.put("Status finalizado", finalizado);
//		map.put("Quarta Show", quartashow);
//
//		return new ResponseEntity<String>(new Gson().toJson(map), HttpStatus.OK);
//	}
	
}
