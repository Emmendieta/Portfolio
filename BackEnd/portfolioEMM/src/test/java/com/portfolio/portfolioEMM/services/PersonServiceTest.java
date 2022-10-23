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
import com.portfolio.portfolioEMM.jsons.PersonCreateRest;
import com.portfolio.portfolioEMM.jsons.PersonRest;
import com.portfolio.portfolioEMM.repositories.PersonRepository;
import com.portfolio.portfolioEMM.security.entities.User;
import com.portfolio.portfolioEMM.services.impl.PersonServiceImpl;

public class PersonServiceTest {

	private final static Long PERSON_ID = 1L;
	private final static String NAME = "Person Name";
	private final static String IMAGE = "imagen";
	private final static String LAST_NAME = "Person Last Name";
	private final static Date AGE = new Date();
	private final static String TITLE = "title";
	private final static String ABOUT = "about";
	private final static String PROVINCE = "province";
	private final static String COUNTRY = "country";
	private final static String BANNER = "banner";
	private final static String EMAIL = "email@email.com";
	private static final String PERSON_DELETED = "Person has been deleted!";
	public static final String ALL_PEOPLE_DELETED = "People has been deleted!";
	public static final String PERSON_NO_DELETE = "Error deleting Person";
	public static final String PERSON_UPDATE = "Person has been update";

	PersonCreateRest PERSON_CREAT_REST = new PersonCreateRest();
	PersonRest PERSON_REST = new PersonRest();

	private static final Person PERSON = new Person();
	private static final User USER = new User();

	private static final List<SocialMedias> SOCIAL_MEDIA_LIST = new ArrayList<>();
	private static final List<Experience> EXPERIENCE_LIST = new ArrayList<>();
	private static final List<Education> EDUCATION_LIST = new ArrayList<>();
	private static final List<HardSoft> HARD_SOFT_LIST = new ArrayList<>();
	private static final List<Proyect> PROYECT_LIST = new ArrayList<>();
	private static final List<Person> PEOPLE_LIST = new ArrayList<>();

	private static final Optional<Person> OPTIONAL_PERSON = Optional.of(PERSON);

	private static final Optional<Person> OPTIONAL_PERSON_EMPTY = Optional.empty();

	@Mock
	PersonRepository personRepository;

