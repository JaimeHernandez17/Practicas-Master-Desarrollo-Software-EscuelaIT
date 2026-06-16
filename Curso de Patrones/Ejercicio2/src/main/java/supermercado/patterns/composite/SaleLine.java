package supermercado.patterns.composite;

import java.math.BigDecimal;

import supermercado.domain.Product;
import supermercado.patterns.visitor.TicketVisitor;

/**
 * PATRÓN COMPOSITE - Hoja concreta
 *
 * Representa una línea de venta estándar en el ticket.
 * Contiene producto, cantidad y precio unitario.
 */
public class SaleLine extends TicketLine {
    private Product product;
    private int quantity;
    private BigDecimal unitPrice;
    private BigDecimal lineDiscount;

    public SaleLine(int lineNumber, Product product, int quantity) {
        super(lineNumber, product.getDescription());
        this.product = product;
        this.quantity = quantity;
        this.unitPrice = product.getPrice();
        this.lineDiscount = BigDecimal.ZERO;
    }

    @Override
    public BigDecimal getAmount() {
        BigDecimal total = unitPrice.multiply(BigDecimal.valueOf(quantity));
        return total.subtract(lineDiscount);
    }

    @Override
    public void accept(TicketVisitor visitor) {
        visitor.visitSaleLine(this);
    }

    @Override
    public String getDescription() {
        return String.format("%d x %s @ %.2f", quantity, product.getDescription(), unitPrice);
    }

    // Getters
    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public BigDecimal getLineDiscount() {
        return lineDiscount;
    }

    public void setLineDiscount(BigDecimal lineDiscount) {
        this.lineDiscount = lineDiscount;
    }
}

