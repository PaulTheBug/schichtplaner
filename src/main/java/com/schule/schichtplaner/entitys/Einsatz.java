package com.schule.schichtplaner.entitys;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "einsatz")
public class Einsatz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "eid")
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "mid", nullable = false)
    private Mitarbeiter mitarbeiter;

    @ManyToOne(optional = false)
    @JoinColumn(name = "sid", nullable = false)
    private Schicht schicht;

    @Column(name = "datum_von", nullable = false)
    private LocalDate datumVon;

    @Column(name = "datum_bis", nullable = false)
    private LocalDate datumBis;

    protected Einsatz() {
    }

    public Einsatz(Mitarbeiter mitarbeiter, Schicht schicht, LocalDate datumVon, LocalDate datumBis) {
        this.mitarbeiter = mitarbeiter;
        this.schicht = schicht;
        this.datumVon = datumVon;
        this.datumBis = datumBis;
    }

    public Integer getId() {
        return id;
    }

    public Mitarbeiter getMitarbeiter() {
        return mitarbeiter;
    }

    public Schicht getSchicht() {
        return schicht;
    }

    public LocalDate getDatumVon() {
        return datumVon;
    }
    public LocalDate getDatumBis() {
        return datumBis;
    }
}
