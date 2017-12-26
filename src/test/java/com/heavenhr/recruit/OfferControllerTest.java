package com.heavenhr.recruit;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.heavenhr.recruit.model.Application;
import com.heavenhr.recruit.model.Candidate;
import com.heavenhr.recruit.model.Offer;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration
@WebAppConfiguration
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class OfferControllerTest {

	@Autowired
	private WebApplicationContext context;

	private MockMvc mvc;

	private ObjectMapper mapper;

	@Before
	public void before() {
		mvc = MockMvcBuilders.webAppContextSetup(context).build();
		mapper = Jackson2ObjectMapperBuilder.json().serializationInclusion(JsonInclude.Include.NON_NULL) // Don’t
				// include
				// null
				// values
				.featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS) // ISODate
				.modules(new JavaTimeModule()).build();
	}

	@Test
	public void testGetOffers() throws Exception {
		Offer offer = new Offer();
		offer.setJobTitle("dummy title");
		offer.setStartDate(LocalDate.now());

		Offer offer2 = new Offer();
		offer2.setJobTitle("dummy title2");
		offer2.setStartDate(LocalDate.now());

		mvc.perform(post("/api/v1/offer").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(mapper.writeValueAsString(offer))).andExpect(status().is2xxSuccessful());

		mvc.perform(post("/api/v1/offer").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(mapper.writeValueAsString(offer2))).andExpect(status().is2xxSuccessful());

		mvc.perform(get("/api/v1/offer")).andExpect(status().is2xxSuccessful())
				.andExpect(jsonPath("$.*", org.hamcrest.Matchers.hasSize(2)));
	}

	@Test
	public void testGetOffer() throws Exception {
		Offer offer = new Offer();
		offer.setJobTitle("dummy title");
		offer.setStartDate(LocalDate.now());

		mvc.perform(post("/api/v1/offer").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(mapper.writeValueAsString(offer))).andExpect(status().is2xxSuccessful());

		mvc.perform(get("/api/v1/offer/1")).andExpect(status().is2xxSuccessful())
				.andExpect(jsonPath("$.*", org.hamcrest.Matchers.notNullValue()));
	}

	@Test
	public void testCreateOffer() throws Exception {
		Offer offer = new Offer();
		offer.setJobTitle("dummy title");
		offer.setStartDate(LocalDate.now());

		mvc.perform(post("/api/v1/offer").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(mapper.writeValueAsString(offer))).andExpect(status().is2xxSuccessful());

		mvc.perform(get("/api/v1/offer")).andExpect(status().is2xxSuccessful())
				.andExpect(jsonPath("$.*", org.hamcrest.Matchers.hasSize(1)));
	}

	@Test
	public void testGetOfferApplication() throws Exception {
		Offer offer = new Offer();
		offer.setJobTitle("dummy title");
		offer.setStartDate(LocalDate.now());

		Application application = new Application();

		Candidate candidate = new Candidate();
		candidate.setEmail("test@admin.com");
		candidate.setResumeText("resume dummy text");

		application.setCandidate(candidate);

		offer.applyForJob(application);

		mvc.perform(post("/api/v1/offer").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(mapper.writeValueAsString(offer))).andExpect(status().is2xxSuccessful());

		mvc.perform(get("/api/v1/offer/1/application/1")).andExpect(status().is2xxSuccessful())
				.andExpect(jsonPath("$.*", org.hamcrest.Matchers.notNullValue()));
	}

	@Test
	public void testGetOfferApplications() throws Exception {
		Offer offer = new Offer();
		offer.setJobTitle("dummy title");
		offer.setStartDate(LocalDate.now());

		Application application = new Application();

		Candidate candidate = new Candidate();
		candidate.setEmail("test@admin.com");
		candidate.setResumeText("resume dummy text");

		application.setCandidate(candidate);

		offer.applyForJob(application);

		mvc.perform(post("/api/v1/offer").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(mapper.writeValueAsString(offer))).andExpect(status().is2xxSuccessful());

		mvc.perform(get("/api/v1/offer/1/application")).andExpect(status().is2xxSuccessful())
				.andExpect(jsonPath("$.*", org.hamcrest.Matchers.hasSize(1)));
	}

}
