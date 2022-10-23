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
import com.portfolio.portfolioEMM.jsons.EducationCreateRest;
import com.portfolio.portfolioEMM.jsons.EducationRest;
import com.portfolio.portfolioEMM.repositories.EducationRepository;
import com.portfolio.portfolioEMM.repositories.PersonRepository;
import com.portfolio.portfolioEMM.security.entities.User;
import com.portfolio.portfolioEMM.services.impl.EducactionServiceImpl;

public class EducationServiceTest {
	private final static Long EDUCATION_ID = 1L;
	private final static String NAME = "Education Name";
	private final static String TITLE = "Education Description";
	private final static String DESCRITPION = "Education descritpion";
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
	private static final String EDUCATION_DELETED = "Education has been deleted!";
	public static final String ALL_EDUCATION_DELETED = "All Educations has been deleted!";
	public static final String NOT_FOUND = "EDUCATION NOT FOUND";

	EducationRest EDUCATION_REST = new EducationRest();
	EducationCreateRest EDUCATION_CREATE_REST = new EducationCreateRest();

	private static final Person PERSON = new Person();
	private static final Education EDUCATION = new Education();
	private static final User USER = new User();

	private static final List<Proyect> PROYECT_LIST = new ArrayList<>();
	private static final List<SocialMedias> SOCIAL_MEDIA_LIST = new ArrayList<>();
	private static final List<Experience> EXPERIENCE_LIST = new ArrayList<>();
	private static final List<Education> EDUCATION_LIST = new ArrayList<>();
	private static final List<HardSoft> HARD_SOFT_LIST = new ArrayList<>();

	private static final Optional<Education> OPTIONAL_EDUCATION = Optional.of(EDUCATION);
	private static final Optional<Person> OPTIONAL_PERSON = Optional.of(PERSON);

	private static final Optional<Education> OPTIONAL_EDUCATION_EMPTY = Optional.empty();
	private static final Optional<Person> OPTIONAL_PERSON_EMPTY = Optional.empty();

	@Mock
	EducationRepository educationRepository;

	@Mock
	PersonRepository personRepository;

	@InjectMocks
	EducactionServiceImpl educactionServiceImpl;

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

		EDUCATION_CREATE_REST.setName(NAME);
		EDUCATION_CREATE_REST.setTitle(TITLE);
		EDUCATION_CREATE_REST.setDescription(DESCRITPION);
		EDUCATION_CREATE_REST.setDateStart(DATE_START);
		EDUCATION_CREATE_REST.setDateEnd(DATE_END);
		EDUCATION_CREATE_REST.setImage(IMAGE);
		EDUCATION_CREATE_REST.setPersonId(PERSON_ID);

