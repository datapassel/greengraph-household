/** Copyright (c) 2016. Code for Princeton. All rights reserved. */
package org.codeforprinceton.greengraph.household.repository;

import org.codeforprinceton.greengraph.household.model.Reading;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Reading JPA Repository.
 */
public interface ReadingRepository extends JpaRepository<Reading, Long> {

}
