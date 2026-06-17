package views;

import models.Color;

/**
 * Vista de colores - representa los colores para la interfaz de consola.
 * VISTA: Define cómo se muestran los colores al usuario.
 */
public enum ColorView {
    RED(Color.RED, 'R'),
    BLUE(Color.BLUE, 'B'),
    YELLOW(Color.YELLOW, 'Y'),
    GREEN(Color.GREEN, 'G'),
    ORANGE(Color.ORANGE, 'O'),
    PURPLE(Color.PURPLE, 'P');

    private final Color color;
    private final char representation;

    ColorView(Color color, char representation) {
        this.color = color;
        this.representation = representation;
    }

    public static ColorView of(Color color) {
        for (ColorView colorView : ColorView.values()) {
            if (colorView.color == color) {
                return colorView;
            }
        }
        return null;
    }

    public void write(Console console) {
        console.write(this.representation);
    }

    public static void writeAll(Console console) {
        for (ColorView colorView : ColorView.values()) {
            console.write(colorView.color.getInitial() + "=" + colorView.representation + " ");
        }
        console.writeln();
    }
}

