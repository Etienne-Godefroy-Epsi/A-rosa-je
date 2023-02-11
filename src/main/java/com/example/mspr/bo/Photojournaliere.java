package com.example.mspr.bo;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "PHOTOJOURNALIERE")
public class PhotoJournaliere {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PHOTO", nullable = false)
    private Integer id;

    @Column(name = "LIEN", nullable = false, length = 30)
    private String lien;

    @Column(name = "DATEJOUR", nullable = false)
    private LocalDate datejour;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_PLANTEAGARDER", nullable = false)
    private PlanteAGarder planteAGarder;

    public PlanteAGarder getPlanteAGarder() {
        return planteAGarder;
    }

    public void setPlanteAGarder(PlanteAGarder planteAGarder) {
        this.planteAGarder = planteAGarder;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLien() {
        return lien;
    }

    public void setLien(String lien) {
        this.lien = lien;
    }

    public LocalDate getDatejour() {
        return datejour;
    }

    public void setDatejour(LocalDate datejour) {
        this.datejour = datejour;
    }

}