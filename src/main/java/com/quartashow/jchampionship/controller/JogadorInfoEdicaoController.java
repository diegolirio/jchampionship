package com.quartashow.jchampionship.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.quartashow.jchampionship.dao.EdicaoDao;
import com.quartashow.jchampionship.model.Edicao;

@Controller
@RequestMapping("/jogadorInfoEdicao")
public class JogadorInfoEdicaoController {

	@Autowired 
	private EdicaoDao edicaoDao;

	@RequestMapping(value="/artilharia/by/edicao/{edicaoId}", method=RequestMethod.GET)
	public ModelAndView pageArtilharia(@PathVariable("edicaoId") long id) {
		ModelAndView mv = new ModelAndView("_base2");
		Edicao edicao = this.edicaoDao.get(Edicao.class, id);
		return mv;
	}
	
}
