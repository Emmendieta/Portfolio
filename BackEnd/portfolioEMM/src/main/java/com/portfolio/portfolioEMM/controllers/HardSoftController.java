package com.portfolio.portfolioEMM.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping(path = "portfolio" + "v1/")
public class HardSoftController {

	@Autowired
	HardSoftService hardSoftService;

	private static final String SUCCES = "Succes";
	private static final String OK = "OK";

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "hardAndSoft/"
			+ "createHardAndSoft", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public PortfolioResponse<String> createHardSoftSkill(@RequestBody HardSoftCreateRest hardSoftCreateRest)
			throws PortfolioException {
		return new PortfolioResponse<String>(SUCCES, String.valueOf(HttpStatus.OK), OK,
				hardSoftService.createHardSoft(hardSoftCreateRest));
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "hardAndSoft" + "/{" + "id"
			+ "}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public PortfolioResponse<HardSoftRest> getHardAndSoftById(@RequestParam Long id) throws PortfolioException {
		return new PortfolioResponse<>(SUCCES, String.valueOf(HttpStatus.OK), OK, hardSoftService.getHardSoftById(id));
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "hardAndSoft" + "{/" + "name"
			+ "}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public PortfolioResponse<HardSoftRest> getHardAndSoftByName(@RequestParam String name) throws PortfolioException {
		return new PortfolioResponse<>(SUCCES, String.valueOf(HttpStatus.OK), OK,
				hardSoftService.getHardSoftByName(name));
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "hardAndSoft/"
			+ "getAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public PortfolioResponse<List<HardSoftRest>> getAllHardAndSoft() throws PortfolioException {
		return new PortfolioResponse<>(OK, String.valueOf(HttpStatus.OK), OK, hardSoftService.getAllHardSoft());
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "hardAndSoft/" + "update" + "/{" + "id"
			+ "}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public PortfolioResponse<String> updateHardAndSoftSkillById(@RequestParam Long id,
			@RequestBody HardSoftCreateRest hardSoftCreateRest) throws PortfolioException {
		return new PortfolioResponse<String>(SUCCES, String.valueOf(HttpStatus.OK), OK,
				hardSoftService.updateHardSoftById(id, hardSoftCreateRest));
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "hardAndSoft/" + "deleteById" + "/{" + "id"
			+ "}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public PortfolioResponse<String> deleteHardSoftById(@RequestParam Long id) throws PortfolioException {
		return new PortfolioResponse<String>(SUCCES, String.valueOf(HttpStatus.OK), OK,
				hardSoftService.deleteHardSoftById(id));
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "hardAndSoft/"
			+ "deleteAll", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public PortfolioResponse<String> deleteAllHardSoft() throws PortfolioException {
		return new PortfolioResponse<String>(SUCCES, String.valueOf(HttpStatus.OK), OK,
				hardSoftService.deleteAllHardSoft());
	}
}
