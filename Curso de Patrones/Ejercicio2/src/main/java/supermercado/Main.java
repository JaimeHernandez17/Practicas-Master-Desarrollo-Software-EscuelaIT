package supermercado;

import java.math.BigDecimal;

import supermercado.domain.*;
import supermercado.patterns.abstractfactory.*;
import supermercado.patterns.strategy.*;
import supermercado.patterns.visitor.*;

/**
 * Clase de demostración del sistema de tickets.
 *
 * Muestra ejemplos de uso de los diferentes patrones implementados.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("=".repeat(60));
        System.out.println("SISTEMA DE TICKETS - SUPERMERCADO INTERNACIONAL");
        System.out.println("=".repeat(60));

        // Demostración con España
        System.out.println("\n>>> DEMOSTRACIÓN ESPAÑA <<<\n");
        demonstrateCountry("ES");

        System.out.println("\n" + "=".repeat(60));

        // Demostración con Francia
        System.out.println("\n>>> DEMOSTRACIÓN FRANCIA <<<\n");
        demonstrateCountry("FR");

        System.out.println("\n" + "=".repeat(60));
        System.out.println("FIN DE LA DEMOSTRACIÓN");
    }

    private static void demonstrateCountry(String countryCode) {
        // 1. Crear el supermercado para el país
        Supermarket supermarket = new Supermarket(countryCode);
        CountryFactory factory = supermarket.getCountryFactory();

        System.out.println("País: " + factory.getCountryCode());
        System.out.println("Permite devoluciones: " + supermarket.supportsReturns());
        System.out.println("Tipo de display: " + factory.createReceiptRenderer().getDisplayType());
        System.out.println();

        // 2. Crear productos de ejemplo
        Product leche = new Product("001", "Leche Entera 1L", new BigDecimal("1.50"), TaxCategory.REDUCED);
        Product pan = new Product("002", "Pan de Molde", new BigDecimal("2.20"), TaxCategory.SUPER_REDUCED);
        Product cerveza = new Product("003", "Cerveza Pack 6", new BigDecimal("5.99"), TaxCategory.GENERAL);

        // 3. Crear y llenar el carrito
        Cart cart = new Cart();
        cart.addItem(leche, 2);
        cart.addItem(pan, 1);
        cart.addItem(cerveza, 1);

        System.out.println("Carrito de compra:");
        for (CartItem item : cart.getItems()) {
            System.out.println("  - " + item.getQuantity() + "x " +
                             item.getProduct().getDescription() + " @ " +
                             item.getProduct().getPrice() + "€");
        }
        System.out.println("Subtotal carrito: " + cart.getSubtotal() + "€");
        System.out.println();

        // 4. Procesar el ticket
        PaymentMethod payment = new CashPayment();
        ((CashPayment) payment).setAmountGiven(new BigDecimal("20.00"));

        Ticket ticket = supermarket.processpurchase(cart, payment);

        // 5. Mostrar información del ticket usando Visitor
        TaxStrategy taxStrategy = factory.createTaxStrategy();
        CurrencyFormatter currencyFormatter = factory.createCurrencyFormatter();

        TotalCalculatorVisitor calculatorVisitor = new TotalCalculatorVisitor(taxStrategy);
        PrinterVisitor printerVisitor = new PrinterVisitor(currencyFormatter);

        ticket.accept(calculatorVisitor);
        ticket.accept(printerVisitor);

        System.out.println("\nDetalle del ticket:");
        System.out.println(printerVisitor.getOutput());
        System.out.println("Total calculado por Visitor: " +
                          currencyFormatter.format(calculatorVisitor.getTotal()));

        // 6. Demostrar devolución (solo si está permitida)
        if (supermarket.supportsReturns()) {
            System.out.println("\n[INFO] Este país permite devoluciones");
        } else {
            System.out.println("\n[INFO] Este país NO permite devoluciones");
        }
    }
}

