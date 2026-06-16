package supermercado.patterns.templatemethod;

import supermercado.domain.*;
import supermercado.patterns.abstractfactory.CountryFactory;

/**
 * PATRÓN TEMPLATE METHOD - Implementación concreta
 *
 * Procesador de tickets para España.
 */
public class SpainTicketProcessor extends TicketProcessor {

    public SpainTicketProcessor(CountryFactory countryFactory) {
        super(countryFactory);
    }

    @Override
    protected boolean validateCart(Cart cart) {
        // Validaciones específicas de España
        // - No se pueden vender ciertos productos a menores
        // - Límite de unidades de alcohol
        return !cart.isEmpty();
    }

    @Override
    protected void applyLocalTaxes(Ticket ticket) {
        // En España el IVA ya está incluido en el precio
        // Solo calcular y desglosar para el ticket
        var taxStrategy = countryFactory.createTaxStrategy();
        // El cálculo ya se hace en el builder
    }

    @Override
    protected void applyLocalDiscounts(Ticket ticket) {
        // España: descuentos por línea (ya aplicados en las líneas)
        var discountStrategy = countryFactory.createDiscountStrategy();
        if (discountStrategy.supportsLineDiscount()) {
            // Los descuentos ya están aplicados en cada línea
        }
    }

    @Override
    protected String formatReceipt(Ticket ticket) {
        // España: display monocromo
        var renderer = countryFactory.createReceiptRenderer();
        return renderer.render(ticket);
    }

    @Override
    protected void notifyLocalSystems(Ticket ticket) {
        // España: notificar a la Agencia Tributaria (AEAT) si corresponde
        // Simulación
        System.out.println("[ES] Ticket registrado en sistema local");
    }
}

