package com.portfolio.portfolioEMM.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.portfolio.portfolioEMM.entities.Education;
import com.portfolio.portfolioEMM.entities.Experience;
import com.portfolio.portfolioEMM.entities.HardSoft;
import com.portfolio.portfolioEMM.entities.Person;
import com.portfolio.portfolioEMM.entities.Proyect;
import com.portfolio.portfolioEMM.entities.SocialMedias;
import com.portfolio.portfolioEMM.exceptions.PortfolioException;
import com.portfolio.portfolioEMM.jsons.HardSoftCreateRest;
import com.portfolio.portfolioEMM.jsons.HardSoftRest;
import com.portfolio.portfolioEMM.repositories.HardSoftRepository;
import com.portfolio.portfolioEMM.repositories.PersonRepository;
import com.portfolio.portfolioEMM.security.entities.User;
import com.portfolio.portfolioEMM.services.impl.HardSoftServiceImpl;

public class HardSoftServiceTest {

	private final static Long SKILL_ID = 1L;
	private final static int PERCENT = 50;
	private final static String IMAGE = "imagen";
	private final static String NAME = "Person Name";
	private final static String LAST_NAME = "Person Last Name";
	private final static Date AGE = new Date();
	private final static String TITLE = "title";
	private final static String ABOUT = "about";
	private final static String PROVINCE = "province";
	private final static String COUNTRY = "country";
	private final static String BANNER = "banner";
	private final static String EMAIL = "email@email.com";
	private final static Long PERSON_ID = 1L;
	private static final String SKILL_DELETE = "Skill has been deleted!";
	public static final String ALL_SKILLS_DELETED = "Skills has been deleted!";
	public static final String SKILL_NO_DELETE = "Error deleting Skill";
	public static final String SKILL_UPDATE = "Skill has been update";
	public static final String INTERNAL_ERROR = "INTERNAL SERVER_ERROR";

	HardSoftCreateRest SKILL_CREAT_REST = new HardSoftCreateRest();
	HardSoftRest SKILL_REST = new HardSoftRest();

	private static final Person PERSON = new Person();
	private static final User USER = new User();
	private static final HardSoft SKILL = new HardSoft();

	private static final List<SocialMedias> SOCIAL_MEDIA_LIST = new ArrayList<>();
	private static final List<Experience> EXPERIENCE_LIST = new ArrayList<>();
	private static final List<Education> EDUCATION_LIST = new ArrayList<>();
	private static final List<HardSoft> HARD_SOFT_LIST = new ArrayList<>();
	private static final List<Proyect> PROYECT_LIST = new ArrayList<>();

	private static final Optional<Person> OPTIONAL_PERSON = Optional.of(PERSON);
	private static final Optional<Person> OPTIONAL_PERSON_EMPTY = Optional.empty();
	private static final Optional<HardSoft> OPTIONAL_SKILL = Optional.of(SKILL);
	private static final Optional<HardSoft> OPTIONAL_SKILL_EMPTY = Optional.empty();

	@Mock
	PersonRepository personRepository;

	@Mock
	HardSoftRepository hardSoftRepository;

	@InjectMocks
	HardSoftServiceImpl hardSoftServiceImpl;

	@Before
	public void init() throws PortfolioException {
		MockitoAnnotations.initMocks(this);

		PERSON.setId(PERSON_ID);
		PERSON.setName(NAME);
		PERSON.setLastName(LAST_NAME);
		PERSON.setAge(AGE);
		PERSON.setTitle(TITLE);
		PERSON.setAbout(ABOUT);
		PERSON.setProvince(PROVINCE);
		PERSON.setCountry(COUNTRY);
		PERSON.setImage(IMAGE);
		PERSON.setBanner(BANNER);
		PERSON.setEmail(EMAIL);
		PERSON.setExperiences(EXPERIENCE_LIST);
		PERSON.setEducations(EDUCATION_LIST);
		PERSON.setHardAndSofts(HARD_SOFT_LIST);
		PERSON.setProyects(PROYECT_LIST);
		PERSON.setSocialMedias(SOCIAL_MEDIA_LIST);
		PERSON.setUser(USER);

		SKILL_CREAT_REST.setName(NAME);
		SKILL_CREAT_REST.setImage(IMAGE);
		SKILL_CREAT_REST.setPercent(PERCENT);
		SKILL_CREAT_REST.setPersonId(PERSON_ID);

		SKILL_REST.setId(SKILL_ID);
		SKILL_REST.setName(NAME);
		SKILL_REST.setPercent(PERCENT);
		SKILL_REST.setImage(IMAGE);
		SKILL_REST.setPersonId(PERSON_ID);

	}

