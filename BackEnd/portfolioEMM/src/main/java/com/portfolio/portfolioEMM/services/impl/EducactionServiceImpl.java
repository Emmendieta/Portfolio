package com.portfolio.portfolioEMM.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portfolio.portfolioEMM.entities.Education;
import com.portfolio.portfolioEMM.entities.Person;
import com.portfolio.portfolioEMM.exceptions.InternalServerErrorException;
import com.portfolio.portfolioEMM.exceptions.NotFountException;
import com.portfolio.portfolioEMM.exceptions.PortfolioException;
import com.portfolio.portfolioEMM.jsons.EducationCreateRest;
import com.portfolio.portfolioEMM.jsons.EducationRest;
import com.portfolio.portfolioEMM.repositories.EducationRepository;
import com.portfolio.portfolioEMM.repositories.PersonRepository;
import com.portfolio.portfolioEMM.services.EducationService;

@Service
public class EducactionServiceImpl implements EducationService {

	@Autowired
	EducationRepository educationRepository;

	@Autowired
	PersonRepository personRepository;

	public static final ModelMapper modelMapper = new ModelMapper();
	private static final Logger LOGGER = LoggerFactory.getLogger(EducactionServiceImpl.class);
	public static final String NOT_FOUND = "EDUCACTION NOT FOUND";
	public static final String INTERNAL_ERROR = "INTERNAL SERVER_ERROR";
	public static final String EDUCACION_OK = "Educaction save!";
	public static final String EDUCACION_UPTDATE = "Educaction update!";
	public static final String EDUCACION_NO_UPTDATE = "Error updating Education";
	public static final String EDUCACION_NO = "Educaction doesn´t save!";
	public static final String EDUCACION_DELETED = "Education has been deleted!";
	public static final String ALL_EDUCACION_DELETED = "All Education has been deleted!";
	public static final String PERSON_NO = "PERSON NOT FOUND!";
	public static final String EDUCATION_EXIST = "EDUCATION ALREDY EXISTS!";

	public String createEducation(EducationCreateRest educationCreateRest) throws PortfolioException {

		final Person personId = personRepository.findById(educationCreateRest.getPersonId())
				.orElseThrow(() -> new NotFountException(PERSON_NO, PERSON_NO));

		if (educationRepository.findByPersonIdAndNameAndTitle(personId.getId(), educationCreateRest.getName(),
				educationCreateRest.getTitle()).isPresent()) {
			throw new NotFountException(EDUCATION_EXIST, EDUCATION_EXIST);
		}

		Education education = new Education();
		education.setName(educationCreateRest.getName());
		education.setTitle(educationCreateRest.getTitle());
		education.setDescription(educationCreateRest.getDescription());
		education.setDateStart(educationCreateRest.getDateStart());
		education.setDateEnd(educationCreateRest.getDateEnd());
		education.setImage(educationCreateRest.getImage());
		education.setPerson(personId);

		try {
			educationRepository.save(education);
		} catch (Exception e) {
			LOGGER.error(EDUCACION_NO, e);
			throw new InternalServerErrorException(INTERNAL_ERROR, INTERNAL_ERROR);
		}

		return EDUCACION_OK;

	}

	public List<EducationRest> getAllEducations() throws PortfolioException {
		final List<Education> educationEntity = educationRepository.findEducations();
		return educationEntity.stream().map(service -> modelMapper.map(service, EducationRest.class))
				.collect(Collectors.toList());
	}

	public EducationRest getEducationById(Long id) throws PortfolioException {
		return modelMapper.map(getEducationEntity(id), EducationRest.class);
	}

	public String editEducaction(Long id, EducationCreateRest educationCreateRest) throws PortfolioException {
		final Person personId = personRepository.findById(educationCreateRest.getPersonId())
				.orElseThrow(() -> new NotFountException(PERSON_NO, PERSON_NO));

		if (educationRepository.findEducationById(id).isPresent()) {

			Education education = educationRepository.findEducationById(id).get();
			education.setName(educationCreateRest.getName());
			education.setTitle(educationCreateRest.getTitle());
			education.setDescription(educationCreateRest.getDescription());
			education.setDateStart(educationCreateRest.getDateStart());
			education.setDateEnd(educationCreateRest.getDateEnd());
			education.setImage(educationCreateRest.getImage());
			education.setPerson(personId);

			try {
				educationRepository.save(education);
			} catch (Exception e) {
				LOGGER.error(EDUCACION_NO, e);
				throw new InternalServerErrorException(INTERNAL_ERROR, INTERNAL_ERROR);
			}
			return EDUCACION_UPTDATE;
		} else {
			return EDUCACION_NO_UPTDATE;
		}

	}

	public String deleteEducationById(Long id) throws PortfolioException {
		educationRepository.findEducationById(id).orElseThrow(() -> new NotFountException(NOT_FOUND, NOT_FOUND));
		try {
			educationRepository.deleteEducationById(id);

		} catch (Exception e) {
			LOGGER.error(INTERNAL_ERROR, e);
			throw new InternalServerErrorException(INTERNAL_ERROR, INTERNAL_ERROR);

		}
		return EDUCACION_DELETED;
	}

	public String deleteAllEducations() throws PortfolioException {
		educationRepository.findEducations();
		try {
			educationRepository.deleteAll();
		} catch (Exception e) {
			LOGGER.error(INTERNAL_ERROR, e);
			throw new InternalServerErrorException(INTERNAL_ERROR, INTERNAL_ERROR);
		}
		return ALL_EDUCACION_DELETED;
	}

	private Education getEducationEntity(Long educationId) throws PortfolioException {
		return educationRepository.findEducationById(educationId)
				.orElseThrow(() -> new NotFountException("SNOT-404-1", NOT_FOUND));
	}

}
