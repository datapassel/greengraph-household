/** Copyright (c) 2016. Code for Princeton. All rights reserved. */
package org.codeforprinceton.greengraph.household;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
 * Household REST Controller Unit Test.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class HouseholdRestControllerTest {

	// private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
	// MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
	private MediaType contentType = new MediaType("application", "hal+json");

	private MockMvc mockMvc;

	private String username = "code";

	private HttpMessageConverter mappingJackson2HttpMessageConverter;

	private Account account;

	private List<Household> householdList = new ArrayList<>();

	@Autowired
	private HouseholdRepository householdRepository;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	void setConverters(HttpMessageConverter<?>[] converters) {

		this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
				.filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter).findAny().get();

		Assert.assertNotNull("The JSON message converter must not be null", this.mappingJackson2HttpMessageConverter);
	}

	@Before
	public void setup() throws Exception {

		this.mockMvc = webAppContextSetup(webApplicationContext).build();

		this.householdRepository.deleteAllInBatch();
		this.accountRepository.deleteAllInBatch();

		this.account = accountRepository.save(new Account.Builder().username(username).password("password").build());

		this.householdList.add(householdRepository.save(new Household.Builder().account(account)
				.address1("First Address Line").address2("Second Address Line").build()));
		this.householdList.add(householdRepository.save(new Household.Builder().account(account)
				.address1("First Address Line").address2("Second Address Line").build()));
	}

	@Test
	public void userNotFound() throws Exception {

		mockMvc.perform(post("/notcode/households/").content(this.json(new Household())).contentType(contentType))
				.andExpect(status().isNotFound());
	}

	@Test
	public void readSingleHousehold() throws Exception {

		mockMvc.perform(get("/" + username + "/households/" + this.householdList.get(0).getId()))
				.andExpect(status().isOk()).andExpect(content().contentType(contentType))
				.andExpect(jsonPath("$.household.id", is(this.householdList.get(0).getId().intValue())))
				.andExpect(jsonPath("$.household.address1", is("First Address Line")))
				.andExpect(jsonPath("$.household.address2", is("Second Address Line")));
		// .andExpect(jsonPath("$._links.self.href", containsString("/" + username + "/households/"
		// + this.householdList.get(0).getId())));
	}

	@Test
	public void readHouseholds() throws Exception {

		mockMvc.perform(get("/" + username + "/households")).andExpect(status().isOk())
				.andExpect(content().contentType(contentType))
				.andExpect(jsonPath("$._embedded.householdResourceList", hasSize(2)))
				.andExpect(jsonPath("$._embedded.householdResourceList[0].household.id",
						is(this.householdList.get(0).getId().intValue())))
				.andExpect(
						jsonPath("$._embedded.householdResourceList[0].household.address1", is("First Address Line")))
				.andExpect(
						jsonPath("$._embedded.householdResourceList[0].household.address2", is("Second Address Line")))
				.andExpect(jsonPath("$._embedded.householdResourceList[1].household.id",
						is(this.householdList.get(1).getId().intValue())))
				.andExpect(
						jsonPath("$._embedded.householdResourceList[1].household.address1", is("First Address Line")))
				.andExpect(
						jsonPath("$._embedded.householdResourceList[1].household.address2", is("Second Address Line")));
	}

	@Test
	public void createHousehold() throws Exception {

		String householdJson = json(new Household.Builder().account(account).address1("First Address Line")
				.address2("Second Address Line").build());
		this.mockMvc.perform(post("/" + username + "/households").contentType(contentType).content(householdJson))
				.andExpect(status().isCreated());
	}

	protected String json(Object object) throws IOException {

		MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
		this.mappingJackson2HttpMessageConverter.write(object, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
		return mockHttpOutputMessage.getBodyAsString();
	}
}
