package com.quartashow.jchampionship.controller;

import java.net.URI;
import java.util.Calendar;
import java.util.List;

import javax.validation.Valid;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.quartashow.jchampionship.controller.common.ValidationResponse;
import com.quartashow.jchampionship.dao.ClassificacaoDao;
import com.quartashow.jchampionship.dao.ClassificacaoHistDao;
import com.quartashow.jchampionship.dao.EdicaoDao;
import com.quartashow.jchampionship.dao.FaseDao;
import com.quartashow.jchampionship.dao.GrupoDao;
import com.quartashow.jchampionship.dao.HarbitoDao;
import com.quartashow.jchampionship.dao.JogoDao;
import com.quartashow.jchampionship.dao.LocalDao;
import com.quartashow.jchampionship.dao.StatusDao;
import com.quartashow.jchampionship.dao.TimeDao;
import com.quartashow.jchampionship.dao.TipoEdicaoDao;
import com.quartashow.jchampionship.helper.ValidationResponseHelper;
import com.quartashow.jchampionship.model.Classificacao;
import com.quartashow.jchampionship.model.Edicao;
import com.quartashow.jchampionship.model.Fase;
import com.quartashow.jchampionship.model.Grupo;
import com.quartashow.jchampionship.model.Harbito;
import com.quartashow.jchampionship.model.Jogo;
import com.quartashow.jchampionship.model.Local;
import com.quartashow.jchampionship.model.Status;
import com.quartashow.jchampionship.model.Time;
import com.quartashow.jchampionship.model.TipoEdicao;

@Controller
@RequestMapping("/edicao")
public class EdicaoController { 
	
	@Autowired
	private EdicaoDao edicaoDao;
	
	@Autowired
	private GrupoDao grupoDao;

	@Autowired
	private JogoDao jogoDao;

	@Autowired
	private HarbitoDao harbitoDao;

	@Autowired
	private LocalDao localDao;

	@Autowired
	private TimeDao timeDao;

	@Autowired
	private ClassificacaoDao classificacaoDao;

	@Autowired
	private ClassificacaoHistDao classificacaoHistDao;

	@Autowired
	private TipoEdicaoDao tipoEdicaoDao;

	@Autowired
	private FaseDao faseDao;

	@Autowired
	private StatusDao statusDao;

	@Autowired
	private PodiumDao podiumDao;	
	
	@RequestMapping(value="/system", method=RequestMethod.GET)
	public ModelAndView pageEdicoesPendentes() {
		ModelAndView mv = new ModelAndView("_base"); 
		mv.addObject("edicoes", this.edicaoDao.getListByStatus(new Status(1l)));
		mv.addObject("content_import", "edicao-system-pendentes");
		return mv;
	}	
	
	@RequestMapping(value="/system/nova", method=RequestMethod.GET)
	public ModelAndView pageNovaEdicao(Edicao edicao) {
		ModelAndView mv = new ModelAndView("_base");
		mv.addObject("content_import", "edicao-system-form");
		mv.addObject("edicao", edicao);
		mv.addObject("tiposEdicao", this.tipoEdicaoDao.getList(TipoEdicao.class));
		System.out.println(edicao);
		return mv;
	}
	
	@RequestMapping(value="/system/nova", method=RequestMethod.POST, produces="application/json")
    public ResponseEntity<String> save(@Valid Edicao edicao, BindingResult result) {
	   
	   if(result.hasErrors() == true) {
		   ValidationResponse validationResponse = new ValidationResponseHelper().fieldsErrorsToValidationResponse(result);		
	       String json = new Gson().toJson(validationResponse);
		   return new ResponseEntity<String>(json, HttpStatus.UNAUTHORIZED);
	   }
	   edicao.setStatus(new Status(1, "Pendente"));
	   this.edicaoDao.save(edicao);
	   URI location = URI.create("/edicao/system/"+edicao.getId()+"/grupos");
	   HttpHeaders responseHeaders = new HttpHeaders();
	   responseHeaders.setLocation(location);
	   responseHeaders.set("id", String.valueOf(edicao.getId()));
	   ResponseEntity<String> responseEntity = new ResponseEntity<String>("OK", responseHeaders, HttpStatus.CREATED);
	   return responseEntity;
	 }
	

	@RequestMapping(value="/get/list/by/status/{idStatus}", method=RequestMethod.GET,produces="application/json")
	public @ResponseBody List<Edicao> getListByStatus(@PathVariable("idStatus") long idStatus) {
		return this.edicaoDao.getListByStatus(new Status(idStatus));
	}		
	
