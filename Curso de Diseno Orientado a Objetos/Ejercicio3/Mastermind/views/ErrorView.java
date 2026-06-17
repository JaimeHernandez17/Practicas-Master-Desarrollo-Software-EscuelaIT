package views;

import models.Combination;
import models.Error;

/**
 * Vista de errores - traduce errores del modelo a mensajes de usuario.
 * VISTA: Mapea los errores del modelo a textos legibles.
 */
public enum ErrorView {
    WRONG_LENGTH("Wrong length, must be " + Combination.getWidth() + " colors."),
    WRONG_CHARACTERS("Invalid characters. Use only valid color initials."),
    DUPLICATED_COLORS("Duplicated colors are not allowed.");

    private final String message;

    ErrorView(String message) {
        this.message = message;
    }

    public static void writeln(Error error, Console console) {
        if (!error.isNull()) {
            ErrorView errorView = ErrorView.values()[error.ordinal()];
            console.writeln("Error: " + errorView.message);
        }
    }
}

