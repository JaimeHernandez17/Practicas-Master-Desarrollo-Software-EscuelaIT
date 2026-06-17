package views;

/**
 * Enumeración de mensajes mostrados al usuario.
 * VISTA: Define los textos de la interfaz de usuario.
 */
public enum MessageView {
    TITLE("----- MASTERMIND -----"),
    ATTEMPTS("Attempts: "),
    SECRET("Secret Combination"),
    PROPOSED_COMBINATION_PROMPT("Propose a combination: "),
    WINNER("You've won!!! ;-)"),
    LOOSER("You've lost!!! :-("),
    RESUME("Do you want to continue? (y/n): "),
    AVAILABLE_COLORS("Available colors: ");

    private final String message;

    MessageView(String message) {
        this.message = message;
    }

    public void write(Console console) {
        console.write(this.message);
    }

    public void writeln(Console console) {
        console.writeln(this.message);
    }
}

