package supermercado.patterns.visitor;

import java.math.BigDecimal;

import supermercado.patterns.composite.*;
import supermercado.patterns.strategy.TaxStrategy;

/**
 * PATRÓN VISITOR - Visitante concreto
 *
 * Calcula el total del ticket incluyendo impuestos.
 */
public class TotalCalculatorVisitor implements TicketVisitor {
    private BigDecimal subtotal;
    private BigDecimal taxAmount;
    private TaxStrategy taxStrategy;

    public TotalCalculatorVisitor(TaxStrategy taxStrategy) {
        this.taxStrategy = taxStrategy;
        this.subtotal = BigDecimal.ZERO;
        this.taxAmount = BigDecimal.ZERO;
    }

    @Override
    public void visitSaleLine(SaleLine line) {
        BigDecimal lineAmount = line.getAmount();
        subtotal = subtotal.add(lineAmount);

        // Calcular impuestos según la estrategia del país
        BigDecimal lineTax = taxStrategy.calculateTax(lineAmount, line.getProduct().getTaxCategory());
        taxAmount = taxAmount.add(lineTax);
    }

    @Override
    public void visitRepetitionLine(RepetitionLine line) {
        // Delegar al cálculo de la línea original
        if (line.getOriginalLine() instanceof SaleLine) {
            visitSaleLine((SaleLine) line.getOriginalLine());
        }
    }

    @Override
    public void visitCancellationLine(CancellationLine line) {
        // Restar el importe de la línea anulada
        BigDecimal lineAmount = line.getAmount(); // Ya es negativo
        subtotal = subtotal.add(lineAmount);
    }

    @Override
    public void visitReturnLine(ReturnLine line) {
        // Restar el importe de la devolución
        BigDecimal lineAmount = line.getAmount(); // Ya es negativo
        subtotal = subtotal.add(lineAmount);
    }

    @Override
    public void visitCompositeSection(CompositeTicketSection section) {
        // La sección no tiene importe propio, sus hijos serán visitados
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public BigDecimal getTaxAmount() {
        return taxAmount;
    }

    public BigDecimal getTotal() {
        if (taxStrategy.isTaxIncludedInPrice()) {
            return subtotal;
        }
        return subtotal.add(taxAmount);
    }

    public void reset() {
        subtotal = BigDecimal.ZERO;
        taxAmount = BigDecimal.ZERO;
    }
}

