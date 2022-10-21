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
import com.portfolio.portfolioEMM.jsons.ProyectCreateRest;
import com.portfolio.portfolioEMM.jsons.ProyectRest;
import com.portfolio.portfolioEMM.responses.PortfolioResponse;
import com.portfolio.portfolioEMM.services.ProyectService;

public class ProyectControllerTest {

	private final static Long PROYECT_ID = 1L;
	private final static String NAME = "Proyect Name";
	private final static String DESCRIPTION = "Description";
	private final static String LINK = "wwww.link.com.ar";
	private final static String IMAGE = "imagen";
	private final static Date DATE_START = new Date();
	private final static Date DATE_END = new Date();
	private final static Long PERSON_ID = 1L;

	private final static String PROYECT_CREATE = "Proyect saved!";
	private final static String PROYECT_DELETED = "Proyect deleted!";
	private final static String ALL_PROYECT_DELETED = "All Proyects has been deleted!";
	public static final String PROYECT_UPTDATE = "Proyect update!";

	private final static ProyectRest PROYECT_REST = new ProyectRest();
	private final static ProyectCreateRest PROYECT_CREATE_REST = new ProyectCreateRest();
	private final static List<ProyectRest> PROYECT_REST_LIST = new ArrayList<>();

	private final static String SUCCES_STATUS = "Succes";
	private final static String SUCCES_CODE = "200 OK";
	private final static String OK = "OK";

	@Mock
	ProyectService proyectService;

	@InjectMocks
	ProyectController proyectController;

	@Before
	public void init() throws PortfolioException {
		MockitoAnnotations.initMocks(this);

		PROYECT_REST.setId(PROYECT_ID);
		PROYECT_REST.setName(NAME);
		PROYECT_REST.setDescription(DESCRIPTION);
		PROYECT_REST.setLink(LINK);
		PROYECT_REST.setImage(IMAGE);
		PROYECT_REST.setDateStart(DATE_START);
		PROYECT_REST.setDateEnd(DATE_END);
		PROYECT_REST.setPersonId(PERSON_ID);

		Mockito.when(proyectService.findProyectById(PROYECT_ID)).thenReturn(PROYECT_REST);
		Mockito.when(proyectService.findAllProyects()).thenReturn(PROYECT_REST_LIST);
		Mockito.when(proyectService.createProyect(PROYECT_CREATE_REST)).thenReturn(PROYECT_CREATE);
		Mockito.when(proyectService.updateProyectById(PROYECT_ID, PROYECT_CREATE_REST)).thenReturn(PROYECT_UPTDATE);
		Mockito.when(proyectService.deleteProyectById(PROYECT_ID)).thenReturn(PROYECT_DELETED);
		Mockito.when(proyectService.deleteAllProyects()).thenReturn(ALL_PROYECT_DELETED);

	}

	@Test
	public void getByIdTest() throws PortfolioException {
		final PortfolioResponse<ProyectRest> response = proyectController.getProyectById(PROYECT_ID);

		assertEquals(response.getStatus(), SUCCES_STATUS);
		assertEquals(response.getCode(), SUCCES_CODE);
		assertEquals(response.getMessage(), OK);
		assertEquals(response.getData(), PROYECT_REST);

	}

	@Test
	public void getAllTest() throws PortfolioException {
		final PortfolioResponse<List<ProyectRest>> response = proyectController.getAllProyects();

		assertEquals(response.getStatus(), SUCCES_STATUS);
		assertEquals(response.getCode(), SUCCES_CODE);
		assertEquals(response.getMessage(), OK);
		assertEquals(response.getData(), PROYECT_REST_LIST);
	}

	@Test
	public void createExperienceTest() throws PortfolioException {
		final PortfolioResponse<String> response = proyectController.createProyect(PROYECT_CREATE_REST);
		assertEquals(response.getStatus(), SUCCES_STATUS);
		assertEquals(response.getCode(), SUCCES_CODE);
		assertEquals(response.getMessage(), OK);
		assertEquals(response.getData(), PROYECT_CREATE);
	}

	@Test
	public void updateExperienceTest() throws PortfolioException {
		final PortfolioResponse<String> response = proyectController.updateProyect(PERSON_ID, PROYECT_CREATE_REST);
		assertEquals(response.getStatus(), SUCCES_STATUS);
		assertEquals(response.getCode(), SUCCES_CODE);
		assertEquals(response.getMessage(), OK);
		assertEquals(response.getData(), PROYECT_UPTDATE);
	}

	@Test
	public void deleteByIdTest() throws PortfolioException {
		final PortfolioResponse<String> response = proyectController.deleteProyectById(PROYECT_ID);
		assertEquals(response.getStatus(), SUCCES_STATUS);
		assertEquals(response.getCode(), SUCCES_CODE);
		assertEquals(response.getMessage(), OK);
		assertEquals(response.getData(), PROYECT_DELETED);
	}

	@Test
	public void deleteAllExperience() throws PortfolioException {
		final PortfolioResponse<String> response = proyectController.deleteAllProyects();
		assertEquals(response.getStatus(), SUCCES_STATUS);
		assertEquals(response.getCode(), SUCCES_CODE);
		assertEquals(response.getMessage(), OK);
		assertEquals(response.getData(), ALL_PROYECT_DELETED);
	}

}
