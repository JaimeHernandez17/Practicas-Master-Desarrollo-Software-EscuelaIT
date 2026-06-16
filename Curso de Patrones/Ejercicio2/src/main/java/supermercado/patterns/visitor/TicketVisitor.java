package supermercado.patterns.visitor;

import supermercado.patterns.composite.*;

/**
 * PATRÓN VISITOR - Visitante
 *
 * Define la interfaz para visitar las diferentes líneas del ticket.
 * Permite agregar nuevas operaciones sobre los elementos del ticket
 * sin modificar sus clases.
 */
public interface TicketVisitor {

    /**
     * Visita una línea de venta.
     */
    void visitSaleLine(SaleLine line);

    /**
     * Visita una línea de repetición.
     */
    void visitRepetitionLine(RepetitionLine line);

    /**
     * Visita una línea de anulación.
     */
    void visitCancellationLine(CancellationLine line);

    /**
     * Visita una línea de devolución.
     */
    void visitReturnLine(ReturnLine line);

    /**
     * Visita una sección compuesta del ticket.
     */
    void visitCompositeSection(CompositeTicketSection section);
}

