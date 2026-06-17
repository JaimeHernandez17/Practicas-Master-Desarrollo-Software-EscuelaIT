package controllers;

import models.Session;
import models.State;

/**
 * Factoría de controladores.
 * Construye el controlador apropiado según el estado actual de la sesión.
 */
public class Logic {

    private final Session session;
    private final StartController startController;
    private final PlayController playController;
    private final ResumeController resumeController;

    public Logic() {
        this.session = new Session();
        this.startController = new StartController(this.session);
        this.playController = new PlayController(this.session);
        this.resumeController = new ResumeController(this.session);
    }

    /**
     * Devuelve el controlador correspondiente al estado actual de la sesión.
     * @return el controlador apropiado para el estado actual
     */
    public Controller getController() {
        State state = this.session.getState();
        switch (state) {
            case INITIAL:
                return this.startController;
            case IN_GAME:
                return this.playController;
            case RESUMING:
                return this.resumeController;
            default:
                return null;
        }
    }

    /**
     * Comprueba si la sesión no ha terminado.
     * @return true si el estado no es EXIT
     */
    public boolean isNotExit() {
        return this.session.getState().isNotExit();
    }
}

