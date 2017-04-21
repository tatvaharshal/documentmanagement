package com.tatvacoconet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * 
 * @author TatvaSoft
 *
 */
@SpringBootApplication
public class TatvaSoftApplication extends SpringBootServletInitializer {

	private static Logger logger = LoggerFactory.getLogger(TatvaSoftApplication.class);
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return super.configure(builder);
	}
	
	public static void main(String[] args) {
        SpringApplication.run(TatvaSoftApplication.class, args);
        logger.info("TatvaSoft Spring Boot Application Running");
    }

}