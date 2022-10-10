package com.portfolio.portfolioEMM.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.portfolio.portfolioEMM.exceptions.PortfolioException;
import com.portfolio.portfolioEMM.jsons.PersonCreateRest;
import com.portfolio.portfolioEMM.jsons.PersonRest;
import com.portfolio.portfolioEMM.responses.PortfolioResponse;
import com.portfolio.portfolioEMM.services.PersonService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/portfolio" + "/v1/")
public class PersonController {

	@Autowired
	PersonService personService;

	private static final String SUCCES = "Succes";
	private static final String OK = "OK";

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "person/"
			+ "createPerson", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public PortfolioResponse<String> createPerson(@RequestBody PersonCreateRest personCreateRest)
			throws PortfolioException {
		return new PortfolioResponse<String>(SUCCES, String.valueOf(HttpStatus.OK), OK,
				personService.createPerson(personCreateRest));
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "person/" + "{" + "personId"
			+ "}", produces = MediaType.APPLICATION_JSON_VALUE)
	public PortfolioResponse<PersonRest> getPersonById(@PathVariable Long personId) throws PortfolioException {
		return new PortfolioResponse<>(SUCCES, String.valueOf(HttpStatus.OK), OK,
				personService.getPersonById(personId));
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "person/"
			+ "getAll", produces = MediaType.APPLICATION_JSON_VALUE)
	public PortfolioResponse<List<PersonRest>> getAllPersons() throws PortfolioException {
		return new PortfolioResponse<>(SUCCES, String.valueOf(HttpStatus.OK), OK, personService.getAllPersons());
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "person/" + "update/" + "{" + "id"
			+ "}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public PortfolioResponse<String> updatePerson(@RequestParam Long id, @RequestBody PersonCreateRest personCreateRest)
			throws PortfolioException {
		return new PortfolioResponse<String>(SUCCES, String.valueOf(HttpStatus.OK), OK,
				personService.updatePersonById(id, personCreateRest));
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "person/" + "deleteById/" + "{" + "id"
			+ "}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public PortfolioResponse<String> deletePersonById(@RequestParam Long id) throws PortfolioException {
		return new PortfolioResponse<String>(SUCCES, String.valueOf(HttpStatus.OK), OK,
				personService.deletePersonById(id));
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "person/"
			+ "deleteAll", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public PortfolioResponse<String> deleteAllPeople() throws PortfolioException {
		return new PortfolioResponse<String>(SUCCES, String.valueOf(HttpStatus.OK), OK,
				personService.deleteAllPersons());
	}

}
