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
import com.portfolio.portfolioEMM.jsons.HardSoftCreateRest;
import com.portfolio.portfolioEMM.jsons.HardSoftRest;
import com.portfolio.portfolioEMM.responses.PortfolioResponse;
import com.portfolio.portfolioEMM.services.HardSoftService;

public class HardSoftControllerTest {

	private final static Long SKILL_ID = 1L;
	private final static String NAME = "skill";
	private final static int PERCENT = 90;
	private final static String IMAGE = "imagen";
	private final static Long PERSON_ID = 1L;
	private final static String SKILL_CREATE = "Skill saved!";
	private final static String SKILL_DELETED = "Skill deleted!";
	private final static String ALL_SKILLS_DELETED = "All Skill has been deleted!";
	public static final String SKILL_UPTDATE = "Skill update!";

	private final static HardSoftRest SKILL_REST = new HardSoftRest();
	private final static HardSoftCreateRest SKILL_CREATE_REST = new HardSoftCreateRest();
	private final static List<HardSoftRest> SKILL_REST_LIST = new ArrayList<>();

	private final static String SUCCES_STATUS = "Succes";
	private final static String SUCCES_CODE = "200 OK";
	private final static String OK = "OK";

	@Mock
	HardSoftService hardSoftService;

	@InjectMocks
	HardSoftController hardSoftController;

	@Before
	public void init() throws PortfolioException {
		MockitoAnnotations.initMocks(this);

		SKILL_REST.setId(SKILL_ID);
		SKILL_REST.setName(NAME);
		SKILL_REST.setPercent(PERCENT);
		SKILL_REST.setImage(IMAGE);
		SKILL_REST.setPersonId(PERSON_ID);

		Mockito.when(hardSoftService.getHardSoftById(SKILL_ID)).thenReturn(SKILL_REST);
		Mockito.when(hardSoftService.getHardSoftByName(NAME)).thenReturn(SKILL_REST);
		Mockito.when(hardSoftService.getAllHardSoft()).thenReturn(SKILL_REST_LIST);
		Mockito.when(hardSoftService.createHardSoft(SKILL_CREATE_REST)).thenReturn(SKILL_CREATE);
		Mockito.when(hardSoftService.updateHardSoftById(SKILL_ID, SKILL_CREATE_REST)).thenReturn(SKILL_UPTDATE);
		Mockito.when(hardSoftService.deleteHardSoftById(SKILL_ID)).thenReturn(SKILL_DELETED);
		Mockito.when(hardSoftService.deleteAllHardSoft()).thenReturn(ALL_SKILLS_DELETED);

	}

	@Test
	public void getByIdTest() throws PortfolioException {
		final PortfolioResponse<HardSoftRest> response = hardSoftController.getHardAndSoftById(SKILL_ID);

		assertEquals(response.getStatus(), SUCCES_STATUS);
		assertEquals(response.getCode(), SUCCES_CODE);
		assertEquals(response.getMessage(), OK);
		assertEquals(response.getData(), SKILL_REST);

	}

	@Test
	public void getByNameTest() throws PortfolioException {
		final PortfolioResponse<HardSoftRest> response = hardSoftController.getHardAndSoftByName(NAME);

		assertEquals(response.getStatus(), SUCCES_STATUS);
		assertEquals(response.getCode(), SUCCES_CODE);
		assertEquals(response.getMessage(), OK);
		assertEquals(response.getData(), SKILL_REST);

	}

	@Test
	public void getAllTest() throws PortfolioException {
		final PortfolioResponse<List<HardSoftRest>> response = hardSoftController.getAllHardAndSoft();

		assertEquals(response.getStatus(), SUCCES_STATUS);
		assertEquals(response.getCode(), SUCCES_CODE);
		assertEquals(response.getMessage(), OK);
		assertEquals(response.getData(), SKILL_REST_LIST);
	}

	@Test
	public void createExperienceTest() throws PortfolioException {
		final PortfolioResponse<String> response = hardSoftController.createHardSoftSkill(SKILL_CREATE_REST);
		assertEquals(response.getStatus(), SUCCES_STATUS);
		assertEquals(response.getCode(), SUCCES_CODE);
		assertEquals(response.getMessage(), OK);
		assertEquals(response.getData(), SKILL_CREATE);
	}

	@Test
	public void updateExperienceTest() throws PortfolioException {
		final PortfolioResponse<String> response = hardSoftController.updateHardAndSoftSkillById(SKILL_ID,
				SKILL_CREATE_REST);
		assertEquals(response.getStatus(), SUCCES_STATUS);
		assertEquals(response.getCode(), SUCCES_CODE);
		assertEquals(response.getMessage(), OK);
		assertEquals(response.getData(), SKILL_UPTDATE);
	}

	@Test
	public void deleteByIdTest() throws PortfolioException {
		final PortfolioResponse<String> response = hardSoftController.deleteById(SKILL_ID);
		assertEquals(response.getStatus(), SUCCES_STATUS);
		assertEquals(response.getCode(), SUCCES_CODE);
		assertEquals(response.getMessage(), OK);
		assertEquals(response.getData(), SKILL_DELETED);
	}

	@Test
	public void deleteAllExperience() throws PortfolioException {
		final PortfolioResponse<String> response = hardSoftController.deleteAllHardSoft();
		assertEquals(response.getStatus(), SUCCES_STATUS);
		assertEquals(response.getCode(), SUCCES_CODE);
		assertEquals(response.getMessage(), OK);
		assertEquals(response.getData(), ALL_SKILLS_DELETED);
	}

}
