/** Copyright (c) 2016. Code for Princeton. All rights reserved. */
package org.codeforprinceton.greengraph.household;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Meter Type model POJO.
 */
@Entity
public class MeterType {

	@Id
	@GeneratedValue
	private Long id;

	private String type;

	/**
	 * Default constructor is expected by JPA. No other reason to have it otherwise
	 */
	protected MeterType() {

	}

	/**
	 * Static Builder to reduce Constructor complexity and increase comprehension.
	 */
	public static class Builder {

		MeterType meterType;

		/**
		 * Required default constructor that allows for the start of property chaining.
		 */
		public Builder() {

			meterType = new MeterType();
		}

		/**
		 * Builder method that takes a property value for the MeterType and allows us to chain the creation with other
		 * property values.
		 * 
		 * @param value the Property Value
		 * @return the MeterType
		 */
		public Builder type(final String value) {

			meterType.setType(value);
			return this;
		}

		/**
		 * Builds the object that the builder represents and returns it to the caller.
		 * 
		 * @return the MeterType
		 */
		public MeterType build() {

			return meterType;
		}
	}

	public final Long getId() {

		return id;
	}

	public final void setId(Long id) {

		this.id = id;
	}

	public final String getType() {

		return type;
	}

	public final void setType(String type) {

		this.type = type;
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
