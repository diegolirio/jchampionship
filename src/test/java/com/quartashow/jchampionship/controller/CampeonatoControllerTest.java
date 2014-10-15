package com.quartashow.jchampionship.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
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

import com.quartashow.jchampionship.dao.CampeonatoDao;
import com.quartashow.jchampionship.dao.EdicaoDao;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath*:spring-context.xml" })
public class CampeonatoControllerTest {

	@InjectMocks
	private CampeonatoController controller;

	private MockMvc mockMvc;

	@Mock
	private CampeonatoDao campeonatoDao;
	
	@Mock
	private EdicaoDao edicaoDao;	
	
	@Before
	public void setUp() throws Exception {
		// MainFilter filter = mock(MainFilter.class);
		// HttpServletRequest request = mock(HttpServletRequest.class);
		// HttpServletResponse response = mock(HttpServletResponse.class);
		// FilterChain chain = mock(FilterChain.class);
		//
		//
		// Connection connection = new
		// TdvConnectionFactory().getConnection("192.168.9.101", "1526", "tdp",
		// "tdvadm", "aged12");
		// System.out.println( connection );
		// request.getSession().setAttribute("connection", connection);
		// request.setAttribute("usuario", "fgoes");
		// request.setAttribute("rota", "021");
		// filter.doFilter(request, response, chain);
		//
		// MockitoAnnotations.initMocks(this);
		// StandaloneMockMvcBuilder standaloneSetup =
		// MockMvcBuilders.standaloneSetup(controller);
		// standaloneSetup.addFilter(filter, "*");
		// mockMvc = standaloneSetup.build();

		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
		// controller.setConnection( connection );
	}

	@Test
	public void verificaInformacoesParaPaginaRoot() throws Exception {
		mockMvc.perform(get("/"))
				.andExpect(status().is(200))
				.andExpect(view().name("_base2"))
				.andExpect(model().attributeExists("content_import", "edicoes"))
				.andExpect(model().attribute("content_import", "campeonato-classificacao"));
	}

	@Test
	public void buscaListaDeCampeonatoRestFullContentTypeDeveSerJson() throws Exception {
		mockMvc.perform(get("/campeonato/get/list"))
			.andExpect(status().isOk())
			.andExpect(content().contentType("application/json"));
	}

}
