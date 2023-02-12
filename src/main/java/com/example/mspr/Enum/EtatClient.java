package com.example.mspr.Enum;

public enum EtatClient {
    DISPONIBLE("Disponible", 'G'),
    INDISPONIBLE("Indisponible", 'I');

    private final String key;
    private final Character value;

    EtatClient(String key, Character value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public Character getValue() {
        return value;
    }

    public static boolean isEtatClient(Character etat) {
        for (EtatClient etatClient : values()) {
            if (etatClient.getValue().equals(etat)) {
                return true;
            }
        }

        return false;
    }
}
