package com.portfolio.portfolioEMM.security.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.portfolio.portfolioEMM.security.entities.Rol;
import com.portfolio.portfolioEMM.security.enums.RolName;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {

	Optional<Rol> findByRolName(RolName rolName);

}
