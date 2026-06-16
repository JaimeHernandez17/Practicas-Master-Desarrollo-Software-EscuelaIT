package supermercado.patterns.templatemethod;

import supermercado.domain.*;
import supermercado.patterns.abstractfactory.CountryFactory;

/**
 * PATRÓN TEMPLATE METHOD
 *
 * Define el esqueleto del algoritmo para procesar un ticket,
 * difiriendo algunos pasos a las subclases de cada país.
 */
public abstract class TicketProcessor {

    protected CountryFactory countryFactory;

    public TicketProcessor(CountryFactory countryFactory) {
        this.countryFactory = countryFactory;
    }

    /**
     * Método plantilla que define el algoritmo de procesamiento.
     * Los pasos específicos se implementan en las subclases.
     */
    public final Ticket processTicket(Cart cart, PaymentMethod payment) {
        // 1. Validar el carrito según reglas del país
        if (!validateCart(cart)) {
            throw new IllegalArgumentException("Carrito no válido según normativa local");
        }

        // 2. Construir el ticket
        Ticket ticket = buildTicket(cart, payment);

        // 3. Aplicar impuestos locales
        applyLocalTaxes(ticket);

        // 4. Aplicar descuentos locales
        applyLocalDiscounts(ticket);

        // 5. Formatear recibo según normativa
        String receipt = formatReceipt(ticket);
        System.out.println(receipt);

        // 6. Notificar a sistemas locales (hacienda, inventario, etc.)
        notifyLocalSystems(ticket);

        return ticket;
    }

    /**
     * Valida el carrito según las reglas del país.
     */
    protected abstract boolean validateCart(Cart cart);

    /**
     * Construye el ticket usando el builder del país.
     */
    protected Ticket buildTicket(Cart cart, PaymentMethod payment) {
        var builder = countryFactory.createTicketBuilder();
        Store store = new Store("001", "Supermercado", "Dirección", "123456789",
                               countryFactory.getCountryCode());

        builder.buildHeader(store);
        for (CartItem item : cart.getItems()) {
            builder.addSaleLine(item.getProduct(), item.getQuantity());
        }
        builder.buildFooter(payment);

        return builder.getResult();
    }

    /**
     * Aplica los impuestos según la normativa del país.
     */
    protected abstract void applyLocalTaxes(Ticket ticket);

    /**
     * Aplica los descuentos según la política del país.
     */
    protected abstract void applyLocalDiscounts(Ticket ticket);

    /**
     * Formatea el recibo según los requisitos del país.
     */
    protected abstract String formatReceipt(Ticket ticket);

    /**
     * Notifica a los sistemas locales (hacienda, inventario, etc.)
     */
    protected abstract void notifyLocalSystems(Ticket ticket);
}

