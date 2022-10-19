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
import com.portfolio.portfolioEMM.jsons.HardSoftCreateRest;
import com.portfolio.portfolioEMM.jsons.HardSoftRest;
import com.portfolio.portfolioEMM.responses.PortfolioResponse;
import com.portfolio.portfolioEMM.services.HardSoftService;

@RestController
@RequestMapping(path = "/portfolio/v1/hardAndSoft/")
@CrossOrigin(origins = "http://localhost:4200/")
//@CrossOrigin(origins = "https://frontendportfolioemm-55080.web.app")

public class HardSoftController {

	@Autowired
	HardSoftService hardSoftService;

	private static final String SUCCES = "Succes";
	private static final String OK = "OK";

	@PreAuthorize("hasRole('ADMIN')")
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "createHardAndSoft", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public PortfolioResponse<String> createHardSoftSkill(@RequestBody HardSoftCreateRest hardSoftCreateRest)
			throws PortfolioException {
		return new PortfolioResponse<String>(SUCCES, String.valueOf(HttpStatus.OK), OK,
				hardSoftService.createHardSoft(hardSoftCreateRest));
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "{" + "id"
			+ "}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public PortfolioResponse<HardSoftRest> getHardAndSoftById(@PathVariable("id") Long id) throws PortfolioException {
		return new PortfolioResponse<>(SUCCES, String.valueOf(HttpStatus.OK), OK, hardSoftService.getHardSoftById(id));
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "getByName/" + "{" + "name"
			+ "}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public PortfolioResponse<HardSoftRest> getHardAndSoftByName(@RequestParam String name) throws PortfolioException {
		return new PortfolioResponse<>(SUCCES, String.valueOf(HttpStatus.OK), OK,
				hardSoftService.getHardSoftByName(name));
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "getAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public PortfolioResponse<List<HardSoftRest>> getAllHardAndSoft() throws PortfolioException {
		return new PortfolioResponse<>(SUCCES, String.valueOf(HttpStatus.OK), OK, hardSoftService.getAllHardSoft());
	}

	@PreAuthorize("hasRole('ADMIN')")
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "update/" + "{" + "id"
			+ "}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public PortfolioResponse<String> updateHardAndSoftSkillById(@PathVariable("id") Long id,
			@RequestBody HardSoftCreateRest hardSoftCreateRest) throws PortfolioException {
		return new PortfolioResponse<String>(SUCCES, String.valueOf(HttpStatus.OK), OK,
				hardSoftService.updateHardSoftById(id, hardSoftCreateRest));
	}

	@PreAuthorize("hasRole('ADMIN')")
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "deleteById/" + "{" + "id"
			+ "}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public PortfolioResponse<String> deleteById(@PathVariable Long id) throws PortfolioException {
		return new PortfolioResponse<String>(SUCCES, String.valueOf(HttpStatus.OK), OK,
				hardSoftService.deleteHardSoftById(id));
	}

	@PreAuthorize("hasRole('ADMIN')")
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "deleteAll", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public PortfolioResponse<String> deleteAllHardSoft() throws PortfolioException {
		return new PortfolioResponse<String>(SUCCES, String.valueOf(HttpStatus.OK), OK,
				hardSoftService.deleteAllHardSoft());
	}
}
