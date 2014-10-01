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
import com.quartashow.jchampionship.model.Status;

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
		mv.addObject("content_import", "campeonato-classificacao");
		return mv;
	}
	
	@RequestMapping(value="/campeonato/get/list", produces="application/json")
	public @ResponseBody List<Campeonato> getList() {
		return this.campeonatoDao.getList(Campeonato.class);
	}
//	
//	@RequestMapping(value="/save")
//	public @ResponseBody Campeonato save() {
//		Campeonato c = new Campeonato();
//		c.setDescricao("Quarta-Show");
//		this.campeonatoDao.save(c);
//		return c;
//	}
	
	@RequestMapping(value="/initValues")
	public void initValues() {
		Status pendente = new Status("Pendente");
		Status andamento = new Status("Em Andamento");
		Status finalizado = new Status("Fianlizado");
		this.statusDao.save(pendente);
		this.statusDao.save(andamento);
		this.statusDao.save(finalizado);
	}
	
}
