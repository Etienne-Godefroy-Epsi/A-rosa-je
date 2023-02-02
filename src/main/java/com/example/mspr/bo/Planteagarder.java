package com.example.mspr.bo;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "PLANTEAGARDER")
public class Planteagarder {
    @Id
    @Column(name = "ID_PLANTEAGARDER", nullable = false)
    private Integer idPlanteagarder;

    @Column(name = "ID_CONTRAT", nullable = false)
    private Integer idContrat;

    @Column(name = "ID_PLANTE", nullable = false)
    private Integer idPlante;

    @Column(name = "QUANTITE")
    private Integer quantite;

    @OneToMany(mappedBy = "idPlanteagarder")
    private Set<Photojournaliere> photojournalieres = new LinkedHashSet<>();

    public Integer getIdPlanteagarder() {
        return idPlanteagarder;
    }

    public void setIdPlanteagarder(Integer idPlanteagarder) {
        this.idPlanteagarder = idPlanteagarder;
    }

    public Integer getIdContrat() {
        return idContrat;
    }

    public void setIdContrat(Integer idContrat) {
        this.idContrat = idContrat;
    }

    public Integer getIdPlante() {
        return idPlante;
    }

    public void setIdPlante(Integer idPlante) {
        this.idPlante = idPlante;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    public Set<Photojournaliere> getPhotojournalieres() {
        return photojournalieres;
    }

    public void setPhotojournalieres(Set<Photojournaliere> photojournalieres) {
        this.photojournalieres = photojournalieres;
    }

}