package com.portfolio.portfolioEMM.repositories;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.portfolio.portfolioEMM.entities.SocialMedias;

@Repository
public interface SocialMediaRepository extends JpaRepository<SocialMedias, Long> {

	Optional<SocialMedias> findSocialMediaById(Long id);

	Optional<SocialMedias> findSocialMediaByName(String name);

	@Query("SELECT sm FROM SocialMedias sm")
	public List<SocialMedias> findSocialMedias();

	@Modifying
	@Transactional
	Optional<SocialMedias> deleteSocialMediaById(Long id);

	@Modifying
	@Transactional
	Optional<SocialMedias> deleteSocialMediaByName(String name);

}
