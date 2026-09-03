package com.schule.schichtplaner.entitys;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Mitarbeiter {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "mid")
	private Integer id;

	private String vorname;
	private String nachname;
	private Integer alter;
	private String adresse;
	private String abteilung;
	private Integer urlaubUebrig;

	protected Mitarbeiter() {
	}

	public Mitarbeiter(String vorname, String nachname, Integer alter, String adresse,
			String abteilung, Integer urlaubUebrig) {
		this.vorname = vorname;
		this.nachname = nachname;
		this.alter = alter;
		this.adresse = adresse;
		this.abteilung = abteilung;
		this.urlaubUebrig = urlaubUebrig;
	}

	public Integer getId() {
		return id;
	}

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public String getNachname() {
		return nachname;
	}

	public void setNachname(String nachname) {
		this.nachname = nachname;
	}

	public Integer getAlter() {
		return alter;
	}

	public void setAlter(Integer alter) {
		this.alter = alter;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getAbteilung() {
		return abteilung;
	}

	public void setAbteilung(String abteilung) {
		this.abteilung = abteilung;
	}

	public Integer getUrlaubUebrig() {
		return urlaubUebrig;
	}

	public void setUrlaubUebrig(Integer urlaubUebrig) {
		this.urlaubUebrig = urlaubUebrig;
	}

}
