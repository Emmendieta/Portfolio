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
@Table(name = "PROYECTS")
public class Proyect {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private Long id;

	@Column(name = "NAMEPRO", nullable = false)
	private String namePro;

	@Column(name = "DESCRIPTIONPRO")
	private String descriptionPro;

	@Column(name = "LINKPRO", nullable = false)
	private String linkPro;

	@Column(name = "IMAGEPRO")
	private String imagePro;

	@Column(name = "DATESTARTPRO", nullable = false)
	private Date dateStartPro;

	@Column(name = "DATEENDPRO")
	private Date dateEndPro;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "PERSON_ID", unique = true, nullable = false)
	private Person person;

	public Proyect() {
		super();
	}

	public Proyect(String namePro, String descriptionPro, String linkPro, String imagePro, Date dateStartPro,
			Date dateEndPro, Person person) {
		super();
		this.namePro = namePro;
		this.descriptionPro = descriptionPro;
		this.linkPro = linkPro;
		this.imagePro = imagePro;
		this.dateStartPro = dateStartPro;
		this.dateEndPro = dateEndPro;
		this.person = person;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNamePro() {
		return namePro;
	}

	public void setNamePro(String namePro) {
		this.namePro = namePro;
	}

	public String getDescriptionPro() {
		return descriptionPro;
	}

	public void setDescriptionPro(String descriptionPro) {
		this.descriptionPro = descriptionPro;
	}

	public String getLinkPro() {
		return linkPro;
	}

	public void setLinkPro(String linkPro) {
		this.linkPro = linkPro;
	}

	public String getImagePro() {
		return imagePro;
	}

	public void setImagePro(String imagePro) {
		this.imagePro = imagePro;
	}

	public Date getDateStartPro() {
		return dateStartPro;
	}

	public void setDateStartPro(Date dateStartPro) {
		this.dateStartPro = dateStartPro;
	}

	public Date getDateEndPro() {
		return dateEndPro;
	}

	public void setDateEndPro(Date dateEndPro) {
		this.dateEndPro = dateEndPro;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

}
