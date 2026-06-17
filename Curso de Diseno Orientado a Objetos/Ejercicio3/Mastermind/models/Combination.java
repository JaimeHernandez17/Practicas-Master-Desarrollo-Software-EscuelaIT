package models;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase base abstracta para combinaciones de colores.
 * MODELO: No contiene referencias a Console ni System.out
 */
public abstract class Combination {

    public static final int WIDTH = 4;

    protected List<Color> colors;

    protected Combination() {
        this.colors = new ArrayList<>();
    }

    public static int getWidth() {
        return WIDTH;
    }

    public List<Color> getColors() {
        return new ArrayList<>(this.colors);
    }

    public Color getColor(int position) {
        assert position >= 0 && position < WIDTH;
        return this.colors.get(position);
    }
}

