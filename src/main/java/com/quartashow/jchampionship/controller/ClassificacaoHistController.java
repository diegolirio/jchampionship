package com.quartashow.jchampionship.controller;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.quartashow.jchampionship.dao.ClassificacaoHistDao;
import com.quartashow.jchampionship.dao.GrupoDao;
import com.quartashow.jchampionship.model.Classificacao;
import com.quartashow.jchampionship.model.Grupo;

@Controller
@RequestMapping("/classificacaoHist")
public class ClassificacaoHistController {

	@Autowired
	private GrupoDao grupoDao;
	
	@Autowired
	private ClassificacaoHistDao classificacaoHistDao;

	@RequestMapping(value="/get/list/by/grupo/{grupoId}/json", method=RequestMethod.GET, produces="application/json")
	public ResponseEntity<String> getListByGrupo(@PathVariable("grupoId") long grupoId) {
		try {
			Grupo grupo = this.grupoDao.get(Grupo.class, grupoId); 
			List<Classificacao> classificacoesHistByGrupo = this.classificacaoHistDao.getClassificacoesHistByGrupo(grupo);
			return new ResponseEntity<String>(new ObjectMapper().writeValueAsString(classificacoesHistByGrupo), HttpStatus.OK);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
