package com.quartashow.jchampionship.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.quartashow.jchampionship.dao.EdicaoDao;
import com.quartashow.jchampionship.dao.JogadorInfoEdicaoDao;
import com.quartashow.jchampionship.model.Edicao;
import com.quartashow.jchampionship.model.JogadorInfoEdicao;

@Controller
@RequestMapping("/jogadorInfoEdicao")
public class JogadorInfoEdicaoController {

	@Autowired 
	private EdicaoDao edicaoDao;
	
	@Autowired
	private JogadorInfoEdicaoDao jogadorInfoEdicaoDao;

	@RequestMapping(value="/artilharia/by/edicao/{edicaoId}", method=RequestMethod.GET)
	public ModelAndView pageArtilharia(@PathVariable("edicaoId") long id) {
		ModelAndView mv = new ModelAndView("_base2");
		mv.addObject("content_import", "jogadorInfoEdicao-page");
		Edicao edicao = this.edicaoDao.get(Edicao.class, id);
		edicao.setJogadoresInfoEdicao(this.jogadorInfoEdicaoDao.getList(JogadorInfoEdicao.class));
		mv.addObject("edicao", edicao);
		return mv;
	}
	
}
