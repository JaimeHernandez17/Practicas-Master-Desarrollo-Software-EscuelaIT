package models;

/**
 * Sesión que mantiene el estado del flujo de la aplicación.
 * Gestiona las transiciones entre estados: INITIAL → IN_GAME → RESUMING → EXIT
 */
public class Session {

    private State state;
    private final Game game;

    public Session() {
        this.state = State.INITIAL;
        this.game = new Game();
    }

    public State getState() {
        return this.state;
    }

    public Game getGame() {
        return this.game;
    }

    /**
     * Transición: INITIAL → IN_GAME
     * Inicia una nueva partida.
     */
    public void next() {
        assert this.state == State.INITIAL;
        this.game.reset();
        this.state = State.IN_GAME;
    }

    /**
     * Transición: IN_GAME → IN_GAME (si no ha terminado)
     *             IN_GAME → RESUMING (si ha terminado)
     * Avanza el estado del juego tras una jugada.
     */
    public void nextState() {
        assert this.state == State.IN_GAME;
        if (this.game.isFinished()) {
            this.state = State.RESUMING;
        }
    }

    /**
     * Transición: RESUMING → INITIAL (si quiere continuar)
     *             RESUMING → EXIT (si no quiere continuar)
     * @param resume true si el jugador quiere otra partida
     */
    public void resume(boolean resume) {
        assert this.state == State.RESUMING;
        if (resume) {
            this.state = State.INITIAL;
        } else {
            this.state = State.EXIT;
        }
    }
}

