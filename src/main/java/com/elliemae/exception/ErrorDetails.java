package com.elliemae.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
/**
 * *
 *
 * @author supraja_giddaluru
 */
@Getter
@Setter
@AllArgsConstructor
public class ErrorDetails {
  private Date timestamp;
  private String message;
  private String details;
}
