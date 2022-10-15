package com.portfolio.portfolioEMM.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.portfolio.portfolioEMM.exceptions.PortfolioException;
import com.portfolio.portfolioEMM.jsons.SocialMediasCreateRest;
import com.portfolio.portfolioEMM.jsons.SocialMediasRest;
import com.portfolio.portfolioEMM.responses.PortfolioResponse;
import com.portfolio.portfolioEMM.services.SocialMediasService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/portfolio" + "/v1/")
public class SocialMediasController {

	@Autowired
	SocialMediasService socialMediasService;

	private static final String SUCCES = "Succes";
	private static final String OK = "OK";
	private static final String HTTP = String.valueOf(HttpStatus.OK);

	@PreAuthorize("hasRole('ADMIN')")
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "socialMedia/"
			+ "createSocialMedia", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public PortfolioResponse<String> createSocialMedia(@RequestBody SocialMediasCreateRest socialMediasCreateRest)
			throws PortfolioException {
		return new PortfolioResponse<String>(SUCCES, HTTP, OK,
				socialMediasService.createSocialMedia(socialMediasCreateRest));
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "socialMedia/" + "findById/" + "{" + "id"
			+ "}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public PortfolioResponse<SocialMediasRest> findSocialMediaById(@PathVariable("id") Long id) throws PortfolioException {
		return new PortfolioResponse<SocialMediasRest>(SUCCES, HTTP, OK, socialMediasService.findSocialMediaById(id));
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "socialMedia/"
			+ "findAll/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public PortfolioResponse<List<SocialMediasRest>> findAllSocialMedias() throws PortfolioException {
		return new PortfolioResponse<List<SocialMediasRest>>(SUCCES, HTTP, OK,
				socialMediasService.findAllSocialMedias());
	}

	@PreAuthorize("hasRole('ADMIN')")
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "socialMedia/" + "updateSocialMedias/" + "{" + "id"
			+ "}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public PortfolioResponse<String> updateSocialMedia(@PathVariable("id") Long id,
			@RequestBody SocialMediasCreateRest socialMediaCreateRest) throws PortfolioException {
		return new PortfolioResponse<String>(SUCCES,String.valueOf(HttpStatus.OK), OK,
				socialMediasService.updateSocialMediaById(id, socialMediaCreateRest));
	}

	@PreAuthorize("hasRole('ADMIN')")
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "socialMedia/" + "deleteById/" + "{" + "id"
			+ "}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public PortfolioResponse<String> deleteSocialMediaById(@PathVariable("id") Long id) throws PortfolioException {
		return new PortfolioResponse<String>(SUCCES, HTTP, OK, socialMediasService.deleteSocialMediaById(id));
	}

	@PreAuthorize("hasRole('ADMIN')")
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "socialMedia/"
			+ "deleteAllSocialMedias", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public PortfolioResponse<String> deleteAllSocialMedias() throws PortfolioException {
		return new PortfolioResponse<String>(SUCCES, HTTP, OK, socialMediasService.deleteAllSocialMedias());
	}

}
