package supermercado.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import supermercado.patterns.composite.TicketComponent;
import supermercado.patterns.visitor.TicketVisitor;

/**
 * Representa un ticket de compra del supermercado.
 * Actúa como raíz del patrón Composite para las líneas del ticket.
 */
public class Ticket implements TicketComponent {
    private String id;
    private LocalDateTime dateTime;
    private Store store;
    private List<TicketComponent> lines;
    private TicketStatus status;
    private PaymentMethod paymentMethod;
    private BigDecimal subtotal;
    private BigDecimal taxAmount;
    private BigDecimal discountAmount;
    private BigDecimal total;

    public Ticket(String id, Store store) {
        this.id = id;
        this.store = store;
        this.dateTime = LocalDateTime.now();
        this.lines = new ArrayList<>();
        this.status = TicketStatus.IN_PROGRESS;
        this.subtotal = BigDecimal.ZERO;
        this.taxAmount = BigDecimal.ZERO;
        this.discountAmount = BigDecimal.ZERO;
        this.total = BigDecimal.ZERO;
    }

    public void addLine(TicketComponent line) {
        lines.add(line);
    }

    public void removeLine(int lineNumber) {
        if (lineNumber >= 0 && lineNumber < lines.size()) {
            lines.remove(lineNumber);
        }
    }

    public TicketComponent getLine(int lineNumber) {
        if (lineNumber >= 0 && lineNumber < lines.size()) {
            return lines.get(lineNumber);
        }
        return null;
    }

    @Override
    public BigDecimal getAmount() {
        return lines.stream()
                .map(TicketComponent::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public void accept(TicketVisitor visitor) {
        for (TicketComponent line : lines) {
            line.accept(visitor);
        }
    }

    @Override
    public String getDescription() {
        return "Ticket #" + id;
    }

    // Getters y Setters
    public String getId() {
        return id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public Store getStore() {
        return store;
    }

    public List<TicketComponent> getLines() {
        return new ArrayList<>(lines);
    }

    public TicketStatus getStatus() {
        return status;
    }

    public void setStatus(TicketStatus status) {
        this.status = status;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(BigDecimal taxAmount) {
        this.taxAmount = taxAmount;
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}

