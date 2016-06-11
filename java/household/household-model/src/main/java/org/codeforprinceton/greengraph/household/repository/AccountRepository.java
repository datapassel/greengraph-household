/** Copyright (c) 2016. Code for Princeton. All rights reserved. */
package org.codeforprinceton.greengraph.household.repository;

import java.util.Optional;

import org.codeforprinceton.greengraph.household.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Account JPA Repository.
 */
public interface AccountRepository extends JpaRepository<Account, Long> {

	/**
	 * Find the Account for a given Username.
	 * 
	 * @param username the Username
	 * @return the Account
	 */
	Optional<Account> findByUsername(String username);
}
