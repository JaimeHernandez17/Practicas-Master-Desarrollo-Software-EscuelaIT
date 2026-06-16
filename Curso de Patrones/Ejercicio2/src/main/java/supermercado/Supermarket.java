package supermercado;

import supermercado.domain.*;
import supermercado.patterns.abstractfactory.*;
import supermercado.patterns.builder.*;
import supermercado.patterns.command.*;
import supermercado.patterns.templatemethod.*;

/**
 * Clase principal del sistema de tickets para supermercado internacional.
 *
 * Demuestra la aplicación de los patrones:
 * - Abstract Factory: Crear familias de objetos por país
 * - Builder: Construcción de tickets complejos
 * - Strategy: Algoritmos de impuestos y descuentos
 * - Command: Operaciones con soporte undo/redo
 * - Template Method: Proceso de ticket con pasos variables
 * - Composite: Estructura de líneas del ticket
 * - Visitor: Operaciones sobre líneas del ticket
 */
public class Supermarket {

    private CountryFactory countryFactory;
    private TicketProcessor ticketProcessor;
    private CommandInvoker commandInvoker;
    private TicketDirector ticketDirector;

    public Supermarket(String countryCode) {
        // Obtener la fábrica del país correspondiente
        this.countryFactory = CountryFactoryProvider.getInstance().getFactory(countryCode);

        // Crear el procesador de tickets según el país
        this.ticketProcessor = createTicketProcessor(countryCode);

        // Inicializar el invocador de comandos
        this.commandInvoker = new CommandInvoker();

        // Inicializar el director de construcción
        this.ticketDirector = new TicketDirector();
        this.ticketDirector.setBuilder(countryFactory.createTicketBuilder());
    }

    private TicketProcessor createTicketProcessor(String countryCode) {
        switch (countryCode.toUpperCase()) {
            case "ES":
                return new SpainTicketProcessor(countryFactory);
            case "FR":
                return new FranceTicketProcessor(countryFactory);
            default:
                throw new IllegalArgumentException("País no soportado: " + countryCode);
        }
    }

    /**
     * Procesa una compra completa.
     */
    public Ticket processpurchase(Cart cart, PaymentMethod payment) {
        return ticketProcessor.processTicket(cart, payment);
    }

    /**
     * Obtiene los métodos de pago disponibles en el país.
     */
    public java.util.List<PaymentMethod> getAvailablePaymentMethods() {
        return countryFactory.getAvailablePaymentMethods();
    }

    /**
     * Verifica si el país permite devoluciones.
     */
    public boolean supportsReturns() {
        return countryFactory.supportsReturns();
    }

    /**
     * Deshace la última operación.
     */
    public boolean undo() {
        return commandInvoker.undo();
    }

    /**
     * Rehace la última operación deshecha.
     */
    public boolean redo() {
        return commandInvoker.redo();
    }

    public CountryFactory getCountryFactory() {
        return countryFactory;
    }

    public TicketDirector getTicketDirector() {
        return ticketDirector;
    }
}

