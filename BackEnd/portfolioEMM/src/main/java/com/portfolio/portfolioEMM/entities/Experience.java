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
	private String nameEx;

	@Column(name = "TITLEEX", nullable = false)
	private String titleEx;

	@Column(name = "ACTIVITIESEX", nullable = false)
	private String activitiesEx;

	@Column(name = "DATESTARTEX", nullable = false)
	private Date dateStartEx;

	@Column(name = "DATEENDEX")
	private Date dateEndEX;

	@Column(name = "IMAGEEX")
	private String imageEx;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "PERSON_ID", nullable = false, unique = true)
	private Person person;

	public Experience() {
		super();
	}

	public Experience(String nameEx, String titleEx, String activitiesEx, Date dateStartEx, Date dateEndEX,
			String imageEx, Person person) {
		super();
		this.nameEx = nameEx;
		this.titleEx = titleEx;
		this.activitiesEx = activitiesEx;
		this.dateStartEx = dateStartEx;
		this.dateEndEX = dateEndEX;
		this.imageEx = imageEx;
		this.person = person;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNameEx() {
		return nameEx;
	}

	public void setNameEx(String nameEx) {
		this.nameEx = nameEx;
	}

	public String getTitleEx() {
		return titleEx;
	}

	public void setTitleEx(String titleEx) {
		this.titleEx = titleEx;
	}

	public String getActivitiesEx() {
		return activitiesEx;
	}

	public void setActivitiesEx(String activitiesEx) {
		this.activitiesEx = activitiesEx;
	}

	public Date getDateStartEx() {
		return dateStartEx;
	}

	public void setDateStartEx(Date dateStartEx) {
		this.dateStartEx = dateStartEx;
	}

	public Date getDateEndEX() {
		return dateEndEX;
	}

	public void setDateEndEX(Date dateEndEX) {
		this.dateEndEX = dateEndEX;
	}

	public String getImageEx() {
		return imageEx;
	}

	public void setImageEx(String imageEx) {
		this.imageEx = imageEx;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

}
