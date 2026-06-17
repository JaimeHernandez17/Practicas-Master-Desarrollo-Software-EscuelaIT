package controllers;

/**
 * Interfaz base para todos los controladores.
 * Implementa el patrón Visitor (double dispatch) para que las vistas
 * puedan procesar cada controlador sin usar instanceof.
 */
public interface Controller {

    /**
     * Método accept del patrón Visitor.
     * Cada controlador concreto llamará al método visit correspondiente del visitor.
     * @param controllersVisitor el visitor que procesará este controlador
     */
    void accept(ControllersVisitor controllersVisitor);
}

