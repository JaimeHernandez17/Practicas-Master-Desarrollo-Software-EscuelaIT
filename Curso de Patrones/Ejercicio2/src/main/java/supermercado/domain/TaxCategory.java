package supermercado.domain;

/**
 * Categorías de impuestos aplicables a productos.
 */
public enum TaxCategory {
    GENERAL,      // IVA/VAT general (ej: 21% en España)
    REDUCED,      // IVA reducido (ej: 10% en España)
    SUPER_REDUCED,// IVA superreducido (ej: 4% en España)
    EXEMPT        // Exento de impuestos
}

