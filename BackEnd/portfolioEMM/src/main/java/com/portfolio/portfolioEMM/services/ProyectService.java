package com.portfolio.portfolioEMM.services;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.portfolio.portfolioEMM.exceptions.PortfolioException;
import com.portfolio.portfolioEMM.jsons.ProyectCreateRest;
import com.portfolio.portfolioEMM.jsons.ProyectRest;

public interface ProyectService {

	String createProyect(ProyectCreateRest proyectCreateRest) throws PortfolioException;

	@Query("SELECT pro FROM Proyects pro")
	public List<ProyectRest> findAllProyects() throws PortfolioException;

	ProyectRest findProyectById(Long id) throws PortfolioException;

	public String editProyectById(Long id) throws PortfolioException;

	public String deleteProyectById(Long id) throws PortfolioException;

	@Query("DELETE pro FROM Proyects pro")
	public String deleteAllProyects() throws PortfolioException;

}
