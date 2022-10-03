package com.portfolio.portfolioEMM.services;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.portfolio.portfolioEMM.exceptions.PortfolioException;
import com.portfolio.portfolioEMM.jsons.ExperienceCreateRest;
import com.portfolio.portfolioEMM.jsons.ExperienceRest;

public interface ExperienceService {

	String createExperience(ExperienceCreateRest experienceCreateRest) throws PortfolioException;

	@Query("SELECT exp FROM Experience exp")
	public List<ExperienceRest> getAllExperiences() throws PortfolioException;

	ExperienceRest getExperienceById(Long id) throws PortfolioException;

	@Modifying
	public String updateExpierenceById(Long id, ExperienceCreateRest experienceCreateRest) throws PortfolioException;

	public String deleteExperienceById(Long id) throws PortfolioException;

	@Query("DELETE exp FROM Experience exp")
	public String deleteAllExperiences() throws PortfolioException;
}
