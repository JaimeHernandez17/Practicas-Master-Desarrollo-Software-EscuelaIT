package supermercado.patterns.builder;

import supermercado.domain.*;
import supermercado.patterns.composite.*;
import supermercado.patterns.strategy.*;

/**
 * PATRÓN BUILDER - Builder concreto
 *
 * Constructor de tickets para España.
 * Características específicas:
 * - Permite devoluciones
 * - Display monocromo
 * - Descuentos por línea
 */
public class SpainTicketBuilder extends AbstractTicketBuilder {

    public SpainTicketBuilder(TaxStrategy taxStrategy, DiscountStrategy discountStrategy) {
        super(taxStrategy, discountStrategy);
    }

    @Override
    public void buildHeader(Store store) {
        this.ticket = new Ticket(generateTicketId(), store);
        // En España: cabecera simple con nombre y dirección
    }

    @Override
    public void addReturnLine(Product product, String originalTicketId, String reason) {
        // España SÍ permite devoluciones
        if (ticket != null) {
            lineCounter++;
            ReturnLine line = new ReturnLine(lineCounter, product, originalTicketId, reason);
            ticket.addLine(line);
        }
    }

    @Override
    public void buildFooter(PaymentMethod paymentMethod) {
        if (ticket != null) {
            ticket.setPaymentMethod(paymentMethod);
            calculateTotals();
            ticket.setStatus(TicketStatus.COMPLETED);
        }
    }
}