	@InjectMocks
	PersonServiceImpl personServiceImpl;

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

	}

	@Test
	public void createPersonTest() throws PortfolioException {
		personServiceImpl.createPerson(PERSON_CREAT_REST);
	}

	@Test(expected = PortfolioException.class)
	public void createPersonTestError() throws PortfolioException {
		Mockito.doThrow(Exception.class).when(personRepository).save(Mockito.any(Person.class));
		personServiceImpl.createPerson(PERSON_CREAT_REST);
		fail();
	}

	@Test
	public void getPersonEntityTest() throws PortfolioException {
		Mockito.when(personRepository.findById(PERSON_ID)).thenReturn(OPTIONAL_PERSON);
	}

	@Test(expected = PortfolioException.class)
	public void getPersonEntityTestError() throws PortfolioException {
		Mockito.when(personRepository.findById(PERSON_ID)).thenReturn(OPTIONAL_PERSON_EMPTY);
		personServiceImpl.getPersonEntity(PERSON_ID);
		fail();
	}

	@Test
	public void getPersonIdTest() throws PortfolioException {
		Mockito.when(personRepository.findById(PERSON_ID)).thenReturn(OPTIONAL_PERSON);
		personServiceImpl.getPersonById(PERSON_ID);
	}

	@Test
	public void getAllPersonsTest() throws PortfolioException {
		Mockito.when(personRepository.findPersons()).thenReturn(Arrays.asList(PERSON));
		final List<PersonRest> response = personServiceImpl.getAllPersons();
		assertNotNull(response);
		assertFalse(response.isEmpty());
		assertEquals(response.size(), 1);
	}

	@Test
	public void updatePersonTest() throws PortfolioException {
		Mockito.when(personRepository.findById(PERSON_ID)).thenReturn(OPTIONAL_PERSON);
		personServiceImpl.updatePersonById(PERSON_ID, PERSON_CREAT_REST);
	}

	@Test(expected = PortfolioException.class)
	public void updatePersonIdErrorTest() throws PortfolioException {
		Mockito.when(personRepository.findById(PERSON_ID)).thenReturn(OPTIONAL_PERSON_EMPTY);
		personServiceImpl.updatePersonById(PERSON_ID, PERSON_CREAT_REST);
		fail();
	}

	/*@Test(expected = PortfolioException.class)
	public void updatePersonIdErrorNotPresentErrorTest() throws PortfolioException {
		Mockito.when(personRepository.findById(PERSON_ID)).thenReturn(OPTIONAL_PERSON);
		Mockito.when(personRepository.findById(PERSON_ID)).thenReturn(OPTIONAL_PERSON_EMPTY);
		Mockito.doThrow(Exception.class).when(personRepository).findById(PERSON_ID);
		final String response = personServiceImpl.updatePersonById(PERSON_ID, PERSON_CREAT_REST);
		assertEquals(response,PERSON_UPDATE);
		fail();
	}*/

	@Test(expected = PortfolioException.class)
	public void updatePersonServerErrorTest() throws PortfolioException {
		Mockito.when(personRepository.findById(PERSON_ID)).thenReturn(OPTIONAL_PERSON);
		Mockito.doThrow(Exception.class).when(personRepository).save(Mockito.any(Person.class));
		personServiceImpl.updatePersonById(PERSON_ID, PERSON_CREAT_REST);
		fail();

	}

	@Test
	public void deletePersonTest() throws PortfolioException {
		Mockito.when(personRepository.findById(PERSON_ID)).thenReturn(OPTIONAL_PERSON);
		Mockito.when(personRepository.deletePersonById(PERSON_ID)).thenReturn(OPTIONAL_PERSON);
		personServiceImpl.deletePersonById(PERSON_ID);
	}

	@Test(expected = PortfolioException.class)
	public void deletePersonFindByIdErrorTest() throws PortfolioException {
		Mockito.when(personRepository.findById(PERSON_ID)).thenReturn(OPTIONAL_PERSON_EMPTY);
		personServiceImpl.deletePersonById(PERSON_ID);
		fail();
	}

	@Test(expected = PortfolioException.class)
	public void deletePersonNoDeleteErrorTest() throws PortfolioException {
		Mockito.when(personRepository.findById(PERSON_ID)).thenReturn(OPTIONAL_PERSON);
		Mockito.when(personRepository.deletePersonById(PERSON_ID)).thenReturn(OPTIONAL_PERSON_EMPTY);
		final String response = personServiceImpl.deletePersonById(PERSON_ID);
		assertEquals(response, PERSON_NO_DELETE);
		fail();
	}

	@Test(expected = PortfolioException.class)
	public void deletePersonServerErrorTest() throws PortfolioException {
		Mockito.when(personRepository.findById(PERSON_ID)).thenReturn(OPTIONAL_PERSON);
		Mockito.when(personRepository.deletePersonById(PERSON_ID)).thenReturn(OPTIONAL_PERSON);
		Mockito.doThrow(Exception.class).when(personRepository).deletePersonById(PERSON_ID);
		final String response = personServiceImpl.deletePersonById(PERSON_ID);
		assertEquals(response, PERSON_DELETED);
		fail();

	}

	@Test
	public void deletePeopleTest() throws PortfolioException {
		Mockito.when(personRepository.findPersons()).thenReturn(PEOPLE_LIST);
		personServiceImpl.deleteAllPersons();
	}

	@Test(expected = PortfolioException.class)
	public void deletePeopleServerErrorTest() throws PortfolioException {
		Mockito.when(personRepository.findAll()).thenReturn(PEOPLE_LIST);
		Mockito.doThrow(Exception.class).when(personRepository).deleteAll();
		final String response = personServiceImpl.deleteAllPersons();
		assertEquals(response, ALL_PEOPLE_DELETED);
		fail();
	}

}