	@Test
	public void createHardSoftTest() throws PortfolioException {
		Mockito.when(personRepository.findById(PERSON_ID)).thenReturn(OPTIONAL_PERSON);
		Mockito.when(hardSoftRepository.findByPersonIdAndName(PERSON_ID, SKILL_REST.getName()))
				.thenReturn(OPTIONAL_SKILL_EMPTY);
		hardSoftServiceImpl.createHardSoft(SKILL_CREAT_REST);
	}

	@Test(expected = PortfolioException.class)
	public void createHardSoftPersonErrorTest() throws PortfolioException {
		Mockito.when(personRepository.findById(PERSON_ID)).thenReturn(OPTIONAL_PERSON_EMPTY);
		hardSoftServiceImpl.createHardSoft(SKILL_CREAT_REST);
		fail();
	}

	@Test(expected = PortfolioException.class)
	public void createHardSoftNotFoundTest() throws PortfolioException {
		Mockito.when(personRepository.findById(PERSON_ID)).thenReturn(OPTIONAL_PERSON);
		Mockito.when(hardSoftRepository.findByPersonIdAndName(PERSON_ID, SKILL_REST.getName()))
				.thenReturn(OPTIONAL_SKILL);
		hardSoftServiceImpl.createHardSoft(SKILL_CREAT_REST);
		fail();
	}

	@Test(expected = PortfolioException.class)
	public void createHardSoftServerErrorTest() throws PortfolioException {
		Mockito.when(personRepository.findById(PERSON_ID)).thenReturn(OPTIONAL_PERSON);
		Mockito.when(hardSoftRepository.findByPersonIdAndName(PERSON_ID, SKILL_REST.getName()))
				.thenReturn(OPTIONAL_SKILL_EMPTY);
		Mockito.doThrow(Exception.class).when(hardSoftRepository).save(Mockito.any(HardSoft.class));
		hardSoftServiceImpl.createHardSoft(SKILL_CREAT_REST);
		fail();
	}

	@Test
	public void getAllHardSoftTest() throws PortfolioException {
		Mockito.when(hardSoftRepository.findHards()).thenReturn(Arrays.asList(SKILL));
		final List<HardSoftRest> response = hardSoftServiceImpl.getAllHardSoft();
		assertNotNull(response);
		assertFalse(response.isEmpty());
		assertEquals(response.size(), 1);
	}

	@Test
	public void getHSEntityTest() throws PortfolioException {
		Mockito.when(hardSoftRepository.findHsById(SKILL_ID)).thenReturn(OPTIONAL_SKILL);
		hardSoftServiceImpl.getHardSoftById(SKILL_ID);
	}

	@Test(expected = PortfolioException.class)
	public void getHSEntityErrorTest() throws PortfolioException {
		Mockito.when(hardSoftRepository.findHsById(SKILL_ID)).thenReturn(OPTIONAL_SKILL_EMPTY);
		hardSoftServiceImpl.getHardSoftById(SKILL_ID);
		fail();
	}

	@Test
	public void getHSEntityNameTest() throws PortfolioException {
		Mockito.when(hardSoftRepository.findHYSByName(NAME)).thenReturn(OPTIONAL_SKILL);
		hardSoftServiceImpl.getHardSoftByName(NAME);
	}

