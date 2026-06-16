package supermercado.patterns.strategy;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

/**
 * PATRÓN STRATEGY - Estrategia concreta
 *
 * Formateador de moneda para Euros (España, Francia, etc.)
 */
public class EuroCurrencyFormatter implements CurrencyFormatter {

    private DecimalFormat formatter;
    private Locale locale;

    public EuroCurrencyFormatter(Locale locale) {
        this.locale = locale;
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(locale);
        symbols.setCurrencySymbol("€");
        this.formatter = new DecimalFormat("#,##0.00 €", symbols);
    }

    // Constructor por defecto para España
    public EuroCurrencyFormatter() {
        this(new Locale("es", "ES"));
    }

    @Override
    public String format(BigDecimal amount) {
        return formatter.format(amount.setScale(2, getRoundingMode()));
    }

    @Override
    public String getSymbol() {
        return "€";
    }

    @Override
    public RoundingMode getRoundingMode() {
        return RoundingMode.HALF_UP;
    }
}

