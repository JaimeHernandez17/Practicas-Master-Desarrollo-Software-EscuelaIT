package supermercado.patterns.strategy;

import java.math.BigDecimal;
import java.math.RoundingMode;

import supermercado.domain.Ticket;

/**
 * PATRÓN STRATEGY - Estrategia concreta
 *
 * Estrategia de descuentos para Francia.
 * - NO soporta descuentos por línea
 * - Soporta descuentos globales
 */
public class FranceDiscountStrategy implements DiscountStrategy {

    private static final BigDecimal DISCOUNT_THRESHOLD = new BigDecimal("100.00");
    private static final BigDecimal DISCOUNT_PERCENTAGE = new BigDecimal("0.05");

    @Override
    public BigDecimal calculateDiscount(Ticket ticket) {
        // En Francia: 5% de descuento global para compras > 100€
        BigDecimal subtotal = ticket.getSubtotal();
        if (subtotal.compareTo(DISCOUNT_THRESHOLD) > 0) {
            return subtotal.multiply(DISCOUNT_PERCENTAGE).setScale(2, RoundingMode.HALF_UP);
        }
        return BigDecimal.ZERO;
    }

    @Override
    public boolean supportsLineDiscount() {
        return false; // Francia NO permite descuentos por línea
    }

    @Override
    public boolean supportsGlobalDiscount() {
        return true; // Francia permite descuentos globales
    }
}

