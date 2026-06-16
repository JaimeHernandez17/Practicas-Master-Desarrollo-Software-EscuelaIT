package supermercado.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa el carrito de compras con los productos seleccionados.
 */
public class Cart {
    private List<CartItem> items;

    public Cart() {
        this.items = new ArrayList<>();
    }

    public void addItem(Product product, int quantity) {
        // Buscar si el producto ya existe en el carrito
        for (CartItem item : items) {
            if (item.getProduct().getCode().equals(product.getCode())) {
                item.setQuantity(item.getQuantity() + quantity);
                return;
            }
        }
        items.add(new CartItem(product, quantity));
    }

    public void removeItem(String productCode) {
        items.removeIf(item -> item.getProduct().getCode().equals(productCode));
    }

    public BigDecimal getSubtotal() {
        return items.stream()
                .map(CartItem::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public List<CartItem> getItems() {
        return new ArrayList<>(items);
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public void clear() {
        items.clear();
    }
}

