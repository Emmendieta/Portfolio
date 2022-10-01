package com.portfolio.portfolioEMM.services;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.portfolio.portfolioEMM.exceptions.PortfolioException;
import com.portfolio.portfolioEMM.jsons.HardSoftCreateRest;
import com.portfolio.portfolioEMM.jsons.HardSoftRest;

public interface HardSoftService {
	
	String createHardSoft(HardSoftCreateRest hardSoftCreateRest) throws PortfolioException;
	
	@Query("SELECT hs FROM Hardsoft hs")
	public List<HardSoftRest> findAllHardSoft() throws PortfolioException;
	
	HardSoftRest findHardSoftById(Long id) throws PortfolioException;
	
	public String editHardSoftById(Long id) throws PortfolioException;
	
	public String deleteHardSoftById(Long id) throws PortfolioException;
	
	@Query("DELETE hs FROM HardSoft hs")
	public String deleteAllHardSoft() throws PortfolioException;

}
