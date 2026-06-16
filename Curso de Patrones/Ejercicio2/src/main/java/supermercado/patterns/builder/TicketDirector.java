package supermercado.patterns.builder;

import supermercado.domain.*;

import java.util.List;

/**
 * PATRÓN BUILDER - Director
 *
 * Orquesta la construcción de tickets usando diferentes builders.
 * Define el orden de construcción y los pasos comunes.
 */
public class TicketDirector {

    private TicketBuilder builder;

    public void setBuilder(TicketBuilder builder) {
        this.builder = builder;
    }

    /**
     * Construye un ticket mínimo solo con líneas de venta.
     */
    public Ticket buildMinimalTicket(Store store, List<CartItem> items) {
        builder.reset();
        builder.buildHeader(store);

        for (CartItem item : items) {
            builder.addSaleLine(item.getProduct(), item.getQuantity());
        }

        return builder.getResult();
    }

    /**
     * Construye un ticket completo con pago procesado.
     */
    public Ticket buildFullTicket(Store store, List<CartItem> items, PaymentMethod payment) {
        builder.reset();
        builder.buildHeader(store);

        for (CartItem item : items) {
            builder.addSaleLine(item.getProduct(), item.getQuantity());
        }

        builder.buildFooter(payment);

        return builder.getResult();
    }

    /**
     * Construye un ticket con una devolución.
     */
    public Ticket buildReturnTicket(Store store, Product product,
                                    String originalTicketId, String reason,
                                    PaymentMethod refundMethod) {
        builder.reset();
        builder.buildHeader(store);
        builder.addReturnLine(product, originalTicketId, reason);
        builder.buildFooter(refundMethod);

        return builder.getResult();
    }
}

