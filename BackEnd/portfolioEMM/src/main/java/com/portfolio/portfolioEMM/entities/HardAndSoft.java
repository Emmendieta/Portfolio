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
@Table(name = "HARDANDSOFT")
public class HardAndSoft {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private Long id;

	@Column(name = "NAMEHYS", nullable = false)
	private String nameHYS;

	@Column(name = "PERCENTHYS", nullable = false)
	private int percentHYS;

	@Column(name = "IMAGEHYS")
	private String iamgeHYS;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "PERSON_ID", unique = true, nullable = false)
	private Person personHYS;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNameHYS() {
		return nameHYS;
	}

	public void setNameHYS(String nameHYS) {
		this.nameHYS = nameHYS;
	}

	public int getPercentHYS() {
		return percentHYS;
	}

	public void setPercentHYS(int percentHYS) {
		this.percentHYS = percentHYS;
	}

	public String getIamgeHYS() {
		return iamgeHYS;
	}

	public void setIamgeHYS(String iamgeHYS) {
		this.iamgeHYS = iamgeHYS;
	}

	public Person getPersonHYS() {
		return personHYS;
	}

	public void setPersonHYS(Person personHYS) {
		this.personHYS = personHYS;
	}

}
