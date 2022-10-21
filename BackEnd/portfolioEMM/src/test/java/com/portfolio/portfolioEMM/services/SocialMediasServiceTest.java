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
import com.portfolio.portfolioEMM.jsons.SocialMediasCreateRest;
import com.portfolio.portfolioEMM.jsons.SocialMediasRest;
import com.portfolio.portfolioEMM.repositories.PersonRepository;
import com.portfolio.portfolioEMM.repositories.SocialMediaRepository;
import com.portfolio.portfolioEMM.security.entities.User;
import com.portfolio.portfolioEMM.services.impl.SocialMediasServiceImpl;

public class SocialMediasServiceTest {

	private final static Long SOCIAL_ID = 1L;
	private final static String NAME = "Social Media Name";
	private final static String IMAGE = "imagen";
	private final static String URL = "wwww.url.com.ar";

	private final static Long PERSON_ID = 1L;
	private final static String LAST_NAME = "person Last Name";
	private final static Date AGE = new Date();
	private final static String TITLE = "title";
	private final static String ABOUT = "about";
	private final static String PROVINCE = "province";
	private final static String COUNTRY = "country";
	private final static String BANNER = "banner";
	private final static String EMAIL = "email@email.com";

	SocialMediasCreateRest SOCIAL_MEDIA_CREAT_REST = new SocialMediasCreateRest();

	private static final Person PERSON = new Person();
	private static final SocialMedias SOCIAL_MEDIA = new SocialMedias();
	private static final User USER = new User();

	private static final List<SocialMedias> SOCIAL_MEDIA_LIST = new ArrayList<>();
	private static final List<Experience> EXPERIENCE_LIST = new ArrayList<>();
	private static final List<Education> EDUCATION_LIST = new ArrayList<>();
	private static final List<HardSoft> HARD_SOFT_LIST = new ArrayList<>();
	private static final List<Proyect> PROYECT_LIST = new ArrayList<>();

	private static final Optional<SocialMedias> OPTIONAL_SOCIAL_MEDIA = Optional.of(SOCIAL_MEDIA);
	private static final Optional<Person> OPTIONAL_PERSON = Optional.of(PERSON);

	private static final Optional<SocialMedias> OPTIONAL_SOCIAL_MEDIA_EMPTY = Optional.empty();
	private static final Optional<Person> OPTIONAL_PERSON_EMPTY = Optional.empty();

	@Mock
	SocialMediaRepository socialMediaRepository;

	@Mock
	PersonRepository personRepository;

	@InjectMocks
	SocialMediasServiceImpl socialMediasServiceImpl;

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

		SOCIAL_MEDIA_CREAT_REST.setName(NAME);
		SOCIAL_MEDIA_CREAT_REST.setImage(IMAGE);
		SOCIAL_MEDIA_CREAT_REST.setUrl(URL);
		SOCIAL_MEDIA_CREAT_REST.setPersonId(PERSON_ID);

	}

	@Test
	public void createSocialMediaTest() throws PortfolioException {
		Mockito.when(personRepository.findById(PERSON_ID)).thenReturn(OPTIONAL_PERSON);
		Mockito.when(socialMediaRepository.findByPersonIdAndName(PERSON.getId(), SOCIAL_MEDIA_CREAT_REST.getName()))
				.thenReturn(OPTIONAL_SOCIAL_MEDIA_EMPTY);
		Mockito.when(socialMediaRepository.save(Mockito.any(SocialMedias.class))).thenReturn(new SocialMedias());
		socialMediasServiceImpl.createSocialMedia(SOCIAL_MEDIA_CREAT_REST);
	}

	@Test(expected = PortfolioException.class)
	public void createSocialMediaFindByIdTestError() throws PortfolioException {
		Mockito.when(personRepository.findById(PERSON_ID)).thenReturn(OPTIONAL_PERSON_EMPTY);
		socialMediasServiceImpl.createSocialMedia(SOCIAL_MEDIA_CREAT_REST);
		fail();
	}

	@Test(expected = PortfolioException.class)
	public void createSocialMediafindByPersonIdAndNameTestError() throws PortfolioException {
		Mockito.when(personRepository.findById(PERSON_ID)).thenReturn(OPTIONAL_PERSON);
		Mockito.when(socialMediaRepository.findByPersonIdAndName(PERSON.getId(), SOCIAL_MEDIA_CREAT_REST.getName()))
				.thenReturn(OPTIONAL_SOCIAL_MEDIA);
		socialMediasServiceImpl.createSocialMedia(SOCIAL_MEDIA_CREAT_REST);
		fail();
	}

	@Test(expected = PortfolioException.class)
	public void createSocialMediaServerTestError() throws PortfolioException {
		Mockito.when(personRepository.findById(PERSON_ID)).thenReturn(OPTIONAL_PERSON);
		Mockito.when(socialMediaRepository.findByPersonIdAndName(PERSON.getId(), SOCIAL_MEDIA_CREAT_REST.getName()))
				.thenReturn(OPTIONAL_SOCIAL_MEDIA_EMPTY);
		Mockito.doThrow(Exception.class).when(socialMediaRepository).save(Mockito.any(SocialMedias.class));
		socialMediasServiceImpl.createSocialMedia(SOCIAL_MEDIA_CREAT_REST);
		fail();
	}

	@Test
	public void getSocialMediaByIdTest() throws PortfolioException {
		Mockito.when(socialMediaRepository.findSocialMediaById(SOCIAL_ID)).thenReturn(Optional.of(SOCIAL_MEDIA));
		socialMediasServiceImpl.findSocialMediaById(SOCIAL_ID);
	}

	@Test(expected = PortfolioException.class)
	public void getSOcialMediaByIdTestError() throws PortfolioException {
		Mockito.when(socialMediaRepository.findSocialMediaById(SOCIAL_ID)).thenReturn(Optional.empty());
		socialMediasServiceImpl.findSocialMediaById(SOCIAL_ID);
		fail();
	}

	@Test
	public void getSocialMediasEntityTest() throws PortfolioException {
		Mockito.when(socialMediaRepository.findSocialMediaById(SOCIAL_ID)).thenReturn(Optional.of(SOCIAL_MEDIA));
		socialMediasServiceImpl.findSocialMediaById(SOCIAL_ID);
	}

	@Test(expected = PortfolioException.class)
	public void getSocialMediasEntityTestError() throws PortfolioException {
		Mockito.when(socialMediaRepository.findSocialMediaById(SOCIAL_ID)).thenReturn(Optional.empty());
		socialMediasServiceImpl.findSocialMediaById(SOCIAL_ID);
		fail();
	}

	@Test
	public void getAllSocialMediasTest() throws PortfolioException {
		Mockito.when(socialMediaRepository.findSocialMedias()).thenReturn(Arrays.asList(SOCIAL_MEDIA));
		final List<SocialMediasRest> response = socialMediasServiceImpl.findAllSocialMedias();
		assertNotNull(response);
		assertFalse(response.isEmpty());
		assertEquals(response.size(), 1);
	}

}
