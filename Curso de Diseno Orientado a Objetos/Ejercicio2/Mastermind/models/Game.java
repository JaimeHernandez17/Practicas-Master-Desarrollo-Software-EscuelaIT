package models;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase principal del modelo (Document) que gestiona el estado del juego.
 * MODELO: No contiene referencias a Console ni System.out.
 * La vista (View) consulta el estado del modelo a través de métodos públicos.
 */
public class Game {

    private static final int MAX_ATTEMPTS = 10;

    private SecretCombination secretCombination;
    private List<ProposedCombination> proposedCombinations;
    private List<Result> results;
    private int attempts;

    public Game() {
        this.reset();
    }

    /**
     * Reinicia el juego para una nueva partida.
     */
    public void reset() {
        this.secretCombination = new SecretCombination();
        this.proposedCombinations = new ArrayList<>();
        this.results = new ArrayList<>();
        this.attempts = 0;
    }

    /**
     * Añade una combinación propuesta y calcula su resultado.
     * @param colors lista de colores de la combinación
     */
    public void addProposedCombination(List<Color> colors) {
        ProposedCombination proposed = new ProposedCombination(colors);
        this.proposedCombinations.add(proposed);
        this.results.add(this.secretCombination.getResult(proposed));
        this.attempts++;
    }

    /**
     * Comprueba si el jugador ha perdido (agotó intentos).
     */
    public boolean isLooser() {
        return this.attempts == MAX_ATTEMPTS && !this.isWinner();
    }

    /**
     * Comprueba si el jugador ha ganado.
     */
    public boolean isWinner() {
        if (this.attempts == 0) {
            return false;
        }
        return this.results.get(this.attempts - 1).isWinner();
    }

    /**
     * Comprueba si el juego ha terminado (ganador o perdedor).
     */
    public boolean isFinished() {
        return this.isWinner() || this.isLooser();
    }

    /**
     * Obtiene el número de intentos realizados.
     */
    public int getAttempts() {
        return this.attempts;
    }

    /**
     * Obtiene el número máximo de intentos permitidos.
     */
    public int getMaxAttempts() {
        return MAX_ATTEMPTS;
    }

    /**
     * Obtiene los colores de una combinación propuesta.
     * @param position índice de la combinación (0-based)
     */
    public List<Color> getColors(int position) {
        return this.proposedCombinations.get(position).getColors();
    }

    /**
     * Obtiene el número de negros de un resultado.
     */
    public int getBlacks(int position) {
        return this.results.get(position).getBlacks();
    }

    /**
     * Obtiene el número de blancos de un resultado.
     */
    public int getWhites(int position) {
        return this.results.get(position).getWhites();
    }

    /**
     * Obtiene el ancho de la combinación.
     */
    public int getWidth() {
        return Combination.getWidth();
    }
}

