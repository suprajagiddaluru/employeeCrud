package com.elliemae.controller;

import com.elliemae.model.JwtUser;
import com.elliemae.security.JwtGenerator;
import io.swagger.annotations.Api;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * *
 *
 * @author supraja_giddaluru
 */
@RestController
@RequestMapping(path ="/token",produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "Token Generation ", description = "Authentication for rest end points with Bearer")
public class TokenController {
  private JwtGenerator jwtGenerator;

  /**
   * Instantiates a new Token controller.
   *
   * @param jwtGenerator the jwt generator
   */
public TokenController(JwtGenerator jwtGenerator) {
    this.jwtGenerator = jwtGenerator;
  }

  /**
   * Generate string.
   *
   * @param jwtUser the jwt user
   * @return the string
   */
@PostMapping
  public String generate(@RequestBody final JwtUser jwtUser) {

    return jwtGenerator.generate(jwtUser);
  }
}
