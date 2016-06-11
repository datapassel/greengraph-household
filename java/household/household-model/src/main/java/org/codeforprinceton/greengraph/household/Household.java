/** Copyright (c) 2016. Code for Princeton. All rights reserved. */
package org.codeforprinceton.greengraph.household;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Household model POJO.
 */
@Entity
public class Household {

	@OneToMany(mappedBy = "household")
	private Set<Meter> meters = new HashSet<>();

	@JsonIgnore
	@ManyToOne
	private Account account;

	@Id
	@GeneratedValue
	private Long id;

	private String address1;

	private String address2;

	private String city;

	private String state;

	private String zipCode;

	/**
	 * Default constructor is expected by JPA. No other reason to have it otherwise
	 */
	protected Household() {

	}

	/**
	 * Static Builder to reduce Constructor complexity and increase comprehension.
	 */
	public static class Builder {

		Household household;

		/**
		 * Required default constructor that allows for the start of property chaining.
		 */
		public Builder() {

			household = new Household();
		}

		/**
		 * Builder method that takes a property value for the Household and allows us to chain the creation with other
		 * property values.
		 * 
		 * @param value the Property Value
		 * @return the Household
		 */
		public Builder account(final Account value) {

			household.setAccount(value);
			return this;
		}

		/**
		 * Builder method that takes a property value for the Household and allows us to chain the creation with other
		 * property values.
		 * 
		 * @param value the Property Value
		 * @return the Household
		 */
		public Builder address1(final String value) {

			household.setAddress1(value);
			return this;
		}

		/**
		 * Builder method that takes a property value for the Household and allows us to chain the creation with other
		 * property values.
		 * 
		 * @param value the Property Value
		 * @return the Household
		 */
		public Builder address2(final String value) {

			household.setAddress2(value);
			return this;
		}

		/**
		 * Builder method that takes a property value for the Household and allows us to chain the creation with other
		 * property values.
		 * 
		 * @param value the Property Value
		 * @return the Household
		 */
		public Builder city(final String value) {

			household.setCity(value);
			return this;
		}

		/**
		 * Builder method that takes a property value for the Household and allows us to chain the creation with other
		 * property values.
		 * 
		 * @param value the Property Value
		 * @return the Household
		 */
		public Builder state(final String value) {

			household.setState(value);
			return this;
		}

		/**
		 * Builder method that takes a property value for the Household and allows us to chain the creation with other
		 * property values.
		 * 
		 * @param value the Property Value
		 * @return the Household
		 */
		public Builder zipCode(final String value) {

			household.setZipCode(value);
			return this;
		}

		/**
		 * Builds the object that the builder represents and returns it to the caller.
		 * 
		 * @return the Household
		 */
		public Household build() {

			return household;
		}
	}

	public Set<Meter> getMeters() {

		return meters;
	}

	public void setMeters(Set<Meter> meters) {

		this.meters = meters;
	}

	public Account getAccount() {

		return account;
	}

	public void setAccount(Account account) {

		this.account = account;
	}

	public final Long getId() {

		return id;
	}

	public final void setId(Long id) {

		this.id = id;
	}

	public final String getAddress1() {

		return address1;
	}

	public final void setAddress1(String address1) {

		this.address1 = address1;
	}

	public final String getAddress2() {

		return address2;
	}

	public final void setAddress2(String address2) {

		this.address2 = address2;
	}

	public final String getCity() {

		return city;
	}

	public final void setCity(String city) {

		this.city = city;
	}

	public final String getState() {

		return state;
	}

	public final void setState(String state) {

		this.state = state;
	}

	public final String getZipCode() {

		return zipCode;
	}

	public final void setZipCode(String zipCode) {

		this.zipCode = zipCode;
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
