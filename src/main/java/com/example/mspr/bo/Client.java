package com.example.mspr.bo;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("C")
public class Client extends Utilisateur {

    /**
     * Etat :
     * DISPONIBLE
     * INDISPONIBLE
     */
    @Column(name = "ETAT")
    private Character etat;

    public Character getEtat() {
        return etat;
    }

    public void setEtat(Character etat) {
        this.etat = etat;
    }
}
