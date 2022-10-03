package com.portfolio.portfolioEMM.services;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.portfolio.portfolioEMM.exceptions.PortfolioException;
import com.portfolio.portfolioEMM.jsons.HardSoftCreateRest;
import com.portfolio.portfolioEMM.jsons.HardSoftRest;

public interface HardSoftService {
	
	String createHardSoft(HardSoftCreateRest hardSoftCreateRest) throws PortfolioException;
	
	@Query("SELECT hs FROM Hardsoft hs")
	public List<HardSoftRest> getAllHardSoft() throws PortfolioException;
	
	HardSoftRest getHardSoftById(Long id) throws PortfolioException;
	
	HardSoftRest getHardSoftByName(String name) throws PortfolioException;
	
	public String updateHardSoftById(Long id, HardSoftCreateRest hardSoftCreateRest) throws PortfolioException;
	
	public String deleteHardSoftById(Long id) throws PortfolioException;
	
	@Query("DELETE hs FROM HardSoft hs")
	public String deleteAllHardSoft() throws PortfolioException;

}
