package com.portfolio.portfolioEMM.repositories;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.portfolio.portfolioEMM.entities.HardSoft;

@Repository
public interface HardSoftRepository extends JpaRepository<HardSoft, Long> {

	Optional<HardSoft> findHsById(Long id);

	Optional<HardSoft> findHYSByName(String name);

	@Query("SELECT hs FROM HardSoft hs")
	public List<HardSoft> findHards();

	@Modifying
	@Transactional
	Optional<HardSoft> deleteHardById(Long id);

	@Modifying
	@Transactional
	Optional<HardSoft> deleteHardByName(String name);

}
