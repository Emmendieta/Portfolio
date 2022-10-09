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
import com.portfolio.portfolioEMM.jsons.ProyectCreateRest;
import com.portfolio.portfolioEMM.jsons.ProyectRest;
import com.portfolio.portfolioEMM.responses.PortfolioResponse;
import com.portfolio.portfolioEMM.services.ProyectService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/portfolio" + "/v1/")
public class ProyectController {

	@Autowired
	ProyectService proyectService;

	private static final String SUCCES = "Succes";
	private static final String OK = "OK";

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "proyect/"
			+ "createProyet", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public PortfolioResponse<String> createProyect(@RequestBody ProyectCreateRest proyectCreateRest)
			throws PortfolioException {
		return new PortfolioResponse<String>(SUCCES, String.valueOf(HttpStatus.OK), OK,
				proyectService.createProyect(proyectCreateRest));
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "proyect/" + "getById/" + "{" + "id"
			+ "}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public PortfolioResponse<ProyectRest> getProyectById(@RequestParam Long id) throws PortfolioException {
		return new PortfolioResponse<>(SUCCES, String.valueOf(HttpStatus.OK), OK, proyectService.findProyectById(id));
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "proyect/"
			+ "getAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public PortfolioResponse<List<ProyectRest>> getAllProyects() throws PortfolioException {
		return new PortfolioResponse<>(SUCCES, String.valueOf(HttpStatus.OK), OK, proyectService.findAllProyects());
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "proyect/" + "updateById/" + "{" + "id"
			+ "}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public PortfolioResponse<String> updateProyect(@RequestParam Long id,
			@RequestBody ProyectCreateRest proyectCreateRest) throws PortfolioException {
		return new PortfolioResponse<String>(SUCCES, String.valueOf(HttpStatus.OK), OK,
				proyectService.updateProyectById(id, proyectCreateRest));
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "proyect/" + "deleteById/" + "{" + "id"
			+ "}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public PortfolioResponse<String> deleteProyectById(@RequestParam Long id) throws PortfolioException {
		return new PortfolioResponse<String>(SUCCES, String.valueOf(HttpStatus.OK), OK,
				proyectService.deleteProyectById(id));
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "proyect/"
			+ "deleteAll", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public PortfolioResponse<String> deleteAllProyects() throws PortfolioException {
		return new PortfolioResponse<String>(SUCCES, String.valueOf(HttpStatus.OK), OK,
				proyectService.deleteAllProyects());
	}

}
