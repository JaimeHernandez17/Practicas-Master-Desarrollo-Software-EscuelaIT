package supermercado.domain;

import java.math.BigDecimal;

/**
 * Pago en efectivo.
 */
public class CashPayment extends PaymentMethod {
    private BigDecimal amountGiven;
    private BigDecimal change;

    public CashPayment() {
        super("Efectivo");
    }

    @Override
    public boolean processPayment(BigDecimal amount) {
        if (amountGiven.compareTo(amount) >= 0) {
            this.amount = amount;
            this.change = amountGiven.subtract(amount);
            return true;
        }
        return false;
    }

    @Override
    public String getReceiptDescription() {
        return String.format("EFECTIVO - Entregado: %.2f - Cambio: %.2f",
                amountGiven, change);
    }

    public void setAmountGiven(BigDecimal amountGiven) {
        this.amountGiven = amountGiven;
    }

    public BigDecimal getChange() {
        return change;
    }
}

