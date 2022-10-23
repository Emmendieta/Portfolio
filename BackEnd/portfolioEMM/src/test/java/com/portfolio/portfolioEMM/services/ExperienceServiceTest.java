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
import com.portfolio.portfolioEMM.jsons.ExperienceCreateRest;
import com.portfolio.portfolioEMM.jsons.ExperienceRest;
import com.portfolio.portfolioEMM.repositories.ExperienceRepository;
import com.portfolio.portfolioEMM.repositories.PersonRepository;
import com.portfolio.portfolioEMM.security.entities.User;
import com.portfolio.portfolioEMM.services.impl.ExperienceServiceImpl;

public class ExperienceServiceTest {

	private final static Long EXPERIENCE_ID = 1L;
	private final static String NAME = "Experience Name";
	private final static String TITLE = "Experience Description";
	private final static String ACTIVITY = "Experience activity";
	private final static Date DATE_START = new Date();
	private final static Date DATE_END = new Date();
	private final static String IMAGE = "imagen";

	private final static Long PERSON_ID = 1L;
	private final static String LAST_NAME = "person Last Name";
	private final static Date AGE = new Date();
	private final static String ABOUT = "about";
	private final static String PROVINCE = "province";
	private final static String COUNTRY = "country";
	private final static String BANNER = "banner";
	private final static String EMAIL = "email@email.com";
	private static final String EXPERIENCE_DELETED = "Experience has been deleted!";
	public static final String ALL_EXPERIENCES_DELETED = "All Experiences has been deleted!";
	public static final String NOT_FOUND = "EXPERIENCE NOT FOUND";

	ExperienceRest EXPERIENCE_REST = new ExperienceRest();
	ExperienceCreateRest EXPERIENCE_CREATE_REST = new ExperienceCreateRest();

	private static final Person PERSON = new Person();
	private static final Experience EXPERIENCE = new Experience();
	private static final User USER = new User();

	private static final List<Proyect> PROYECT_LIST = new ArrayList<>();
	private static final List<SocialMedias> SOCIAL_MEDIA_LIST = new ArrayList<>();
	private static final List<Experience> EXPERIENCE_LIST = new ArrayList<>();
	private static final List<Education> EDUCATION_LIST = new ArrayList<>();
	private static final List<HardSoft> HARD_SOFT_LIST = new ArrayList<>();

	private static final Optional<Experience> OPTIONAL_EXPERIENCE = Optional.of(EXPERIENCE);
	private static final Optional<Person> OPTIONAL_PERSON = Optional.of(PERSON);

	private static final Optional<Experience> OPTIONAL_EXPERIENCE_EMPTY = Optional.empty();
	private static final Optional<Person> OPTIONAL_PERSON_EMPTY = Optional.empty();

	@Mock
	ExperienceRepository experienceRepository;

	@Mock
	PersonRepository personRepository;

	@InjectMocks
	ExperienceServiceImpl experienceServiceImpl;

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

		EXPERIENCE_CREATE_REST.setName(NAME);
		EXPERIENCE_CREATE_REST.setTitle(TITLE);
		EXPERIENCE_CREATE_REST.setActivity(ACTIVITY);
		EXPERIENCE_CREATE_REST.setImage(IMAGE);
		EXPERIENCE_CREATE_REST.setDateStart(DATE_START);
		EXPERIENCE_CREATE_REST.setDateEnd(DATE_END);
		EXPERIENCE_CREATE_REST.setPersonId(PERSON_ID);

