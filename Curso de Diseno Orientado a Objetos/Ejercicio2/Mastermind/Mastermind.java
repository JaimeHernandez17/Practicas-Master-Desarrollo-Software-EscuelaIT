import models.Game;
import views.GameView;

/**
 * Punto de entrada del juego Mastermind.
 *
 * Arquitectura Document-View (Console):
 * - El modelo (Document) está en el paquete 'models' y NO conoce la consola ni System.out
 * - Las vistas (View) están en el paquete 'views' y gestionan toda la interacción con el usuario
 *
 * El modelo expone métodos públicos para que las vistas puedan consultar su estado,
 * pero el modelo NO tiene referencias a ninguna clase de vista.
 */
public class Mastermind {

    public static void main(String[] args) {
        Game game = new Game();
        GameView gameView = new GameView(game);
        gameView.play();
    }
}

