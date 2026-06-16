package supermercado.patterns.strategy;

import java.math.BigDecimal;
import java.math.RoundingMode;

import supermercado.domain.TaxCategory;

/**
 * PATRÓN STRATEGY - Estrategia concreta
 *
 * Implementación del cálculo de Sales Tax para USA.
 * En USA los impuestos varían por estado y NO están incluidos en el precio.
 */
public class USATaxStrategy implements TaxStrategy {

    private BigDecimal stateTaxRate;

    public USATaxStrategy(BigDecimal stateTaxRate) {
        this.stateTaxRate = stateTaxRate;
    }

    // Constructor por defecto con tasa de California (7.25%)
    public USATaxStrategy() {
        this(new BigDecimal("0.0725"));
    }

    @Override
    public BigDecimal calculateTax(BigDecimal amount, TaxCategory category) {
        if (category == TaxCategory.EXEMPT) {
            return BigDecimal.ZERO;
        }
        // En USA el impuesto se añade al precio base
        return amount.multiply(stateTaxRate).setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public BigDecimal getTaxRate(TaxCategory category) {
        if (category == TaxCategory.EXEMPT) {
            return BigDecimal.ZERO;
        }
        return stateTaxRate;
    }

    @Override
    public boolean isTaxIncludedInPrice() {
        return false; // En USA el impuesto NO está incluido
    }

    public void setStateTaxRate(BigDecimal stateTaxRate) {
        this.stateTaxRate = stateTaxRate;
    }
}

