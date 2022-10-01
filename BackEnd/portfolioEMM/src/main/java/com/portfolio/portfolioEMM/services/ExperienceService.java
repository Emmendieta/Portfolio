package com.portfolio.portfolioEMM.services;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.portfolio.portfolioEMM.exceptions.PortfolioException;
import com.portfolio.portfolioEMM.jsons.ExperienceCreateRest;
import com.portfolio.portfolioEMM.jsons.ExperienceRest;

public interface ExperienceService {

	String createExperience(ExperienceCreateRest experienceRest) throws PortfolioException;

	@Query("SELECT exp FROM Experience exp")
	public List<ExperienceRest> getAllExperiences() throws PortfolioException;

	ExperienceRest getExperienceById(Long id) throws PortfolioException;

	public String editExpierenceById(Long id) throws PortfolioException;

	public String deleteExperienceById(Long id) throws PortfolioException;

	@Query("DELETE exp FROM Experience exp")
	public String deleteAllExperiences() throws PortfolioException;
}
