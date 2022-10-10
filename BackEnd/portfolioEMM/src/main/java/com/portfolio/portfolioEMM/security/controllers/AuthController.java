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
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:4200")
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

	@PostMapping("/newUser")
	public ResponseEntity<?> newUser(@Valid @RequestBody NewUser newUser, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return new ResponseEntity<Object>(new Message("Error: Wrong type of input"), HttpStatus.BAD_REQUEST);

		if (userService.existsByUserName(newUser.getUserName()))
			return new ResponseEntity<Object>(new Message("Error: User Name alredy exist!"), HttpStatus.BAD_REQUEST);

		if (userService.existsByEmail(newUser.getEmail()))
			return new ResponseEntity(new Message("Error: Email alredy exist!"), HttpStatus.BAD_REQUEST);

		User user = new User(newUser.getName(), newUser.getUserName(), newUser.getEmail(),
				passwordEncoder.encode(newUser.getPassword()));

		Set<Rol> roles = new HashSet<>();
		roles.add(rolService.getByRolName(RolName.ROL_USER).get());

		if (newUser.getRols().contains("admin"))
			roles.add(rolService.getByRolName(RolName.ROL_ADMIN).get());
		user.setRols(roles);
		userService.save(user);

		return new ResponseEntity(new Message("User saved!"), HttpStatus.CREATED);
	}

	@PostMapping("/login")
	public ResponseEntity<JwtJson> login(@Valid @RequestBody LoginUser loginUsuario, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return new ResponseEntity(new Message("Campos mal puestos"), HttpStatus.BAD_REQUEST);

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginUsuario.getUserName(), loginUsuario.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = jwtProvider.generateToken(authentication);

		UserDetails userDetails = (UserDetails) authentication.getPrincipal();

		JwtJson jwtDto = new JwtJson(jwt, userDetails.getUsername(), userDetails.getAuthorities());

		return new ResponseEntity(jwtDto, HttpStatus.OK);
	}

	/*
	 * @PostMapping("/login") public ResponseEntity<JwtJson>
	 * login(@Valid @RequestBody LoginUser loginUser, BindingResult bindingResult) {
	 * if (bindingResult.hasErrors()) { return new ResponseEntity(new
	 * ErrorDto("Error: Invalid input!"), HttpStatus.BAD_REQUEST); } Authentication
	 * authentication = authenticationManager.authenticate( new
	 * UsernamePasswordAuthenticationToken(loginUser.getNameUser(),
	 * loginUser.getPassword()));
	 * 
	 * SecurityContextHolder.getContext().setAuthentication(authentication);
	 * 
	 * String jwt = jwtProvider.generateToke(authentication);
	 * 
	 * UserDetails userDetails = (UserDetails) authentication.getPrincipal();
	 * 
	 * JwtJson jwtJson = new JwtJson(jwt, userDetails.getUsername(),
	 * userDetails.getAuthorities());
	 * 
	 * return new ResponseEntity(jwtJson, HttpStatus.OK); }
	 */

}
