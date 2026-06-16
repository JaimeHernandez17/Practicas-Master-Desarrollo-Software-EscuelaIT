package supermercado.patterns.command;

import java.util.Stack;

/**
 * PATRÓN COMMAND - Invoker
 *
 * Gestiona la ejecución de comandos y mantiene el historial
 * para soportar operaciones de deshacer/rehacer.
 */
public class CommandInvoker {

    private Stack<Command> history;
    private Stack<Command> redoStack;

    public CommandInvoker() {
        this.history = new Stack<>();
        this.redoStack = new Stack<>();
    }

    /**
     * Ejecuta un comando y lo añade al historial.
     */
    public void executeCommand(Command command) {
        command.execute();
        history.push(command);
        redoStack.clear(); // Limpiar redo stack al ejecutar nuevo comando
    }

    /**
     * Deshace el último comando ejecutado.
     * @return true si se pudo deshacer
     */
    public boolean undo() {
        if (!history.isEmpty()) {
            Command command = history.pop();
            command.undo();
            redoStack.push(command);
            return true;
        }
        return false;
    }

    /**
     * Rehace el último comando deshecho.
     * @return true si se pudo rehacer
     */
    public boolean redo() {
        if (!redoStack.isEmpty()) {
            Command command = redoStack.pop();
            command.execute();
            history.push(command);
            return true;
        }
        return false;
    }

    /**
     * Verifica si hay comandos para deshacer.
     */
    public boolean canUndo() {
        return !history.isEmpty();
    }

    /**
     * Verifica si hay comandos para rehacer.
     */
    public boolean canRedo() {
        return !redoStack.isEmpty();
    }

    /**
     * Obtiene la descripción del último comando.
     */
    public String getLastCommandDescription() {
        if (!history.isEmpty()) {
            return history.peek().getDescription();
        }
        return "No hay comandos";
    }

    /**
     * Limpia todo el historial.
     */
    public void clear() {
        history.clear();
        redoStack.clear();
    }
}

