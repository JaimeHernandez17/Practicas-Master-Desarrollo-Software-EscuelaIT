package supermercado.patterns.templatemethod;

import supermercado.domain.*;
import supermercado.patterns.abstractfactory.CountryFactory;

/**
 * PATRÓN TEMPLATE METHOD - Implementación concreta
 *
 * Procesador de tickets para Francia.
 */
public class FranceTicketProcessor extends TicketProcessor {

    public FranceTicketProcessor(CountryFactory countryFactory) {
        super(countryFactory);
    }

    @Override
    protected boolean validateCart(Cart cart) {
        // Validaciones específicas de Francia
        // - Restricciones sobre productos
        return !cart.isEmpty();
    }

    @Override
    protected void applyLocalTaxes(Ticket ticket) {
        // En Francia la TVA también está incluida en el precio
        var taxStrategy = countryFactory.createTaxStrategy();
        // El cálculo ya se hace en el builder
    }

    @Override
    protected void applyLocalDiscounts(Ticket ticket) {
        // Francia: descuentos globales (se aplican al final)
        var discountStrategy = countryFactory.createDiscountStrategy();
        if (discountStrategy.supportsGlobalDiscount()) {
            var discount = discountStrategy.calculateDiscount(ticket);
            ticket.setDiscountAmount(discount);
            ticket.setTotal(ticket.getTotal().subtract(discount));
        }
    }

    @Override
    protected String formatReceipt(Ticket ticket) {
        // Francia: pantalla gráfica mínimo 12''
        var renderer = countryFactory.createReceiptRenderer();
        return renderer.render(ticket);
    }

    @Override
    protected void notifyLocalSystems(Ticket ticket) {
        // Francia: notificar a la DGFiP (Dirección General de Finanzas Públicas)
        // También sistema anti-fraude
        System.out.println("[FR] Ticket enregistré dans le système local");
    }
}

