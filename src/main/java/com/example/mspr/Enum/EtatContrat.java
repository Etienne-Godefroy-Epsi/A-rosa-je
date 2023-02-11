package com.example.mspr.Enum;

public enum EtatContrat {

    SANSBOTANISTE("Sans botaniste", 'S'),
    AVECBOTANISTE("Avec botaniste", 'B'),
    URGENCE("Urgence", 'U'),
    TERMINE("Terminé", 'T');

    private final String key;
    private final Character value;

    EtatContrat(String key, Character value) {
        this.key = key;
        this.value = value;
    }

    public Character getValue() {
        return value;
    }

    public String getKey() {
        return key;
    }
}
