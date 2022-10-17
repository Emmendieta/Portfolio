package com.portfolio.portfolioEMM.entities;

import java.util.Date;

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

	@Column(name = "NAMEEXP", nullable = false)
	private String name;

	@Column(name = "TITLEEXP", nullable = false)
	private String title;

	@Column(name = "ACTIVITYEXP", nullable = false)
	private String activity;

	@Column(name = "DATESTARTEXP", nullable = false)
	private Date dateStart;

	@Column(name = "DATEENDEXP")
	private Date dateEnd;

	@Column(name = "IMAGEEXP")
	private String image;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PERSON_ID", nullable = false)
	private Person person;

	public Experience() {
	}

	public Experience(Long id, String name, String title, String activity, Date dateStart, Date dateEnd, String image,
			Person person) {
		this.id = id;
		this.name = name;
		this.title = title;
		this.activity = activity;
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

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

}
