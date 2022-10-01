package com.portfolio.portfolioEMM.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "PERSON")
public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private Long id;

	@Column(name = "NAMEP", nullable = false)
	private String nameP;

	@Column(name = "LASTNAMEP", nullable = false)
	private String lastNameP;

	@Column(name = "AGEP", nullable = false)
	private Date ageP;

	@Column(name = "TITLEP", nullable = false)
	private String titleP;

	@Column(name = "ABOUTP")
	private String aboutP;

	@Column(name = "PROVINCEP", nullable = false)
	private String provinceP;

	@Column(name = "COUNTRYP", nullable = false)
	private String countryP;

	@Column(name = "IMAGEP")
	private String imageP;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "person")
	private List<Experience> experiences;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "person")
	private List<Education> educations;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "person")
	private List<HardSoft> hardAndSofts;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "person")
	private List<Proyect> proyects;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "person")
	private List<SocialMedias> socialMedias;

	public Person() {
		super();
	}

	public Person(String nameP, String lastNameP, Date ageP, String titleP, String aboutP, String provinceP,
			String countryP, String imageP, List<Experience> experiences, List<Education> educations,
			List<HardSoft> hardAndSofts, List<Proyect> proyects, List<SocialMedias> socialMedias) {
		super();
		this.nameP = nameP;
		this.lastNameP = lastNameP;
		this.ageP = ageP;
		this.titleP = titleP;
		this.aboutP = aboutP;
		this.provinceP = provinceP;
		this.countryP = countryP;
		this.imageP = imageP;
		this.experiences = experiences;
		this.educations = educations;
		this.hardAndSofts = hardAndSofts;
		this.proyects = proyects;
		this.socialMedias = socialMedias;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNameP() {
		return nameP;
	}

	public void setNameP(String nameP) {
		this.nameP = nameP;
	}

	public String getLastNameP() {
		return lastNameP;
	}

	public void setLastNameP(String lastNameP) {
		this.lastNameP = lastNameP;
	}

	public Date getAgeP() {
		return ageP;
	}

	public void setAgeP(Date ageP) {
		this.ageP = ageP;
	}

	public String getTitleP() {
		return titleP;
	}

	public void setTitleP(String titleP) {
		this.titleP = titleP;
	}

	public String getAboutP() {
		return aboutP;
	}

	public void setAboutP(String aboutP) {
		this.aboutP = aboutP;
	}

	public String getProvinceP() {
		return provinceP;
	}

	public void setProvinceP(String provinceP) {
		this.provinceP = provinceP;
	}

	public String getCountryP() {
		return countryP;
	}

	public void setCountryP(String countryP) {
		this.countryP = countryP;
	}

	public String getImageP() {
		return imageP;
	}

	public void setImageP(String imageP) {
		this.imageP = imageP;
	}

	public List<Experience> getExperiences() {
		return experiences;
	}

	public void setExperiences(List<Experience> experiences) {
		this.experiences = experiences;
	}

	public List<Education> getEducations() {
		return educations;
	}

	public void setEducations(List<Education> educations) {
		this.educations = educations;
	}

	public List<HardSoft> getHardAndSofts() {
		return hardAndSofts;
	}

	public void setHardAndSofts(List<HardSoft> hardAndSofts) {
		this.hardAndSofts = hardAndSofts;
	}

	public List<Proyect> getProyects() {
		return proyects;
	}

	public void setProyects(List<Proyect> proyects) {
		this.proyects = proyects;
	}

	public List<SocialMedias> getSocialMedias() {
		return socialMedias;
	}

	public void setSocialMedias(List<SocialMedias> socialMedias) {
		this.socialMedias = socialMedias;
	}

}
