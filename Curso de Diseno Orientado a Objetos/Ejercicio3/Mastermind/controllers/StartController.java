package controllers;

import models.Session;

/**
 * Controlador de inicio de partida.
 * Gestiona la transición del estado INITIAL a IN_GAME.
 */
public class StartController implements Controller {

    private final Session session;

    StartController(Session session) {
        this.session = session;
    }

    /**
     * Inicia una nueva partida.
     */
    public void start() {
        this.session.next();
    }

    @Override
    public void accept(ControllersVisitor controllersVisitor) {
        controllersVisitor.visit(this);
    }
}