	@RequestMapping(value="/system/{idEdicao}/grupos", method=RequestMethod.GET)
	public ModelAndView pageAddGrupos(@PathVariable("idEdicao") long idEdicao) {
		ModelAndView mv = new ModelAndView("_base");
		mv.addObject("content_import", "edicao-system-grupos");
		Edicao edicao = this.edicaoDao.get(Edicao.class, idEdicao);
		mv.addObject("edicao", edicao);
		mv.addObject("grupos", grupoDao.getGruposByEdicao(edicao));
		return mv;
	}	
	
	@RequestMapping(value="/system/{idEdicao}/times", method=RequestMethod.GET)
	public ModelAndView pageAddTimes(@PathVariable("idEdicao") long idEdicao) {
		ModelAndView mv = new ModelAndView("_base");
		mv.addObject("content_import", "edicao-system-times");
		Edicao edicao = this.edicaoDao.get(Edicao.class, idEdicao);
		mv.addObject("edicao", edicao);
		mv.addObject("times", this.timeDao.getList(Time.class));
		List<Grupo> grupos = this.grupoDao.getGruposByEdicao(edicao);
		for (Grupo grupo : grupos) 
			grupo.setClassificacoes(this.classificacaoDao.getClassificacoesByGrupo(grupo));
		mv.addObject("grupos", grupos); 
		return mv;
	}		
	
	@RequestMapping(value="/system/{idEdicao}/jogos", method=RequestMethod.GET) 
	public ModelAndView pageAddJogos(@PathVariable("idEdicao") long idEdicao) {
		ModelAndView mv = new ModelAndView("_base");
		mv.addObject("content_import", "edicao-system-jogos");
		Edicao edicao = this.edicaoDao.get(Edicao.class, idEdicao);
		mv.addObject("edicao", edicao);
		mv.addObject("jogos", this.jogoDao.getJogosByEdicao(edicao));
		mv.addObject("grupos", this.grupoDao.getGruposByEdicao(edicao));
		mv.addObject("harbitos", this.harbitoDao.getList(Harbito.class));
		mv.addObject("locais", this.localDao.getList(Local.class));
		mv.addObject("times", this.timeDao.getTimesByEdicaoClassificacao(edicao));
		return mv;
	}
	
	@RequestMapping(value="/system/{idEdicao}/confirmacao", method=RequestMethod.GET)
	public ModelAndView pageConfirmacaoEdicao(@PathVariable("idEdicao") long idEdicao) {
		ModelAndView mv = new ModelAndView("_base");
		mv.addObject("content_import", "edicao-system-confirmacao");
		Edicao edicao = this.edicaoDao.get(Edicao.class, idEdicao);
		edicao.setGrupos(this.grupoDao.getGruposByEdicao(edicao));
		for (Grupo g : edicao.getGrupos()) {
			edicao.getGrupos().get(edicao.getGrupos().indexOf(g)).setClassificacoes(this.classificacaoDao.getClassificacoesByGrupo(g));
			edicao.getGrupos().get(edicao.getGrupos().indexOf(g)).setJogos(this.jogoDao.getJogosByGrupo(g));
		}
		mv.addObject("edicao", edicao); 
		return mv;
	}		

