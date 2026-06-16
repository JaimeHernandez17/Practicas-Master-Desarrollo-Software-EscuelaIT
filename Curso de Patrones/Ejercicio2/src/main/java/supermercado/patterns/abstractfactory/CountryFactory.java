package supermercado.patterns.abstractfactory;

import java.util.List;

import supermercado.domain.PaymentMethod;
import supermercado.patterns.builder.TicketBuilder;
import supermercado.patterns.strategy.*;

/**
 * PATRÓN ABSTRACT FACTORY - Fábrica abstracta
 *
 * Define la interfaz para crear familias de objetos relacionados
 * sin especificar sus clases concretas.
 *
 * Cada país tendrá su propia fábrica que crea:
 * - Builder de tickets adaptado al país
 * - Estrategia de impuestos del país
 * - Estrategia de descuentos del país
 * - Formateador de moneda del país
 * - Renderizador de recibos del país
 * - Métodos de pago disponibles en el país
 */
public interface CountryFactory {

    /**
     * Crea un builder de tickets adaptado al país.
     */
    TicketBuilder createTicketBuilder();

    /**
     * Crea la estrategia de cálculo de impuestos del país.
     */
    TaxStrategy createTaxStrategy();

    /**
     * Crea la estrategia de descuentos del país.
     */
    DiscountStrategy createDiscountStrategy();

    /**
     * Crea el formateador de moneda del país.
     */
    CurrencyFormatter createCurrencyFormatter();

    /**
     * Crea el renderizador de recibos según normativa del país.
     */
    ReceiptRenderer createReceiptRenderer();

    /**
     * Obtiene los métodos de pago disponibles en el país.
     */
    List<PaymentMethod> getAvailablePaymentMethods();

    /**
     * Indica si el país permite devoluciones.
     */
    boolean supportsReturns();

    /**
     * Obtiene el código del país.
     */
    String getCountryCode();
}

