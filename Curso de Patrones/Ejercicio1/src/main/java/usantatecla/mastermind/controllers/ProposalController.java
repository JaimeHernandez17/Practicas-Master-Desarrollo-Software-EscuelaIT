package usantatecla.mastermind.controllers;

import java.util.List;

import usantatecla.mastermind.models.Combination;
import usantatecla.mastermind.models.CommandRegistry;
import usantatecla.mastermind.models.Game;
import usantatecla.mastermind.models.ProposedCombinationCommand;
import usantatecla.mastermind.models.State;
import usantatecla.mastermind.types.Color;
import usantatecla.mastermind.types.Error;

public class ProposalController extends Controller {

	private CommandRegistry commandRegistry;

	public ProposalController(Game game, State state) {
		super(game, state);
		this.commandRegistry = new CommandRegistry();
	}

	public Error addProposedCombination(List<Color> colors) {
		Error error = null;
		if (colors.size() != Combination.getWidth()) {
			error = Error.WRONG_LENGTH;
		} else {
			for (int i = 0; i < colors.size(); i++) {
				if (colors.get(i) == null) {
					error = Error.WRONG_CHARACTERS;
				} else {
					for (int j = i+1; j < colors.size(); j++) {
						if (colors.get(i) == colors.get(j)) {
							error = Error.DUPLICATED;
						}
					}
				}				
			}
		}
		if (error == null){
			this.commandRegistry.execute(new ProposedCombinationCommand(this.game, colors));
			if (this.game.isWinner() || this.game.isLooser()) {
				this.state.next();
			}
		}
		return error;	
	}

	public void undo() {
		this.commandRegistry.undo();
	}

	public void redo() {
		this.commandRegistry.redo();
		if (this.game.isWinner() || this.game.isLooser()) {
			this.state.next();
		}
	}

	public boolean isUndoable() {
		return this.commandRegistry.isUndoable();
	}

	public boolean isRedoable() {
		return this.commandRegistry.isRedoable();
	}

	public boolean isWinner() {
		return this.game.isWinner();
	}

	public boolean isLooser() {
		return this.game.isLooser();
	}
	
	public int getAttempts() {
		return this.game.getAttempts();
	}

	public List<Color> getColors(int position) {
		return this.game.getColors(position);
	}

	public int getBlacks(int position) {
		return this.game.getBlacks(position);
	}

	public int getWhites(int position) {
		return this.game.getWhites(position);
	}
	
	@Override
	public void accept(ControllersVisitor controllersVisitor) {
		controllersVisitor.visit(this);
	}

}
