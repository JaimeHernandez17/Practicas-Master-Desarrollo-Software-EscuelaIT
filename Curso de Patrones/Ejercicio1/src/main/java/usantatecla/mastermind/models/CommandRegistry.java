package usantatecla.mastermind.models;

import java.util.Stack;

public class CommandRegistry {

    private Stack<Command> undoStack;

    private Stack<Command> redoStack;

    public CommandRegistry() {
        this.undoStack = new Stack<>();
        this.redoStack = new Stack<>();
    }

    public void execute(Command command) {
        command.execute();
        this.undoStack.push(command);
        this.redoStack.clear();
    }

    public void undo() {
        assert this.isUndoable();
        Command command = this.undoStack.pop();
        command.undo();
        this.redoStack.push(command);
    }

    public void redo() {
        assert this.isRedoable();
        Command command = this.redoStack.pop();
        command.execute();
        this.undoStack.push(command);
    }

    public boolean isUndoable() {
        return !this.undoStack.isEmpty();
    }

    public boolean isRedoable() {
        return !this.redoStack.isEmpty();
    }

}

