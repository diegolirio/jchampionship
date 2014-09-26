package com.diegolirio.jchampionship;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/edicao")
public class EdicaoController {

	@RequestMapping(value="/system/nova")
	public ModelAndView pageSystemNova() {
		ModelAndView mv = new ModelAndView("_base");
		mv.addObject("content_import", "edicao-system-form");
		return mv;
	}
	
}
