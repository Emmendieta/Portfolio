package com.portfolio.portfolioEMM.services;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.portfolio.portfolioEMM.exceptions.PortfolioException;
import com.portfolio.portfolioEMM.jsons.SocialMediasCreateRest;
import com.portfolio.portfolioEMM.jsons.SocialMediasRest;

public interface SocialMediasService {

	String createSocialMedia(SocialMediasCreateRest socialMediasCreateRest) throws PortfolioException;

	@Query("SELECT sm FROM Socialmedias sm")
	public List<SocialMediasRest> findAllSocialMedias() throws PortfolioException;

	SocialMediasRest findSocialMediaById(Long id) throws PortfolioException;

	public String updateSocialMediaById(Long id, SocialMediasRest socialMediasRest) throws PortfolioException;

	public String deleteSocialMediaById(Long id) throws PortfolioException;

	@Query("DELETE sm FROM Socialmedias sm")
	public String deleteAllSocialMedias() throws PortfolioException;

}
