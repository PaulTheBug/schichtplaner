package com.schule.schichtplaner.entitys;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Urlaub {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "uid")
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "mid", nullable = false)
	private Mitarbeiter mitarbeiter;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "kwid", nullable = false)
	private CalenderWeek kalenderwoche;

	private String status;

	protected Urlaub() {
	}

	public Urlaub(Mitarbeiter mitarbeiter, CalenderWeek kalenderwoche, String status) {
		this.mitarbeiter = mitarbeiter;
		this.kalenderwoche = kalenderwoche;
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public Mitarbeiter getMitarbeiter() {
		return mitarbeiter;
	}

	public CalenderWeek getKalenderwoche() {
		return kalenderwoche;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}