package supermercado.patterns.abstractfactory;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import supermercado.domain.*;
import supermercado.patterns.builder.*;
import supermercado.patterns.strategy.*;

/**
 * PATRÓN ABSTRACT FACTORY - Fábrica concreta
 *
 * Fábrica de componentes para Francia.
 * Características:
 * - TVA incluido en precios
 * - Descuentos globales (no por línea)
 * - Pantalla gráfica de 12'' obligatoria
 * - NO permite devoluciones
 */
public class FranceFactory implements CountryFactory {

    @Override
    public TicketBuilder createTicketBuilder() {
        return new FranceTicketBuilder(createTaxStrategy(), createDiscountStrategy());
    }

    @Override
    public TaxStrategy createTaxStrategy() {
        return new FranceTaxStrategy();
    }

    @Override
    public DiscountStrategy createDiscountStrategy() {
        return new FranceDiscountStrategy();
    }

    @Override
    public CurrencyFormatter createCurrencyFormatter() {
        return new EuroCurrencyFormatter(Locale.FRANCE);
    }

    @Override
    public ReceiptRenderer createReceiptRenderer() {
        return new GraphicDisplayRenderer(); // Francia requiere pantalla gráfica
    }

    @Override
    public List<PaymentMethod> getAvailablePaymentMethods() {
        return Arrays.asList(
            new CashPayment(),
            new CardPayment(CardPayment.CardType.CREDIT),
            new CardPayment(CardPayment.CardType.DEBIT)
            // Francia podría tener métodos de pago adicionales como Carte Bleue
        );
    }

    @Override
    public boolean supportsReturns() {
        return false; // Francia NO permite devoluciones
    }

    @Override
    public String getCountryCode() {
        return "FR";
    }
}

