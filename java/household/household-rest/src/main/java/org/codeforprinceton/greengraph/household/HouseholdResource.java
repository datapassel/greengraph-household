package org.codeforprinceton.greengraph.household;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class HouseholdResource extends ResourceSupport {

	private final Household household;

	public HouseholdResource(Household household) {

		String username = household.getAccount().getUsername();
		this.household = household;
		this.add(new Link(household.getId().toString(), "household-id"));
		this.add(linkTo(HouseholdRestController.class, username).withRel("households"));
		this.add(linkTo(methodOn(HouseholdRestController.class, username).readHouseHold(username, household.getId()))
				.withSelfRel());
	}

	public Household getHousehold() {

		return household;
	}
}
