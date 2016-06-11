/** Copyright (c) 2016. Code for Princeton. All rights reserved. */
package org.codeforprinceton.greengraph.household.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Application Boot Loader.
 */
@Configuration
@ComponentScan
@EnableAutoConfiguration
public class Application {

	/**
	 * Private constructor, as this acts as a Singleton.
	 */
	private Application() {

	}

	/**
	 * The main method - nice and procedural!
	 * 
	 * @param args the Arguments
	 */
	public static void main(String[] args) {

		SpringApplication.run(Application.class, args);
	}
}
