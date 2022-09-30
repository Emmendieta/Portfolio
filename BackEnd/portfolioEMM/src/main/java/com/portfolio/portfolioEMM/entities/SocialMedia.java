package com.portfolio.portfolioEMM.entities;

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
@Table(name = "SOCIALMEDIAS")
public class SocialMedia {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private Long id;

	@Column(name = "NAMESM", nullable = false)
	private String nameSM;

	@Column(name = "IMAGESM", nullable = false)
	private String imageSM;

	@Column(name = "URLSM", nullable = false)
	private String urlSM;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "PERSON_ID", unique = true, nullable = false)
	private Person personSM;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNameSM() {
		return nameSM;
	}

	public void setNameSM(String nameSM) {
		this.nameSM = nameSM;
	}

	public String getImageSM() {
		return imageSM;
	}

	public void setImageSM(String imageSM) {
		this.imageSM = imageSM;
	}

	public String getUrlSM() {
		return urlSM;
	}

	public void setUrlSM(String urlSM) {
		this.urlSM = urlSM;
	}

	public Person getPersonSM() {
		return personSM;
	}

	public void setPersonSM(Person personSM) {
		this.personSM = personSM;
	}

}
