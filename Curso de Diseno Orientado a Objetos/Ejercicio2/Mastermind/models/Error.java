package models;

/**
 * Enumeración de posibles errores de entrada.
 * MODELO: El modelo detecta errores pero no los muestra directamente.
 */
public enum Error {
    WRONG_LENGTH,
    WRONG_CHARACTERS,
    DUPLICATED_COLORS,
    NULL;

    public boolean isNull() {
        return this == NULL;
    }
}

