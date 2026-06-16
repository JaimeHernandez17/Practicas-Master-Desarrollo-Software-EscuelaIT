package supermercado.patterns.abstractfactory;

import java.util.Arrays;
import java.util.List;

import supermercado.domain.*;
import supermercado.patterns.builder.*;
import supermercado.patterns.strategy.*;

/**
 * PATRÓN ABSTRACT FACTORY - Fábrica concreta
 *
 * Fábrica de componentes para España.
 * Características:
 * - IVA incluido en precios
 * - Descuentos por línea (no globales)
 * - Display monocromo
 * - Permite devoluciones
 */
public class SpainFactory implements CountryFactory {

    @Override
    public TicketBuilder createTicketBuilder() {
        return new SpainTicketBuilder(createTaxStrategy(), createDiscountStrategy());
    }

    @Override
    public TaxStrategy createTaxStrategy() {
        return new SpainTaxStrategy();
    }

    @Override
    public DiscountStrategy createDiscountStrategy() {
        return new SpainDiscountStrategy();
    }

    @Override
    public CurrencyFormatter createCurrencyFormatter() {
        return new EuroCurrencyFormatter();
    }

    @Override
    public ReceiptRenderer createReceiptRenderer() {
        return new MonochromeDisplayRenderer();
    }

    @Override
    public List<PaymentMethod> getAvailablePaymentMethods() {
        return Arrays.asList(
            new CashPayment(),
            new CardPayment(CardPayment.CardType.CREDIT),
            new CardPayment(CardPayment.CardType.DEBIT)
        );
    }

    @Override
    public boolean supportsReturns() {
        return true; // España permite devoluciones
    }

    @Override
    public String getCountryCode() {
        return "ES";
    }
}

