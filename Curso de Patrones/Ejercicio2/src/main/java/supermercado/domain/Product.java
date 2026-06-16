package supermercado.domain;

import java.math.BigDecimal;

/**
 * Representa un producto del supermercado.
 */
public class Product {
    private String code;
    private String description;
    private BigDecimal price;
    private TaxCategory taxCategory;

    public Product(String code, String description, BigDecimal price, TaxCategory taxCategory) {
        this.code = code;
        this.description = description;
        this.price = price;
        this.taxCategory = taxCategory;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public TaxCategory getTaxCategory() {
        return taxCategory;
    }
}

