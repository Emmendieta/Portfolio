package com.portfolio.portfolioEMM.services;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.portfolio.portfolioEMM.exceptions.PortfolioException;
import com.portfolio.portfolioEMM.jsons.EducationCreateRest;
import com.portfolio.portfolioEMM.jsons.EducationRest;

public interface EducationService {

	String createEducation(EducationCreateRest educationCreateRest) throws PortfolioException;

	@Query("SELECT edu FROM Education edu")
	public List<EducationRest> getAllEducations() throws PortfolioException;

	EducationRest getEducationById(Long id) throws PortfolioException;

	public String editEducaction(Long id, EducationCreateRest educationCreateRest) throws PortfolioException;

	public String deleteEducationById(Long id) throws PortfolioException;

	@Query("DELETE edu FROM Education edu")
	public String deleteAllEducations() throws PortfolioException;

}
