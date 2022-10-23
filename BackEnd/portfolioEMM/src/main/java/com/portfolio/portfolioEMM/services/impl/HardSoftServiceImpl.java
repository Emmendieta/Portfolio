package com.portfolio.portfolioEMM.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portfolio.portfolioEMM.entities.HardSoft;
import com.portfolio.portfolioEMM.entities.Person;
import com.portfolio.portfolioEMM.exceptions.InternalServerErrorException;
import com.portfolio.portfolioEMM.exceptions.NotFountException;
import com.portfolio.portfolioEMM.exceptions.PortfolioException;
import com.portfolio.portfolioEMM.jsons.HardSoftCreateRest;
import com.portfolio.portfolioEMM.jsons.HardSoftRest;
import com.portfolio.portfolioEMM.repositories.HardSoftRepository;
import com.portfolio.portfolioEMM.repositories.PersonRepository;
import com.portfolio.portfolioEMM.services.HardSoftService;

@Service
public class HardSoftServiceImpl implements HardSoftService {

	@Autowired
	HardSoftRepository hardSoftRepository;

	@Autowired
	PersonRepository personRepository;

	public static final ModelMapper modelMapper = new ModelMapper();
	private static final Logger LOGGER = LoggerFactory.getLogger(HardSoftServiceImpl.class);
	public static final String NOT_FOUND = "HARD OR SOFT SKILL NOT FOUND";
	public static final String INTERNAL_ERROR = "INTERNAL SERVER_ERROR";
	public static final String HYS_OK = "Hard or Soft SKill save!";
	public static final String HYS_UPTDATE = "Hard or Soft SKill update!";
	public static final String HYS_NO_UPTDATE = "Error updating Hard or Soft SKill";
	public static final String HYS_NO = "Hard or Soft SKill doesn´t save!";
	public static final String HYS_DELETED = "The Hard or Soft SKills has been deleted!";
	public static final String HYS_NO_DELETED = "The Hard or Soft SKills couldn´t been deleted!";
	public static final String ALL_HYS_DELETED = "All Hard or Soft SKills has been deleted!";
	public static final String PERSON_NO = "PERSON NOT FOUND!";
	public static final String HYS_EXIST = "HARD OR SOFT SKILL ALREDY EXISTS!";

	@Override
	public String createHardSoft(HardSoftCreateRest hardSoftCreateRest) throws PortfolioException {

		Person personId = personRepository.findById(hardSoftCreateRest.getPersonId())
				.orElseThrow(() -> new NotFountException(PERSON_NO, PERSON_NO));

		if (hardSoftRepository.findByPersonIdAndName(personId.getId(), hardSoftCreateRest.getName()).isPresent()) {
			throw new NotFountException(HYS_EXIST, HYS_EXIST);
		}
		HardSoft hs = new HardSoft();
		hs.setName(hardSoftCreateRest.getName());
		hs.setPercent(hardSoftCreateRest.getPercent());
		hs.setImage(hardSoftCreateRest.getImage());
		hs.setPerson(personId);

		try {
			hardSoftRepository.save(hs);
		} catch (Exception e) {
			LOGGER.error(HYS_NO, e);
			throw new InternalServerErrorException(INTERNAL_ERROR, INTERNAL_ERROR);
		}

		return HYS_OK;

	}

	public List<HardSoftRest> getAllHardSoft() throws PortfolioException {
		final List<HardSoft> hsEntity = hardSoftRepository.findHards();
		return hsEntity.stream().map(service -> modelMapper.map(service, HardSoftRest.class))
				.collect(Collectors.toList());
	}

	public HardSoftRest getHardSoftById(Long id) throws PortfolioException {
		return modelMapper.map(getHSEntity(id), HardSoftRest.class);
	}

	public HardSoftRest getHardSoftByName(String name) throws PortfolioException {
		return modelMapper.map(getHSEntityName(name), HardSoftRest.class);
	}

	public String updateHardSoftById(Long id, HardSoftCreateRest hardSoftCreateRest) throws PortfolioException {

		final Person personId = personRepository.findById(hardSoftCreateRest.getPersonId())
				.orElseThrow(() -> new NotFountException(PERSON_NO, PERSON_NO));

		if (hardSoftRepository.findHsById(id).isPresent()) {

			HardSoft hs = hardSoftRepository.findHsById(id).get();
			hs.setName(hardSoftCreateRest.getName());
			hs.setPercent(hardSoftCreateRest.getPercent());
			hs.setImage(hardSoftCreateRest.getImage());
			hs.setPerson(personId);

			try {
				hardSoftRepository.save(hs);
			} catch (Exception e) {
				LOGGER.error(HYS_NO_UPTDATE, e);
				throw new InternalServerErrorException(INTERNAL_ERROR, INTERNAL_ERROR);
			}
			return HYS_UPTDATE;
		} else {
			throw new InternalServerErrorException(HYS_NO_DELETED, HYS_NO_DELETED);
		}

	}

	public String deleteHardSoftById(Long id) throws PortfolioException {
		hardSoftRepository.findHsById(id).orElseThrow(() -> new NotFountException(HYS_NO, HYS_OK));
		try {
			hardSoftRepository.deleteHardById(id);
		} catch (Exception e) {
			LOGGER.error(HYS_NO_DELETED, e);
			throw new InternalServerErrorException(INTERNAL_ERROR, INTERNAL_ERROR);
		}
		return HYS_DELETED;
	}

	public String deleteAllHardSoft() throws PortfolioException {
		hardSoftRepository.findHards();
		try {
			hardSoftRepository.deleteAll();
		} catch (Exception e) {
			LOGGER.error(HYS_NO_DELETED, e);
			throw new InternalServerErrorException(INTERNAL_ERROR, INTERNAL_ERROR);
		}
		return ALL_HYS_DELETED;
	}

	private HardSoft getHSEntity(Long hSid) throws PortfolioException {
		return hardSoftRepository.findHsById(hSid).orElseThrow(() -> new NotFountException("SNOT-404-1", HYS_NO));
	}

	private HardSoft getHSEntityName(String name) throws PortfolioException {
		return hardSoftRepository.findHYSByName(name).orElseThrow(() -> new NotFountException(HYS_NO, HYS_NO));
	}

}
