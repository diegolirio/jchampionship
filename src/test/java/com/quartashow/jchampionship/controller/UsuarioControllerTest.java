package com.quartashow.jchampionship.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.quartashow.jchampionship.dao.UsuarioDao;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath*:spring-context.xml" })
public class UsuarioControllerTest {
	
	@InjectMocks
	private UsuarioController controller;
	
	private MockMvc mockMvc;
	
	@Mock
	private UsuarioDao usuarioDao;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}
	
	@Test
	public void testDeveRetornarAPaginaDeLoginComViewEModels() throws Exception {
		mockMvc.perform(get("/usuario/login"))
			.andExpect(status().isOk())
			.andExpect(view().name("_login"));
	}
	
	@Test
	public void testDeveFazerLoginDoUsuarioViaAjaxRetornandoJSON() throws Exception {
		
		// TODO: 
		
//		Usuario usuario = new Usuario("diegolirio.dl@gmail.com", "198586");
//		boolean isLogged = true;
//		Mockito.when(this.usuarioDao.login(usuario)).thenReturn(isLogged);
//		
//		mockMvc.perform(MockMvcRequestBuilders.post("/usuario/login")
//				.param("email", usuario.getEmail())
//				.param("senha", usuario.getSenha()))
//			.andExpect(status().isOk());
//		
//		Mockito.verify(usuarioDao.login(usuario));
	}

}
