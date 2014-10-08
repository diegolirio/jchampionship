package com.quartashow.jchampionship.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("harbito")
public class HarbitoController {

	@RequestMapping(value="/page/simple", method=RequestMethod.GET)
	public ModelAndView pageSimple() {
		ModelAndView mv = new ModelAndView("_base_simple");
		mv.addObject("content_import", "harbito-system-form");
		return mv;
	}
	
}
