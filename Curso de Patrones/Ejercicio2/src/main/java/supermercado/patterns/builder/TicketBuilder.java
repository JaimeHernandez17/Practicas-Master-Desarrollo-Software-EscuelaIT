package supermercado.patterns.builder;

import supermercado.domain.*;
import supermercado.patterns.composite.TicketComponent;

/**
 * PATRÓN BUILDER - Builder
 *
 * Define la interfaz para construir las diferentes partes de un ticket.
 * Permite crear tickets de diferentes tipos según el país.
 */
public interface TicketBuilder {

    /**
     * Reinicia el builder para crear un nuevo ticket.
     */
    void reset();

    /**
     * Construye la cabecera del ticket con datos de la tienda.
     * @param store La tienda donde se realiza la compra
     */
    void buildHeader(Store store);

    /**
     * Añade una línea de venta al ticket.
     * @param product El producto vendido
     * @param quantity La cantidad
     */
    void addSaleLine(Product product, int quantity);

    /**
     * Añade una línea de repetición (copia de otra línea).
     * @param lineNumber El número de línea a repetir
     */
    void addRepetitionLine(int lineNumber);

    /**
     * Añade una línea de anulación.
     * @param lineNumber El número de línea a anular
     */
    void addCancellationLine(int lineNumber);

    /**
     * Añade una línea de devolución.
     * @param product El producto a devolver
     * @param originalTicketId El ID del ticket original
     * @param reason El motivo de la devolución
     */
    void addReturnLine(Product product, String originalTicketId, String reason);

    /**
     * Construye el pie del ticket con el método de pago.
     * @param paymentMethod El método de pago utilizado
     */
    void buildFooter(PaymentMethod paymentMethod);

    /**
     * Obtiene el ticket construido.
     * @return El ticket finalizado
     */
    Ticket getResult();
}

