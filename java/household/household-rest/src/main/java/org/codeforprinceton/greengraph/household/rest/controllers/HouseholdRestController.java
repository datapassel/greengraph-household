/** Copyright (c) 2016. Code for Princeton. All rights reserved. */
package org.codeforprinceton.greengraph.household.rest.controllers;

import java.util.Collection;

import org.codeforprinceton.greengraph.household.model.Household;
import org.codeforprinceton.greengraph.household.repository.AccountRepository;
import org.codeforprinceton.greengraph.household.repository.HouseholdRepository;
import org.codeforprinceton.greengraph.household.repository.MeterRepository;
import org.codeforprinceton.greengraph.household.repository.ReadingRepository;
import org.codeforprinceton.greengraph.household.rest.exceptions.UserNotFoundException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Household Rest Controller.
 */
@RestController
@RequestMapping("/{username}/households")
public class HouseholdRestController {

	private final HouseholdRepository householdRepository;

	private final AccountRepository accountRepository;

	private final MeterRepository meterRepository;

	private final ReadingRepository readingRepository;

	/**
	 * Required constructor.
	 * 
	 * @param householdRepository the Household Repository
	 * @param accountRepository the Account Repository
	 * @param meterRepository the Meter Repository
	 * @param readingRepository the Reading Repository
	 */
	public HouseholdRestController(HouseholdRepository householdRepository, AccountRepository accountRepository,
			MeterRepository meterRepository, ReadingRepository readingRepository) {

		super();

		this.householdRepository = householdRepository;
		this.accountRepository = accountRepository;
		this.meterRepository = meterRepository;
		this.readingRepository = readingRepository;
	}

	/**
	 * Reads the Households from the Repository, if the User represented by the Username is valid.
	 * 
	 * @param username the Username
	 * @return the Collection of Household
	 */
	@RequestMapping(method = RequestMethod.GET)
	public Collection<Household> readHouseholds(@PathVariable String username) {

		validateUser(username);
		return this.householdRepository.findByAccountUsername(username);
	}

	/**
	 * Reads a Household from the Repository, if the User represented by the Username is valid.
	 * 
	 * @param username the Username
	 * @param householdId the Household Identifier
	 * @return the Household
	 */
	@RequestMapping(value = "/householdId", method = RequestMethod.GET)
	public Household readHouseHold(@PathVariable String username, @PathVariable Long householdId) {

		validateUser(username);
		return this.householdRepository.findOne(householdId);
	}

	private void validateUser(String username) {

		this.accountRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
	}
}
