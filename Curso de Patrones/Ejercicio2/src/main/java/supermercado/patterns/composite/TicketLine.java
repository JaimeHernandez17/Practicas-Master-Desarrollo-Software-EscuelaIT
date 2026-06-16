package supermercado.patterns.composite;

import java.math.BigDecimal;

/**
 * PATRÓN COMPOSITE - Hoja abstracta
 *
 * Clase base para todas las líneas individuales del ticket.
 */
public abstract class TicketLine implements TicketComponent {
    protected int lineNumber;
    protected String description;

    public TicketLine(int lineNumber, String description) {
        this.lineNumber = lineNumber;
        this.description = description;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    @Override
    public abstract BigDecimal getAmount();
}

