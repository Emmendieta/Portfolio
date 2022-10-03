package com.portfolio.portfolioEMM.jsons;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExperienceCreateRest {

	@JsonProperty("name")
	private String name;

	@JsonProperty("title")
	private String title;

	@JsonProperty("activity")
	private String activity;

	@JsonProperty("dateStart")
	private Date dateStart;

	@JsonProperty("dateEnd")
	private Date dateEnd;

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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	public Date getDateStart() {
		return dateStart;
	}

	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}

	public Date getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
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

	public ExperienceCreateRest() {
	}

	public ExperienceCreateRest(String name, String title, String activity, Date dateStart, Date dateEnd, String image,
			Long personId) {
		this.name = name;
		this.title = title;
		this.activity = activity;
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
		this.image = image;
		this.personId = personId;
	}

}
