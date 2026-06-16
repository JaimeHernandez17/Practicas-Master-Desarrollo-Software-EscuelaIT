package supermercado.patterns.strategy;

import java.math.BigDecimal;
import java.math.RoundingMode;

import supermercado.domain.TaxCategory;

/**
 * PATRÓN STRATEGY - Estrategia concreta
 *
 * Implementación del cálculo de TVA para Francia.
 * - TVA Normal: 20%
 * - TVA Intermedio: 10%
 * - TVA Reducido: 5.5%
 */
public class FranceTaxStrategy implements TaxStrategy {

    private static final BigDecimal TVA_NORMAL = new BigDecimal("0.20");
    private static final BigDecimal TVA_INTERMEDIATE = new BigDecimal("0.10");
    private static final BigDecimal TVA_REDUCED = new BigDecimal("0.055");
    private static final BigDecimal TVA_EXEMPT = BigDecimal.ZERO;

    @Override
    public BigDecimal calculateTax(BigDecimal amount, TaxCategory category) {
        BigDecimal rate = getTaxRate(category);
        // En Francia la TVA también está incluida en el precio
        BigDecimal divisor = BigDecimal.ONE.add(rate);
        BigDecimal baseImponible = amount.divide(divisor, 4, RoundingMode.HALF_UP);
        return amount.subtract(baseImponible).setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public BigDecimal getTaxRate(TaxCategory category) {
        switch (category) {
            case GENERAL:
                return TVA_NORMAL;
            case REDUCED:
                return TVA_INTERMEDIATE;
            case SUPER_REDUCED:
                return TVA_REDUCED;
            case EXEMPT:
            default:
                return TVA_EXEMPT;
        }
    }

    @Override
    public boolean isTaxIncludedInPrice() {
        return true; // En Francia la TVA está incluida
    }
}

