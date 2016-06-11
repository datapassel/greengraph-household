/** Copyright (c) 2016. Code for Princeton. All rights reserved. */
package org.codeforprinceton.greengraph.household.rest.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.codeforprinceton.greengraph.household.model.Household;
import org.codeforprinceton.greengraph.household.repository.AccountRepository;
import org.codeforprinceton.greengraph.household.repository.HouseholdRepository;
import org.codeforprinceton.greengraph.household.rest.Application;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

/**
 * Household REST Controller Unit Tests.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class HouseholdRestControllerUnitTest {

	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	private MockMvc mockMvc;

	private String username = "codeforprinceton";

	private HttpMessageConverter mappingJackson2HttpMessageConverter;

	// private Account account;

	private List<Household> householdList = new ArrayList<>();

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	private HouseholdRepository householdRepository;

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	void setConverters(HttpMessageConverter<?>[] converters) {

		this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
				.filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter).findAny().get();

		Assert.assertNotNull("The JSON message converter must not be null!", this.mappingJackson2HttpMessageConverter);

	}

	@Before
	public void setup() throws Exception {

		this.mockMvc = webAppContextSetup(webApplicationContext).build();

		this.householdRepository.deleteAllInBatch();
		this.accountRepository.deleteAllInBatch();

		// this.account = accountRepository.save(new Account.Builder().username(username).password("password").build());

		this.householdList.add(householdRepository.save(new Household.Builder().address1("Princeton Library")
				.address2("1 Washington Road").city("Princeton").state("New Jersey").zipCode("08544").build()));

		this.householdList.add(householdRepository.save(new Household.Builder().address1("132 Mercer Street")
				.city("Princeton").state("New Jersey").zipCode("08540").build()));
	}

	@Test
	public void userNotFound() throws Exception {

		mockMvc.perform(post("/notauser/households").content(this.json(new Household.Builder().build()))
				.contentType(contentType)).andExpect(status().isNotFound());
	}

	@Test
	public void readHouseholds() throws Exception {

		mockMvc.perform(get("/" + username + "/households")).andExpect(status().isOk())
				.andExpect(content().contentType(contentType));
		// TODO add field level asserts
	}

	@Test
	public void readSingleHousehold() throws Exception {

		mockMvc.perform(get("/" + username + "/households/" + this.householdList.get(0).getId()))
				.andExpect(status().isOk()).andExpect(content().contentType(contentType));
		// TODO add field level asserts
	}

	@Test
	public void createHousehold() throws Exception {

		String householdJson = json(new Household.Builder().address1("The Nassau Club").address2("6 Mercer Street")
				.city("Princeton").state("New Jersey").zipCode("08540").build());

		this.mockMvc.perform(post("/" + username + "/households").contentType(contentType).content(householdJson))
				.andExpect(status().isCreated());
	}

	private String json(Household household) throws IOException {

		MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();

		this.mappingJackson2HttpMessageConverter.write(household, MediaType.APPLICATION_JSON, mockHttpOutputMessage);

		return mockHttpOutputMessage.getBodyAsString();
	}
}
