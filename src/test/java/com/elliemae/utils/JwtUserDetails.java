package com.elliemae.utils;

import org.junit.Test;
/**
 * *
 *
 * @author supraja_giddaluru
 */
public class JwtUserDetails {
  @Test
  public void testAccesors_shouldAccessProperField() {

    PojoTestUtils.validateAccessors(JwtUserDetails.class);
  }
}
