package models;

/**
 * Resultado de comparar una combinación propuesta con el secreto.
 * - Negros: color y posición correctos
 * - Blancos: color correcto, posición incorrecta
 * MODELO: No contiene referencias a Console ni System.out
 */
class Result {

    private final int blacks;
    private final int whites;

    Result(int blacks, int whites) {
        assert blacks >= 0 && blacks <= Combination.getWidth();
        assert whites >= 0 && whites <= Combination.getWidth();
        this.blacks = blacks;
        this.whites = whites;
    }

    /**
     * Comprueba si el resultado representa una victoria (todos negros).
     */
    boolean isWinner() {
        return this.blacks == Combination.getWidth();
    }

    int getBlacks() {
        return this.blacks;
    }

    int getWhites() {
        return this.whites;
    }
}

