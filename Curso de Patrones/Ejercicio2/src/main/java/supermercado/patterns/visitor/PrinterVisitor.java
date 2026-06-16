package supermercado.patterns.visitor;

import supermercado.patterns.composite.*;
import supermercado.patterns.strategy.CurrencyFormatter;

/**
 * PATRÓN VISITOR - Visitante concreto
 *
 * Genera la representación textual del ticket para impresión.
 */
public class PrinterVisitor implements TicketVisitor {
    private StringBuilder output;
    private CurrencyFormatter currencyFormatter;

    public PrinterVisitor(CurrencyFormatter currencyFormatter) {
        this.currencyFormatter = currencyFormatter;
        this.output = new StringBuilder();
    }

    @Override
    public void visitSaleLine(SaleLine line) {
        output.append(String.format("%3d | %-30s | %s%n",
                line.getLineNumber(),
                line.getDescription(),
                currencyFormatter.format(line.getAmount())));
    }

    @Override
    public void visitRepetitionLine(RepetitionLine line) {
        output.append(String.format("%3d | %-30s | %s%n",
                line.getLineNumber(),
                line.getDescription(),
                currencyFormatter.format(line.getAmount())));
    }

    @Override
    public void visitCancellationLine(CancellationLine line) {
        output.append(String.format("%3d | %-30s | %s%n",
                line.getLineNumber(),
                "** " + line.getDescription() + " **",
                currencyFormatter.format(line.getAmount())));
    }

    @Override
    public void visitReturnLine(ReturnLine line) {
        output.append(String.format("%3d | %-30s | %s%n",
                line.getLineNumber(),
                ">> " + line.getDescription(),
                currencyFormatter.format(line.getAmount())));
    }

    @Override
    public void visitCompositeSection(CompositeTicketSection section) {
        output.append(section.getDescription()).append("\n");
    }

    public String getOutput() {
        return output.toString();
    }

    public void reset() {
        output = new StringBuilder();
    }
}

