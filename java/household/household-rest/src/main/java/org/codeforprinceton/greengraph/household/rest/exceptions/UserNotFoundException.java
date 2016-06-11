/** Copyright (c) 2016. Code for Princeton. All rights reserved. */
package org.codeforprinceton.greengraph.household.rest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * User Not Found Exception.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -1057974966212955727L;

	/**
	 * Required constructor.
	 * 
	 * @param username the Username
	 */
	public UserNotFoundException(String username) {
		super("Could not find user '" + username + "'.");
	}
}