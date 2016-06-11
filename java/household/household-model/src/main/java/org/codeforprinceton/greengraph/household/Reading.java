/** Copyright (c) 2016. Code for Princeton. All rights reserved. */
package org.codeforprinceton.greengraph.household;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Reading model POJO.
 */
@Entity
public class Reading {

	@JsonIgnore
	@ManyToOne
	private Meter meter;

	@Id
	@GeneratedValue
	private Long id;

	private Date date;

	private Double consumption;

	/**
	 * Default constructor is expected by JPA. No other reason to have it otherwise
	 */
	protected Reading() {

	}

	/**
	 * Static Builder to reduce Constructor complexity and increase comprehension.
	 */
	public static class Builder {

		Reading reading;

		/**
		 * Required default constructor that allows for the start of property chaining.
		 */
		public Builder() {

			reading = new Reading();
		}

		/**
		 * Builder method that takes a property value for the Reading and allows us to chain the creation with other
		 * property values.
		 * 
		 * @param value the Property Value
		 * @return the Reading
		 */
		public Builder meter(final Meter value) {

			reading.setMeter(value);
			return this;
		}

		/**
		 * Builder method that takes a property value for the Reading and allows us to chain the creation with other
		 * property values.
		 * 
		 * @param value the Property Value
		 * @return the Reading
		 */
		public Builder date(final Date value) {

			reading.setDate(value);
			return this;
		}

		/**
		 * Builder method that takes a property value for the Reading and allows us to chain the creation with other
		 * property values.
		 * 
		 * @param value the Property Value
		 * @return the Reading
		 */
		public Builder consumption(final Double value) {

			reading.setConsumption(value);
			return this;
		}

		/**
		 * Builds the object that the builder represents and returns it to the caller.
		 * 
		 * @return the Reading
		 */
		public Reading build() {

			return reading;
		}
	}

	public final Meter getMeter() {

		return meter;
	}

	public final void setMeter(Meter meter) {

		this.meter = meter;
	}

	public final Long getId() {

		return id;
	}

	public final void setId(Long id) {

		this.id = id;
	}

	public final Date getDate() {

		return date;
	}

	public final void setDate(Date date) {

		this.date = date;
	}

	public final Double getConsumption() {

		return consumption;
	}

	public final void setConsumption(Double consumption) {

		this.consumption = consumption;
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
