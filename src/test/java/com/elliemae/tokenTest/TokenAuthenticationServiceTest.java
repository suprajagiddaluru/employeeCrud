package com.elliemae.tokenTest;

import com.elliemae.controller.TokenController;
import com.elliemae.model.JwtUser;
import com.elliemae.security.JwtGenerator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.assertNotNull;

/**
 * *
 *
 * @author supraja_giddaluru
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TokenAuthenticationServiceTest {

  @Autowired private MockMvc mvc;
  @Autowired private TokenController tokenController;
  @Autowired private JwtGenerator jwtGenerator;

  @Test
  public void shouldGenerateAuthToken() throws Exception {
    JwtUser jwt = JwtUser.builder().userName("supraja").id(1).role("admin").build();
    String token = jwtGenerator.generate(jwt);

    assertNotNull(token);
    ////mvc.perform(MockMvcRequestBuilders.get("/token").header("Authorization", token))
        //.andExpect(status().isOk());
  }
}
