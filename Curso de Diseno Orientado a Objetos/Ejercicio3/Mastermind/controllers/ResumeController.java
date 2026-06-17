package controllers;

import models.Session;

/**
 * Controlador de reinicio/continuar partida.
 * Gestiona la transición del estado RESUMING a INITIAL o EXIT.
 */
public class ResumeController implements Controller {

    private final Session session;

    ResumeController(Session session) {
        this.session = session;
    }

    /**
     * Procesa la respuesta del usuario sobre si quiere continuar.
     * @param resume true si el jugador quiere otra partida
     */
    public void resume(boolean resume) {
        this.session.resume(resume);
    }

    /**
     * Comprueba si el jugador ganó la partida anterior.
     */
    public boolean isWinner() {
        return this.session.getGame().isWinner();
    }

    /**
     * Comprueba si el jugador perdió la partida anterior.
     */
    public boolean isLooser() {
        return this.session.getGame().isLooser();
    }

    @Override
    public void accept(ControllersVisitor controllersVisitor) {
        controllersVisitor.visit(this);
    }
}