		EDUCATION_REST.setId(EDUCATION_ID);
		EDUCATION_REST.setName(NAME);
		EDUCATION_REST.setTitle(TITLE);
		EDUCATION_REST.setDescription(DESCRITPION);
		EDUCATION_REST.setDateStart(DATE_START);
		EDUCATION_REST.setDateEnd(DATE_END);
		EDUCATION_REST.setImage(IMAGE);
		EDUCATION_REST.setPersonId(PERSON_ID);

	}

	@Test
	public void createEducationTest() throws PortfolioException {
		Mockito.when(personRepository.findById(PERSON_ID)).thenReturn(OPTIONAL_PERSON);
		Mockito.when(educationRepository.findByPersonIdAndNameAndTitle(PERSON_ID, NAME, TITLE))
				.thenReturn(OPTIONAL_EDUCATION_EMPTY);
		Mockito.when(educationRepository.save(Mockito.any(Education.class))).thenReturn(new Education());
		educactionServiceImpl.createEducation(EDUCATION_CREATE_REST);
	}

	@Test(expected = PortfolioException.class)
	public void createEducationTestFindByIdErrorTest() throws PortfolioException {
		Mockito.when(personRepository.findById(PERSON_ID)).thenReturn(OPTIONAL_PERSON_EMPTY);
		educactionServiceImpl.createEducation(EDUCATION_CREATE_REST);
		fail();
	}

	@Test(expected = PortfolioException.class)
	public void createEducationFindByIdNameTitleErrorTest() throws PortfolioException {
		Mockito.when(personRepository.findById(PERSON_ID)).thenReturn(OPTIONAL_PERSON);
		Mockito.when(educationRepository.findByPersonIdAndNameAndTitle(PERSON_ID, NAME, TITLE))
				.thenReturn(OPTIONAL_EDUCATION);
		educactionServiceImpl.createEducation(EDUCATION_CREATE_REST);
		fail();
	}

	@Test(expected = PortfolioException.class)
	public void createEducationeServerErrorTest() throws PortfolioException {
		Mockito.when(personRepository.findById(PERSON_ID)).thenReturn(OPTIONAL_PERSON);
		Mockito.when(educationRepository.findByPersonIdAndNameAndTitle(PERSON_ID, NAME, TITLE))
				.thenReturn(OPTIONAL_EDUCATION_EMPTY);
		Mockito.doThrow(Exception.class).when(educationRepository).save(Mockito.any(Education.class));
		educactionServiceImpl.createEducation(EDUCATION_CREATE_REST);
		fail();
	}

	@Test
	public void getEducationByIdTest() throws PortfolioException {
		Mockito.when(educationRepository.findEducationById(EDUCATION_ID)).thenReturn(Optional.of(EDUCATION));
		educactionServiceImpl.getEducationById(EDUCATION_ID);
	}

	@Test(expected = PortfolioException.class)
	public void getEducationByIdErrorTest() throws PortfolioException {
		Mockito.when(educationRepository.findEducationById(EDUCATION_ID)).thenReturn(Optional.empty());
		educactionServiceImpl.getEducationById(EDUCATION_ID);
		fail();
	}

	@Test
	public void getAllEducationTest() throws PortfolioException {
		Mockito.when(educationRepository.findEducations()).thenReturn(Arrays.asList(EDUCATION));
		final List<EducationRest> response = educactionServiceImpl.getAllEducations();
		assertNotNull(response);
		assertFalse(response.isEmpty());
		assertEquals(response.size(), 1);
	}

	@Test
	public void updateEducationByIdTest() throws PortfolioException {
		Mockito.when(personRepository.findById(PERSON_ID)).thenReturn(OPTIONAL_PERSON);
		Mockito.when(educationRepository.findEducationById(EDUCATION_REST.getId())).thenReturn(OPTIONAL_EDUCATION);
		Mockito.when(educationRepository.save(Mockito.any(Education.class))).thenReturn(new Education());
		educactionServiceImpl.updateEducationById(EDUCATION_ID, EDUCATION_CREATE_REST);
	}

	@Test(expected = PortfolioException.class)
	public void updateEducationByIdPersonTestError() throws PortfolioException {
		Mockito.when(personRepository.findById(PERSON_ID)).thenReturn(OPTIONAL_PERSON_EMPTY);
		educactionServiceImpl.updateEducationById(EDUCATION_ID, EDUCATION_CREATE_REST);
		fail();
	}

	/*@Test(expected = PortfolioException.class)
	public void updateEducationByIdEducationErrorTest() throws PortfolioException {
		Mockito.when(personRepository.findById(PERSON_ID)).thenReturn(OPTIONAL_PERSON);
		Mockito.when(educationRepository.findEducationById(EDUCATION_ID)).thenReturn(OPTIONAL_EDUCATION_EMPTY);
		educactionServiceImpl.updateEducationById(EDUCATION_ID, EDUCATION_CREATE_REST);
		fail();
	}*/

	@Test(expected = PortfolioException.class)
	public void updateEducationByIdServerTestError() throws PortfolioException {
		Mockito.when(personRepository.findById(PERSON_ID)).thenReturn(OPTIONAL_PERSON);
		Mockito.when(educationRepository.findEducationById(EDUCATION_REST.getId())).thenReturn(OPTIONAL_EDUCATION);
		Mockito.doThrow(Exception.class).when(educationRepository).save(Mockito.any(Education.class));
		educactionServiceImpl.updateEducationById(EDUCATION_ID, EDUCATION_CREATE_REST);
		fail();
	}

	@Test
	public void deleteEducationeByIdTest() throws PortfolioException {

		Mockito.when(educationRepository.findEducationById(EDUCATION_REST.getId())).thenReturn(OPTIONAL_EDUCATION);
		educactionServiceImpl.deleteEducationById(EDUCATION_ID);
	}

	@Test(expected = PortfolioException.class)
	public void deleteEducationByIdTestError() throws PortfolioException {
		Mockito.when(educationRepository.findEducationById(EDUCATION_REST.getId()))
				.thenReturn(OPTIONAL_EDUCATION_EMPTY);
		Mockito.doThrow(Exception.class).when(educationRepository).deleteEducationById(EDUCATION_ID);
		educactionServiceImpl.deleteEducationById(EDUCATION_ID);
		fail();

	}

	@Test(expected = PortfolioException.class)
	public void deleteEducationByIdServerErrorTest() throws PortfolioException {
		Mockito.when(educationRepository.findEducationById(EDUCATION_REST.getId())).thenReturn(OPTIONAL_EDUCATION);
		Mockito.doThrow(Exception.class).when(educationRepository).deleteEducationById(EDUCATION_ID);
		final String response = educactionServiceImpl.deleteEducationById(EDUCATION_ID);
		assertEquals(response, EDUCATION_DELETED);
		fail();
	}

	@Test
	public void deleteAllEducationsTest() throws PortfolioException {
		Mockito.when(educationRepository.findEducations()).thenReturn(EDUCATION_LIST);
		educactionServiceImpl.deleteAllEducations();
	}

	@Test(expected = PortfolioException.class)
	public void deleteAllExperiencesTestError() throws PortfolioException {
		Mockito.doThrow(Exception.class).when(educationRepository).deleteAll();
		final String response = educactionServiceImpl.deleteAllEducations();
		assertEquals(response, ALL_EDUCATION_DELETED);
		fail();
	}

	@Test(expected = PortfolioException.class)
	public void getExperienceEntityTestError() throws PortfolioException {
		Mockito.when(educationRepository.findEducationById(EDUCATION_ID)).thenReturn(Optional.empty());
		educactionServiceImpl.getEducationById(EDUCATION_ID);
		fail();
	}

}
