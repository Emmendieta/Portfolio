package com.portfolio.portfolioEMM.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portfolio.portfolioEMM.entities.Person;
import com.portfolio.portfolioEMM.exceptions.InternalServerErrorException;
import com.portfolio.portfolioEMM.exceptions.NotFountException;
import com.portfolio.portfolioEMM.exceptions.PortfolioException;
import com.portfolio.portfolioEMM.jsons.PersonCreateRest;
import com.portfolio.portfolioEMM.jsons.PersonRest;
import com.portfolio.portfolioEMM.repositories.PersonRepository;
import com.portfolio.portfolioEMM.services.PersonService;

@Service
public class PersonServiceImpl implements PersonService {

	@Autowired
	PersonRepository personRepository;

	public static final ModelMapper modelMapper = new ModelMapper();
	private static final Logger LOGGER = LoggerFactory.getLogger(EducactionServiceImpl.class);
	public static final String INTERNAL_ERROR = "INTERNAL SERVER_ERROR";
	public static final String PERSON_OK = "Person save!";
	public static final String PERSON_UPTDATE = "Person update!";
	public static final String PERSON_NO_UPTDATE = "Error updating Person";
	public static final String PERSON_NO_DELETE = "Error deleting Person";
	public static final String PERSON_NO = "Person doesn´t save!";
	public static final String PERSON_DELETED = "Person has been deleted!";
	public static final String PEOPLE_DELETED = "All people has been deleted!";
	public static final String PERSON_NOT_FOUND = "PERSON NOT FOUND!";
	public static final String PERSON_EXIST = "PERSON ALREDY EXISTS!";

	public String createPerson(PersonCreateRest personCreateRest) throws PortfolioException {

		Person person = new Person();
		person.setName(personCreateRest.getName());
		person.setLastName(personCreateRest.getLastName());
		person.setAge(personCreateRest.getAge());
		person.setTitle(personCreateRest.getTitle());
		person.setAbout(personCreateRest.getAbout());
		person.setProvince(personCreateRest.getProvince());
		person.setCountry(personCreateRest.getCountry());
		person.setImage(personCreateRest.getImage());
		person.setBanner(personCreateRest.getBanner());
		person.setEmail(personCreateRest.getEmail());

		try {
			personRepository.save(person);
		} catch (Exception e) {
			LOGGER.error(PERSON_NO, e);
			throw new InternalServerErrorException(INTERNAL_ERROR, INTERNAL_ERROR);
		}

		return PERSON_OK;

	}

	public List<PersonRest> getAllPersons() throws PortfolioException {
		final List<Person> personEntity = personRepository.findPersons();
		return personEntity.stream().map(service -> modelMapper.map(service, PersonRest.class))
				.collect(Collectors.toList());

	}

	public PersonRest getPersonById(Long id) throws PortfolioException {
		return modelMapper.map(getPersonEntity(id), PersonRest.class);
	}

	public String updatePersonById(Long id, PersonCreateRest personCreateRest) throws PortfolioException {

		final Person personId = personRepository.findById(id)
				.orElseThrow(() -> new NotFountException(PERSON_NOT_FOUND, PERSON_NOT_FOUND));

		if (personRepository.findById(personId.getId()).isPresent()) {

			Person person = personRepository.findById(id).get();
			person.setName(personCreateRest.getName());
			person.setLastName(personCreateRest.getLastName());
			person.setAge(personCreateRest.getAge());
			person.setTitle(personCreateRest.getTitle());
			person.setAbout(personCreateRest.getAbout());
			person.setProvince(personCreateRest.getProvince());
			person.setCountry(personCreateRest.getCountry());
			person.setImage(personCreateRest.getImage());
			person.setBanner(personCreateRest.getBanner());
			person.setEmail(personCreateRest.getEmail());
			person.setId(id);

			try {
				personRepository.save(person);
			} catch (Exception e) {
				LOGGER.error(PERSON_NO_UPTDATE, e);
				throw new InternalServerErrorException(INTERNAL_ERROR, INTERNAL_ERROR);
			}
		}

		return PERSON_UPTDATE;

	}

	public String deletePersonById(Long id) throws PortfolioException {
		personRepository.findById(id).orElseThrow(() -> new NotFountException(PERSON_NOT_FOUND, PERSON_NOT_FOUND));
		try {
			personRepository.deletePersonById(id)
					.orElseThrow(() -> new NotFountException(PERSON_NO_DELETE, PERSON_NO_DELETE));
		} catch (Exception e) {
			LOGGER.error(INTERNAL_ERROR, e);
			throw new InternalServerErrorException(INTERNAL_ERROR, INTERNAL_ERROR);
		}
		return PERSON_DELETED;
	}

	public String deleteAllPersons() throws PortfolioException {
		personRepository.findAll();
		try {
			personRepository.deleteAll();
		} catch (Exception e) {
			LOGGER.error(INTERNAL_ERROR, e);
			throw new InternalServerErrorException(INTERNAL_ERROR, INTERNAL_ERROR);
		}
		return PEOPLE_DELETED;
	}

	public Person getPersonEntity(Long personId) throws PortfolioException {
		return personRepository.findById(personId)
				.orElseThrow(() -> new NotFountException("SNOT-404-1", PERSON_NOT_FOUND));
	}
	



}
