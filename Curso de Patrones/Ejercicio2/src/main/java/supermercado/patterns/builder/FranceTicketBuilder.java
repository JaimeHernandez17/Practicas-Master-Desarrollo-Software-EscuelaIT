package supermercado.patterns.builder;

import supermercado.domain.*;
import supermercado.patterns.strategy.*;

/**
 * PATRÓN BUILDER - Builder concreto
 *
 * Constructor de tickets para Francia.
 * Características específicas:
 * - NO permite devoluciones
 * - Pantalla gráfica 12''
 * - Descuentos globales
 */
public class FranceTicketBuilder extends AbstractTicketBuilder {

    public FranceTicketBuilder(TaxStrategy taxStrategy, DiscountStrategy discountStrategy) {
        super(taxStrategy, discountStrategy);
    }

    @Override
    public void buildHeader(Store store) {
        this.ticket = new Ticket(generateTicketId(), store);
        // En Francia: cabecera con más información legal
    }

    @Override
    public void addReturnLine(Product product, String originalTicketId, String reason) {
        // Francia NO permite devoluciones - lanzar excepción
        throw new UnsupportedOperationException(
            "Las devoluciones no están permitidas en Francia. " +
            "Contacte con servicio al cliente."
        );
    }

    @Override
    public void buildFooter(PaymentMethod paymentMethod) {
        if (ticket != null) {
            ticket.setPaymentMethod(paymentMethod);
            calculateTotals();

            // En Francia se aplican descuentos globales al final
            applyGlobalDiscounts();

            ticket.setStatus(TicketStatus.COMPLETED);
        }
    }

    private void applyGlobalDiscounts() {
        // Aplicar descuentos globales según política francesa
        if (discountStrategy.supportsGlobalDiscount()) {
            var discount = discountStrategy.calculateDiscount(ticket);
            ticket.setDiscountAmount(discount);
            ticket.setTotal(ticket.getTotal().subtract(discount));
        }
    }
}

