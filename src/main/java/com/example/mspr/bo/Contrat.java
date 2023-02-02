package com.example.mspr.bo;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "CONTRAT")
public class Contrat {
    @Id
    @Column(name = "ID_CONTRAT", nullable = false)
    private Integer idContrat;

    @Column(name = "DATEDEBUT")
    private LocalDate datedebut;

    @Column(name = "DATEFIN")
    private LocalDate datefin;

    @Column(name = "ETATCONTRAT", length = 32)
    private String etatcontrat;

    @Column(name = "ID_CLIENT", nullable = false)
    private Integer idClient;

    @Column(name = "ID_GARDIEN", nullable = false)
    private Integer idGardien;

    @Column(name = "ID_BOTANISTE", nullable = false)
    private Integer idBotaniste;

    @OneToMany(mappedBy = "idContrat")
    private Set<Planteagarder> planteagarders = new LinkedHashSet<>();

    public Integer getIdContrat() {
        return idContrat;
    }

    public void setIdContrat(Integer idContrat) {
        this.idContrat = idContrat;
    }

    public LocalDate getDatedebut() {
        return datedebut;
    }

    public void setDatedebut(LocalDate datedebut) {
        this.datedebut = datedebut;
    }

    public LocalDate getDatefin() {
        return datefin;
    }

    public void setDatefin(LocalDate datefin) {
        this.datefin = datefin;
    }

    public String getEtatcontrat() {
        return etatcontrat;
    }

    public void setEtatcontrat(String etatcontrat) {
        this.etatcontrat = etatcontrat;
    }

    public Integer getIdClient() {
        return idClient;
    }

    public void setIdClient(Integer idClient) {
        this.idClient = idClient;
    }

    public Integer getIdGardien() {
        return idGardien;
    }

    public void setIdGardien(Integer idGardien) {
        this.idGardien = idGardien;
    }

    public Integer getIdBotaniste() {
        return idBotaniste;
    }

    public void setIdBotaniste(Integer idBotaniste) {
        this.idBotaniste = idBotaniste;
    }

    public Set<Planteagarder> getPlanteagarders() {
        return planteagarders;
    }

    public void setPlanteagarders(Set<Planteagarder> planteagarders) {
        this.planteagarders = planteagarders;
    }

}