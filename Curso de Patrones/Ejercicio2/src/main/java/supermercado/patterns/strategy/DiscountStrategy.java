package supermercado.patterns.strategy;

import java.math.BigDecimal;

import supermercado.domain.Ticket;

/**
 * PATRÓN STRATEGY - Estrategia
 *
 * Define la interfaz para el cálculo de descuentos.
 * Cada país puede tener diferentes políticas de descuentos.
 */
public interface DiscountStrategy {

    /**
     * Calcula el descuento aplicable al ticket.
     * @param ticket El ticket sobre el que calcular descuentos
     * @return El importe total de descuento
     */
    BigDecimal calculateDiscount(Ticket ticket);

    /**
     * Indica si este país soporta descuentos por línea.
     * @return true si se permiten descuentos individuales
     */
    boolean supportsLineDiscount();

    /**
     * Indica si este país soporta descuentos globales.
     * @return true si se permiten descuentos sobre el total
     */
    boolean supportsGlobalDiscount();
}

