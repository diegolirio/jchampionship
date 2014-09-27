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
import com.quartashow.jchampionship.model.Campeonato;

@Controller
public class CampeonatoController {
	
	private static final Logger logger = LoggerFactory.getLogger(CampeonatoController.class);
	
	@Autowired
	private CampeonatoDao campeonatoDao;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home() {
		logger.info("");
		ModelAndView mv = new ModelAndView("_base");
		mv.addObject("content_import", "campeonato-classificacao");
		return mv;
	}
	
	@RequestMapping(value="/get")
	public @ResponseBody List<Campeonato> getList() {
		return this.campeonatoDao.getList(Campeonato.class);
	}
	
	@RequestMapping(value="/save")
	public @ResponseBody Campeonato save() {
		Campeonato c = new Campeonato();
		c.setDescricao("Quarta-Show");
		this.campeonatoDao.save(c);
		return c;
	}
	
}
