package com.portfolio.portfolioEMM.repositories;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.portfolio.portfolioEMM.entities.HardAndSoft;

@Repository
public interface HardAndSoftRepository extends JpaRepository<HardAndSoft, Long> {

	Optional<HardAndSoft> findHYSById(Long id);

	Optional<HardAndSoft> findHYSByName(String Name);

	@Query("SELECT hys FROM HARDANDSOFT hys")
	public List<HardAndSoft> findAllHYS();

	@Modifying
	@Transactional
	Optional<HardAndSoft> editHYQById(Long id);

	@Modifying
	@Transactional
	Optional<HardAndSoft> editHYQByName(String name);

	@Modifying
	@Transactional
	Optional<HardAndSoft> deteleHYSById(Long id);

	@Modifying
	@Transactional
	Optional<HardAndSoft> deleteHYSByName(String name);

	@Modifying
	@Transactional
	Optional<HardAndSoft> deleteAllHYS();

}
