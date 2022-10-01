package com.portfolio.portfolioEMM.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portfolio.portfolioEMM.entities.Person;
import com.portfolio.portfolioEMM.entities.Proyect;
import com.portfolio.portfolioEMM.exceptions.InternalServerErrorException;
import com.portfolio.portfolioEMM.exceptions.NotFountException;
import com.portfolio.portfolioEMM.exceptions.PortfolioException;
import com.portfolio.portfolioEMM.jsons.ProyectCreateRest;
import com.portfolio.portfolioEMM.jsons.ProyectRest;
import com.portfolio.portfolioEMM.repositories.PersonRepository;
import com.portfolio.portfolioEMM.repositories.ProyectRepository;
import com.portfolio.portfolioEMM.services.ProyectService;

@Service
public class ProyectServiceImpl implements ProyectService {

	@Autowired
	ProyectRepository proyectRepository;

	@Autowired
	PersonRepository personRepository;

	public static final ModelMapper modelMapper = new ModelMapper();
	private static final Logger LOGGER = LoggerFactory.getLogger(EducactionServiceImpl.class);
	public static final String NOT_FOUND = "PROYECT NOT FOUND";
	public static final String INTERNAL_ERROR = "INTERNAL SERVER_ERROR";
	public static final String PROYECT_OK = "Proyect save!";
	public static final String PROYECT_UPTDATE = "Proyect update!";
	public static final String PROYECT_NO_UPTDATE = "Error updating Education";
	public static final String PROYECT_NO = "Proyect doesn´t save!";
	public static final String PROYECT_NO_DELETED = "Error trying to delete proyect!";
	public static final String PROYECT_DELETED = "Proyect has been deleted!";
	public static final String ALL_PROYECTS_DELETED = "All Proyects has been deleted!";
	public static final String PERSON_NO = "PERSON NOT FOUND!";
	public static final String PROYECT_EXIST = "PROYECT ALREDY EXISTS!";

	public String createProyect(ProyectCreateRest proyectCreateRest) throws PortfolioException {

		final Person personId = personRepository.findById(proyectCreateRest.getPersonId())
				.orElseThrow(() -> new NotFountException(PERSON_NO, PERSON_NO));

		if (proyectRepository.findByPersonIdAndName(personId.getId(), proyectCreateRest.getName()).isPresent()) {
			throw new NotFountException(PROYECT_EXIST, PROYECT_EXIST);
		}

		Proyect proyect = new Proyect();
		proyect.setName(proyectCreateRest.getName());
		proyect.setDescription(proyectCreateRest.getDescription());
		proyect.setLink(proyectCreateRest.getLink());
		proyect.setDateStart(proyectCreateRest.getDateStart());
		proyect.setDateEnd(proyectCreateRest.getDateEnd());
		proyect.setImage(proyectCreateRest.getImage());
		proyect.setPerson(personId);

		try {
			proyectRepository.save(proyect);
		} catch (Exception e) {
			LOGGER.error(PROYECT_NO, e);
			throw new InternalServerErrorException(INTERNAL_ERROR, INTERNAL_ERROR);
		}

		return PROYECT_OK;
	}

	public List<ProyectRest> findAllProyects() throws PortfolioException {

		final List<Proyect> proyectEntity = proyectRepository.findProyects();
		return proyectEntity.stream().map(service -> modelMapper.map(service, ProyectRest.class))
				.collect(Collectors.toList());
	}

	public ProyectRest findProyectById(Long id) throws PortfolioException {
		return modelMapper.map(getProyectEntity(id), ProyectRest.class);
	}

	public String updateProyectById(Long id, ProyectRest proyectRest) throws PortfolioException {
		final Person personId = personRepository.findById(proyectRest.getPersonId())
				.orElseThrow(() -> new NotFountException(PERSON_NO, PERSON_NO));

		if (proyectRepository.findByPersonIdAndName(personId.getId(), proyectRest.getName()).isPresent()) {

			Proyect proyect = getProyectEntity(id);
			proyect.setName(proyectRest.getName());
			proyect.setLink(proyectRest.getLink());
			proyect.setDescription(proyectRest.getDescription());
			proyect.setDateStart(proyectRest.getDateStart());
			proyect.setDateEnd(proyectRest.getDateEnd());
			proyect.setImage(proyectRest.getImage());
			proyect.setPerson(personId);

			try {
				proyectRepository.save(proyect);
			} catch (Exception e) {
				LOGGER.error(PROYECT_NO, e);
				throw new InternalServerErrorException(INTERNAL_ERROR, INTERNAL_ERROR);
			}
			return PROYECT_UPTDATE;
		} else {
			return PROYECT_NO_UPTDATE;
		}

	}

	public String deleteProyectById(Long id) throws PortfolioException {
		proyectRepository.findProyectById(id).orElseThrow(() -> new NotFountException(NOT_FOUND, NOT_FOUND));
		try {
			proyectRepository.deleteProyectById(id)
					.orElseThrow(() -> new NotFountException(PROYECT_NO_DELETED, PROYECT_NO_DELETED));

		} catch (Exception e) {
			LOGGER.error(INTERNAL_ERROR, e);
			throw new InternalServerErrorException(INTERNAL_ERROR, INTERNAL_ERROR);

		}
		return PROYECT_DELETED;
	}

	public String deleteAllProyects() throws PortfolioException {
		proyectRepository.findProyects();
		try {
			proyectRepository.deleteAll();
		} catch (Exception e) {
			LOGGER.error(INTERNAL_ERROR, e);
			throw new InternalServerErrorException(INTERNAL_ERROR, INTERNAL_ERROR);
		}
		return ALL_PROYECTS_DELETED;
	}

	private Proyect getProyectEntity(Long educationId) throws PortfolioException {
		return proyectRepository.findProyectById(educationId)
				.orElseThrow(() -> new NotFountException("SNOT-404-1", NOT_FOUND));
	}

}
