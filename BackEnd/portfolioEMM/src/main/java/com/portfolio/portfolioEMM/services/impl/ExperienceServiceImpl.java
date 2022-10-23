package com.portfolio.portfolioEMM.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portfolio.portfolioEMM.entities.Experience;
import com.portfolio.portfolioEMM.entities.Person;
import com.portfolio.portfolioEMM.exceptions.InternalServerErrorException;
import com.portfolio.portfolioEMM.exceptions.NotFountException;
import com.portfolio.portfolioEMM.exceptions.PortfolioException;
import com.portfolio.portfolioEMM.jsons.ExperienceCreateRest;
import com.portfolio.portfolioEMM.jsons.ExperienceRest;
import com.portfolio.portfolioEMM.repositories.ExperienceRepository;
import com.portfolio.portfolioEMM.repositories.PersonRepository;
import com.portfolio.portfolioEMM.services.ExperienceService;

@Service
public class ExperienceServiceImpl implements ExperienceService {

	@Autowired
	ExperienceRepository experienceRepository;

	@Autowired
	PersonRepository personRepository;

	public static final ModelMapper modelMapper = new ModelMapper();
	private static final Logger LOGGER = LoggerFactory.getLogger(ExperienceServiceImpl.class);
	public static final String NOT_FOUND = "EXPERIENCE NOT FOUND";
	public static final String INTERNAL_ERROR = "INTERNAL SERVER ERROR";
	public static final String EXPERIENCE_OK = "Experience save!";
	public static final String EXPERIENCE_UPTDATE = "Experience update!";
	public static final String EXPERIENCE_NO_UPTDATE = "Error updating Experience";
	public static final String EXPERIENCE_NO = "Experience doesn´t save!";
	public static final String EXPERIENCE_DELETED = "The Experience has been deleted!";
	public static final String EXPERIENCE_NO_DELETED = "The Experience hasn´t been deleted!";
	public static final String ALL_EXPERIENCE_DELETED = "All Experience has been deleted!";
	public static final String PERSON_NO = "PERSON NOT FOUND!";
	public static final String EXPERIENCE_EXIST = "Experience ALREDY EXISTS!";

	public String createExperience(ExperienceCreateRest experienceCreateRest) throws PortfolioException {
		final Person personId = personRepository.findById(experienceCreateRest.getPersonId())
				.orElseThrow(() -> new NotFountException(PERSON_NO, PERSON_NO));

		if (experienceRepository.findByPersonIdAndNameAndTitle(personId.getId(), experienceCreateRest.getName(),
				experienceCreateRest.getTitle()).isPresent()) {
			throw new NotFountException(EXPERIENCE_EXIST, EXPERIENCE_EXIST);
		}

		Experience experience = new Experience();
		experience.setName(experienceCreateRest.getName());
		experience.setTitle(experienceCreateRest.getTitle());
		experience.setActivity(experienceCreateRest.getActivity());
		experience.setDateStart(experienceCreateRest.getDateStart());
		experience.setDateEnd(experienceCreateRest.getDateEnd());
		experience.setImage(experienceCreateRest.getImage());
		experience.setPerson(personId);

		try {
			experienceRepository.save(experience);
		} catch (Exception e) {
			LOGGER.error(EXPERIENCE_NO, e);
			throw new InternalServerErrorException(INTERNAL_ERROR, INTERNAL_ERROR);
		}

		return EXPERIENCE_OK;

	}
	
	
	/*public String createExperience(ExperienceCreateRest experienceCreateRest) throws PortfolioException {
		final Person personId = personRepository.findById(experienceCreateRest.getPersonId())
				.orElseThrow(() -> new NotFountException(PERSON_NO, PERSON_NO));

		if (experienceRepository.findByPersonIdAndNameAndTitle(personId.getId(), experienceCreateRest.getImage(),
				experienceCreateRest.getTitle()).isPresent()) {
			throw new NotFountException(EXPERIENCE_EXIST, EXPERIENCE_EXIST);
		}

		Experience experience = new Experience();
		experience.setName(experienceCreateRest.getName());
		experience.setTitle(experienceCreateRest.getTitle());
		experience.setActivity(experienceCreateRest.getActivity());
		experience.setDateStart(experienceCreateRest.getDateStart());
		experience.setDateEnd(experienceCreateRest.getDateEnd());
		experience.setImage(experienceCreateRest.getImage());
		experience.setPerson(personId);

		try {
			experienceRepository.save(experience);
		} catch (Exception e) {
			LOGGER.error(EXPERIENCE_NO, e);
			throw new InternalServerErrorException(INTERNAL_ERROR, INTERNAL_ERROR);
		}

		return EXPERIENCE_OK;

	}*/

	public List<ExperienceRest> getAllExperiences() throws PortfolioException {
		final List<Experience> experienceEntity = experienceRepository.findAll();
		return experienceEntity.stream().map(service -> modelMapper.map(service, ExperienceRest.class))
				.collect(Collectors.toList());
	}

	public ExperienceRest getExperienceById(Long id) throws PortfolioException {
		return modelMapper.map(getExperienceEntity(id), ExperienceRest.class);
	}

	public String updateExpierenceById(Long id, ExperienceCreateRest experienceCreateRest) throws PortfolioException {

		final Person personId = personRepository.findById(experienceCreateRest.getPersonId())
				.orElseThrow(() -> new NotFountException(PERSON_NO, PERSON_NO));

		if (experienceRepository.findExperienceById(id).isPresent()) {

			Experience experience = experienceRepository.findExperienceById(id).get();
			experience.setName(experienceCreateRest.getName());
			experience.setImage(experienceCreateRest.getImage());
			experience.setTitle(experienceCreateRest.getTitle());
			experience.setActivity(experienceCreateRest.getActivity());
			experience.setDateStart(experienceCreateRest.getDateStart());
			experience.setDateEnd(experienceCreateRest.getDateEnd());
			experience.setPerson(personId);

			try {
				experienceRepository.save(experience);
			} catch (Exception e) {
				LOGGER.error(EXPERIENCE_NO, e);
				throw new InternalServerErrorException(INTERNAL_ERROR, INTERNAL_ERROR);
			}

			return EXPERIENCE_UPTDATE;

		} else {
			throw new InternalServerErrorException(EXPERIENCE_NO_UPTDATE, EXPERIENCE_NO_UPTDATE);
		}

	}

	public String deleteExperienceById(Long id) throws PortfolioException {
		experienceRepository.findExperienceById(id).orElseThrow(() -> new NotFountException(NOT_FOUND, NOT_FOUND));
		try {
			experienceRepository.deleteExperienceById(id);

		} catch (Exception e) {

			LOGGER.error(INTERNAL_ERROR, e);
			throw new InternalServerErrorException(INTERNAL_ERROR, INTERNAL_ERROR);
		}
		return EXPERIENCE_DELETED;
	}

	public String deleteAllExperiences() throws PortfolioException {
		experienceRepository.findAll();
		try {
			experienceRepository.deleteAll();
			return ALL_EXPERIENCE_DELETED;
		} catch (Exception e) {
			LOGGER.error(INTERNAL_ERROR, e);
			throw new InternalServerErrorException(INTERNAL_ERROR, INTERNAL_ERROR);
		}
	}

	private Experience getExperienceEntity(Long experienceId) throws PortfolioException {
		return experienceRepository.findExperienceById(experienceId)
				.orElseThrow(() -> new NotFountException("SNOT-404-1", NOT_FOUND));
	}

}
