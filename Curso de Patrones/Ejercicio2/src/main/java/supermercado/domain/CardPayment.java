package supermercado.domain;

import java.math.BigDecimal;

/**
 * Pago con tarjeta (crédito/débito).
 */
public class CardPayment extends PaymentMethod {
    private String lastFourDigits;
    private CardType cardType;

    public enum CardType {
        CREDIT,
        DEBIT
    }

    public CardPayment(CardType cardType) {
        super(cardType == CardType.CREDIT ? "Tarjeta de Crédito" : "Tarjeta de Débito");
        this.cardType = cardType;
    }

    @Override
    public boolean processPayment(BigDecimal amount) {
        // Simulación de procesamiento de pago con tarjeta
        this.amount = amount;
        return true;
    }

    @Override
    public String getReceiptDescription() {
        return String.format("TARJETA %s - ****%s",
                cardType == CardType.CREDIT ? "CRÉDITO" : "DÉBITO",
                lastFourDigits);
    }

    public void setLastFourDigits(String lastFourDigits) {
        this.lastFourDigits = lastFourDigits;
    }

    public CardType getCardType() {
        return cardType;
    }
}

