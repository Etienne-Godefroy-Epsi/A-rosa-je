package com.example.mspr.bo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "PHOTOJOURNALIERE")
public class Photojournaliere {
    @Id
    @Column(name = "ID_PHOTO", nullable = false)
    private Integer idPhoto;

    @Column(name = "ID_PLANTEAGARDER", nullable = false)
    private Integer idPlanteagarder;

    @Column(name = "LIENPHOTO", length = 128)
    private String lienphoto;

    @Column(name = "DATEJOUR")
    private LocalDate datejour;

    public Integer getIdPhoto() {
        return idPhoto;
    }

    public void setIdPhoto(Integer idPhoto) {
        this.idPhoto = idPhoto;
    }

    public Integer getIdPlanteagarder() {
        return idPlanteagarder;
    }

    public void setIdPlanteagarder(Integer idPlanteagarder) {
        this.idPlanteagarder = idPlanteagarder;
    }

    public String getLienphoto() {
        return lienphoto;
    }

    public void setLienphoto(String lienphoto) {
        this.lienphoto = lienphoto;
    }

    public LocalDate getDatejour() {
        return datejour;
    }

    public void setDatejour(LocalDate datejour) {
        this.datejour = datejour;
    }

}