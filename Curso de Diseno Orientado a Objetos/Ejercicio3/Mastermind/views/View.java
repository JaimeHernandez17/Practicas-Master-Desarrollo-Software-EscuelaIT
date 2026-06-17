package views;

import controllers.Logic;

/**
 * Interfaz base para vistas.
 * Las implementaciones (ConsoleView, GraphicView, etc.) interactúan con Logic.
 */
public interface View {

    /**
     * Ejecuta el bucle principal de la vista.
     * @param logic la lógica del juego
     */
    void interact(Logic logic);
}

