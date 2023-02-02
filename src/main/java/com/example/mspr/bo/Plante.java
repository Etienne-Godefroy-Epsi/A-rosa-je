package com.example.mspr.bo;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "PLANTE")
public class Plante {
    @Id
    @Column(name = "ID_PLANTE", nullable = false)
    private Integer idPlante;

    @Column(name = "NOMPLANTE", length = 128)
    private String nomplante;

    @Column(name = "TYPEPLANTE", length = 128)
    private String typeplante;

    @Column(name = "PHOTOGENERIC", length = 128)
    private String photogeneric;

    @OneToMany(mappedBy = "idPlante")
    private Set<Planteagarder> planteagarders = new LinkedHashSet<>();

    public Integer getIdPlante() {
        return idPlante;
    }

    public void setIdPlante(Integer idPlante) {
        this.idPlante = idPlante;
    }

    public String getNomplante() {
        return nomplante;
    }

    public void setNomplante(String nomplante) {
        this.nomplante = nomplante;
    }

    public String getTypeplante() {
        return typeplante;
    }

    public void setTypeplante(String typeplante) {
        this.typeplante = typeplante;
    }

    public String getPhotogeneric() {
        return photogeneric;
    }

    public void setPhotogeneric(String photogeneric) {
        this.photogeneric = photogeneric;
    }

    public Set<Planteagarder> getPlanteagarders() {
        return planteagarders;
    }

    public void setPlanteagarders(Set<Planteagarder> planteagarders) {
        this.planteagarders = planteagarders;
    }

}