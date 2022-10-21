package com.portfolio.portfolioEMM.controllers;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.portfolio.portfolioEMM.exceptions.PortfolioException;
import com.portfolio.portfolioEMM.jsons.SocialMediasCreateRest;
import com.portfolio.portfolioEMM.jsons.SocialMediasRest;
import com.portfolio.portfolioEMM.responses.PortfolioResponse;
import com.portfolio.portfolioEMM.services.SocialMediasService;

public class SocialMediasControllerTest {

	private final static Long SOCIAL_ID = 1L;
	private final static String NAME = "Social Media Name";
	private final static String IMAGE = "imagen";
	private final static String URL = "wwww.url.com.ar";
	private final static Long PERSON_ID = 1L;

	private final static String SOCIAL_CREATE = "Social saved!";
	private final static String SOCIAL_DELETED = "Social deleted!";
	private final static String ALL_SOCIAL_DELETED = "All Socials has been deleted!";
	public static final String SOCIAL_UPTDATE = "Social update!";

	private final static SocialMediasRest SOCIAL_REST = new SocialMediasRest();
	private final static SocialMediasCreateRest SOCIAL_CREATE_REST = new SocialMediasCreateRest();
	private final static List<SocialMediasRest> SOCIAL_REST_LIST = new ArrayList<>();

	private final static String SUCCES_STATUS = "Succes";
	private final static String SUCCES_CODE = "200 OK";
	private final static String OK = "OK";

	@Mock
	SocialMediasService socialMediasService;

	@InjectMocks
	SocialMediasController socialMediasController;

	@Before
	public void init() throws PortfolioException {
		MockitoAnnotations.initMocks(this);

		SOCIAL_REST.setId(SOCIAL_ID);
		SOCIAL_REST.setName(NAME);
		SOCIAL_REST.setImage(IMAGE);
		SOCIAL_REST.setUrl(URL);
		SOCIAL_REST.setPersonId(PERSON_ID);

		Mockito.when(socialMediasService.findSocialMediaById(SOCIAL_ID)).thenReturn(SOCIAL_REST);
		Mockito.when(socialMediasService.findAllSocialMedias()).thenReturn(SOCIAL_REST_LIST);
		Mockito.when(socialMediasService.createSocialMedia(SOCIAL_CREATE_REST)).thenReturn(SOCIAL_CREATE);
		Mockito.when(socialMediasService.updateSocialMediaById(SOCIAL_ID, SOCIAL_CREATE_REST))
				.thenReturn(SOCIAL_UPTDATE);
		Mockito.when(socialMediasService.deleteSocialMediaById(SOCIAL_ID)).thenReturn(SOCIAL_DELETED);
		Mockito.when(socialMediasService.deleteAllSocialMedias()).thenReturn(ALL_SOCIAL_DELETED);

	}

	@Test
	public void getByIdTest() throws PortfolioException {
		final PortfolioResponse<SocialMediasRest> response = socialMediasController.findSocialMediaById(SOCIAL_ID);

		assertEquals(response.getStatus(), SUCCES_STATUS);
		assertEquals(response.getCode(), SUCCES_CODE);
		assertEquals(response.getMessage(), OK);
		assertEquals(response.getData(), SOCIAL_REST);

	}

	@Test
	public void getAllTest() throws PortfolioException {
		final PortfolioResponse<List<SocialMediasRest>> response = socialMediasController.findAllSocialMedias();

		assertEquals(response.getStatus(), SUCCES_STATUS);
		assertEquals(response.getCode(), SUCCES_CODE);
		assertEquals(response.getMessage(), OK);
		assertEquals(response.getData(), SOCIAL_REST_LIST);
	}

	@Test
	public void createExperienceTest() throws PortfolioException {
		final PortfolioResponse<String> response = socialMediasController.createSocialMedia(SOCIAL_CREATE_REST);
		assertEquals(response.getStatus(), SUCCES_STATUS);
		assertEquals(response.getCode(), SUCCES_CODE);
		assertEquals(response.getMessage(), OK);
		assertEquals(response.getData(), SOCIAL_CREATE);
	}

	@Test
	public void updateExperienceTest() throws PortfolioException {
		final PortfolioResponse<String> response = socialMediasController.updateSocialMedia(SOCIAL_ID,
				SOCIAL_CREATE_REST);
		assertEquals(response.getStatus(), SUCCES_STATUS);
		assertEquals(response.getCode(), SUCCES_CODE);
		assertEquals(response.getMessage(), OK);
		assertEquals(response.getData(), SOCIAL_UPTDATE);
	}

	@Test
	public void deleteByIdTest() throws PortfolioException {
		final PortfolioResponse<String> response = socialMediasController.deleteSocialMediaById(SOCIAL_ID);
		assertEquals(response.getStatus(), SUCCES_STATUS);
		assertEquals(response.getCode(), SUCCES_CODE);
		assertEquals(response.getMessage(), OK);
		assertEquals(response.getData(), SOCIAL_DELETED);
	}

	@Test
	public void deleteAllExperience() throws PortfolioException {
		final PortfolioResponse<String> response = socialMediasController.deleteAllSocialMedias();
		assertEquals(response.getStatus(), SUCCES_STATUS);
		assertEquals(response.getCode(), SUCCES_CODE);
		assertEquals(response.getMessage(), OK);
		assertEquals(response.getData(), ALL_SOCIAL_DELETED);
	}

}
