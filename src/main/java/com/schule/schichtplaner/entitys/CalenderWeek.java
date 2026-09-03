package com.schule.schichtplaner.entitys;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "kalenderwoche")
public class CalenderWeek {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "kwid")
	private Integer id;

	private Integer kwNummer;
	private Integer jahr;

	@OneToMany(mappedBy = "kalenderwoche")
	private List<Urlaub> urlaube = new ArrayList<>();

	protected CalenderWeek() {
	}

	public CalenderWeek(Integer kwNummer, Integer jahr) {
		this.kwNummer = kwNummer;
		this.jahr = jahr;
	}

	public Integer getId() {
		return id;
	}

	public Integer getKwNummer() {
		return kwNummer;
	}

	public void setKwNummer(Integer kwNummer) {
		this.kwNummer = kwNummer;
	}

	public Integer getJahr() {
		return jahr;
	}

	public void setJahr(Integer jahr) {
		this.jahr = jahr;
	}

	public List<Urlaub> getUrlaube() {
		return urlaube;
	}
}