	@Test(expected = PortfolioException.class)
	public void getHSEntityNameErrorTest() throws PortfolioException {
		Mockito.when(hardSoftRepository.findHYSByName(NAME)).thenReturn(OPTIONAL_SKILL_EMPTY);
		hardSoftServiceImpl.getHardSoftByName(NAME);
		fail();
	}

	@Test
	public void updateHardSoftByIdTest() throws PortfolioException {
		Mockito.when(personRepository.findById(PERSON_ID)).thenReturn(OPTIONAL_PERSON);
		Mockito.when(hardSoftRepository.findHsById(SKILL_ID)).thenReturn(OPTIONAL_SKILL);
		hardSoftServiceImpl.updateHardSoftById(SKILL_ID, SKILL_CREAT_REST);
	}

	@Test(expected = PortfolioException.class)
	public void updateHardSoftByIdPersonErrorTest() throws PortfolioException {
		Mockito.when(personRepository.findById(PERSON_ID)).thenReturn(OPTIONAL_PERSON_EMPTY);
		hardSoftServiceImpl.updateHardSoftById(SKILL_ID, SKILL_CREAT_REST);
		fail();
	}

	@Test(expected = PortfolioException.class)
	public void updateHardSoftByIdSkillErrorTest() throws PortfolioException {
		Mockito.when(personRepository.findById(PERSON_ID)).thenReturn(OPTIONAL_PERSON);
		Mockito.when(hardSoftRepository.findHsById(SKILL_ID)).thenReturn(OPTIONAL_SKILL_EMPTY);
		hardSoftServiceImpl.updateHardSoftById(SKILL_ID, SKILL_CREAT_REST);
		fail();
	}

	@Test(expected = PortfolioException.class)
	public void updateHardSoftByIdServerErrorTest() throws PortfolioException {
		Mockito.when(personRepository.findById(PERSON_ID)).thenReturn(OPTIONAL_PERSON);
		Mockito.when(hardSoftRepository.findHsById(SKILL_ID)).thenReturn(OPTIONAL_SKILL);
		Mockito.doThrow(Exception.class).when(hardSoftRepository).save(Mockito.any(HardSoft.class));
		hardSoftServiceImpl.updateHardSoftById(SKILL_ID, SKILL_CREAT_REST);
		fail();
	}

	@Test
	public void deleteHardSoftByIdTest() throws PortfolioException {
		Mockito.when(hardSoftRepository.findHsById(PERSON_ID)).thenReturn(OPTIONAL_SKILL);
		hardSoftServiceImpl.deleteHardSoftById(SKILL_ID);
	}

	@Test(expected = PortfolioException.class)
	public void deleteHardSoftByIdErrorTest() throws PortfolioException {
		Mockito.when(hardSoftRepository.findHsById(PERSON_ID)).thenReturn(OPTIONAL_SKILL_EMPTY);
		hardSoftServiceImpl.deleteHardSoftById(SKILL_ID);
		fail();
	}

	@Test(expected = PortfolioException.class)
	public void deleteHardSoftByIdServerErrorTest() throws PortfolioException {
		Mockito.when(hardSoftRepository.findHsById(PERSON_ID)).thenReturn(OPTIONAL_SKILL);
		Mockito.doThrow(Exception.class).when(hardSoftRepository).deleteHardById(SKILL_ID);
		final String response = hardSoftServiceImpl.deleteHardSoftById(SKILL_ID);
		assertEquals(response, SKILL_DELETE);
		fail();
	}

	@Test
	public void deleteAllHardSoftTest() throws PortfolioException {
		Mockito.when(hardSoftRepository.findHards()).thenReturn(HARD_SOFT_LIST);
		hardSoftServiceImpl.deleteAllHardSoft();
	}

	@Test(expected = PortfolioException.class)
	public void deleteAllHardSoftServerErrorTest() throws PortfolioException {
		Mockito.when(hardSoftRepository.findHards()).thenReturn(HARD_SOFT_LIST);
		Mockito.doThrow(Exception.class).when(hardSoftRepository).deleteAll();
		hardSoftServiceImpl.deleteAllHardSoft();
		fail();
	}
}
