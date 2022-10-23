package com.portfolio.portfolioEMM.repositories;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.portfolio.portfolioEMM.entities.Proyect;

@Repository
public interface ProyectRepository extends JpaRepository<Proyect, Long> {

	Optional<Proyect> findProyectById(Long id);
	
	Optional<Proyect> findByPersonIdAndName(Long personId, String name);

	@Query("SELECT pro FROM Proyect pro")
	public List<Proyect> findProyects();

	@Modifying
	@Transactional
	Optional<Proyect> deleteProyectById(Long id);

}
