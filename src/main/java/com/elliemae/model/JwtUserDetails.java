package com.elliemae.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/** @author supraja_giddaluru */
public class JwtUserDetails implements UserDetails {

  private String userName;
  private String token;
  private Long id;
  private Collection<? extends GrantedAuthority> authorities;

  /**
   * Instantiates a new Jwt user details.
   *
   * @param userName the user name
   * @param id the id
   * @param token the token
   * @param grantedAuthorities the granted authorities
   */
public JwtUserDetails(
      String userName, long id, String token, List<GrantedAuthority> grantedAuthorities) {

    this.userName = userName;
    this.id = id;
    this.token = token;
    this.authorities = grantedAuthorities;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public String getPassword() {
    return null;
  }

  @Override
  public String getUsername() {
    return userName;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  /**
   * Gets user name.
   *
   * @return the user name
   */
public String getUserName() {
    return userName;
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
   * Gets id.
   *
   * @return the id
   */
public Long getId() {
    return id;
  }
}
