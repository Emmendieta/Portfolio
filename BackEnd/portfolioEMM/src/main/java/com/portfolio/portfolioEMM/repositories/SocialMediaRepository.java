package com.portfolio.portfolioEMM.repositories;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.portfolio.portfolioEMM.entities.SocialMedia;

@Repository
public interface SocialMediaRepository extends JpaRepository<SocialMedia, Long> {

	Optional<SocialMedia> findSocialMediaById(Long id);

	Optional<SocialMedia> findSocialMediaByName(String name);

	@Query("SELECT sm FROM SOCIALMEDIAS sm")
	public List<SocialMedia> findAllSM();

	@Modifying
	@Transactional
	Optional<SocialMedia> editSocialMediaById(Long id);

	@Modifying
	@Transactional
	Optional<SocialMedia> editSocialMediaByName(String name);

	@Modifying
	@Transactional
	Optional<SocialMedia> deleteSocialMediaById(Long id);

	@Modifying
	@Transactional
	Optional<SocialMedia> deleteSocialMediaByName(String name);

	@Modifying
	@Transactional
	Optional<SocialMedia> deleteAllSocialMedias();
}
