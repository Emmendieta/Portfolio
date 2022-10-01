package com.portfolio.portfolioEMM.entities;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "EXPERIENCE")
public class Experience {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private Long id;

	@Column(name = "NAMEEX", nullable = false)
	private String name;

	@Column(name = "TITLEEX", nullable = false)
	private String title;

	@Column(name = "ACTIVITIESEX", nullable = false)
	private String activities;

	@Column(name = "DATESTARTEX", nullable = false)
	private Date dateStart;

	@Column(name = "DATEENDEX")
	private Date dateEnd;

	@Column(name = "IMAGEEX")
	private String image;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "PERSON_ID", nullable = false, unique = true)
	private Person person;

	public Experience() {
		super();
	}

	public Experience(Long id, String name, String title, String activities, Date dateStart, Date dateEnd, String image,
			Person person) {
		super();
		this.id = id;
		this.name = name;
		this.title = title;
		this.activities = activities;
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
		this.image = image;
		this.person = person;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public String getActivities() {
		return activities;
	}

	public void setActivities(String activities) {
		this.activities = activities;
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

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

}
