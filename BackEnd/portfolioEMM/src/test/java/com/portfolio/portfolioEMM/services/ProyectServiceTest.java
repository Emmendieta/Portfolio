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
import com.portfolio.portfolioEMM.jsons.ProyectCreateRest;
import com.portfolio.portfolioEMM.jsons.ProyectRest;
import com.portfolio.portfolioEMM.repositories.PersonRepository;
import com.portfolio.portfolioEMM.repositories.ProyectRepository;
import com.portfolio.portfolioEMM.security.entities.User;
import com.portfolio.portfolioEMM.services.impl.ProyectServiceImpl;

public class ProyectServiceTest {

	private final static Long PROYECT_ID = 1L;
	private final static String NAME = "Proyect Name";
	private final static String DESCRIPTION = "Proyect Description";
	private final static String IMAGE = "imagen";
	private final static String LINK = "wwww.link.com.ar";
	private final static Date DATE_START = new Date();
	private final static Date DATE_END = new Date();

	private final static Long PERSON_ID = 1L;
	private final static String LAST_NAME = "person Last Name";
	private final static Date AGE = new Date();
	private final static String TITLE = "title";
	private final static String ABOUT = "about";
	private final static String PROVINCE = "province";
	private final static String COUNTRY = "country";
	private final static String BANNER = "banner";
	private final static String EMAIL = "email@email.com";
	private static final String PROYECT_DELETED = "Proyect has been deleted!";
	public static final String ALL_PROYECTS_DELETED = "All Proyects has been deleted!";
	public static final String NOT_FOUND = "PROYECT NOT FOUND";

	ProyectRest PROYECT_REST = new ProyectRest();
	ProyectCreateRest PROYECT_CREATE_REST = new ProyectCreateRest();

	private static final Person PERSON = new Person();
	private static final Proyect PROYECT = new Proyect();
	private static final User USER = new User();

	private static final List<Proyect> PROYECT_LIST = new ArrayList<>();
	private static final List<SocialMedias> SOCIAL_MEDIA_LIST = new ArrayList<>();
	private static final List<Experience> EXPERIENCE_LIST = new ArrayList<>();
	private static final List<Education> EDUCATION_LIST = new ArrayList<>();
	private static final List<HardSoft> HARD_SOFT_LIST = new ArrayList<>();

	private static final Optional<Proyect> OPTIONAL_PROYECT = Optional.of(PROYECT);
	private static final Optional<Person> OPTIONAL_PERSON = Optional.of(PERSON);

	private static final Optional<Proyect> OPTIONAL_PROYECT_EMPTY = Optional.empty();
	private static final Optional<Person> OPTIONAL_PERSON_EMPTY = Optional.empty();

	@Mock
	ProyectRepository proyectRepository;

	@Mock
	PersonRepository personRepository;

	@InjectMocks
	ProyectServiceImpl proyectServiceImpl;

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

		PROYECT_CREATE_REST.setName(NAME);
		PROYECT_CREATE_REST.setDescription(DESCRIPTION);
		PROYECT_CREATE_REST.setImage(IMAGE);
		PROYECT_CREATE_REST.setLink(LINK);
		PROYECT_CREATE_REST.setDateStart(DATE_START);
		PROYECT_CREATE_REST.setDateEnd(DATE_END);
		PROYECT_CREATE_REST.setPersonId(PERSON_ID);

