package supermercado.patterns.strategy;

import java.math.BigDecimal;

import supermercado.domain.TaxCategory;

/**
 * PATRÓN STRATEGY - Estrategia
 *
 * Define la interfaz para el cálculo de impuestos.
 * Cada país implementará su propia estrategia fiscal.
 */
public interface TaxStrategy {

    /**
     * Calcula el impuesto para un importe dado.
     * @param amount El importe base
     * @param category La categoría fiscal del producto
     * @return El importe del impuesto
     */
    BigDecimal calculateTax(BigDecimal amount, TaxCategory category);

    /**
     * Obtiene el tipo impositivo para una categoría.
     * @param category La categoría fiscal
     * @return El porcentaje como decimal (ej: 0.21 para 21%)
     */
    BigDecimal getTaxRate(TaxCategory category);

    /**
     * Indica si los precios ya incluyen impuestos.
     * @return true si los impuestos están incluidos en el precio
     */
    boolean isTaxIncludedInPrice();
}

