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
import com.portfolio.portfolioEMM.jsons.EducationCreateRest;
import com.portfolio.portfolioEMM.jsons.EducationRest;
import com.portfolio.portfolioEMM.responses.PortfolioResponse;
import com.portfolio.portfolioEMM.services.EducationService;

public class EducationControllerTest {

	private final static Long EDUCATION_ID = 1L;
	private final static String NAME = "education";
	private final static String TITLE = "title";
	private final static String DESCRIPTION = "description";
	private final static Date DATE_START = new Date();
	private final static Date DATE_END = new Date();
	private final static String IMAGE = "image";
	private final static Long PERSON_ID = 1L;
	private final static String EDUCATION_CREATE = "Education saved!";
	private final static String EDUCATION_DELETED = "Education deleted!";
	private final static String ALL_EDUCACION_DELETED = "All Education has been deleted!";
	public static final String EDUCACION_UPTDATE = "Educaction update!";

	private final static EducationRest EDUCATION_REST = new EducationRest();
	private final static EducationCreateRest EDUCATION_CREATE_REST = new EducationCreateRest();
	private final static List<EducationRest> EDUCATION_REST_LIST = new ArrayList<>();

	private final static String SUCCES_STATUS = "Succes";
	private final static String SUCCES_CODE = "200 OK";
	private final static String OK = "OK";

	@Mock
	EducationService educationService;

	@InjectMocks
	EducationController educationController;

	@Before
	public void init() throws PortfolioException {
		MockitoAnnotations.initMocks(this);

		EDUCATION_REST.setId(EDUCATION_ID);
		EDUCATION_REST.setName(NAME);
		EDUCATION_REST.setTitle(TITLE);
		EDUCATION_REST.setDescription(DESCRIPTION);
		EDUCATION_REST.setDateStart(DATE_START);
		EDUCATION_REST.setDateEnd(DATE_END);
		EDUCATION_REST.setImage(IMAGE);
		EDUCATION_REST.setPersonId(PERSON_ID);

		Mockito.when(educationService.getEducationById(EDUCATION_ID)).thenReturn(EDUCATION_REST);
		Mockito.when(educationService.getAllEducations()).thenReturn(EDUCATION_REST_LIST);
		Mockito.when(educationService.createEducation(EDUCATION_CREATE_REST)).thenReturn(EDUCATION_CREATE);
		Mockito.when(educationService.updateEducationById(EDUCATION_ID, EDUCATION_CREATE_REST))
				.thenReturn(EDUCACION_UPTDATE);
		Mockito.when(educationService.deleteEducationById(EDUCATION_ID)).thenReturn(EDUCATION_DELETED);
		Mockito.when(educationService.deleteAllEducations()).thenReturn(ALL_EDUCACION_DELETED);

	}

	@Test
	public void getByIdTest() throws PortfolioException {
		final PortfolioResponse<EducationRest> response = educationController.getById(EDUCATION_ID);

		assertEquals(response.getStatus(), SUCCES_STATUS);
		assertEquals(response.getCode(), SUCCES_CODE);
		assertEquals(response.getMessage(), OK);
		assertEquals(response.getData(), EDUCATION_REST);

	}

	@Test
	public void getAllTest() throws PortfolioException {
		final PortfolioResponse<List<EducationRest>> response = educationController.getAll();

		assertEquals(response.getStatus(), SUCCES_STATUS);
		assertEquals(response.getCode(), SUCCES_CODE);
		assertEquals(response.getMessage(), OK);
		assertEquals(response.getData(), EDUCATION_REST_LIST);
	}

	@Test
	public void createEducationTest() throws PortfolioException {
		final PortfolioResponse<String> response = educationController.createEducation(EDUCATION_CREATE_REST);
		assertEquals(response.getStatus(), SUCCES_STATUS);
		assertEquals(response.getCode(), SUCCES_CODE);
		assertEquals(response.getMessage(), OK);
		assertEquals(response.getData(), EDUCATION_CREATE);
	}

	@Test
	public void updateEducationTest() throws PortfolioException {
		final PortfolioResponse<String> response = educationController.updateEducation(EDUCATION_ID,
				EDUCATION_CREATE_REST);
		assertEquals(response.getStatus(), SUCCES_STATUS);
		assertEquals(response.getCode(), SUCCES_CODE);
		assertEquals(response.getMessage(), OK);
		assertEquals(response.getData(), EDUCACION_UPTDATE);
	}

	@Test
	public void deleteByIdTest() throws PortfolioException {
		final PortfolioResponse<String> response = educationController.deleteById(EDUCATION_ID);
		assertEquals(response.getStatus(), SUCCES_STATUS);
		assertEquals(response.getCode(), SUCCES_CODE);
		assertEquals(response.getMessage(), OK);
		assertEquals(response.getData(), EDUCATION_DELETED);
	}

	@Test
	public void deleteAllEducation() throws PortfolioException {
		final PortfolioResponse<String> response = educationController.deleteAll();
		assertEquals(response.getStatus(), SUCCES_STATUS);
		assertEquals(response.getCode(), SUCCES_CODE);
		assertEquals(response.getMessage(), OK);
		assertEquals(response.getData(), ALL_EDUCACION_DELETED);
	}

}
