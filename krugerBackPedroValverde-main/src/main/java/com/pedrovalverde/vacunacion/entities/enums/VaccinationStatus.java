package com.pedrovalverde.vacunacion.entities.enums;

/**
 * Enum for all entities for validate the vaccination status
 */
public enum VaccinationStatus {
    Y("Vacunado"),
    N("No Vacunado");

    private final String value;

    VaccinationStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
