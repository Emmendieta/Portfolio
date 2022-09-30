package com.portfolio.portfolioEMM.repositories;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.portfolio.portfolioEMM.entities.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

	Optional<Person> findById(Long id);

	@Query("SELECT p FROM PERSON p")
	public List<Person> findAll();

	@Modifying
	@Transactional
	Optional<Person> editPersonById(Long id);

	@Modifying
	@Transactional
	Optional<Person> deletePersonById(Long id);

	@Modifying
	@Transactional
	Optional<Person> deleteAllPerson();

}
