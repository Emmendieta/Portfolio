package com.portfolio.portfolioEMM.repositories;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.portfolio.portfolioEMM.entities.Education;

@Repository
public interface EducationRepository extends JpaRepository<Education, Long> {

	Optional<Education> findEducationById(Long id);

	@Query("SELECT edu FROM Education edu")
	public List<Education> findAllEducation();

	@Modifying
	@Transactional
	Optional<Education> editEducationById(Long id);

	@Modifying
	@Transactional
	Optional<Education> delteEducationById(Long id);

	@Modifying
	@Transactional
	Optional<Education> deleteAllEducation();

}
