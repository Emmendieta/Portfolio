package com.portfolio.portfolioEMM.security.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.portfolio.portfolioEMM.security.entities.MainUser;
import com.portfolio.portfolioEMM.security.entities.User;
import com.portfolio.portfolioEMM.security.services.UserService;

@Service
public class UserServiceImpl implements UserDetailsService {

	@Autowired
	UserService userService;

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userService.getByUserName(username).get();
		return MainUser.build(user);
	}

}
