package supermercado.patterns.abstractfactory;

import supermercado.domain.Ticket;

/**
 * PATRÓN ABSTRACT FACTORY - Producto concreto
 *
 * Renderizador de recibos para display monocromo.
 * Usado en España según normativa.
 */
public class MonochromeDisplayRenderer implements ReceiptRenderer {

    @Override
    public String render(Ticket ticket) {
        StringBuilder sb = new StringBuilder();
        sb.append("================================\n");
        sb.append("    SUPERMERCADO INTERNACIONAL  \n");
        sb.append("================================\n");
        sb.append("Tienda: ").append(ticket.getStore().getName()).append("\n");
        sb.append("Fecha: ").append(ticket.getDateTime()).append("\n");
        sb.append("--------------------------------\n");

        // Las líneas del ticket se renderizarían aquí via Visitor
        sb.append("[Líneas del ticket]\n");

        sb.append("--------------------------------\n");
        sb.append("TOTAL: ").append(ticket.getTotal()).append(" €\n");
        sb.append("================================\n");
        sb.append("      ¡Gracias por su compra!   \n");

        return sb.toString();
    }

    @Override
    public String getDisplayType() {
        return "Display Monocromo";
    }
}

