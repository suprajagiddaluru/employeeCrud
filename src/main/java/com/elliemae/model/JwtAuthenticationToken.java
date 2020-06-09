package com.elliemae.model;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

/** @author supraja_giddaluru */
public class JwtAuthenticationToken extends UsernamePasswordAuthenticationToken {

  private String token;

  /**
   * Instantiates a new Jwt authentication token.
   *
   * @param token the token
   */
public JwtAuthenticationToken(String token) {
    super(null, null);
    this.token = token;
  }

  /**
   * Gets token.
   *
   * @return the token
   */
public String getToken() {
    return token;
  }

  /**
   * Sets token.
   *
   * @param token the token
   */
public void setToken(String token) {
    this.token = token;
  }

  @Override
  public Object getCredentials() {
    return null;
  }

  @Override
  public Object getPrincipal() {
    return null;
  }
}
