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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.portfolio.portfolioEMM.security.entities.User;

@Entity
@Table(name = "PERSON")
public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private Long id;

	@Column(name = "NAMEP", nullable = false)
	private String name;

	@Column(name = "LASTNAMEP", nullable = false)
	private String lastName;

	@Column(name = "AGEP", nullable = false)
	private Date age;

	@Column(name = "TITLEP", nullable = false)
	private String title;

	@Column(name = "ABOUTP")
	private String about;

	@Column(name = "PROVINCEP", nullable = false)
	private String province;

	@Column(name = "COUNTRYP", nullable = false)
	private String country;

	@Column(name = "IMAGEP")
	private String image;

	@Column(name = "BANNERP")
	private String banner;

	@Column(name = "EMAILP")
	private String email;

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

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "person")
	private User user;

	public Person() {
	}

	public Person(Long id, String name, String lastName, Date age, String title, String about, String province,
			String country, String image, String banner, String email, List<Experience> experiences,
			List<Education> educations, List<HardSoft> hardAndSofts, List<Proyect> proyects,
			List<SocialMedias> socialMedias, List<User> users) {
		super();
		this.id = id;
		this.name = name;
		this.lastName = lastName;
		this.age = age;
		this.title = title;
		this.about = about;
		this.province = province;
		this.country = country;
		this.image = image;
		this.banner = banner;
		this.email = email;
		this.experiences = experiences;
		this.educations = educations;
		this.hardAndSofts = hardAndSofts;
		this.proyects = proyects;
		this.socialMedias = socialMedias;
		this.user = user;
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

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getAge() {
		return age;
	}

	public void setAge(Date age) {
		this.age = age;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
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

	public String getBanner() {
		return banner;
	}

	public void setBanner(String banner) {
		this.banner = banner;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
