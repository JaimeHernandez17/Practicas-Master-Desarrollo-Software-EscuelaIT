package supermercado.domain;

import java.math.BigDecimal;

/**
 * Clase base abstracta para los métodos de pago.
 * Permite extensión para diferentes países con distintos métodos de pago disponibles.
 */
public abstract class PaymentMethod {
    protected String name;
    protected BigDecimal amount;

    public PaymentMethod(String name) {
        this.name = name;
        this.amount = BigDecimal.ZERO;
    }

    public abstract boolean processPayment(BigDecimal amount);

    public abstract String getReceiptDescription();

    public String getName() {
        return name;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}

