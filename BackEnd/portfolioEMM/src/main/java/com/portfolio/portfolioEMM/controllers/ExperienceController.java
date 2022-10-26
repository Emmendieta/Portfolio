package com.portfolio.portfolioEMM.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.portfolio.portfolioEMM.exceptions.PortfolioException;
import com.portfolio.portfolioEMM.jsons.ExperienceCreateRest;
import com.portfolio.portfolioEMM.jsons.ExperienceRest;
import com.portfolio.portfolioEMM.responses.PortfolioResponse;
import com.portfolio.portfolioEMM.services.ExperienceService;

@RestController
@RequestMapping(path = "/portfolio/v1/experience/")
//@CrossOrigin(origins = "http://localhost:4200")
@CrossOrigin(origins = "https://frontendportfolioemm-55080.web.app")

public class ExperienceController {

	@Autowired
	ExperienceService experienceService;

	private static final String SUCCES = "Succes";
	private static final String OK = "OK";

	@PreAuthorize("hasRole('ADMIN')")
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "createExperience", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public PortfolioResponse<String> createExperience(@RequestBody ExperienceCreateRest experienceCreateRest)
			throws PortfolioException {
		return new PortfolioResponse<String>(SUCCES, String.valueOf(HttpStatus.OK), OK,
				experienceService.createExperience(experienceCreateRest));
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "{" + "id" + "}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public PortfolioResponse<ExperienceRest> getById(@PathVariable("id") Long id) throws PortfolioException {
		return new PortfolioResponse<>(SUCCES, String.valueOf(HttpStatus.OK), OK,
				experienceService.getExperienceById(id));
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "GetAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public PortfolioResponse<List<ExperienceRest>> getAll() throws PortfolioException {
		return new PortfolioResponse<>(SUCCES, String.valueOf(HttpStatus.OK), OK,
				experienceService.getAllExperiences());
	}

	@PreAuthorize("hasRole('ADMIN')")
	@ResponseStatus(HttpStatus.OK)
	// @PutMapping("experience/update/{id}")
	@RequestMapping(value = "update/" + "{" + "id"
			+ "}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public PortfolioResponse<String> updateExperience(@PathVariable("id") Long id,
			@RequestBody ExperienceCreateRest experienceCreateRest) throws PortfolioException {
		return new PortfolioResponse<String>(SUCCES, String.valueOf(HttpStatus.OK), OK,
				experienceService.updateExpierenceById(id, experienceCreateRest));
	}

	@PreAuthorize("hasRole('ADMIN')")
	@ResponseStatus(HttpStatus.OK)
	@DeleteMapping("deleteById/" + "{" + "id}")
	public PortfolioResponse<String> deleteById(@PathVariable("id") Long id) throws PortfolioException {
		return new PortfolioResponse<String>(SUCCES, String.valueOf(HttpStatus.OK), OK,
				experienceService.deleteExperienceById(id));
	}

	@PreAuthorize("hasRole('ADMIN')")
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "deleteAll", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public PortfolioResponse<String> deleteAllExperiene() throws PortfolioException {
		return new PortfolioResponse<String>(SUCCES, String.valueOf(HttpStatus.OK), OK,
				experienceService.deleteAllExperiences());
	}

}
