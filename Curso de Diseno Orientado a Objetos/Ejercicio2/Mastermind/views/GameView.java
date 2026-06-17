package views;

import models.Color;
import models.Error;
import models.Game;
import models.ProposedCombination;

import java.util.ArrayList;
import java.util.List;

/**
 * Vista principal del juego que gestiona la interacción con el usuario.
 * VISTA: Esta clase conoce la consola y el modelo (Game),
 * pero el modelo NO conoce esta clase ni la consola.
 */
public class GameView {

    private final Game game;
    private final Console console;

    public GameView(Game game) {
        this.game = game;
        this.console = Console.getInstance();
    }

    /**
     * Ejecuta el bucle principal del juego.
     */
    public void play() {
        do {
            this.playGame();
        } while (this.isResumed());
    }

    private void playGame() {
        this.game.reset();
        this.showTitle();
        this.showAvailableColors();

        do {
            this.proposeAndProcess();
            this.showBoard();
        } while (!this.game.isFinished());

        this.showResult();
    }

    private void showTitle() {
        this.console.writeln();
        MessageView.TITLE.writeln(this.console);
        this.console.writeln();
    }

    private void showAvailableColors() {
        MessageView.AVAILABLE_COLORS.write(this.console);
        ColorView.writeAll(this.console);
        this.console.writeln();
    }

    private void proposeAndProcess() {
        List<Color> colors = this.readProposedCombination();
        this.game.addProposedCombination(colors);
    }

    private List<Color> readProposedCombination() {
        Error error;
        String input;
        do {
            MessageView.PROPOSED_COMBINATION_PROMPT.write(this.console);
            input = this.console.readString("");
            error = ProposedCombination.validate(input);
            ErrorView.writeln(error, this.console);
        } while (!error.isNull());

        List<Color> colors = new ArrayList<>();
        for (int i = 0; i < input.length(); i++) {
            colors.add(Color.valueOf(input.charAt(i)));
        }
        return colors;
    }

    private void showBoard() {
        this.console.writeln();
        MessageView.ATTEMPTS.write(this.console);
        this.console.writeln(this.game.getAttempts() + "/" + this.game.getMaxAttempts());
        this.console.writeln();

        // Mostrar línea del secreto (oculto)
        for (int i = 0; i < this.game.getWidth(); i++) {
            this.console.write("*");
        }
        this.console.writeln();

        // Mostrar combinaciones propuestas y sus resultados
        for (int i = 0; i < this.game.getAttempts(); i++) {
            this.showProposedCombination(i);
            this.showResult(i);
            this.console.writeln();
        }
    }

    private void showProposedCombination(int position) {
        List<Color> colors = this.game.getColors(position);
        for (Color color : colors) {
            ColorView colorView = ColorView.of(color);
            if (colorView != null) {
                colorView.write(this.console);
            }
        }
        this.console.write(" --> ");
    }

    private void showResult(int position) {
        int blacks = this.game.getBlacks(position);
        int whites = this.game.getWhites(position);
        this.console.write(blacks + " blacks, " + whites + " whites");
    }

    private void showResult() {
        this.console.writeln();
        if (this.game.isWinner()) {
            MessageView.WINNER.writeln(this.console);
        } else {
            MessageView.LOOSER.writeln(this.console);
        }
    }

    private boolean isResumed() {
        char answer;
        do {
            MessageView.RESUME.write(this.console);
            String input = this.console.readString("");
            answer = input.isEmpty() ? ' ' : Character.toLowerCase(input.charAt(0));
        } while (answer != 'y' && answer != 'n');
        return answer == 'y';
    }
}


