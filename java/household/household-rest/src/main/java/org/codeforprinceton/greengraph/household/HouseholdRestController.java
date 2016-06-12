/** Copyright (c) 2016. Code for Princeton. All rights reserved. */
package org.codeforprinceton.greengraph.household;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * Household Rest Controller.
 */
@RestController
@RequestMapping("/{username}/households")
@ComponentScan(basePackages = "org.codeforprinceton.greengraph.household")
@EnableAutoConfiguration
public class HouseholdRestController {

	private final HouseholdRepository householdRepository;

	private final AccountRepository accountRepository;

	/**
	 * Required constructor.
	 * 
	 * @param householdRepository the Household Repository
	 * @param accountRepository the Account Repository
	 */
	@Autowired
	public HouseholdRestController(HouseholdRepository householdRepository, AccountRepository accountRepository) {

		super();

		this.householdRepository = householdRepository;
		this.accountRepository = accountRepository;
	}

	@RequestMapping(method = RequestMethod.POST)
	ResponseEntity<?> add(@PathVariable String username, @RequestBody Household input) {

		this.validateUser(username);
		return this.accountRepository.findByUsername(username).map(account -> {
			Household result = householdRepository.save(new Household.Builder().account(account)
					.address1(input.getAddress1()).address2(input.getAddress2()).build());

			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(result.getId()).toUri());
			return new ResponseEntity<>(null, httpHeaders, HttpStatus.CREATED);
		}).get();
	}

	/**
	 * Reads the Households from the Repository, if the User represented by the Username is valid.
	 * 
	 * @param username the Username
	 * @return the Collection of Household
	 */
	@RequestMapping(method = RequestMethod.GET)
	public Resources<HouseholdResource> readHouseholds(@PathVariable String username) {

		validateUser(username);

		List<HouseholdResource> householdResourceList = householdRepository.findByAccountUsername(username).stream()
				.map(HouseholdResource::new).collect(Collectors.toList());

		return new Resources<HouseholdResource>(householdResourceList);
	}

	/**
	 * Reads a Household from the Repository, if the User represented by the Username is valid.
	 * 
	 * @param username the Username
	 * @param householdId the Household Identifier
	 * @return the Household
	 */
	@RequestMapping(value = "/{householdId} ", method = RequestMethod.GET)
	public HouseholdResource readHouseHold(@PathVariable String username, @PathVariable Long householdId) {

		validateUser(username);
		return new HouseholdResource(this.householdRepository.findOne(householdId));
	}

	private void validateUser(String username) {

		this.accountRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
	}
}
