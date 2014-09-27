package com.quartashow.jchampionship.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.quartashow.jchampionship.dao.CampeonatoDao;
import com.quartashow.jchampionship.model.Campeonato;

/**
 * Handles requests for the application home page.
 */
@Controller
public class CampeonatoController {
	
	private static final Logger logger = LoggerFactory.getLogger(CampeonatoController.class);
	
	@Autowired
	private CampeonatoDao campeonatoDao;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
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
