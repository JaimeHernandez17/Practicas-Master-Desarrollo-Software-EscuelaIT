package supermercado.patterns.abstractfactory;

import java.util.HashMap;
import java.util.Map;

/**
 * PATRÓN SINGLETON + ABSTRACT FACTORY
 *
 * Proveedor centralizado de fábricas por país.
 * Implementa el patrón Singleton para garantizar una única instancia.
 */
public class CountryFactoryProvider {

    private static CountryFactoryProvider instance;
    private Map<String, CountryFactory> factories;

    private CountryFactoryProvider() {
        factories = new HashMap<>();
        registerDefaultFactories();
    }

    public static synchronized CountryFactoryProvider getInstance() {
        if (instance == null) {
            instance = new CountryFactoryProvider();
        }
        return instance;
    }

    private void registerDefaultFactories() {
        // Registrar fábricas por defecto
        factories.put("ES", new SpainFactory());
        factories.put("FR", new FranceFactory());
        // Se pueden añadir más países aquí...
    }

    /**
     * Obtiene la fábrica para un país específico.
     * @param countryCode Código ISO del país (ES, FR, US, etc.)
     * @return La fábrica del país
     * @throws IllegalArgumentException si el país no está soportado
     */
    public CountryFactory getFactory(String countryCode) {
        CountryFactory factory = factories.get(countryCode.toUpperCase());
        if (factory == null) {
            throw new IllegalArgumentException("País no soportado: " + countryCode);
        }
        return factory;
    }

    /**
     * Registra una nueva fábrica de país.
     * @param countryCode Código ISO del país
     * @param factory La fábrica a registrar
     */
    public void registerFactory(String countryCode, CountryFactory factory) {
        factories.put(countryCode.toUpperCase(), factory);
    }

    /**
     * Verifica si un país está soportado.
     * @param countryCode Código ISO del país
     * @return true si el país está soportado
     */
    public boolean isCountrySupported(String countryCode) {
        return factories.containsKey(countryCode.toUpperCase());
    }
}

