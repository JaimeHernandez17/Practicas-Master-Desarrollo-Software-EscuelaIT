package models;

/**
 * Estados del flujo de la aplicación Mastermind.
 * INITIAL: Estado inicial, listo para comenzar una nueva partida.
 * IN_GAME: Estado durante el juego activo.
 * RESUMING: Estado para preguntar si se desea continuar jugando.
 * EXIT: Estado final, la aplicación terminará.
 */
public enum State {
    INITIAL,
    IN_GAME,
    RESUMING,
    EXIT;

    public boolean isNotExit() {
        return this != EXIT;
    }
}

