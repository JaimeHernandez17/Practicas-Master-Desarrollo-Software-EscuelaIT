/**
 * JUEGO MASTERMIND - Implementación Simple con Herencia
 * Basado en diagrama de clases proporcionado
 *
 * Jerarquía de Herencia:
 * - Combination (clase base)
 *   - SecretCombination (hereda)
 *   - ProposedCombination (hereda)
 *
 * @author Master Desarrollo de Software
 * @date Junio 2026
 */

import java.util.Scanner;

// ==================== ENUMERACIÓN COLOR ====================

enum Color {
    RED('r'),
    BLUE('b'),
    GREEN('g'),
    YELLOW('y'),
    PINK('p'),
    WHITE('w'),
    SILVER('s'),
    ORANGE('o');

    private char shortName;

    Color(char shortName) {
        this.shortName = shortName;
    }

    public char shortName() {
        return shortName;
    }

    public static Color valueOf(char c) {
        for (Color color : Color.values()) {
            if (color.shortName == c) {
                return color;
            }
        }
        return null;
    }

    public static String allColors() {
        StringBuilder sb = new StringBuilder();
        for (Color color : Color.values()) {
            sb.append(color.shortName());
        }
        return sb.toString();
    }
}

// ==================== ENUMERACIÓN SPIKE ====================

enum Spike {
    WHITE,
    BLACK
}

// ==================== CLASE GESTION IO ====================

class GestionIO {
    private Scanner scanner;

    public GestionIO() {
        this.scanner = new Scanner(System.in);
    }

    public void write(String text) {
        System.out.print(text);
    }

    public String read() {
        return scanner.nextLine();
    }
}

// ==================== CLASE BASE: COMBINATION ====================

/**
 * Clase base para combinaciones de colores
 * HERENCIA: SecretCombination y ProposedCombination heredan de esta clase
 */
class Combination {
    protected static final int LENGTH = 4;
    protected Color[] colors;

    public Combination() {
        this.colors = new Color[LENGTH];
    }

    public boolean isValid() {
        for (Color color : colors) {
            if (color == null) {
                return false;
            }
        }
        return true;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Color color : colors) {
            sb.append(color != null ? color.shortName() : '-');
        }
        return sb.toString();
    }

    public boolean equals(Combination other) {
        if (other == null) return false;
        for (int i = 0; i < LENGTH; i++) {
            if (this.colors[i] != other.colors[i]) {
                return false;
            }
        }
        return true;
    }

    public boolean equals(Combination other, int index) {
        if (other == null || index < 0 || index >= LENGTH) {
            return false;
        }
        return this.colors[index] == other.colors[index];
    }

    public boolean notEquals(Combination other, int index) {
        return !equals(other, index);
    }

    public boolean contains(Combination other, int index) {
        if (other == null || index < 0 || index >= LENGTH) {
            return false;
        }
        Color searchColor = other.colors[index];
        for (Color color : this.colors) {
            if (color == searchColor) {
                return true;
            }
        }
        return false;
    }

    public int length() {
        return LENGTH;
    }

    public boolean isValidLength() {
        return colors.length == LENGTH;
    }

    public boolean isValidColors() {
        return isValid();
    }

    public boolean hasRepeatingColors() {
        for (int i = 0; i < colors.length; i++) {
            for (int j = i + 1; j < colors.length; j++) {
                if (colors[i] != null && colors[i] == colors[j]) {
                    return true;
                }
            }
        }
        return false;
    }
}

// ==================== CLASE DERIVADA: SECRET COMBINATION ====================

/**
 * SecretCombination HEREDA de Combination
 * Representa el código secreto generado aleatoriamente
 */
class SecretCombination extends Combination {

    public SecretCombination() {
        super();
        generateRandom();
    }

    private void generateRandom() {
        Color[] allColors = Color.values();
        for (int i = 0; i < LENGTH; i++) {
            int randomIndex = (int) (Math.random() * allColors.length);
            colors[i] = allColors[randomIndex];
        }
    }
}

// ==================== CLASE DERIVADA: PROPOSED COMBINATION ====================

/**
 * ProposedCombination HEREDA de Combination
 * Representa un intento del jugador
 */
class ProposedCombination extends Combination {

    public ProposedCombination(GestionIO io) {
        super();
        read(io);
    }

    private void read(GestionIO io) {
        io.write("Proposed combination (" + LENGTH + " colors, use " + Color.allColors() + "): ");
        String input = io.read().trim();

        if (input.length() != LENGTH) {
            io.write("Error: Must be " + LENGTH + " characters\n");
            read(io);
            return;
        }

        for (int i = 0; i < LENGTH; i++) {
            Color color = Color.valueOf(input.charAt(i));
            if (color == null) {
                io.write("Error: Invalid color '" + input.charAt(i) + "'\n");
                read(io);
                return;
            }
            colors[i] = color;
        }
    }
}

// ==================== CLASE ATTEMPT ====================

/**
 * Clase que representa un intento con su evaluación
 */
class Attempt {
    private Spike[] spikes;
    private ProposedCombination proposed;

