package supermercado.patterns.composite;

import java.math.BigDecimal;

import supermercado.patterns.visitor.TicketVisitor;

/**
 * PATRÓN COMPOSITE - Hoja concreta
 *
 * Representa una línea de repetición que duplica el contenido
 * de otra línea existente en el ticket.
 */
public class RepetitionLine extends TicketLine {
    private TicketLine originalLine;

    public RepetitionLine(int lineNumber, TicketLine originalLine) {
        super(lineNumber, "REPETICIÓN de línea " + originalLine.getLineNumber());
        this.originalLine = originalLine;
    }

    @Override
    public BigDecimal getAmount() {
        return originalLine.getAmount();
    }

    @Override
    public void accept(TicketVisitor visitor) {
        visitor.visitRepetitionLine(this);
    }

    @Override
    public String getDescription() {
        return "REP: " + originalLine.getDescription();
    }

    public TicketLine getOriginalLine() {
        return originalLine;
    }
}

