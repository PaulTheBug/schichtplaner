package com.schule.schichtplaner.entitys;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Schicht {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sid")
	private Integer id;

	private String schichtart;


	protected Schicht() {
	}

	public Schicht(String schichtart) {
		this.schichtart = schichtart;
	}

	public Integer getId() {
		return id;
	}

	public String getSchichtart() {
		return schichtart;
	}

	public void setSchichtart(String schichtart) {
		this.schichtart = schichtart;
	}

}