    public Attempt(ProposedCombination proposed) {
        this.proposed = proposed;
        this.spikes = new Spike[Combination.LENGTH];
    }

    public void result(SecretCombination secret) {
        int spikeIndex = 0;

        boolean[] secretUsed = new boolean[Combination.LENGTH];
        boolean[] proposedUsed = new boolean[Combination.LENGTH];

        // Primero: contar BLACK (posición y color correctos)
        for (int i = 0; i < Combination.LENGTH; i++) {
            if (secret.equals(proposed, i)) {
                spikes[spikeIndex++] = Spike.BLACK;
                secretUsed[i] = true;
                proposedUsed[i] = true;
            }
        }

        // Segundo: contar WHITE (color correcto, posición incorrecta)
        for (int i = 0; i < Combination.LENGTH; i++) {
            if (!proposedUsed[i]) {
                for (int j = 0; j < Combination.LENGTH; j++) {
                    if (!secretUsed[j] && proposed.colors[i] == secret.colors[j]) {
                        spikes[spikeIndex++] = Spike.WHITE;
                        secretUsed[j] = true;
                        break;
                    }
                }
            }
        }
    }

    public void print() {
        System.out.print(proposed + " --> ");
        int blacks = numSpikes(Spike.BLACK);
        int whites = numSpikes(Spike.WHITE);
        System.out.println(blacks + " blacks, " + whites + " whites");
    }

    public boolean isWinner() {
        return numSpikes(Spike.BLACK) == Combination.LENGTH;
    }

    public boolean isLoser() {
        return !isWinner();
    }

    private int numSpikes(Spike spike) {
        int count = 0;
        for (Spike s : spikes) {
            if (s == spike) {
                count++;
            }
        }
        return count;
    }
}

// ==================== CLASE GAME ====================

/**
 * Clase que controla un juego individual
 */
class Game {
    private static final int MAX_ATTEMPTS = 10;
    private Attempt[] attempts;
    private SecretCombination secret;
    private int numAttempts;

    public Game() {
        this.attempts = new Attempt[MAX_ATTEMPTS];
        this.numAttempts = 0;
        this.secret = new SecretCombination();
    }

    public void play() {
        GestionIO io = new GestionIO();

        io.write("\n=== NEW GAME ===\n");
        io.write("Guess the secret combination of " + Combination.LENGTH + " colors\n");
        io.write("Colors: " + Color.allColors() + "\n");
        io.write("Max attempts: " + MAX_ATTEMPTS + "\n\n");

        while (hasMoreAttempts()) {
            ProposedCombination proposed = new ProposedCombination(io);
            Attempt attempt = new Attempt(proposed);
            attempt.result(secret);
            attempts[numAttempts++] = attempt;

            attempt.print();

            if (attempt.isWinner()) {
                io.write("\n*** YOU WIN! ***\n");
                io.write("Secret was: " + secret + "\n");
                printAttempts();
                return;
            }
        }

        io.write("\n*** GAME OVER ***\n");
        io.write("Secret was: " + secret + "\n");
        printAttempts();
    }

    public boolean hasWon() {
        return numAttempts > 0 && attempts[numAttempts - 1].isWinner();
    }

    public void printAttempts() {
        System.out.println("\n--- All Attempts ---");
        for (int i = 0; i < numAttempts; i++) {
            System.out.print((i + 1) + ". ");
            attempts[i].print();
        }
    }

    private boolean hasMoreAttempts() {
        return numAttempts < MAX_ATTEMPTS;
    }

    public void finish() {
        System.out.println("Game finished with " + numAttempts + " attempts");
    }
}

// ==================== CLASE PRINCIPAL: MASTERMIND ====================

/**
 * Clase principal que gestiona múltiples juegos
 */
public class Mastermind {
    private Game[] games;
    private int numGames;

    public Mastermind() {
        this.games = new Game[10];
        this.numGames = 0;
    }

    public static void main(String[] args) {
        Mastermind mastermind = new Mastermind();
        GestionIO io = new GestionIO();

        io.write("╔════════════════════════════════════╗\n");
        io.write("║     MASTERMIND WITH INHERITANCE    ║\n");
        io.write("╚════════════════════════════════════╝\n");

        do {
            Game game = new Game();
            mastermind.games[mastermind.numGames++] = game;
            game.play();
            game.finish();

        } while (mastermind.playAgain(io));

        mastermind.printStats(io);
        io.write("\nThanks for playing!\n");
    }

    private boolean playAgain(GestionIO io) {
        io.write("\nPlay again? (y/n): ");
        String answer = io.read().trim().toLowerCase();
        return answer.equals("y") || answer.equals("yes");
    }

    private void printStats(GestionIO io) {
        io.write("\n=== STATISTICS ===\n");
        io.write("Games played: " + numGames + "\n");

        int wins = 0;
        for (int i = 0; i < numGames; i++) {
            if (games[i].hasWon()) {
                wins++;
            }
        }

        io.write("Wins: " + wins + "\n");
        io.write("Losses: " + (numGames - wins) + "\n");
    }
}

