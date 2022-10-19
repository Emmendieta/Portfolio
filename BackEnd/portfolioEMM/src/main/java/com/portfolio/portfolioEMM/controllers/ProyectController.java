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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.portfolio.portfolioEMM.exceptions.PortfolioException;
import com.portfolio.portfolioEMM.jsons.ProyectCreateRest;
import com.portfolio.portfolioEMM.jsons.ProyectRest;
import com.portfolio.portfolioEMM.responses.PortfolioResponse;
import com.portfolio.portfolioEMM.services.ProyectService;

@RestController
@RequestMapping(path = "/portfolio/v1/proyect/")
@CrossOrigin(origins = "http://localhost:4200")
//@CrossOrigin(origins = "https://frontendportfolioemm-55080.web.app")
public class ProyectController {

	@Autowired
	ProyectService proyectService;

	private static final String SUCCES = "Succes";
	private static final String OK = "OK";

	@PreAuthorize("hasRole('ADMIN')")
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "createProyect", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public PortfolioResponse<String> createProyect(@RequestBody ProyectCreateRest proyectCreateRest)
			throws PortfolioException {
		return new PortfolioResponse<String>(SUCCES, String.valueOf(HttpStatus.OK), OK,
				proyectService.createProyect(proyectCreateRest));
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "getById/" + "{" + "id"
			+ "}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public PortfolioResponse<ProyectRest> getProyectById(@PathVariable("id") Long id) throws PortfolioException {
		return new PortfolioResponse<>(SUCCES, String.valueOf(HttpStatus.OK), OK, proyectService.findProyectById(id));
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "getAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public PortfolioResponse<List<ProyectRest>> getAllProyects() throws PortfolioException {
		return new PortfolioResponse<>(SUCCES, String.valueOf(HttpStatus.OK), OK, proyectService.findAllProyects());
	}

	@PreAuthorize("hasRole('ADMIN')")
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "updateById/" + "{" + "id"
			+ "}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public PortfolioResponse<String> updateProyect(@PathVariable("id") Long id,
			@RequestBody ProyectCreateRest proyectCreateRest) throws PortfolioException {
		return new PortfolioResponse<String>(SUCCES, String.valueOf(HttpStatus.OK), OK,
				proyectService.updateProyectById(id, proyectCreateRest));
	}

	@PreAuthorize("hasRole('ADMIN')")
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "deleteById/" + "{" + "id"
			+ "}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public PortfolioResponse<String> deleteProyectById(@PathVariable Long id) throws PortfolioException {
		return new PortfolioResponse<String>(SUCCES, String.valueOf(HttpStatus.OK), OK,
				proyectService.deleteProyectById(id));
	}

	@PreAuthorize("hasRole('ADMIN')")
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "deleteAll", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public PortfolioResponse<String> deleteAllProyects() throws PortfolioException {
		return new PortfolioResponse<String>(SUCCES, String.valueOf(HttpStatus.OK), OK,
				proyectService.deleteAllProyects());
	}

}
