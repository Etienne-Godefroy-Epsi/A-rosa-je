package com.example.mspr.bo;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "UTILISATEUR")
public class Utilisateur {
    @Id
    @Column(name = "ID_UTILISATEUR", nullable = false)
    private Integer idUtilisateur;

    @Column(name = "MDP", length = 128)
    private String mdp;

    @Column(name = "NOM", length = 128)
    private String nom;

    @Column(name = "PRENOM", length = 128)
    private String prenom;

    @Column(name = "ADRESSE", length = 128)
    private String adresse;

    @Column(name = "MAIL", length = 128)
    private String mail;

    @Column(name = "DESCRIPTION", length = 128)
    private String description;

    @Column(name = "TYPE", length = 32)
    private String type;

    @OneToMany(mappedBy = "idClient")
    private Set<Contrat> contrats_client = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idGardien")
    private Set<Contrat> contrats_gardien = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idBotaniste")
    private Set<Contrat> contrats_botaniste = new LinkedHashSet<>();

    public Integer getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(Integer idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Set<Contrat> getContrats_client() {
        return contrats_client;
    }

    public void setContrats_client(Set<Contrat> contrats_client) {
        this.contrats_client = contrats_client;
    }

    public Set<Contrat> getContrats_gardien() {
        return contrats_gardien;
    }

    public void setContrats_gardien(Set<Contrat> contrats_gardien) {
        this.contrats_gardien = contrats_gardien;
    }

    public Set<Contrat> getContrats_botaniste() {
        return contrats_botaniste;
    }

    public void setContrats_botaniste(Set<Contrat> contrats_botaniste) {
        this.contrats_botaniste = contrats_botaniste;
    }

}