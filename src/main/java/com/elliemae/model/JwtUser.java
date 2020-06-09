package com.elliemae.model;

import lombok.*;

/** @author supraja_giddaluru */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JwtUser {

  private String userName;
  private long id;
  private String role;
}
