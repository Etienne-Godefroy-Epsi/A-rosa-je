package com.example.mspr.bo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Table(name = "CONTRAT")
public class Contrat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CONTRAT", nullable = false)
    private Integer id;

    @Column(name = "DATEDEBUT", nullable = false)
    private LocalDate datedebut;

    @Column(name = "DATEFIN", nullable = false)
    private LocalDate datefin;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_CLIENT", nullable = false)
    private Utilisateur client;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_GARDIEN", nullable = false)
    private Utilisateur gardien;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_BOTANISTE")
    private Utilisateur botaniste;

    /**
     * Different etat d'un contrat
     * S : Sans Botanique, mais sans urgence
     * U : Sans Botanique, mais avec urgence
     * B : Avec Botanique
     * T : Contrat terminé
     */
    @Column(name = "ETAT", nullable = false)
    private Character etat;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Utilisateur getClient() {
        return client;
    }

    public void setClient(Utilisateur client) {
        this.client = client;
    }

    public Utilisateur getGardien() {
        return gardien;
    }

    public void setGardien(Utilisateur gardien) {
        this.gardien = gardien;
    }

    public Utilisateur getBotaniste() {
        return botaniste;
    }

    public void setBotaniste(Utilisateur botaniste) {
        this.botaniste = botaniste;
    }

    public Character getEtat() {
        return etat;
    }

    public void setEtat(Character etat) {
        this.etat = etat;
    }

}