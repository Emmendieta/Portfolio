package com.portfolio.portfolioEMM.repositories;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.portfolio.portfolioEMM.entities.Experience;

@Repository
public interface ExperienceRepository extends JpaRepository<Experience, Long> {

	Optional<Experience> findExperienceById(Long id);

	@Query("SELECT exp FROM Experience exp")
	public List<Experience> findExperiences();

	Optional<Experience> findByPersonIdAndNameAndTitle(Long personId, String name, String title);

	@Modifying
	@Transactional
	Optional<Experience> deleteExperienceById(Long id);

}
