package com.autentication.authenticationservice;

import com.autentication.authenticationservice.security.AuthenticationServiceApplication;
import com.autentication.authenticationservice.security.config.JwtConfig;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collection;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AuthenticationServiceApplication.class)
@AutoConfigureMockMvc

public class AuthenticationServiceApplicationTests {
	@Autowired
	private MockMvc mvc;
/*
	@Autowired
	JwtConfig jwtConfig;
	@Test
	public void shouldNotAllowAccessToUnauthenticatedUsers() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/test")).andExpect(status().isForbidden());
	}

	@Test
	public void shouldGenerateAuthToken() throws Exception {
		String token = jwtConfig.generateToken("john", (Collection<? extends GrantedAuthority>) new HashMap<Object, Object>());

		assertNotNull(token);
		mvc.perform(MockMvcRequestBuilders.get("/test").header("Authorization", token)).andExpect(status().isOk());
	}
*/
}
