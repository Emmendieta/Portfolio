package com.portfolio.portfolioEMM.security.jsons;

import java.util.HashSet;
import java.util.Set;

public class NewUser {

	private String name;
	private String userName;
	private String email;
	private String password;
	private Long personId;

	private Set<String> rols = new HashSet<>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<String> getRols() {
		return rols;
	}

	public void setRols(Set<String> rols) {
		this.rols = rols;
	}

	public Long getPersonId() {
		return personId;
	}

	public void setPersonId(Long personId) {
		this.personId = personId;
	}

}
