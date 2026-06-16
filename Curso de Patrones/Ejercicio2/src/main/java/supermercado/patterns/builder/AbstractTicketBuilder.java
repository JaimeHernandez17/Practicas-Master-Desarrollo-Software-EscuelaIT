package supermercado.patterns.builder;

import java.math.BigDecimal;
import java.util.UUID;

import supermercado.domain.*;
import supermercado.patterns.composite.*;
import supermercado.patterns.strategy.*;

/**
 * PATRÓN BUILDER - Builder abstracto
 *
 * Implementación base del builder de tickets con la lógica común.
 * Los métodos específicos de cada país se implementan en las subclases.
 */
public abstract class AbstractTicketBuilder implements TicketBuilder {

    protected Ticket ticket;
    protected TaxStrategy taxStrategy;
    protected DiscountStrategy discountStrategy;
    protected int lineCounter;

    public AbstractTicketBuilder(TaxStrategy taxStrategy, DiscountStrategy discountStrategy) {
        this.taxStrategy = taxStrategy;
        this.discountStrategy = discountStrategy;
        reset();
    }

    @Override
    public void reset() {
        this.ticket = null;
        this.lineCounter = 0;
    }

    @Override
    public void addSaleLine(Product product, int quantity) {
        if (ticket != null) {
            lineCounter++;
            SaleLine line = new SaleLine(lineCounter, product, quantity);
            ticket.addLine(line);
        }
    }

    @Override
    public void addRepetitionLine(int lineNumber) {
        if (ticket != null) {
            TicketComponent originalLine = ticket.getLine(lineNumber - 1);
            if (originalLine instanceof TicketLine) {
                lineCounter++;
                RepetitionLine line = new RepetitionLine(lineCounter, (TicketLine) originalLine);
                ticket.addLine(line);
            }
        }
    }

    @Override
    public void addCancellationLine(int lineNumber) {
        if (ticket != null) {
            TicketComponent originalLine = ticket.getLine(lineNumber - 1);
            if (originalLine instanceof TicketLine) {
                lineCounter++;
                CancellationLine line = new CancellationLine(lineCounter, (TicketLine) originalLine);
                ticket.addLine(line);
            }
        }
    }

    @Override
    public Ticket getResult() {
        Ticket result = ticket;
        reset();
        return result;
    }

    protected String generateTicketId() {
        return UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    protected void calculateTotals() {
        if (ticket != null) {
            BigDecimal subtotal = ticket.getAmount();
            ticket.setSubtotal(subtotal);

            // Calcular impuestos (simplificado)
            BigDecimal taxAmount = subtotal.multiply(new BigDecimal("0.21"));
            ticket.setTaxAmount(taxAmount);

            // Calcular descuentos
            BigDecimal discount = discountStrategy.calculateDiscount(ticket);
            ticket.setDiscountAmount(discount);

            // Calcular total
            if (taxStrategy.isTaxIncludedInPrice()) {
                ticket.setTotal(subtotal.subtract(discount));
            } else {
                ticket.setTotal(subtotal.add(taxAmount).subtract(discount));
            }
        }
    }
}

