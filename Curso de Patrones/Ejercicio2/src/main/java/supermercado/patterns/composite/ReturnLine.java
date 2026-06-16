package supermercado.patterns.composite;

import java.math.BigDecimal;

import supermercado.domain.Product;
import supermercado.patterns.visitor.TicketVisitor;

/**
 * PATRÓN COMPOSITE - Hoja concreta
 *
 * Representa una línea de devolución de un producto.
 * No disponible en todos los países (ej: Francia no permite devoluciones).
 */
public class ReturnLine extends TicketLine {
    private Product product;
    private String originalTicketId;
    private String reason;

    public ReturnLine(int lineNumber, Product product, String originalTicketId, String reason) {
        super(lineNumber, "DEVOLUCIÓN: " + product.getDescription());
        this.product = product;
        this.originalTicketId = originalTicketId;
        this.reason = reason;
    }

    @Override
    public BigDecimal getAmount() {
        // Importe negativo para devolución
        return product.getPrice().negate();
    }

    @Override
    public void accept(TicketVisitor visitor) {
        visitor.visitReturnLine(this);
    }

    @Override
    public String getDescription() {
        return String.format("DEV: %s (Ticket: %s)", product.getDescription(), originalTicketId);
    }

    public Product getProduct() {
        return product;
    }

    public String getOriginalTicketId() {
        return originalTicketId;
    }

    public String getReason() {
        return reason;
    }
}

