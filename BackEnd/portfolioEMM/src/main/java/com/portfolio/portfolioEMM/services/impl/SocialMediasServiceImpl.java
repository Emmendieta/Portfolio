package com.portfolio.portfolioEMM.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portfolio.portfolioEMM.entities.Person;
import com.portfolio.portfolioEMM.entities.SocialMedias;
import com.portfolio.portfolioEMM.exceptions.InternalServerErrorException;
import com.portfolio.portfolioEMM.exceptions.NotFountException;
import com.portfolio.portfolioEMM.exceptions.PortfolioException;
import com.portfolio.portfolioEMM.jsons.SocialMediasCreateRest;
import com.portfolio.portfolioEMM.jsons.SocialMediasRest;
import com.portfolio.portfolioEMM.repositories.PersonRepository;
import com.portfolio.portfolioEMM.repositories.SocialMediaRepository;
import com.portfolio.portfolioEMM.services.SocialMediasService;

@Service
public class SocialMediasServiceImpl implements SocialMediasService {

	@Autowired
	SocialMediaRepository socialMediaRepository;

	@Autowired
	PersonRepository personRepository;

	public static final ModelMapper modelMapper = new ModelMapper();
	private static final Logger LOGGER = LoggerFactory.getLogger(SocialMediasServiceImpl.class);
	public static final String NOT_FOUND = "SOCIAL MEDIA NOT FOUND";
	public static final String INTERNAL_ERROR = "INTERNAL SERVER_ERROR";
	public static final String SOCIAL_OK = "Social Media save!";
	public static final String SOCIAL_UPTDATE = "Social Media update!";
	public static final String SOCIAL_NO_UPTDATE = "Error updating Social Media";
	public static final String SOCIAL_NO = "Social Media doesn´t save!";
	public static final String SOCIAL_NO_DELETED = "Error trying to delete Social Media!";
	public static final String SOCIAL_DELETED = "Social Media has been deleted!";
	public static final String ALL_SOCIALS_DELETED = "All Social Medias has been deleted!";
	public static final String PERSON_NO = "PERSON NOT FOUND!";
	public static final String SOCIAL_EXIST = "SOCIAL MEDIA ALREDY EXISTS!";

	public String createSocialMedia(SocialMediasCreateRest socialMediasCreateRest) throws PortfolioException {

		final Person personId = personRepository.findById(socialMediasCreateRest.getPersonId())
				.orElseThrow(() -> new NotFountException(PERSON_NO, PERSON_NO));

		if (socialMediaRepository.findByPersonIdAndName(personId.getId(), socialMediasCreateRest.getName())
				.isPresent()) {
			throw new NotFountException(SOCIAL_EXIST, SOCIAL_EXIST);
		} else {

		}

		SocialMedias socialMedias = new SocialMedias();
		socialMedias.setName(socialMediasCreateRest.getName());
		socialMedias.setImage(socialMediasCreateRest.getImage());
		socialMedias.setUrl(socialMediasCreateRest.getUrl());
		socialMedias.setPerson(personId);

		try {
			socialMediaRepository.save(socialMedias);
		} catch (Exception e) {
			LOGGER.error(SOCIAL_NO, e);
			throw new InternalServerErrorException(INTERNAL_ERROR, INTERNAL_ERROR);
		}

		return SOCIAL_OK;
	}

	public List<SocialMediasRest> findAllSocialMedias() throws PortfolioException {
		final List<SocialMedias> socialMediastEntity = socialMediaRepository.findSocialMedias();
		return socialMediastEntity.stream().map(service -> modelMapper.map(service, SocialMediasRest.class))
				.collect(Collectors.toList());
	}

	public SocialMediasRest findSocialMediaById(Long id) throws PortfolioException {
		return modelMapper.map(getSocialMediasEntity(id), SocialMediasRest.class);
	}

	public String updateSocialMediaById(Long id, SocialMediasCreateRest socialMediasCreateRest)
			throws PortfolioException {

		final Person personId = personRepository.findById(socialMediasCreateRest.getPersonId())
				.orElseThrow(() -> new NotFountException(PERSON_NO, PERSON_NO));

		if (socialMediaRepository.findSocialMediaById(id).isPresent()) {

			SocialMedias socialMedias = socialMediaRepository.findSocialMediaById(id).get();
			socialMedias.setName(socialMediasCreateRest.getName());
			socialMedias.setImage(socialMediasCreateRest.getImage());
			socialMedias.setUrl(socialMediasCreateRest.getUrl());
			socialMedias.setPerson(personId);

			try {
				socialMediaRepository.save(socialMedias);
				return SOCIAL_UPTDATE;
			} catch (Exception e) {
				LOGGER.error(SOCIAL_NO, e);
				throw new InternalServerErrorException(INTERNAL_ERROR, INTERNAL_ERROR);
			}

		} else {
			throw new InternalServerErrorException(SOCIAL_NO_UPTDATE, SOCIAL_NO_UPTDATE);
		}
	}

	public String deleteSocialMediaById(Long id) throws PortfolioException {
		socialMediaRepository.findSocialMediaById(id);
		try {
			socialMediaRepository.deleteSocialMediaById(id);
		} catch (Exception e) {
			LOGGER.error(INTERNAL_ERROR, e);
			throw new InternalServerErrorException(INTERNAL_ERROR, INTERNAL_ERROR);
		}
		return SOCIAL_DELETED;
	}

	public String deleteAllSocialMedias() throws PortfolioException {
		socialMediaRepository.findSocialMedias();
		try {
			socialMediaRepository.deleteAll();
		} catch (Exception e) {
			LOGGER.error(INTERNAL_ERROR, e);
			throw new InternalServerErrorException(INTERNAL_ERROR, INTERNAL_ERROR);
		}
		return ALL_SOCIALS_DELETED;
	}

	private SocialMedias getSocialMediasEntity(Long socialMediaId) throws PortfolioException {
		return socialMediaRepository.findSocialMediaById(socialMediaId)
				.orElseThrow(() -> new NotFountException("SNOT-404-1", NOT_FOUND));
	}

}
