package com.elliemae.security;

import com.elliemae.model.JwtAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The type Jwt authentication token filter.
 */
public class JwtAuthenticationTokenFilter extends AbstractAuthenticationProcessingFilter {

  /**
   * Instantiates a new Jwt authentication token filter.
   */
public JwtAuthenticationTokenFilter() {
    super("/rest/**");
  }

  @Override
  public Authentication attemptAuthentication(
      HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
      throws AuthenticationException, IOException, ServletException {

    String header = httpServletRequest.getHeader("Authorization");

    if (header == null || !header.startsWith("Bearer ")) {
      throw new RuntimeException("JWT Token is missing");
    }

    String authenticationToken = header.substring(6);

    JwtAuthenticationToken token = new JwtAuthenticationToken(authenticationToken);
    return getAuthenticationManager().authenticate(token);
  }

  @Override
  protected void successfulAuthentication(
      HttpServletRequest request,
      HttpServletResponse response,
      FilterChain chain,
      Authentication authResult)
      throws IOException, ServletException {
    super.successfulAuthentication(request, response, chain, authResult);
    chain.doFilter(request, response);
  }
}
