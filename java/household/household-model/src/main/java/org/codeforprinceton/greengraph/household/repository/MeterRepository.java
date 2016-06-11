/** Copyright (c) 2016. Code for Princeton. All rights reserved. */
package org.codeforprinceton.greengraph.household.repository;

import org.codeforprinceton.greengraph.household.model.Meter;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Meter JPA Repository.
 */
public interface MeterRepository extends JpaRepository<Meter, Long> {

}
