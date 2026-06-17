package models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Combinación secreta generada aleatoriamente.
 * MODELO: No contiene referencias a Console ni System.out
 */
class SecretCombination extends Combination {

    SecretCombination() {
        List<Color> availableColors = new ArrayList<>();
        for (Color color : Color.values()) {
            availableColors.add(color);
        }

        Random random = new Random(System.currentTimeMillis());
        Collections.shuffle(availableColors, random);

        // Tomar los primeros WIDTH colores (sin repetición)
        for (int i = 0; i < WIDTH; i++) {
            this.colors.add(availableColors.get(i));
        }
    }

    /**
     * Calcula el resultado de comparar una combinación propuesta con el secreto.
     * @param proposedCombination la combinación propuesta por el jugador
     * @return Result con el número de negros y blancos
     */
    Result getResult(ProposedCombination proposedCombination) {
        int blacks = 0;
        for (int i = 0; i < this.colors.size(); i++) {
            if (proposedCombination.contains(this.colors.get(i), i)) {
                blacks++;
            }
        }

        int whites = 0;
        for (Color color : this.colors) {
            if (proposedCombination.contains(color)) {
                whites++;
            }
        }

        // Los blancos son los que coinciden en color pero NO en posición
        return new Result(blacks, whites - blacks);
    }
}

