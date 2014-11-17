package com.quartashow.jchampionship.controller;

import java.util.ArrayList;
import java.util.List;

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
import com.quartashow.jchampionship.dao.FaseDao;
import com.quartashow.jchampionship.dao.PosicaoDao;
import com.quartashow.jchampionship.dao.StatusDao;
import com.quartashow.jchampionship.dao.TipoEdicaoDao;
import com.quartashow.jchampionship.dao.UsuarioDao;
import com.quartashow.jchampionship.model.Campeonato;
import com.quartashow.jchampionship.model.Edicao;
import com.quartashow.jchampionship.model.Evento;
import com.quartashow.jchampionship.model.Fase;
import com.quartashow.jchampionship.model.Posicao;
import com.quartashow.jchampionship.model.Status;
import com.quartashow.jchampionship.model.TipoEdicao;
import com.quartashow.jchampionship.model.Usuario;

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

	@Autowired
	private UsuarioDao usuarioDao;

	@Autowired
	private TipoEdicaoDao tipoEdicaoDao;

	@Autowired
	private FaseDao faseDao;

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

		// usuario
		Usuario diegolirio = new Usuario();
		diegolirio.setEmail("diegolirio.dl@gmail.com");
		diegolirio.setNome("Diego Lirio");
		diegolirio.setSenha("198586");
		diegolirio.setUriImage("/jchampionship/static/quartashow/img/usuario/profile-user_fake.png");
		diegolirio.setSuperUsuario(true);
		this.usuarioDao.save(diegolirio);
		
		Usuario test = new Usuario();
		test.setEmail("test@test.com");
		test.setNome("Fulano");
		test.setSenha("123456");
		test.setUriImage("/jchampionship/static/quartashow/img/usuario/profile-user_fake.png");
		this.usuarioDao.save(test);		
		
		// campeonato
		Campeonato quartashow = new Campeonato();
		quartashow.setDescricao("Quarta Show");
		List<Usuario> usuarios = new ArrayList<Usuario>();
		usuarios.add(diegolirio);
		quartashow.setUsuarios(usuarios);
		this.campeonatoDao.save(quartashow);
		
		// Status
		this.statusDao.save(new Status("Pendente", "interrogacao.png"));
		this.statusDao.save(new Status("Em Andamento", "bola_32.png"));
		this.statusDao.save(new Status("Finalizado", "apito_24.png"));
		 
		// posicao
		Posicao gk = new Posicao();
		gk.setDescricao("Goleiro");
		gk.setSigla("GK");
		gk.setImgName("golerio_24x24.png");
		this.posicaoDao.save(gk);
		
		Posicao at = new Posicao();
		at.setDescricao("Linha");
		at.setSigla("AT");
		at.setImgName("chuteira_32.png");
		this.posicaoDao.save(at);	
		
		// eventos do jogo
		Evento gol = new Evento();
		gol.setDescricao("Gol");
		gol.setImgName("gol.png");
		this.eventoDao.save(gol);

		Evento ca = new Evento();
		ca.setDescricao("Cartão Amarelo");
		ca.setImgName("cartao-amarelo.png");
		this.eventoDao.save(ca);

		Evento cv = new Evento();
		cv.setDescricao("Cartão Vermelho");
		cv.setImgName("cartao-vermelho.png");
		this.eventoDao.save(cv);

		// Tipo da Edicao...
		TipoEdicao _grupoMataMata = new TipoEdicao(); // id=1
		_grupoMataMata.setDescricao("1ª fase (fase de Grupo) e Mata-mata");
		this.tipoEdicaoDao.save(_grupoMataMata);
		
		TipoEdicao pontosCorridos = new TipoEdicao(); // id=2
		pontosCorridos.setDescricao("Pontos Corridos");
		this.tipoEdicaoDao.save(pontosCorridos);

		TipoEdicao mataMata = new TipoEdicao(); // id=3
		mataMata.setDescricao("Mata-Mata");
		this.tipoEdicaoDao.save(mataMata);		
		
		// Fases
		Fase _1fase = new Fase();
		_1fase.setDescricao("1ª fase (fase de grupos)");
		_1fase.setSigla('G');
		this.faseDao.save(_1fase);

		Fase _final = new Fase();
		_final.setDescricao("Final");
		_final.setSigla('F');
		this.faseDao.save(_final);
		
		Fase _3Lugar = new Fase();
		_3Lugar.setDescricao("3º Lugar");
		_3Lugar.setSigla('3');
		this.faseDao.save(_3Lugar);
		
		Fase semiFinal = new Fase();
		semiFinal.setDescricao("Semi-Final");
		semiFinal.setSigla('S');
		this.faseDao.save(semiFinal);
		
		Fase quartas = new Fase();
		quartas.setDescricao("Quarta-de-Final");
		quartas.setSigla('Q');		
		this.faseDao.save(quartas);

		Fase oitavas = new Fase();
		oitavas.setDescricao("Oitavas-de-Final");
		oitavas.setSigla('O');
		this.faseDao.save(oitavas);
		
		//Fase pontosCorridosFase = new Fase();
		//pontosCorridosFase.setDescricao("Pontos Corridos");
		//pontosCorridosFase.setSigla('P');				
		
		return new ResponseEntity<String>("<a href='/jchampionship'>OK</a>", HttpStatus.OK);
	}
	
}
