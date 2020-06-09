package com.elliemae.security;

import com.elliemae.model.JwtUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * The type Jwt generator.
 */
@Component
public class JwtGenerator {

  /**
   * Generate string.
   *
   * @param jwtUser the jwt user
   * @return the string
   */
public String generate(JwtUser jwtUser) {

    // extend the expration time.
    Date date1 = new Date();
    long t1 = date1.getTime();
    Date expirationTime1 = new Date(t1 + 5000l); // prolongation 5 seconds

    Claims claims =
        Jwts.claims()
            // .setExpiration(expirationTime1)
            .setSubject(jwtUser.getUserName());
    claims.put("userId", String.valueOf(jwtUser.getId()));
    claims.put("role", jwtUser.getRole());

    return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, "supraja").compact();
  }
}
