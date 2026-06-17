import controllers.Logic;
import views.ConsoleView;
import views.View;

/**
 * Punto de entrada del juego Mastermind.
 *
 * Arquitectura MVC con Double Dispatching:
 * - El modelo (models) contiene la lógica del juego y el estado (Session, Game)
 * - Los controladores (controllers) median entre modelo y vistas
 * - Las vistas (views) implementan ControllersVisitor para procesar cada controlador
 *   sin usar instanceof (double dispatch)
 *
 * Flujo de estados: INITIAL → IN_GAME → RESUMING → EXIT
 *
 * Este diseño permite añadir fácilmente nuevas vistas (ej: GraphicView/Swing)
 * simplemente implementando ControllersVisitor.
 */
public class Mastermind {

    public static void main(String[] args) {
        Logic logic = new Logic();
        View view = new ConsoleView();
        view.interact(logic);
    }
}

