package supermercado.patterns.command;

import supermercado.domain.Ticket;
import supermercado.patterns.composite.TicketComponent;

/**
 * PATRÓN COMMAND - Comando concreto
 *
 * Comando para eliminar una línea del ticket.
 */
public class RemoveLineCommand implements Command {

    private Ticket ticket;
    private int lineNumber;
    private TicketComponent removedLine;
    private boolean executed;

    public RemoveLineCommand(Ticket ticket, int lineNumber) {
        this.ticket = ticket;
        this.lineNumber = lineNumber;
        this.executed = false;
    }

    @Override
    public void execute() {
        if (!executed) {
            removedLine = ticket.getLine(lineNumber);
            ticket.removeLine(lineNumber);
            executed = true;
        }
    }

    @Override
    public void undo() {
        if (executed && removedLine != null) {
            ticket.addLine(removedLine);
            executed = false;
        }
    }

    @Override
    public String getDescription() {
        return "Eliminar línea: " + lineNumber;
    }
}

