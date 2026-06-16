package supermercado.patterns.strategy;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

/**
 * PATRÓN STRATEGY - Estrategia concreta
 *
 * Formateador de moneda para Dólares (USA, etc.)
 */
public class DollarCurrencyFormatter implements CurrencyFormatter {

    private DecimalFormat formatter;

    public DollarCurrencyFormatter() {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
        symbols.setCurrencySymbol("$");
        this.formatter = new DecimalFormat("$#,##0.00", symbols);
    }

    @Override
    public String format(BigDecimal amount) {
        return formatter.format(amount.setScale(2, getRoundingMode()));
    }

    @Override
    public String getSymbol() {
        return "$";
    }

    @Override
    public RoundingMode getRoundingMode() {
        return RoundingMode.HALF_UP;
    }
}

