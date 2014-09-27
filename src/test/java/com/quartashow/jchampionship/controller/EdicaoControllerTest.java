package com.quartashow.jchampionship.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath*:spring-context.xml" })
public class EdicaoControllerTest {

	@InjectMocks
	private EdicaoController controller;

	private MockMvc mockMvc;

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
	public void testIndex() throws Exception {
		mockMvc.perform(get("/edicao/system/nova"))
			.andExpect(status().is(200))
			.andExpect(view().name("_base"))
			.andExpect(model().attributeExists("content_import"))
			.andExpect(model().attribute("content_import", "edicao-system-form"));
	}	

}
