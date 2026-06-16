package supermercado.patterns.strategy;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * PATRÓN STRATEGY - Estrategia
 *
 * Define la interfaz para formatear importes según la moneda del país.
 */
public interface CurrencyFormatter {

    /**
     * Formatea un importe según las convenciones del país.
     * @param amount El importe a formatear
     * @return El importe formateado como String
     */
    String format(BigDecimal amount);

    /**
     * Obtiene el símbolo de la moneda.
     * @return El símbolo (€, $, £, etc.)
     */
    String getSymbol();

    /**
     * Obtiene el modo de redondeo.
     * @return El modo de redondeo a aplicar
     */
    RoundingMode getRoundingMode();
}

