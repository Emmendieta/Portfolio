package com.portfolio.portfolioEMM.services;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.portfolio.portfolioEMM.entities.Person;
import com.portfolio.portfolioEMM.exceptions.PortfolioException;
import com.portfolio.portfolioEMM.jsons.PersonCreateRest;
import com.portfolio.portfolioEMM.jsons.PersonRest;

public interface PersonService {

	public String createPerson(PersonCreateRest personCreateRest) throws PortfolioException;

	@Query("SELECT per FROM Person pers")
	public List<PersonRest> getAllPersons() throws PortfolioException;

	PersonRest getPersonById(Long id) throws PortfolioException;

	public String updatePersonById(Long id, PersonCreateRest personCreateRest) throws PortfolioException;

	public String deletePersonById(Long id) throws PortfolioException;

	@Query("DELETE per FROM Person per")
	public String deleteAllPersons() throws PortfolioException;
	
}
