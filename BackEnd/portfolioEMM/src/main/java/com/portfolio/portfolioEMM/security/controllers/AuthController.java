package com.portfolio.portfolioEMM.security.controllers;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portfolio.portfolioEMM.dtos.Message;
import com.portfolio.portfolioEMM.entities.Person;
import com.portfolio.portfolioEMM.exceptions.NotFountException;
import com.portfolio.portfolioEMM.exceptions.PortfolioException;
import com.portfolio.portfolioEMM.repositories.PersonRepository;
import com.portfolio.portfolioEMM.security.entities.Rol;
import com.portfolio.portfolioEMM.security.entities.User;
import com.portfolio.portfolioEMM.security.enums.RolName;
import com.portfolio.portfolioEMM.security.jsons.JwtJson;
import com.portfolio.portfolioEMM.security.jsons.LoginUser;
import com.portfolio.portfolioEMM.security.jsons.NewUser;
import com.portfolio.portfolioEMM.security.jwt.JwtProvider;
import com.portfolio.portfolioEMM.security.services.RolService;
import com.portfolio.portfolioEMM.security.services.UserService;

@RestController
@CrossOrigin(origins = "https://frontendportfolioemm.web.app/")
//@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("portfolio/v1/auth/")
public class AuthController {

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserService userService;

	@Autowired
	RolService rolService;

	@Autowired
	JwtProvider jwtProvider;

	@Autowired
	PersonRepository personRepository;

	public static final String PERSON_NO = "PERSON NOT FOUND!";

	@PostMapping("newUser")
	public ResponseEntity<?> newUser(@Valid @RequestBody NewUser newUser, BindingResult bindingResult)
			throws PortfolioException {

		if (bindingResult.hasErrors())
			return new ResponseEntity<Object>(new Message("Error: Wrong type of input"), HttpStatus.BAD_REQUEST);

		if (userService.existsByUserName(newUser.getUserName()))
			return new ResponseEntity<Object>(new Message("Error: User Name alredy exist!"), HttpStatus.BAD_REQUEST);

		if (userService.existsByEmail(newUser.getEmail()))
			return new ResponseEntity(new Message("Error: Email alredy exist!"), HttpStatus.BAD_REQUEST);

		final Person personId = personRepository.findById(newUser.getPersonId())
				.orElseThrow(() -> new NotFountException(PERSON_NO, PERSON_NO));
		
		User user = new User();
		user.setName(newUser.getName());
		user.setUserName(newUser.getUserName());
		user.setEmail(newUser.getEmail());
		user.setPassword(passwordEncoder.encode(newUser.getPassword()));
		user.setPerson(personId);

		Set<Rol> rols = new HashSet<>();
		rols.add(rolService.getByRolName(RolName.ROLE_USER).get());

		if (newUser.getRols().contains("admin"))
			rols.add(rolService.getByRolName(RolName.ROLE_ADMIN).get());
		user.setRols(rols);
		userService.save(user);

		return new ResponseEntity(new Message("User saved!"), HttpStatus.CREATED);
	}

	@PostMapping("login")
	public ResponseEntity<JwtJson> login(@Valid @RequestBody LoginUser loginUser, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return new ResponseEntity(new Message("Error: Wrong fields!"), HttpStatus.BAD_REQUEST);

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginUser.getUserName(), loginUser.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = jwtProvider.generateToken(authentication);

		UserDetails userDetails = (UserDetails) authentication.getPrincipal();

		JwtJson jwtDto = new JwtJson(jwt, userDetails.getUsername(), userDetails.getAuthorities());

		return new ResponseEntity(jwtDto, HttpStatus.OK);
	}

}
