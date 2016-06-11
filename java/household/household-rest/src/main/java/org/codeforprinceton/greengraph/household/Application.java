/** Copyright (c) 2016. Code for Princeton. All rights reserved. */
package org.codeforprinceton.greengraph.household;

import java.util.Arrays;

import org.codeforprinceton.greengraph.household.Account;
import org.codeforprinceton.greengraph.household.AccountRepository;
import org.codeforprinceton.greengraph.household.Household;
import org.codeforprinceton.greengraph.household.HouseholdRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Application Boot Loader.
 */
@Configuration
@ComponentScan(basePackageClasses = { Account.class, AccountRepository.class })

@EnableAutoConfiguration
public class Application {

	@Bean
	CommandLineRunner init(AccountRepository accountRepository, HouseholdRepository householdRepository) {

		return (evt) -> Arrays.asList("joebloggs,janedoe".split(",")).forEach(

				a -> {
					Account account = accountRepository
							.save(new Account.Builder().username(a).password("password").build());

					householdRepository.save(new Household.Builder().account(account).address1("First Address Line")
							.address2("Second Address Line").build());

					householdRepository.save(new Household.Builder().account(account).address1("First Address Line")
							.address2("Second Address Line").build());
				});
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
