package supermercado.patterns.composite;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import supermercado.patterns.visitor.TicketVisitor;

/**
 * PATRÓN COMPOSITE - Compuesto
 *
 * Permite agrupar múltiples líneas del ticket como una sección.
 * Útil para organizar el ticket en cabecera, cuerpo y pie.
 */
public class CompositeTicketSection implements TicketComponent {
    private String name;
    private List<TicketComponent> children;

    public CompositeTicketSection(String name) {
        this.name = name;
        this.children = new ArrayList<>();
    }

    public void add(TicketComponent component) {
        children.add(component);
    }

    public void remove(TicketComponent component) {
        children.remove(component);
    }

    public TicketComponent getChild(int index) {
        if (index >= 0 && index < children.size()) {
            return children.get(index);
        }
        return null;
    }

    @Override
    public BigDecimal getAmount() {
        return children.stream()
                .map(TicketComponent::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public void accept(TicketVisitor visitor) {
        visitor.visitCompositeSection(this);
        for (TicketComponent child : children) {
            child.accept(visitor);
        }
    }

    @Override
    public String getDescription() {
        return "=== " + name + " ===";
    }

    public List<TicketComponent> getChildren() {
        return new ArrayList<>(children);
    }

    public String getName() {
        return name;
    }
}

