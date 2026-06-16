package supermercado.patterns.abstractfactory;

import supermercado.domain.Ticket;

/**
 * PATRÓN ABSTRACT FACTORY - Producto abstracto
 *
 * Define la interfaz para renderizar recibos según
 * las normativas y dispositivos de cada país.
 */
public interface ReceiptRenderer {

    /**
     * Renderiza el ticket para su presentación.
     * @param ticket El ticket a renderizar
     * @return La representación del ticket
     */
    String render(Ticket ticket);

    /**
     * Obtiene el tipo de display soportado.
     * @return Descripción del tipo de display
     */
    String getDisplayType();
}

