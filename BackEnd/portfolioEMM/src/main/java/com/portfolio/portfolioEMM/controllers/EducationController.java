package com.portfolio.portfolioEMM.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.portfolio.portfolioEMM.exceptions.PortfolioException;
import com.portfolio.portfolioEMM.jsons.EducationCreateRest;
import com.portfolio.portfolioEMM.jsons.EducationRest;
import com.portfolio.portfolioEMM.responses.PortfolioResponse;
import com.portfolio.portfolioEMM.services.EducationService;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping(path = "portfolio" + "/v1/")
public class EducationController {

	@Autowired
	EducationService educationService;

	private static final String SUCCES = "Succes";
	private static final String OK = "OK";

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "education/"
			+ "createEducation", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public PortfolioResponse<String> createEducation(@RequestBody EducationCreateRest educationCreateRest)
			throws PortfolioException {
		return new PortfolioResponse<String>(SUCCES, String.valueOf(HttpStatus.OK), OK,
				educationService.createEducation(educationCreateRest));
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "education/" + "{" + "id"
			+ "}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public PortfolioResponse<EducationRest> findEducationById(@RequestParam Long id) throws PortfolioException {
		return new PortfolioResponse<>(SUCCES, String.valueOf(HttpStatus.OK), OK,
				educationService.getEducationById(id));
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "education/"
			+ "getAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public PortfolioResponse<List<EducationRest>> getAll() throws PortfolioException {
		return new PortfolioResponse<>(SUCCES, String.valueOf(HttpStatus.OK), OK, educationService.getAllEducations());
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "education/" + "update/" + "{" + "id"
			+ "}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public PortfolioResponse<String> updateEducationById(@RequestParam Long id,
			@RequestBody EducationCreateRest educationCreateRest) throws PortfolioException {
		return new PortfolioResponse<String>(SUCCES, String.valueOf(HttpStatus.OK), OK,
				educationService.editEducaction(id, educationCreateRest));
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "education/" + "deleteById/" + "{" + "id"
			+ "}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public PortfolioResponse<String> deleteById(@PathVariable Long id) throws PortfolioException {
		return new PortfolioResponse<String>(OK, String.valueOf(HttpStatus.OK), OK,
				educationService.deleteEducationById(id));
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "education/"
			+ "deleteAll", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public PortfolioResponse<String> deleteAll() throws PortfolioException {
		return new PortfolioResponse<String>(SUCCES, String.valueOf(HttpStatus.OK), OK,
				educationService.deleteAllEducations());
	}

}
