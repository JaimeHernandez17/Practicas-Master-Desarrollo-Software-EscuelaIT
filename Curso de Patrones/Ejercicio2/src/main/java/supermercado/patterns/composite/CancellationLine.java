package supermercado.patterns.composite;

import java.math.BigDecimal;

import supermercado.patterns.visitor.TicketVisitor;

/**
 * PATRÓN COMPOSITE - Hoja concreta
 *
 * Representa una línea de anulación que cancela otra línea
 * del mismo ticket con un importe negativo.
 */
public class CancellationLine extends TicketLine {
    private TicketLine cancelledLine;

    public CancellationLine(int lineNumber, TicketLine cancelledLine) {
        super(lineNumber, "ANULACIÓN de línea " + cancelledLine.getLineNumber());
        this.cancelledLine = cancelledLine;
    }

    @Override
    public BigDecimal getAmount() {
        // Importe negativo para anular
        return cancelledLine.getAmount().negate();
    }

    @Override
    public void accept(TicketVisitor visitor) {
        visitor.visitCancellationLine(this);
    }

    @Override
    public String getDescription() {
        return "ANUL: " + cancelledLine.getDescription();
    }

    public TicketLine getCancelledLine() {
        return cancelledLine;
    }
}

