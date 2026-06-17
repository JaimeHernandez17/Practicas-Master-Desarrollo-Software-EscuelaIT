package models;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Combinación propuesta por el jugador.
 * MODELO: No contiene referencias a Console ni System.out
 */
public class ProposedCombination extends Combination {

    public ProposedCombination(List<Color> colors) {
        assert colors != null && colors.size() == WIDTH;
        this.colors = colors;
    }

    /**
     * Valida si un string representa una combinación válida.
     * @param colorString cadena con iniciales de colores
     * @return Error.NULL si es válida, otro Error en caso contrario
     */
    public static Error validate(String colorString) {
        if (colorString == null || colorString.length() != WIDTH) {
            return Error.WRONG_LENGTH;
        }

        Set<Color> usedColors = new HashSet<>();
        for (int i = 0; i < colorString.length(); i++) {
            Color color = Color.valueOf(colorString.charAt(i));
            if (color == null) {
                return Error.WRONG_CHARACTERS;
            }
            if (usedColors.contains(color)) {
                return Error.DUPLICATED_COLORS;
            }
            usedColors.add(color);
        }
        return Error.NULL;
    }

    /**
     * Comprueba si el color en la posición dada coincide con el color proporcionado.
     */
    boolean contains(Color color, int position) {
        return this.colors.get(position) == color;
    }

    /**
     * Comprueba si la combinación contiene el color dado.
     */
    boolean contains(Color color) {
        return this.colors.contains(color);
    }
}

