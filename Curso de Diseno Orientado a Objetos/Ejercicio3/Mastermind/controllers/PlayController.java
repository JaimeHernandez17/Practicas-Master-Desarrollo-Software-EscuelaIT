package controllers;

import models.Color;
import models.Error;
import models.ProposedCombination;
import models.Session;

import java.util.ArrayList;
import java.util.List;

/**
 * Controlador de juego.
 * Gestiona las propuestas de combinación durante el estado IN_GAME.
 */
public class PlayController implements Controller {

    private final Session session;

    PlayController(Session session) {
        this.session = session;
    }

    /**
     * Valida una cadena de combinación propuesta.
     * @param colorString cadena con iniciales de colores
     * @return Error.NULL si es válida, otro Error en caso contrario
     */
    public Error validateProposedCombination(String colorString) {
        return ProposedCombination.validate(colorString);
    }

    /**
     * Añade una combinación propuesta al juego.
     * @param colorString cadena con iniciales de colores
     */
    public void addProposedCombination(String colorString) {
        List<Color> colors = new ArrayList<>();
        for (int i = 0; i < colorString.length(); i++) {
            colors.add(Color.valueOf(colorString.charAt(i)));
        }
        this.session.getGame().addProposedCombination(colors);
        this.session.nextState();
    }

    /**
     * Obtiene el número de intentos realizados.
     */
    public int getAttempts() {
        return this.session.getGame().getAttempts();
    }

    /**
     * Obtiene el número máximo de intentos permitidos.
     */
    public int getMaxAttempts() {
        return this.session.getGame().getMaxAttempts();
    }

    /**
     * Obtiene los colores de una combinación propuesta.
     * @param position índice de la combinación (0-based)
     */
    public List<Color> getColors(int position) {
        return this.session.getGame().getColors(position);
    }

    /**
     * Obtiene el número de negros de un resultado.
     */
    public int getBlacks(int position) {
        return this.session.getGame().getBlacks(position);
    }

    /**
     * Obtiene el número de blancos de un resultado.
     */
    public int getWhites(int position) {
        return this.session.getGame().getWhites(position);
    }

    /**
     * Obtiene el ancho de la combinación.
     */
    public int getWidth() {
        return this.session.getGame().getWidth();
    }

    /**
     * Comprueba si el jugador ha ganado.
     */
    public boolean isWinner() {
        return this.session.getGame().isWinner();
    }

    /**
     * Comprueba si el jugador ha perdido.
     */
    public boolean isLooser() {
        return this.session.getGame().isLooser();
    }

    @Override
    public void accept(ControllersVisitor controllersVisitor) {
        controllersVisitor.visit(this);
    }
}

