package usantatecla.mastermind.models;

import java.util.List;

import usantatecla.mastermind.types.Color;

public class ProposedCombinationCommand implements Command {

    private Game game;

    private List<Color> colors;

    public ProposedCombinationCommand(Game game, List<Color> colors) {
        this.game = game;
        this.colors = colors;
    }

    @Override
    public void execute() {
        this.game.addProposedCombination(this.colors);
    }

    @Override
    public void undo() {
        this.game.removeLastProposedCombination();
    }

}

