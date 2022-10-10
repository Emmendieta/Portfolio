package com.portfolio.portfolioEMM.security.services;


import java.util.Optional;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portfolio.portfolioEMM.security.entities.Rol;
import com.portfolio.portfolioEMM.security.enums.RolName;
import com.portfolio.portfolioEMM.security.repositories.RolRepository;

@Service
@Transactional
public class RolService {
	
	@Autowired
	RolRepository rolRepository;
	
	public Optional<Rol> getByRolName(RolName rolName){
		return rolRepository.findByRolName(rolName);
	}
	
	public void save(Rol rol) {
		rolRepository.save(rol);
	}

}
