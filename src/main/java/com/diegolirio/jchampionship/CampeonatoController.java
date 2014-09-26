package com.diegolirio.jchampionship;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class CampeonatoController {
	
	private static final Logger logger = LoggerFactory.getLogger(CampeonatoController.class);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home() {
		logger.info("URL Root");
		ModelAndView mv = new ModelAndView("_base");
		mv.addObject("content_import", "campeonato-classificacao");
		return mv;
	}
	
}
