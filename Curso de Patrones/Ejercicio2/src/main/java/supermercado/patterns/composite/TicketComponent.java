package supermercado.patterns.composite;

import java.math.BigDecimal;

import supermercado.patterns.visitor.TicketVisitor;

/**
 * PATRÓN COMPOSITE - Componente
 *
 * Interfaz común para todas las líneas del ticket, permitiendo tratar
 * de manera uniforme tanto líneas individuales como agrupaciones de líneas.
 */
public interface TicketComponent {

    /**
     * Obtiene el importe asociado a este componente.
     * @return El importe (puede ser positivo o negativo para anulaciones)
     */
    BigDecimal getAmount();

    /**
     * Acepta un visitante para realizar operaciones sobre el componente.
     * Implementación del patrón Visitor.
     * @param visitor El visitante que realizará la operación
     */
    void accept(TicketVisitor visitor);

    /**
     * Obtiene la descripción del componente para mostrar en el ticket.
     * @return Descripción formateada
     */
    String getDescription();
}