	@RequestMapping(value="/system/{edicaoId}/set/status/{statusId}", method=RequestMethod.POST, produces="application/json")
    public ResponseEntity<String> setStatus(@PathVariable("edicaoId") long edicaoId, @PathVariable("statusId") long statusId) {
		try {
			Edicao edicao = this.edicaoDao.get(Edicao.class, edicaoId);
			edicao.setStatus(new Status(statusId)); 
			this.edicaoDao.update(edicao);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(URI.create("edicao/"+edicaoId));
			String json = new ObjectMapper().writeValueAsString(edicao);
			return new ResponseEntity<String>(json, headers , HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	 }	
	
	@RequestMapping(value="/{id}")
	public ModelAndView pagePublicEdicao(@PathVariable("id") long id) {
		ModelAndView mv = new ModelAndView("_base2");
		mv.addObject("content_import", "edicao-page");
		Edicao edicao = this.edicaoDao.get(Edicao.class, id);
		edicao.setGrupos(this.grupoDao.getGruposByEdicao(edicao));
		for (Grupo g : edicao.getGrupos()) {
			edicao.getGrupos().get(edicao.getGrupos().indexOf(g)).setClassificacoes(this.classificacaoDao.getClassificacoesByGrupo(g));
			edicao.getGrupos().get(edicao.getGrupos().indexOf(g)).setJogos(this.jogoDao.getJogosByGrupo(g));
		}		
		mv.addObject("podium", this.podiumDao.get(edicao)); 
		mv.addObject("edicao", edicao);
		return mv;
	}
 
	@RequestMapping(value="/delete/{id}", method=RequestMethod.POST, produces="application/json")
	public ResponseEntity<String> delete(@PathVariable("id") long id) {
		try {
			// TODO Verificar se há Grupos Jogos e times vinculados com a edicao...
			this.edicaoDao.delete(Edicao.class, id);
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value="/{id}/estatisticas")
	public ModelAndView pageEstatisticas(@PathVariable("id") long id) {
		ModelAndView mv = new ModelAndView("_base2");
		mv.addObject("content_import", "edicao-estatisticas");
		Edicao edicao = this.edicaoDao.get(Edicao.class, id);
		//List<Grupo> grupos = this.grupoDao.getGruposByEdicao(edicao);
		//for (Grupo grupo : grupos) {
		//	grupo.setClassificacoes(this.classificacaoHistDao.getClassificacoesHistByGrupo(grupo));
		//}
		//edicao.setGrupos(grupos);
		mv.addObject("edicao", edicao); 
		return mv;
	}	
	
	@RequestMapping(value="/system/{id}/finalizarPrimeiraFase", method=RequestMethod.POST, produces="application/json")
	public ResponseEntity<String> finalizar1Fase(@PathVariable("id") long id) {
		try {
			Edicao edicao = this.edicaoDao.get(Edicao.class, id);
			List<Grupo> grupos = this.grupoDao.getGruposByEdicao(edicao);
			for (Grupo grupo : grupos) {
				// verifica se todos os grupos são da 1a fase.
				if(grupo.getFase().getSigla() != '1')
					throw new RuntimeException("Existem grupos que nao sao da Primeira fase(pode ja ter sido finalizados), nao ha possibilidades de finalizar.");
				List<Jogo> jogos = this.jogoDao.getJogosByGrupo(grupo);
				// verifica se exite pelo menos 1 jogo por grupo.
				if(jogos.size() <= 0) 
					throw new RuntimeException("Grupo " + grupo.getDescricao() + " não contem jogos.");
				for (Jogo jogo : jogos) { 
					// verifica se todos os jogos estão finalizados.
					if(jogo.getStatus().getId() != 3)
						throw new RuntimeException("Existem jogos não encerrados, encerrem todos os jogos para finalizar a Fase!");
					// verifica se todos os times tem a mesma qtde de jogos.
				}
				List<Classificacao> classificacoes = this.classificacaoDao.getClassificacoesByGrupo(grupo);
				int qtdeJ = classificacoes.get(0).getJogos();
				for (Classificacao classificacao : classificacoes) {
					 if(qtdeJ != classificacao.getJogos()) 
						 throw new RuntimeException("Todos os Times devem estar com a mesma quantidade de Jogos para finalizar a fase!");
				}
			}
			if(grupos.size() == 1) {
				List<Classificacao> classificacoes = this.classificacaoDao.getClassificacoesByGrupo(grupos.get(0));
				if(classificacoes.size() <= 3) {
					// Cria somente a Final
					criarFinal1Fase1GrupoMenosQ3Times(grupos.get(0));
				} else if(classificacoes.size() > 3) {
					// cria 3lugar e final
					criar3LugarFinal1Fase1GrupoMenosQ3Times(grupos.get(0));
				}
			} else if (grupos.size() == 2) {
				// cria semi
			} else if (grupos.size() == 4) {
				// cria quartas
			} else if (grupos.size() == 8) {
				// cria oitavas
			}
			return new ResponseEntity<String>(HttpStatus.CREATED);
		} catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	private boolean criarFinal1Fase1GrupoMenosQ3Times(Grupo grupoUnicoPrimeiraFase) {
		// cria 2 fase com final e 3 lugar
		Fase _2fase = this.faseDao.get('2');
		Grupo grupo = new Grupo();
		grupo.setEdicao(grupoUnicoPrimeiraFase.getEdicao());
		grupo.setFase(_2fase);
		grupo.setDescricao("Segunda Fase (Mata-mata)");
		grupo.setStatus(this.statusDao.get(Status.class, 2l));
		grupoDao.save(grupo);
		List<Classificacao> classificacoes = this.classificacaoDao.getClassificacoesByGrupo(grupoUnicoPrimeiraFase);
		List<Jogo> jogos = this.jogoDao.getJogosByGrupo(grupoUnicoPrimeiraFase);
		// Cria a Final, rodada -1
		criaJogoMataMata(grupo, 
						 jogos.get(0).getHarbito(), 
						 jogos.get(0).getLocal(), 
						 classificacoes,
						 -1,
						 1,
						 2);
		return true;
	}
	
	private boolean criar3LugarFinal1Fase1GrupoMenosQ3Times(Grupo grupoUnicoPrimeiraFase) {
		// cria 2 fase com final e 3 lugar
		Fase _2fase = this.faseDao.get('2');
		Grupo grupo = new Grupo();
		grupo.setEdicao(grupoUnicoPrimeiraFase.getEdicao());
		grupo.setFase(_2fase);
		//grupo.setDescricao("Segunda Fase (Mata-mata)");
		grupo.setStatus(this.statusDao.get(Status.class, 2l));
		grupoDao.save(grupo);
		List<Classificacao> classificacoes = this.classificacaoDao.getClassificacoesByGrupo(grupoUnicoPrimeiraFase);
		List<Jogo> jogos = this.jogoDao.getJogosByGrupo(grupoUnicoPrimeiraFase);
		// Cria a 3 Lugar, rodada -3
		criaJogoMataMata(grupo, 
					     jogos.get(0).getHarbito(), 
					     jogos.get(0).getLocal(), 
					     classificacoes,
					     -3,
					     3,
					     4);		
		// Cria a Final, rodada -1
		criaJogoMataMata(grupo, 
						 jogos.get(0).getHarbito(), 
						 jogos.get(0).getLocal(), 
						 classificacoes,
						 -1,
						 1,
						 2);
		grupoUnicoPrimeiraFase.setStatus(new Status(3l));
		this.grupoDao.update(grupoUnicoPrimeiraFase);
		return true;
	}

	private void criaJogoMataMata(Grupo grupo, Harbito harbito, Local local, List<Classificacao> classificacoes, int rodada, int colocacaoTimeA, int colocacaoTimeB) {
		Jogo jogo = new Jogo();
		jogo.setDataHora(Calendar.getInstance().getTime());
		jogo.setGrupo(grupo);
		jogo.setHarbito(harbito);
		jogo.setLocal(local);
		jogo.setRodada(rodada);
		jogo.setSequencia((int)this.jogoDao.getCountJogosByGrupo(grupo)+1);
		jogo.setStatus(new Status(1l));
		for (Classificacao classificacao : classificacoes) {
			boolean timeAOK = false;
			if(classificacao.getColocacao() == colocacaoTimeA && timeAOK == false) {
				jogo.setTimeA(classificacao.getTime());
				timeAOK = true;
			} else if(classificacao.getColocacao() == colocacaoTimeB || classificacao.getColocacao() == colocacaoTimeA) {
				jogo.setTimeB(classificacao.getTime());
			}
		}
		this.jogoDao.save(jogo);
	}
	
	@RequestMapping(value="/{id}/Voltar/primeira/fase", method=RequestMethod.POST, produces="application/json")
	public ResponseEntity<String> voltarParaPrimeiraFase(@PathVariable("id") long id) {
		try {
			Edicao edicao = this.edicaoDao.get(Edicao.class, id);
			List<Grupo> gruposFase2 = this.grupoDao.getGruposSegundaFaseByEdicao(edicao);
			for (Grupo grupo : gruposFase2) {
				List<Jogo> jogos = this.jogoDao.getJogosByGrupo(grupo);
				for (Jogo jogo : jogos) {
					if(jogo.getStatus().getId() > 1) { // > 1, em andamento ou finalizado
						throw new RuntimeException("2ª Fase em andamento impossivel voltar para 1ª Fase!");
					}
				}
			}

			// Exclui Jogos da 2ª Fase
			for (Grupo grupo : gruposFase2) {
				List<Jogo> jogos = this.jogoDao.getJogosByGrupo(grupo); 
				for (Jogo jogo : jogos) {
					if(jogo.getStatus().getId() == 1) { // > 1, em andamento ou finalizado
						this.jogoDao.delete(Jogo.class, jogo.getId());
					}
				}
				this.grupoDao.delete(Grupo.class, grupo.getId());
			}		
			
			List<Grupo> grupos = this.grupoDao.getGruposByEdicao(edicao);
			for (Grupo grupo : grupos) {
				grupo.setStatus(new Status(2l));
				this.grupoDao.update(grupo);
			}			
			//HttpHeaders headers = new HttpHeaders();
			//headers.setLocation(URI.create("/jchampionship/"));
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
 	
}
