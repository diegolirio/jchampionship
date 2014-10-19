package com.quartashow.jchampionship.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.quartashow.jchampionship.dao.CampeonatoDao;
import com.quartashow.jchampionship.dao.EdicaoDao;
import com.quartashow.jchampionship.dao.EventoDao;
import com.quartashow.jchampionship.dao.PosicaoDao;
import com.quartashow.jchampionship.dao.StatusDao;
import com.quartashow.jchampionship.model.Campeonato;
import com.quartashow.jchampionship.model.Edicao;
import com.quartashow.jchampionship.model.Evento;
import com.quartashow.jchampionship.model.Posicao;
import com.quartashow.jchampionship.model.Status;

@Controller
public class CampeonatoController {
	
	private static final Logger logger = LoggerFactory.getLogger(CampeonatoController.class);
	
	@Autowired
	private CampeonatoDao campeonatoDao;
	
	@Autowired
	private EdicaoDao edicaoDao;	
	
	@Autowired
	private StatusDao statusDao;

	@Autowired
	private EventoDao eventoDao;

	@Autowired
	private PosicaoDao posicaoDao;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home() {
		logger.info("");
		ModelAndView mv = new ModelAndView("_base2");
		mv.addObject("content_import", "campeonato-classificacao");
		List<Edicao> listByStatus = this.edicaoDao.getListByStatus(new Status(2));
		mv.addObject("edicoes", listByStatus);
		return mv;
	}
	
	@RequestMapping(value="/campeonato/get/list", produces="application/json")
	public @ResponseBody List<Campeonato> getList() {
		return this.campeonatoDao.getList(Campeonato.class);
	}
	
	@RequestMapping(value="/pre_cadastro", method=RequestMethod.GET)
	public ResponseEntity<String> initValues() {
		Status pendente = new Status("Pendente");
		Status andamento = new Status("Em Andamento");
		Status finalizado = new Status("Fianlizado");
		this.statusDao.save(pendente);
		this.statusDao.save(andamento);
		this.statusDao.save(finalizado);

		Campeonato quartashow = new Campeonato();
		quartashow.setDescricao("Quarta Show");
		this.campeonatoDao.save(quartashow);
		
		Posicao gk = new Posicao();
		gk.setDescricao("Goleiro");
		gk.setSigla("GK");
		this.posicaoDao.save(gk);
		
		Posicao at = new Posicao();
		at.setDescricao("Linha");
		at.setSigla("AT");
		this.posicaoDao.save(at);	
		
		Evento gol = new Evento();
		gol.setDescricao("Gol");
		this.eventoDao.save(gol);

		Evento ca = new Evento();
		ca.setDescricao("Cartão Amarelo");
		this.eventoDao.save(ca);

		Evento cv = new Evento();
		cv.setDescricao("Cartão Vermelho");
		this.eventoDao.save(cv);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("Status pendente", pendente);
		map.put("Status andamento", andamento);
		map.put("Status finalizado", finalizado);
		map.put("Quarta Show", quartashow);
		
		

		return new ResponseEntity<String>("OK", HttpStatus.OK);
	}
	
}