		PROYECT_REST.setId(PROYECT_ID);
		PROYECT_REST.setName(NAME);
		PROYECT_REST.setDescription(DESCRIPTION);
		PROYECT_REST.setImage(IMAGE);
		PROYECT_REST.setLink(LINK);
		PROYECT_REST.setDateStart(DATE_START);
		PROYECT_REST.setDateEnd(DATE_END);
		PROYECT_REST.setPersonId(PERSON_ID);

	}

	@Test
	public void createProyectTest() throws PortfolioException {
		Mockito.when(personRepository.findById(PERSON_ID)).thenReturn(OPTIONAL_PERSON);
		Mockito.when(proyectRepository.findByPersonIdAndName(PERSON.getId(), PROYECT_CREATE_REST.getName()))
				.thenReturn(OPTIONAL_PROYECT_EMPTY);
		Mockito.when(proyectRepository.save(Mockito.any(Proyect.class))).thenReturn(new Proyect());
		proyectServiceImpl.createProyect(PROYECT_CREATE_REST);
	}

	@Test(expected = PortfolioException.class)
	public void createProyectFindByIdTestError() throws PortfolioException {
		Mockito.when(personRepository.findById(PERSON_ID)).thenReturn(OPTIONAL_PERSON_EMPTY);
		proyectServiceImpl.createProyect(PROYECT_CREATE_REST);
		fail();
	}

	@Test(expected = PortfolioException.class)
	public void createProyectfindByPersonIdAndNameTestError() throws PortfolioException {
		Mockito.when(personRepository.findById(PERSON_ID)).thenReturn(OPTIONAL_PERSON);
		Mockito.when(proyectRepository.findByPersonIdAndName(PERSON.getId(), PROYECT_CREATE_REST.getName()))
				.thenReturn(OPTIONAL_PROYECT);
		proyectServiceImpl.createProyect(PROYECT_CREATE_REST);
		fail();
	}

	@Test(expected = PortfolioException.class)
	public void createProyectServerTestError() throws PortfolioException {
		Mockito.when(personRepository.findById(PERSON_ID)).thenReturn(OPTIONAL_PERSON);
		Mockito.when(proyectRepository.findByPersonIdAndName(PERSON.getId(), PROYECT_CREATE_REST.getName()))
				.thenReturn(OPTIONAL_PROYECT_EMPTY);
		Mockito.doThrow(Exception.class).when(proyectRepository).save(Mockito.any(Proyect.class));
		proyectServiceImpl.createProyect(PROYECT_CREATE_REST);
		fail();
	}

	@Test
	public void findProyectByIdTest() throws PortfolioException {
		Mockito.when(proyectRepository.findProyectById(PROYECT_ID)).thenReturn(Optional.of(PROYECT));
		proyectServiceImpl.findProyectById(PROYECT_ID);
	}

	/*@Test(expected = PortfolioException.class)
	public void findProyectByIdTestError() throws PortfolioException {
		Mockito.when(proyectRepository.findProyectById(PROYECT_ID)).thenReturn(Optional.empty());
		proyectServiceImpl.getProyectEntity(PROYECT_ID);
		fail();
	}*/

	@Test
	public void getProyectEntityTest() throws PortfolioException {
		Mockito.when(proyectRepository.findProyectById(PROYECT_ID)).thenReturn(Optional.of(PROYECT));
		proyectServiceImpl.findProyectById(PROYECT_ID);
	}

	@Test(expected = PortfolioException.class)
	public void getSocialMediasEntityTestError() throws PortfolioException {
		Mockito.when(proyectRepository.findProyectById(PROYECT_ID)).thenReturn(Optional.empty());
		proyectServiceImpl.findProyectById(PROYECT_ID);
		fail();
	}

	@Test
	public void findAllProyectsTest() throws PortfolioException {
		Mockito.when(proyectRepository.findProyects()).thenReturn(Arrays.asList(PROYECT));
		final List<ProyectRest> response = proyectServiceImpl.findAllProyects();
		assertNotNull(response);
		assertFalse(response.isEmpty());
		assertEquals(response.size(), 1);
	}

	@Test
	public void updateProyectByIdTest() throws PortfolioException {
		Mockito.when(personRepository.findById(PERSON_ID)).thenReturn(OPTIONAL_PERSON);
		Mockito.when(proyectRepository.findProyectById(PROYECT_REST.getId())).thenReturn(OPTIONAL_PROYECT);
		Mockito.when(proyectRepository.save(Mockito.any(Proyect.class))).thenReturn(new Proyect());
		proyectServiceImpl.updateProyectById(PROYECT_ID, PROYECT_CREATE_REST);
	}

	@Test(expected = PortfolioException.class)
	public void updateProyectByIdPersonTestError() throws PortfolioException {
		Mockito.when(personRepository.findById(PERSON_ID)).thenReturn(OPTIONAL_PERSON_EMPTY);
		proyectServiceImpl.updateProyectById(PROYECT_ID, PROYECT_CREATE_REST);
		fail();
	}

	/*@Test(expected = PortfolioException.class)
	public void updateProyectByIdNotFoundTestError() throws PortfolioException {
		Mockito.when(personRepository.findById(PERSON_ID)).thenReturn(OPTIONAL_PERSON);
		Mockito.when(proyectRepository.findProyectById(PROYECT_REST.getId())).thenReturn(OPTIONAL_PROYECT_EMPTY);
		//Mockito.when(proyectRepository.findProyectById(PROYECT_REST.getId())).thenReturn(Optional.empty());
		proyectServiceImpl.updateProyectById(PROYECT_ID, PROYECT_CREATE_REST);
		fail();
	}*/

	@Test(expected = PortfolioException.class)
	public void updateProyectByIdServerTestError() throws PortfolioException {
		Mockito.when(personRepository.findById(PERSON_ID)).thenReturn(OPTIONAL_PERSON);
		Mockito.when(proyectRepository.findProyectById(PROYECT_REST.getId())).thenReturn(OPTIONAL_PROYECT);
		Mockito.doThrow(Exception.class).when(proyectRepository).save(Mockito.any(Proyect.class));
		proyectServiceImpl.updateProyectById(PROYECT_ID, PROYECT_CREATE_REST);
		fail();
	}

	@Test
	public void deleteProyectByIdTest() throws PortfolioException {

		Mockito.when(proyectRepository.findProyectById(PROYECT_REST.getId())).thenReturn(OPTIONAL_PROYECT);
		proyectServiceImpl.deleteProyectById(PROYECT_ID);
	}

	@Test(expected = PortfolioException.class)
	public void deleteProyectByIdTestError() throws PortfolioException {
		Mockito.when(proyectRepository.findProyectById(PROYECT_REST.getId())).thenReturn(OPTIONAL_PROYECT);
		Mockito.doThrow(Exception.class).when(proyectRepository).deleteProyectById(PROYECT_ID);
		final String response = proyectServiceImpl.deleteProyectById(PROYECT_ID);
		assertEquals(response, PROYECT_DELETED);
		fail();

	}

	@Test
	public void deleteAllProyectsTest() throws PortfolioException {
		Mockito.when(proyectRepository.findProyects()).thenReturn(PROYECT_LIST);
		proyectServiceImpl.deleteAllProyects();
	}

	@Test(expected = PortfolioException.class)
	public void deleteAllProyectsTestError() throws PortfolioException {
		Mockito.doThrow(Exception.class).when(proyectRepository).deleteAll();
		final String response = proyectServiceImpl.deleteAllProyects();
		assertEquals(response, ALL_PROYECTS_DELETED);
		fail();
	}
}
