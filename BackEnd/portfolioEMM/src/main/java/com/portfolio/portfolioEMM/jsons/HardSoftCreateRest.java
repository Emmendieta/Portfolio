package com.portfolio.portfolioEMM.jsons;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class HardSoftCreateRest {

	@JsonProperty("name")
	private String name;

	@JsonProperty("porcent")
	private int porcent;

	@JsonProperty("image")
	private String image;

	@JsonProperty("personId")
	private Long personId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPorcent() {
		return porcent;
	}

	public void setPorcent(int porcent) {
		this.porcent = porcent;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Long getPersonId() {
		return personId;
	}

	public void setPersonId(Long personId) {
		this.personId = personId;
	}

}
