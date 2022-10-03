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
	private String name;

	@Column(name = "DESCRIPTIONPRO")
	private String description;

	@Column(name = "LINKPRO", nullable = false)
	private String link;

	@Column(name = "IMAGEPRO")
	private String image;

	@Column(name = "DATESTARTPRO", nullable = false)
	private Date dateStart;

	@Column(name = "DATEENDPRO")
	private Date dateEnd;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PERSON_ID", unique = true, nullable = false)
	private Person person;

	public Proyect() {
		super();
	}

	public Proyect(Long id, String name, String description, String link, String image, Date dateStart, Date dateEnd,
			Person person) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.link = link;
		this.image = image;
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
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

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

}
