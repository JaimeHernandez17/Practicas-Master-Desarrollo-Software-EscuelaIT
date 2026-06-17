package views;

import controllers.ControllersVisitor;
import controllers.Logic;
import controllers.PlayController;
import controllers.ResumeController;
import controllers.StartController;
import models.Color;
import models.Error;

import java.util.List;

/**
 * Vista de consola que implementa ControllersVisitor.
 * Procesa cada tipo de controlador mediante double dispatch,
 * eliminando la necesidad de usar instanceof.
 */
public class ConsoleView implements View, ControllersVisitor {

    private final Console console;

    public ConsoleView() {
        this.console = Console.getInstance();
    }

    @Override
    public void interact(Logic logic) {
        while (logic.isNotExit()) {
            logic.getController().accept(this);
        }
    }

    @Override
    public void visit(StartController startController) {
        this.showTitle();
        this.showAvailableColors();
        startController.start();
    }

    @Override
    public void visit(PlayController playController) {
        this.proposeAndProcess(playController);
        this.showBoard(playController);
    }

    @Override
    public void visit(ResumeController resumeController) {
        this.showResult(resumeController);
        boolean resume = this.isResumed();
        resumeController.resume(resume);
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

    private void proposeAndProcess(PlayController playController) {
        Error error;
        String input;
        do {
            MessageView.PROPOSED_COMBINATION_PROMPT.write(this.console);
            input = this.console.readString("");
            error = playController.validateProposedCombination(input);
            ErrorView.writeln(error, this.console);
        } while (!error.isNull());

        playController.addProposedCombination(input);
    }

    private void showBoard(PlayController playController) {
        this.console.writeln();
        MessageView.ATTEMPTS.write(this.console);
        this.console.writeln(playController.getAttempts() + "/" + playController.getMaxAttempts());
        this.console.writeln();

        // Mostrar línea del secreto (oculto)
        for (int i = 0; i < playController.getWidth(); i++) {
            this.console.write("*");
        }
        this.console.writeln();

        // Mostrar combinaciones propuestas y sus resultados
        for (int i = 0; i < playController.getAttempts(); i++) {
            this.showProposedCombination(playController, i);
            this.showResult(playController, i);
            this.console.writeln();
        }
    }

    private void showProposedCombination(PlayController playController, int position) {
        List<Color> colors = playController.getColors(position);
        for (Color color : colors) {
            ColorView colorView = ColorView.of(color);
            if (colorView != null) {
                colorView.write(this.console);
            }
        }
        this.console.write(" --> ");
    }

    private void showResult(PlayController playController, int position) {
        int blacks = playController.getBlacks(position);
        int whites = playController.getWhites(position);
        this.console.write(blacks + " blacks, " + whites + " whites");
    }

    private void showResult(ResumeController resumeController) {
        this.console.writeln();
        if (resumeController.isWinner()) {
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

