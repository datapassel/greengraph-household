/** Copyright (c) 2016. Code for Princeton. All rights reserved. */
package org.codeforprinceton.greengraph.household;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Household JPA Repository.
 */
public interface HouseholdRepository extends JpaRepository<Household, Long> {

	/**
	 * Find the Household(s) for a given Account's Username.
	 * 
	 * @param username the Username
	 * @return the Household(s)
	 */
	Collection<Household> findByAccountUsername(String username);
}
