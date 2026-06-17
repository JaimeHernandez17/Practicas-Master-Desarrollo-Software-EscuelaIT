package controllers;

/**
 * Interfaz Visitor para el patrón double dispatch.
 * Las vistas implementan esta interfaz para procesar cada tipo de controlador
 * sin necesidad de usar instanceof.
 */
public interface ControllersVisitor {

    /**
     * Visita el controlador de inicio de partida.
     * @param startController el controlador de inicio
     */
    void visit(StartController startController);

    /**
     * Visita el controlador de juego (proponer combinación).
     * @param playController el controlador de juego
     */
    void visit(PlayController playController);

    /**
     * Visita el controlador de reinicio/continuar.
     * @param resumeController el controlador de reinicio
     */
    void visit(ResumeController resumeController);
}

