package models;

/**
 * Enumeración de colores disponibles en el juego Mastermind.
 * MODELO: El modelo no conoce cómo se representan en la vista (Console).
 */
public enum Color {
    RED('r'),
    BLUE('b'),
    YELLOW('y'),
    GREEN('g'),
    ORANGE('o'),
    PURPLE('p');

    private final char initial;

    Color(char initial) {
        this.initial = initial;
    }

    public char getInitial() {
        return this.initial;
    }

    public static Color valueOf(char initial) {
        for (Color color : Color.values()) {
            if (color.initial == Character.toLowerCase(initial)) {
                return color;
            }
        }
        return null;
    }

    public static int length() {
        return Color.values().length;
    }

    public static String getAllInitials() {
        StringBuilder initials = new StringBuilder();
        for (Color color : Color.values()) {
            initials.append(color.initial);
        }
        return initials.toString();
    }
}

