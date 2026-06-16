package supermercado.patterns.abstractfactory;

import supermercado.domain.Ticket;

/**
 * PATRÓN ABSTRACT FACTORY - Producto concreto
 *
 * Renderizador de recibos para pantalla gráfica de 12''.
 * Requerido en Francia según normativa.
 */
public class GraphicDisplayRenderer implements ReceiptRenderer {

    @Override
    public String render(Ticket ticket) {
        StringBuilder sb = new StringBuilder();
        sb.append("╔══════════════════════════════════════════╗\n");
        sb.append("║   🛒 SUPERMERCADO INTERNACIONAL 🛒      ║\n");
        sb.append("╠══════════════════════════════════════════╣\n");
        sb.append("║ Tienda: ").append(String.format("%-31s", ticket.getStore().getName())).append("║\n");
        sb.append("║ Dirección: ").append(String.format("%-28s", ticket.getStore().getAddress())).append("║\n");
        sb.append("║ Fecha: ").append(String.format("%-32s", ticket.getDateTime())).append("║\n");
        sb.append("╠══════════════════════════════════════════╣\n");

        // Las líneas del ticket se renderizarían aquí via Visitor
        sb.append("║ [Líneas del ticket con gráficos]         ║\n");

        sb.append("╠══════════════════════════════════════════╣\n");
        sb.append("║ SUBTOTAL:              ").append(String.format("%15s", ticket.getSubtotal())).append(" €║\n");
        sb.append("║ IVA:                   ").append(String.format("%15s", ticket.getTaxAmount())).append(" €║\n");
        sb.append("║ TOTAL:                 ").append(String.format("%15s", ticket.getTotal())).append(" €║\n");
        sb.append("╚══════════════════════════════════════════╝\n");
        sb.append("           Merci de votre visite!            \n");

        return sb.toString();
    }

    @Override
    public String getDisplayType() {
        return "Pantalla Gráfica 12\"";
    }
}

