package com.elliemae.security;

import com.elliemae.model.JwtUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

/** @author supraja_giddaluru */
@Component
public class JwtValidator {

  private static final String secret = "supraja";

  /**
   * Validate jwt user.
   *
   * @param token the token
   * @return the jwt user
   */
public JwtUser validate(String token) {

    JwtUser jwtUser = null;
    try {
      Claims body = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();

      jwtUser = new JwtUser();

      jwtUser.setUserName(body.getSubject());
      jwtUser.setId(Long.parseLong((String) body.get("userId")));
      jwtUser.setRole((String) body.get("role"));
    } catch (Exception e) {
      System.out.println(e);
    }

    return jwtUser;
  }
}
