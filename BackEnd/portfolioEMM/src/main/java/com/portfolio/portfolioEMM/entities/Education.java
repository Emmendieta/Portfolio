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
@Table(name = "EDUCATION")
public class Education {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private Long id;

	@Column(name = "NAMEED", nullable = false)
	private String nameEd;

	@Column(name = "TITLEED", nullable = false)
	private String titleEd;

	@Column(name = "DESCRIPTIONED")
	private String descriptionEd;

	@Column(name = "DATESTARTED", nullable = false)
	private Date dateStartEd;

	@Column(name = "DATEENDED")
	private Date dateEndEd;

	@Column(name = "IMAGEED")
	private String imageEd;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "PERSON_ID", unique = true, nullable = false)
	private Person person;

	public Education() {
		super();
	}

	public Education(String nameEd, String titleEd, String descriptionEd, Date dateStartEd, Date dateEndEd,
			String imageEd, Person person) {
		super();
		this.nameEd = nameEd;
		this.titleEd = titleEd;
		this.descriptionEd = descriptionEd;
		this.dateStartEd = dateStartEd;
		this.dateEndEd = dateEndEd;
		this.imageEd = imageEd;
		this.person = person;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNameEd() {
		return nameEd;
	}

	public void setNameEd(String nameEd) {
		this.nameEd = nameEd;
	}

	public String getTitleEd() {
		return titleEd;
	}

	public void setTitleEd(String titleEd) {
		this.titleEd = titleEd;
	}

	public String getDescriptionEd() {
		return descriptionEd;
	}

	public void setDescriptionEd(String descriptionEd) {
		this.descriptionEd = descriptionEd;
	}

	public Date getDateStartEd() {
		return dateStartEd;
	}

	public void setDateStartEd(Date dateStartEd) {
		this.dateStartEd = dateStartEd;
	}

	public Date getDateEndEd() {
		return dateEndEd;
	}

	public void setDateEndEd(Date dateEndEd) {
		this.dateEndEd = dateEndEd;
	}

	public String getImageEd() {
		return imageEd;
	}

	public void setImageEd(String imageEd) {
		this.imageEd = imageEd;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

}
