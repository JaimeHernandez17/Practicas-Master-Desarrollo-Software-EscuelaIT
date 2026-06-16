package supermercado.patterns.strategy;

import java.math.BigDecimal;

import supermercado.domain.Ticket;

/**
 * PATRÓN STRATEGY - Estrategia concreta
 *
 * Estrategia de descuentos para España.
 * - Soporta descuentos por línea
 * - NO soporta descuentos globales
 */
public class SpainDiscountStrategy implements DiscountStrategy {

    @Override
    public BigDecimal calculateDiscount(Ticket ticket) {
        // Los descuentos de España se aplican por línea individual
        // Este método devuelve el total ya descontado en las líneas
        return ticket.getDiscountAmount();
    }

    @Override
    public boolean supportsLineDiscount() {
        return true; // España permite descuentos por línea
    }

    @Override
    public boolean supportsGlobalDiscount() {
        return false; // España NO permite descuentos globales
    }
}

