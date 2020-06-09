package com.elliemae;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/***
 * @author supraja_giddaluru
 *
 */
@Log4j2
@SpringBootApplication
public class EmployeeCrudApplication {

  /**
   * The entry point of application.
   *
   * @param args the input arguments
   */
  public static void main(String[] args) {
        log.info(":: in the main class of boot App ::");

		SpringApplication.run(EmployeeCrudApplication.class, args);
		log.info(":: Exit from main class of boot App ::");
	}

}
