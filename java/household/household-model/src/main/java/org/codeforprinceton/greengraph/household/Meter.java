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
 * Meter model POJO.
 */
@Entity
public class Meter {

	@OneToMany(mappedBy = "meter")
	private Set<Reading> readings = new HashSet<>();

	@JsonIgnore
	@ManyToOne
	private Household household;

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	private MeterType meterType;

	private String name;

	/**
	 * Default constructor is expected by JPA. No other reason to have it otherwise
	 */
	protected Meter() {

	}

	/**
	 * Static Builder to reduce Constructor complexity and increase comprehension.
	 */
	public static class Builder {

		Meter meter;

		/**
		 * Required default constructor that allows for the start of property chaining.
		 */
		public Builder() {

			meter = new Meter();
		}

		/**
		 * Builder method that takes a property value for the Meter and allows us to chain the creation with other
		 * property values.
		 * 
		 * @param value the Property Value
		 * @return the Meter
		 */
		public Builder household(final Household value) {

			meter.setHousehold(value);
			return this;
		}

		/**
		 * Builder method that takes a property value for the Meter and allows us to chain the creation with other
		 * property values.
		 * 
		 * @param value the Property Value
		 * @return the Meter
		 */
		public Builder meterType(final MeterType value) {

			meter.setMeterType(value);
			return this;
		}

		/**
		 * Builder method that takes a property value for the Meter and allows us to chain the creation with other
		 * property values.
		 * 
		 * @param value the Property Value
		 * @return the Meter
		 */
		public Builder name(final String value) {

			meter.setName(value);
			return this;
		}

		/**
		 * Builds the object that the builder represents and returns it to the caller.
		 * 
		 * @return the Meter
		 */
		public Meter build() {

			return meter;
		}
	}

	public final Household getHousehold() {

		return household;
	}

	public final void setHousehold(Household household) {

		this.household = household;
	}

	public final Long getId() {

		return id;
	}

	public final void setId(Long id) {

		this.id = id;
	}

	public final MeterType getMeterType() {

		return meterType;
	}

	public final void setMeterType(MeterType meterType) {

		this.meterType = meterType;
	}

	public final String getName() {

		return name;
	}

	public final void setName(String name) {

		this.name = name;
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
