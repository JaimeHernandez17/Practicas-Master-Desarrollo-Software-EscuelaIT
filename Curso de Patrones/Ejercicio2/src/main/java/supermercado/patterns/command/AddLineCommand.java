package supermercado.patterns.command;

import supermercado.domain.Ticket;
import supermercado.patterns.composite.TicketLine;

/**
 * PATRÓN COMMAND - Comando concreto
 *
 * Comando para añadir una línea al ticket.
 */
public class AddLineCommand implements Command {

    private Ticket ticket;
    private TicketLine line;
    private boolean executed;

    public AddLineCommand(Ticket ticket, TicketLine line) {
        this.ticket = ticket;
        this.line = line;
        this.executed = false;
    }

    @Override
    public void execute() {
        if (!executed) {
            ticket.addLine(line);
            executed = true;
        }
    }

    @Override
    public void undo() {
        if (executed) {
            ticket.removeLine(line.getLineNumber() - 1);
            executed = false;
        }
    }

    @Override
    public String getDescription() {
        return "Añadir línea: " + line.getDescription();
    }
}

