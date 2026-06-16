package supermercado.patterns.command;

/**
 * PATRÓN COMMAND - Command
 *
 * Define la interfaz para todas las operaciones del sistema.
 * Permite encapsular peticiones como objetos, parametrizar clientes
 * y soportar operaciones de deshacer.
 */
public interface Command {

    /**
     * Ejecuta el comando.
     */
    void execute();

    /**
     * Deshace la ejecución del comando.
     */
    void undo();

    /**
     * Obtiene una descripción del comando.
     * @return Descripción legible del comando
     */
    String getDescription();
}

