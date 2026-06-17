package views;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Clase utilitaria para entrada/salida por consola.
 * VISTA: Gestiona toda la interacción con System.in y System.out.
 */
public class Console {

    private static Console instance;
    private final BufferedReader bufferedReader;

    private Console() {
        this.bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    }

    public static Console getInstance() {
        if (instance == null) {
            instance = new Console();
        }
        return instance;
    }

    public String readString(String title) {
        String input = null;
        boolean ok = false;
        do {
            this.write(title);
            try {
                input = this.bufferedReader.readLine();
                ok = true;
            } catch (IOException ex) {
                this.writeError("Error reading input");
            }
        } while (!ok);
        return input;
    }

    public char readChar(String title) {
        char charValue = ' ';
        boolean ok = false;
        do {
            String input = this.readString(title);
            if (input.length() != 1) {
                this.writeError("Enter a single character");
            } else {
                charValue = input.charAt(0);
                ok = true;
            }
        } while (!ok);
        return charValue;
    }

    public void writeln() {
        System.out.println();
    }

    public void write(String string) {
        System.out.print(string);
    }

    public void writeln(String string) {
        System.out.println(string);
    }

    public void write(char character) {
        System.out.print(character);
    }

    public void writeln(int integer) {
        System.out.println(integer);
    }

    private void writeError(String message) {
        System.out.println("ERROR: " + message);
    }
}

