package com.portfolio.portfolioEMM.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.portfolio.portfolioEMM.exceptions.PortfolioException;
import com.portfolio.portfolioEMM.jsons.PersonCreateRest;
import com.portfolio.portfolioEMM.jsons.PersonRest;
import com.portfolio.portfolioEMM.responses.PortfolioResponse;
import com.portfolio.portfolioEMM.services.PersonService;

@RestController
@RequestMapping(path = "/portfolio/v1/person/")
@CrossOrigin(origins = "http://localhost:4200")
//@CrossOrigin(origins = "https://frontendportfolioemm-55080.web.app")
public class PersonController {

	@Autowired
	PersonService personService;

	private static final String SUCCES = "Succes";
	private static final String OK = "OK";

	@PreAuthorize("hasRole('ADMIN')")
	@ResponseStatus(HttpStatus.OK)
	@PostMapping(value = "createPerson", produces = MediaType.APPLICATION_JSON_VALUE)
	public PortfolioResponse<String> createPerson(@RequestBody PersonCreateRest personCreateRest)
			throws PortfolioException {
		return new PortfolioResponse<String>(SUCCES, String.valueOf(HttpStatus.OK), OK,
				personService.createPerson(personCreateRest));
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "{" + "id" + "}", produces = MediaType.APPLICATION_JSON_VALUE)
	public PortfolioResponse<PersonRest> getPersonById(@PathVariable("id") Long id) throws PortfolioException {
		return new PortfolioResponse<>(SUCCES, String.valueOf(HttpStatus.OK), OK, personService.getPersonById(id));
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "getAll", produces = MediaType.APPLICATION_JSON_VALUE)
	public PortfolioResponse<List<PersonRest>> getAllPersons() throws PortfolioException {
		return new PortfolioResponse<>(SUCCES, String.valueOf(HttpStatus.OK), OK, personService.getAllPersons());
	}

	@PreAuthorize("hasRole('ADMIN')")
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "update/" + "{" + "id"
			+ "}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public PortfolioResponse<String> updatePerson(@PathVariable("id") Long id,
			@RequestBody PersonCreateRest personCreateRest) throws PortfolioException {
		return new PortfolioResponse<String>(SUCCES, String.valueOf(HttpStatus.OK), OK,
				personService.updatePersonById(id, personCreateRest));
	}

	@PreAuthorize("hasRole('ADMIN')")
	@ResponseStatus(HttpStatus.OK)
	@DeleteMapping(value = "deleteById/" + "{" + "id" + "}", produces = MediaType.APPLICATION_JSON_VALUE)
	public PortfolioResponse<String> deletePersonById(@PathVariable Long id) throws PortfolioException {
		return new PortfolioResponse<String>(SUCCES, String.valueOf(HttpStatus.OK), OK,
				personService.deletePersonById(id));
	}

	@PreAuthorize("hasRole('ADMIN')")
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "deleteAll", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public PortfolioResponse<String> deleteAllPeople() throws PortfolioException {
		return new PortfolioResponse<String>(SUCCES, String.valueOf(HttpStatus.OK), OK,
				personService.deleteAllPersons());
	}

}
