package supermercado.patterns.command;

import java.math.BigDecimal;

import supermercado.domain.*;

/**
 * PATRÓN COMMAND - Comando concreto
 *
 * Comando para procesar el pago del ticket.
 */
public class ProcessPaymentCommand implements Command {

    private Ticket ticket;
    private PaymentMethod paymentMethod;
    private BigDecimal amount;
    private TicketStatus previousStatus;
    private boolean executed;

    public ProcessPaymentCommand(Ticket ticket, PaymentMethod paymentMethod, BigDecimal amount) {
        this.ticket = ticket;
        this.paymentMethod = paymentMethod;
        this.amount = amount;
        this.executed = false;
    }

    @Override
    public void execute() {
        if (!executed) {
            previousStatus = ticket.getStatus();

            boolean paymentSuccess = paymentMethod.processPayment(amount);

            if (paymentSuccess) {
                ticket.setPaymentMethod(paymentMethod);
                ticket.setStatus(TicketStatus.COMPLETED);
                executed = true;
            }
        }
    }

    @Override
    public void undo() {
        if (executed) {
            ticket.setStatus(previousStatus);
            ticket.setPaymentMethod(null);
            executed = false;
        }
    }

    @Override
    public String getDescription() {
        return "Procesar pago: " + amount + " con " + paymentMethod.getName();
    }
}