		EXPERIENCE_REST.setId(EXPERIENCE_ID);
		EXPERIENCE_REST.setName(NAME);
		EXPERIENCE_REST.setTitle(TITLE);
		EXPERIENCE_REST.setActivity(ACTIVITY);
		EXPERIENCE_REST.setImage(IMAGE);
		EXPERIENCE_REST.setDateStart(DATE_START);
		EXPERIENCE_REST.setDateEnd(DATE_END);
		EXPERIENCE_REST.setPersonId(PERSON_ID);

	}

	@Test
	public void createExperienceTest() throws PortfolioException {
		Mockito.when(personRepository.findById(PERSON_ID)).thenReturn(OPTIONAL_PERSON);
		Mockito.when(experienceRepository.findByPersonIdAndNameAndTitle(PERSON_ID, NAME, TITLE))
				.thenReturn(OPTIONAL_EXPERIENCE_EMPTY);
		Mockito.when(experienceRepository.save(Mockito.any(Experience.class))).thenReturn(new Experience());
		experienceServiceImpl.createExperience(EXPERIENCE_CREATE_REST);
	}

	@Test(expected = PortfolioException.class)
	public void createExperienceFindByIdErrorTest() throws PortfolioException {
		Mockito.when(personRepository.findById(PERSON_ID)).thenReturn(OPTIONAL_PERSON_EMPTY);
		experienceServiceImpl.createExperience(EXPERIENCE_CREATE_REST);
		fail();
	}

	@Test(expected = PortfolioException.class)
	public void createExperienceFindByIdNameTitleErrorTest() throws PortfolioException {
		Mockito.when(personRepository.findById(PERSON_ID)).thenReturn(OPTIONAL_PERSON);
		Mockito.when(experienceRepository.findByPersonIdAndNameAndTitle(PERSON_ID, NAME, TITLE))
				.thenReturn(OPTIONAL_EXPERIENCE);
		experienceServiceImpl.createExperience(EXPERIENCE_CREATE_REST);
		fail();
	}

	@Test(expected = PortfolioException.class)
	public void createExperienceServerErrorTest() throws PortfolioException {
		Mockito.when(personRepository.findById(PERSON_ID)).thenReturn(OPTIONAL_PERSON);
		Mockito.when(experienceRepository.findByPersonIdAndNameAndTitle(PERSON_ID, NAME, TITLE))
				.thenReturn(OPTIONAL_EXPERIENCE_EMPTY);
		Mockito.doThrow(Exception.class).when(experienceRepository).save(Mockito.any(Experience.class));
		experienceServiceImpl.createExperience(EXPERIENCE_CREATE_REST);
		fail();
	}

	@Test
	public void findExperienceByIdTest() throws PortfolioException {
		Mockito.when(experienceRepository.findExperienceById(EXPERIENCE_ID)).thenReturn(Optional.of(EXPERIENCE));
		experienceServiceImpl.getExperienceById(EXPERIENCE_ID);
	}

	@Test(expected = PortfolioException.class)
	public void findExperienceByIdErrorTest() throws PortfolioException {
		Mockito.when(experienceRepository.findExperienceById(EXPERIENCE_ID)).thenReturn(Optional.empty());
		experienceServiceImpl.getExperienceById(EXPERIENCE_ID);
		fail();
	}

	@Test
	public void getAllExperiencesTest() throws PortfolioException {
		Mockito.when(experienceRepository.findAll()).thenReturn(Arrays.asList(EXPERIENCE));
		final List<ExperienceRest> response = experienceServiceImpl.getAllExperiences();
		assertNotNull(response);
		assertFalse(response.isEmpty());
		assertEquals(response.size(), 1);
	}

	@Test
	public void updateExpierenceByIdTest() throws PortfolioException {
		Mockito.when(personRepository.findById(PERSON_ID)).thenReturn(OPTIONAL_PERSON);
		Mockito.when(experienceRepository.findExperienceById(EXPERIENCE_REST.getId())).thenReturn(OPTIONAL_EXPERIENCE);
		Mockito.when(experienceRepository.save(Mockito.any(Experience.class))).thenReturn(new Experience());
		experienceServiceImpl.updateExpierenceById(EXPERIENCE_ID, EXPERIENCE_CREATE_REST);
	}

	@Test(expected = PortfolioException.class)
	public void updateExpierenceByIdPersonTestError() throws PortfolioException {
		Mockito.when(personRepository.findById(PERSON_ID)).thenReturn(OPTIONAL_PERSON_EMPTY);
		experienceServiceImpl.updateExpierenceById(EXPERIENCE_ID, EXPERIENCE_CREATE_REST);
		fail();
	}
	
	@Test(expected = PortfolioException.class)
	public void updateExpierenceByIdSExperienceErrorTest()throws PortfolioException {
		Mockito.when(personRepository.findById(PERSON_ID)).thenReturn(OPTIONAL_PERSON);
		Mockito.when(experienceRepository.findExperienceById(EXPERIENCE_REST.getId())).thenReturn(OPTIONAL_EXPERIENCE_EMPTY);
		experienceServiceImpl.updateExpierenceById(EXPERIENCE_ID, EXPERIENCE_CREATE_REST);
		fail();
	}

	@Test(expected = PortfolioException.class)
	public void updateExpierenceByIdServerTestError() throws PortfolioException {
		Mockito.when(personRepository.findById(PERSON_ID)).thenReturn(OPTIONAL_PERSON);
		Mockito.when(experienceRepository.findExperienceById(EXPERIENCE_REST.getId())).thenReturn(OPTIONAL_EXPERIENCE);
		Mockito.doThrow(Exception.class).when(experienceRepository).save(Mockito.any(Experience.class));
		experienceServiceImpl.updateExpierenceById(EXPERIENCE_ID, EXPERIENCE_CREATE_REST);
		fail();
	}

	@Test
	public void deleteExpierenceByIdTest() throws PortfolioException {

		Mockito.when(experienceRepository.findExperienceById(EXPERIENCE_REST.getId())).thenReturn(OPTIONAL_EXPERIENCE);
		experienceServiceImpl.deleteExperienceById(EXPERIENCE_ID);
	}

	@Test(expected = PortfolioException.class)
	public void deleteExpierenceByIdTestError() throws PortfolioException {
		Mockito.when(experienceRepository.findExperienceById(EXPERIENCE_REST.getId())).thenReturn(OPTIONAL_EXPERIENCE_EMPTY);
		Mockito.doThrow(Exception.class).when(experienceRepository).deleteExperienceById(EXPERIENCE_ID);
		experienceServiceImpl.deleteExperienceById(EXPERIENCE_ID);
		fail();

	}
	
	@Test(expected = PortfolioException.class)
	public void deleteExpierenceByIdServerErrorTest() throws PortfolioException {
		Mockito.when(experienceRepository.findExperienceById(EXPERIENCE_REST.getId())).thenReturn(OPTIONAL_EXPERIENCE);
		Mockito.doThrow(Exception.class).when(experienceRepository).deleteExperienceById(EXPERIENCE_ID);
		final String response = experienceServiceImpl.deleteExperienceById(EXPERIENCE_ID);
		assertEquals(response, EXPERIENCE_DELETED);
		fail();
	}
	

	@Test
	public void deleteAllExperiencesTest() throws PortfolioException {
		Mockito.when(experienceRepository.findAll()).thenReturn(EXPERIENCE_LIST);
		experienceServiceImpl.deleteAllExperiences();
	}

	@Test(expected = PortfolioException.class)
	public void deleteAllExperiencesTestError() throws PortfolioException {
		Mockito.doThrow(Exception.class).when(experienceRepository).deleteAll();
		final String response = experienceServiceImpl.deleteAllExperiences();
		assertEquals(response, ALL_EXPERIENCES_DELETED);
		fail();
	}

	@Test(expected = PortfolioException.class)
	public void getExperienceEntityTestError() throws PortfolioException {
		Mockito.when(experienceRepository.findExperienceById(EXPERIENCE_ID)).thenReturn(Optional.empty());
		experienceServiceImpl.getExperienceById(EXPERIENCE_ID);
		fail();
	}

}
