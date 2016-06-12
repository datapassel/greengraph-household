/** Copyright (c) 2016. Code for Princeton. All rights reserved. */
package org.codeforprinceton.greengraph.household;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Account model POJO.
 */
@Entity
public class Account {

	@OneToMany(mappedBy = "account")
	private Set<Household> households = new HashSet<>();

	@Id
	@GeneratedValue
	private Long id;

	private String username;

	private String password;

	/**
	 * Default constructor is expected by JPA. No other reason to have it otherwise
	 */
	protected Account() {

	}

	/**
	 * Static Builder to reduce Constructor complexity and increase comprehension.
	 */
	public static class Builder {

		Account account;

		/**
		 * Required default constructor that allows for the start of property chaining.
		 */
		public Builder() {

			account = new Account();
		}

		/**
		 * Builder method that takes a property value for the Account and allows us to chain the creation with other
		 * property values.
		 * 
		 * @param value the Property Value
		 * @return the Account
		 */
		public Builder username(final String value) {

			account.setUsername(value);
			return this;
		}

		/**
		 * Builder method that takes a property value for the Account and allows us to chain the creation with other
		 * property values.
		 * 
		 * @param value the Property Value
		 * @return the Account
		 */
		public Builder password(final String value) {

			account.setPassword(value);
			return this;
		}

		/**
		 * Builds the object that the builder represents and returns it to the caller.
		 * 
		 * @return the Account
		 */
		public Account build() {

			return account;
		}
	}

	public Set<Household> getHouseholds() {

		return households;
	}

	public void setHouseholds(Set<Household> households) {

		this.households = households;
	}

	public final Long getId() {

		return id;
	}

	public final void setId(Long id) {

		this.id = id;
	}

	public final String getUsername() {

		return username;
	}

	public final void setUsername(String username) {

		this.username = username;
	}

	public final String getPassword() {

		return password;
	}

	public final void setPassword(String password) {

		this.password = password;
	}

	@Override
	public boolean equals(Object object) {

		return EqualsBuilder.reflectionEquals(this, object, Boolean.FALSE);
	}

	@Override
	public int hashCode() {

		return HashCodeBuilder.reflectionHashCode(this, Boolean.FALSE);
	}

	@Override
	public String toString() {

		return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE, Boolean.FALSE);
	}
}
