package supermercado.patterns.strategy;

import java.math.BigDecimal;
import java.math.RoundingMode;

import supermercado.domain.TaxCategory;

/**
 * PATRÓN STRATEGY - Estrategia concreta
 *
 * Implementación del cálculo de IVA para España.
 * - IVA General: 21%
 * - IVA Reducido: 10%
 * - IVA Superreducido: 4%
 */
public class SpainTaxStrategy implements TaxStrategy {

    private static final BigDecimal VAT_GENERAL = new BigDecimal("0.21");
    private static final BigDecimal VAT_REDUCED = new BigDecimal("0.10");
    private static final BigDecimal VAT_SUPER_REDUCED = new BigDecimal("0.04");
    private static final BigDecimal VAT_EXEMPT = BigDecimal.ZERO;

    @Override
    public BigDecimal calculateTax(BigDecimal amount, TaxCategory category) {
        BigDecimal rate = getTaxRate(category);
        // En España el IVA está incluido en el precio
        // Para calcular el IVA: precio * (tipo / (1 + tipo))
        BigDecimal divisor = BigDecimal.ONE.add(rate);
        BigDecimal baseImponible = amount.divide(divisor, 4, RoundingMode.HALF_UP);
        return amount.subtract(baseImponible).setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public BigDecimal getTaxRate(TaxCategory category) {
        switch (category) {
            case GENERAL:
                return VAT_GENERAL;
            case REDUCED:
                return VAT_REDUCED;
            case SUPER_REDUCED:
                return VAT_SUPER_REDUCED;
            case EXEMPT:
            default:
                return VAT_EXEMPT;
        }
    }

    @Override
    public boolean isTaxIncludedInPrice() {
        return true; // En España el IVA está incluido
    }
}

