package com.quartashow.jchampionship.controller;

import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
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
import org.springframework.web.servlet.ModelAndView;

import com.quartashow.jchampionship.controller.common.ValidationResponse;
import com.quartashow.jchampionship.dao.CampeonatoDao;
import com.quartashow.jchampionship.dao.UsuarioDao;
import com.quartashow.jchampionship.helper.ValidationResponseHelper;
import com.quartashow.jchampionship.model.Campeonato;
import com.quartashow.jchampionship.model.Usuario;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioDao usuarioDao;
	@Autowired
	private CampeonatoDao campeonatoDao;

	@RequestMapping(value="/login", method=RequestMethod.GET)
	public ModelAndView pageLogin() {
		ModelAndView mv = new ModelAndView("_login");
		return mv;
	} 
	
	@RequestMapping(value="/login", method=RequestMethod.POST, produces="application/json")
	public ResponseEntity<String> login(@Valid Usuario usuario, BindingResult result, HttpSession session) {
		try {
			if(result.hasFieldErrors("email")) {
				ValidationResponse validationResponse = new ValidationResponseHelper().fieldsErrorsToValidationResponse(result.getFieldError("email"));
				return new ResponseEntity<String>(new ObjectMapper().writeValueAsString(validationResponse), HttpStatus.UNAUTHORIZED);
			}
			if(this.usuarioDao.login(usuario) == true) {
				usuario = usuarioDao.get(usuario.getEmail());
				for (Campeonato c : usuario.getCampeonatos()) {
					if(c.getId() == 1l)
						session.setAttribute("admin", true);
				}
				session.setAttribute("usuario", usuario);
				HttpHeaders headers = new HttpHeaders(); 
				headers.setLocation(URI.create("/"));
				return new ResponseEntity<String>(headers , HttpStatus.OK);
			} else {
				ValidationResponse validationResponse = new ValidationResponse();
				validationResponse.setStatus("ERROR");
				Map<String, String> errorMessages = new HashMap<String, String>();
				errorMessages.put("login", "Email ou senha invalido...");
				validationResponse.setErrorMessages(errorMessages );
				return new ResponseEntity<String>(new ObjectMapper().writeValueAsString(validationResponse), HttpStatus.UNAUTHORIZED);
			}
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value="/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}	
	
	@RequestMapping(value="/system/perfil/{id}", method=RequestMethod.GET)
	public ModelAndView pageMeuPerfil(@PathVariable("id") long id, HttpSession session, String cadastre) {
		ModelAndView mv = new ModelAndView("_base2");
		mv.addObject("content_import", "usuario-edit-perfil");
		Usuario usuarioSession = (Usuario) session.getAttribute("usuario");
		if("meu".equals(cadastre) == false) {
			if (id > 0l)  {
				if (usuarioSession.isSuperUsuario()) {
					mv.addObject("usuarioCadastro", this.usuarioDao.get(Usuario.class, id));
				}
			} else {
				mv.addObject("usuarioCadastro", usuarioSession);
			}
		}
		else {
			mv.addObject("usuarioCadastro", (Usuario) session.getAttribute("usuario"));
		}
		return mv;
	}
	
}
