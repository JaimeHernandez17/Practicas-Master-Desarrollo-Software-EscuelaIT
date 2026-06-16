package usantatecla.mastermind.views.console;

import java.util.List;

import usantatecla.mastermind.controllers.ProposalController;
import usantatecla.mastermind.types.Color;
import usantatecla.mastermind.types.Error;
import usantatecla.mastermind.views.MessageView;
import usantatecla.utils.WithConsoleView;

class ProposalView extends WithConsoleView {

	void interact(ProposalController proposalController) {
		boolean moveMade = false;
		do {
			String input = this.console.readString(this.buildPrompt(proposalController));
			if (input == null) {
				break;
			}

			if (input.equalsIgnoreCase(MessageView.UNDO.getMessage())) {
				if (proposalController.isUndoable()) {
					proposalController.undo();
					this.showBoard(proposalController);
				} else {
					this.console.writeln(MessageView.UNDO_NOT_AVAILABLE.getMessage());
				}
			} else if (input.equalsIgnoreCase(MessageView.REDO.getMessage())) {
				if (proposalController.isRedoable()) {
					proposalController.redo();
					this.showBoard(proposalController);
					if (proposalController.isWinner() || proposalController.isLooser()) {
						moveMade = true;
					}
				} else {
					this.console.writeln(MessageView.REDO_NOT_AVAILABLE.getMessage());
				}
			} else {
				List<Color> colors = ProposedCombinationView.getColors(input);
				Error error = proposalController.addProposedCombination(colors);
				if (error != null) {
					new ErrorView(error).writeln();
				} else {
					this.showBoard(proposalController);
					moveMade = true;
				}
			}
		} while (!moveMade);

		if (proposalController.isWinner()) {
			this.console.writeln(MessageView.WINNER.getMessage());
		} else if (proposalController.isLooser()) {
			this.console.writeln(MessageView.LOOSER.getMessage());
		}
	}

	private String buildPrompt(ProposalController proposalController) {
		String prompt = MessageView.PROPOSED_COMBINATION.getMessage();
		if (proposalController.isUndoable() || proposalController.isRedoable()) {
			prompt += "[";
			if (proposalController.isUndoable()) {
				prompt += MessageView.UNDO.getMessage();
			}
			if (proposalController.isUndoable() && proposalController.isRedoable()) {
				prompt += "/";
			}
			if (proposalController.isRedoable()) {
				prompt += MessageView.REDO.getMessage();
			}
			prompt += "] ";
		}
		return prompt;
	}

	private void showBoard(ProposalController proposalController) {
		this.console.writeln();
		new AttemptsView(proposalController).writeln();
		new SecretCombinationView(proposalController).writeln();
		for (int i = 0; i < proposalController.getAttempts(); i++) {
			new ProposedCombinationView(proposalController).write(i);
			new ResultView(proposalController).writeln(i);
		}
	}

}
