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
import com.portfolio.portfolioEMM.jsons.ExperienceCreateRest;
import com.portfolio.portfolioEMM.jsons.ExperienceRest;
import com.portfolio.portfolioEMM.responses.PortfolioResponse;
import com.portfolio.portfolioEMM.services.ExperienceService;

public class ExperienceControllerTest {

	private final static Long EXPERIENCE_ID = 1L;
	private final static String NAME = "experience";
	private final static String TITLE = "title";
	private final static String ACTIVITY = "activity";
	private final static Date DATE_START = new Date();
	private final static Date DATE_END = new Date();
	private final static String IMAGE = "image";
	private final static Long PERSON_ID = 1L;
	private final static String EXPERIENCE_CREATE = "Experience saved!";
	private final static String EXPERIENCE_DELETED = "Experience deleted!";
	private final static String ALL_EXPERIENCE_DELETED = "All Experience has been deleted!";
	public static final String EXPERIENCE_UPTDATE = "Experience update!";

	private final static ExperienceRest EXPERIENCE_REST = new ExperienceRest();
	private final static ExperienceCreateRest EXPERIENCE_CREATE_REST = new ExperienceCreateRest();
	private final static List<ExperienceRest> EXPERIENCE_REST_LIST = new ArrayList<>();

	private final static String SUCCES_STATUS = "Succes";
	private final static String SUCCES_CODE = "200 OK";
	private final static String OK = "OK";

	@Mock
	ExperienceService experienceService;

	@InjectMocks
	ExperienceController experienceController;

	@Before
	public void init() throws PortfolioException {
		MockitoAnnotations.initMocks(this);

		EXPERIENCE_REST.setId(EXPERIENCE_ID);
		EXPERIENCE_REST.setName(NAME);
		EXPERIENCE_REST.setTitle(TITLE);
		EXPERIENCE_REST.setActivity(ACTIVITY);
		EXPERIENCE_REST.setDateStart(DATE_START);
		EXPERIENCE_REST.setDateEnd(DATE_END);
		EXPERIENCE_REST.setImage(IMAGE);
		EXPERIENCE_REST.setPersonId(PERSON_ID);

		Mockito.when(experienceService.getExperienceById(EXPERIENCE_ID)).thenReturn(EXPERIENCE_REST);
		Mockito.when(experienceService.getAllExperiences()).thenReturn(EXPERIENCE_REST_LIST);
		Mockito.when(experienceService.createExperience(EXPERIENCE_CREATE_REST)).thenReturn(EXPERIENCE_CREATE);
		Mockito.when(experienceService.updateExpierenceById(EXPERIENCE_ID, EXPERIENCE_CREATE_REST))
				.thenReturn(EXPERIENCE_UPTDATE);
		Mockito.when(experienceService.deleteExperienceById(EXPERIENCE_ID)).thenReturn(EXPERIENCE_DELETED);
		Mockito.when(experienceService.deleteAllExperiences()).thenReturn(ALL_EXPERIENCE_DELETED);

	}

	@Test
	public void getByIdTest() throws PortfolioException {
		final PortfolioResponse<ExperienceRest> response = experienceController.getById(EXPERIENCE_ID);

		assertEquals(response.getStatus(), SUCCES_STATUS);
		assertEquals(response.getCode(), SUCCES_CODE);
		assertEquals(response.getMessage(), OK);
		assertEquals(response.getData(), EXPERIENCE_REST);

	}

	@Test
	public void getAllTest() throws PortfolioException {
		final PortfolioResponse<List<ExperienceRest>> response = experienceController.getAll();

		assertEquals(response.getStatus(), SUCCES_STATUS);
		assertEquals(response.getCode(), SUCCES_CODE);
		assertEquals(response.getMessage(), OK);
		assertEquals(response.getData(), EXPERIENCE_REST_LIST);
	}

	@Test
	public void createExperienceTest() throws PortfolioException {
		final PortfolioResponse<String> response = experienceController.createExperience(EXPERIENCE_CREATE_REST);
		assertEquals(response.getStatus(), SUCCES_STATUS);
		assertEquals(response.getCode(), SUCCES_CODE);
		assertEquals(response.getMessage(), OK);
		assertEquals(response.getData(), EXPERIENCE_CREATE);
	}

	@Test
	public void updateExperienceTest() throws PortfolioException {
		final PortfolioResponse<String> response = experienceController.updateExperience(EXPERIENCE_ID,
				EXPERIENCE_CREATE_REST);
		assertEquals(response.getStatus(), SUCCES_STATUS);
		assertEquals(response.getCode(), SUCCES_CODE);
		assertEquals(response.getMessage(), OK);
		assertEquals(response.getData(), EXPERIENCE_UPTDATE);
	}

	@Test
	public void deleteByIdTest() throws PortfolioException {
		final PortfolioResponse<String> response = experienceController.deleteById(EXPERIENCE_ID);
		assertEquals(response.getStatus(), SUCCES_STATUS);
		assertEquals(response.getCode(), SUCCES_CODE);
		assertEquals(response.getMessage(), OK);
		assertEquals(response.getData(), EXPERIENCE_DELETED);
	}

	@Test
	public void deleteAllExperience() throws PortfolioException {
		final PortfolioResponse<String> response = experienceController.deleteAllExperiene();
		assertEquals(response.getStatus(), SUCCES_STATUS);
		assertEquals(response.getCode(), SUCCES_CODE);
		assertEquals(response.getMessage(), OK);
		assertEquals(response.getData(), ALL_EXPERIENCE_DELETED);
	}

}
