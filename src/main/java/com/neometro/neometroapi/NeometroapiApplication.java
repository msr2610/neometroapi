package com.neometro.neometroapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.microsoft.applicationinsights.attach.ApplicationInsights;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@SpringBootApplication
public class NeometroapiApplication {

	private static final Logger logger = LogManager.getLogger(NeometroapiApplication.class);


	public static void main(String[] args) {
		ApplicationInsights.attach();

		SpringApplication.run(NeometroapiApplication.class, args);
		logger.info("Application Started");
	}

}
