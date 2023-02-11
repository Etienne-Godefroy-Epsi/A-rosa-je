package com.example.mspr.Enum;

public enum EtatUtilisateur {
    DISPONIBLE("Disponible", 'G'),
    INDISPONIBLE("Indisponible", 'I');

    private final String key;
    private final Character value;

    EtatUtilisateur(String key, Character value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public Character getValue() {
        return value;
    }
}
