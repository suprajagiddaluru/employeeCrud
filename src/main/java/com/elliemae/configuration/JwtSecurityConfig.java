package com.elliemae.configuration;

import com.elliemae.security.JwtAuthenticationEntryPoint;
import com.elliemae.security.JwtAuthenticationProvider;
import com.elliemae.security.JwtAuthenticationTokenFilter;
import com.elliemae.security.JwtSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Collections;

/** @author supraja_giddaluru */
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
@Configuration
public class JwtSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired private JwtAuthenticationProvider authenticationProvider;
  @Autowired private JwtAuthenticationEntryPoint entryPoint;

  @Bean
  public AuthenticationManager authenticationManager() {
    return new ProviderManager(Collections.singletonList(authenticationProvider));
  }

  /**
   * Authentication token filter jwt authentication token filter.
   *
   * @return the jwt authentication token filter
   */
  @Bean
  public JwtAuthenticationTokenFilter authenticationTokenFilter() {
    JwtAuthenticationTokenFilter filter = new JwtAuthenticationTokenFilter();
    filter.setAuthenticationManager(authenticationManager());
    filter.setAuthenticationSuccessHandler(new JwtSuccessHandler());
    return filter;
  }

  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring()
            .antMatchers(
                    "/v2/api-docs",
                    "/configuration/ui",
                    "/swagger-resources/**",
                    "/configuration/**",
                    "/swagger-ui.html",
                    "/webjars/**",
                    "/csrf");
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {

    http.csrf()
            .disable()
            .authorizeRequests()
            .antMatchers(
                    "/v2/api-docs",
                    "/configuration/ui",
                    "/swagger-resources/**",
                    "/configuration/**",
                    "/swagger-ui.html#/**",
                    "/webjars/**",
                    "/csrf")
            .permitAll()
            .antMatchers("/rest/**")
            .authenticated()
            .and()
            .exceptionHandling()
            .authenticationEntryPoint(entryPoint)
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    http.addFilterBefore(authenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    http.headers().cacheControl();
  }
}
