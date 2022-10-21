package com.portfolio.portfolioEMM.controllers;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.portfolio.portfolioEMM.exceptions.PortfolioException;
import com.portfolio.portfolioEMM.jsons.PersonCreateRest;
import com.portfolio.portfolioEMM.jsons.PersonRest;
import com.portfolio.portfolioEMM.responses.PortfolioResponse;
import com.portfolio.portfolioEMM.services.PersonService;

public class PersonControllerTest {

	private final static Long PERSON_ID = 1L;
	private final static String NAME = "person Name";
	private final static String LAST_NAME = "person Last Name";
	private final static Date AGE = new Date();
	private final static String TITLE = "title";
	private final static String ABOUT = "about";
	private final static String PROVINCE = "province";
	private final static String COUNTRY = "country";
	private final static String IMAGE = "imagen";
	private final static String BANNER = "banner";
	private final static String EMAIL = "email@email.com";

	private final static String PERSON_CREATE = "Person saved!";
	private final static String PERSON_DELETED = "Person deleted!";
	private final static String ALL_PEOPLE_DELETED = "All Person has been deleted!";
	public static final String PERSON_UPTDATE = "Person update!";

	private final static PersonRest PERSON_REST = new PersonRest();
	private final static PersonCreateRest PERSON_CREATE_REST = new PersonCreateRest();
	private final static List<PersonRest> PERSON_REST_LIST = new ArrayList<>();

	private final static String SUCCES_STATUS = "Succes";
	private final static String SUCCES_CODE = "200 OK";
	private final static String OK = "OK";

	@Mock
	PersonService personService;

	@InjectMocks
	PersonController personController;

	@Before
	public void init() throws PortfolioException {
		MockitoAnnotations.initMocks(this);

		PERSON_REST.setId(PERSON_ID);
		PERSON_REST.setName(NAME);
		PERSON_REST.setLastName(LAST_NAME);
		PERSON_REST.setAge(AGE);
		PERSON_REST.setTitle(TITLE);
		PERSON_REST.setAbout(ABOUT);
		PERSON_REST.setProvince(PROVINCE);
		PERSON_REST.setCountry(COUNTRY);
		PERSON_REST.setImage(IMAGE);
		PERSON_REST.setBanner(BANNER);
		PERSON_REST.setEmail(EMAIL);

		Mockito.when(personService.getPersonById(PERSON_ID)).thenReturn(PERSON_REST);
		Mockito.when(personService.getAllPersons()).thenReturn(PERSON_REST_LIST);
		Mockito.when(personService.createPerson(PERSON_CREATE_REST)).thenReturn(PERSON_CREATE);
		Mockito.when(personService.updatePersonById(PERSON_ID, PERSON_CREATE_REST)).thenReturn(PERSON_UPTDATE);
		Mockito.when(personService.deletePersonById(PERSON_ID)).thenReturn(PERSON_DELETED);
		Mockito.when(personService.deleteAllPersons()).thenReturn(ALL_PEOPLE_DELETED);

	}

	@Test
	public void getByIdTest() throws PortfolioException {
		final PortfolioResponse<PersonRest> response = personController.getPersonById(PERSON_ID);

		assertEquals(response.getStatus(), SUCCES_STATUS);
		assertEquals(response.getCode(), SUCCES_CODE);
		assertEquals(response.getMessage(), OK);
		assertEquals(response.getData(), PERSON_REST);

	}

	@Test
	public void getAllTest() throws PortfolioException {
		final PortfolioResponse<List<PersonRest>> response = personController.getAllPersons();

		assertEquals(response.getStatus(), SUCCES_STATUS);
		assertEquals(response.getCode(), SUCCES_CODE);
		assertEquals(response.getMessage(), OK);
		assertEquals(response.getData(), PERSON_REST_LIST);
	}

	@Test
	public void createExperienceTest() throws PortfolioException {
		final PortfolioResponse<String> response = personController.createPerson(PERSON_CREATE_REST);
		assertEquals(response.getStatus(), SUCCES_STATUS);
		assertEquals(response.getCode(), SUCCES_CODE);
		assertEquals(response.getMessage(), OK);
		assertEquals(response.getData(), PERSON_CREATE);
	}

	@Test
	public void updateExperienceTest() throws PortfolioException {
		final PortfolioResponse<String> response = personController.updatePerson(PERSON_ID, PERSON_CREATE_REST);
		assertEquals(response.getStatus(), SUCCES_STATUS);
		assertEquals(response.getCode(), SUCCES_CODE);
		assertEquals(response.getMessage(), OK);
		assertEquals(response.getData(), PERSON_UPTDATE);
	}

	@Test
	public void deleteByIdTest() throws PortfolioException {
		final PortfolioResponse<String> response = personController.deletePersonById(PERSON_ID);
		assertEquals(response.getStatus(), SUCCES_STATUS);
		assertEquals(response.getCode(), SUCCES_CODE);
		assertEquals(response.getMessage(), OK);
		assertEquals(response.getData(), PERSON_DELETED);
	}

	@Test
	public void deleteAllExperience() throws PortfolioException {
		final PortfolioResponse<String> response = personController.deleteAllPeople();
		assertEquals(response.getStatus(), SUCCES_STATUS);
		assertEquals(response.getCode(), SUCCES_CODE);
		assertEquals(response.getMessage(), OK);
		assertEquals(response.getData(), ALL_PEOPLE_DELETED);
	}

}
