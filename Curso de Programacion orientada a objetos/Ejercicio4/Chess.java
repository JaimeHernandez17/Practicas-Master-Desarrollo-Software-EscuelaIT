/**
 * JUEGO DE AJEDREZ - Implementación con Polimorfismo
 *
 * Polimorfismo: cada pieza sobreescribe isValidMove() con sus propias reglas.
 *
 * Piezas implementadas:
 * - Peon (Pawn)
 * - Rey (King)
 * - Caballo (Knight)
 * - Torre (Rook)
 * - Alfil (Bishop)
 * - Reina (Queen)
 *
 * @author Master Desarrollo de Software
 * @date Junio 2026
 */

import java.util.Scanner;

// ==================== ENUMERACIÓN COLOR ====================

enum Color {
    WHITE, BLACK;

    public Color opposite() {
        return this == WHITE ? BLACK : WHITE;
    }

    public String toString() {
        return this == WHITE ? "White" : "Black";
    }
}

// ==================== CLASE POSICION ====================

class Position {
    public final int row;
    public final int col;

    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public boolean isInsideBoard() {
        return row >= 0 && row < 8 && col >= 0 && col < 8;
    }

    public boolean equals(Position other) {
        return other != null && this.row == other.row && this.col == other.col;
    }

    /** Convierte notación "e2" a Position */
    public static Position fromString(String s) {
        if (s == null || s.length() != 2) return null;
        char colChar = Character.toLowerCase(s.charAt(0));
        char rowChar = s.charAt(1);
        int col = colChar - 'a';
        int row = rowChar - '1';
        if (col < 0 || col > 7 || row < 0 || row > 7) return null;
        return new Position(row, col);
    }

    public String toString() {
        return "" + (char)('a' + col) + (row + 1);
    }
}

// ==================== CLASE BASE ABSTRACTA: PIECE ====================

/**
 * Clase abstracta base para todas las piezas del ajedrez.
 * POLIMORFISMO: cada subclase implementa su propia versión de isValidMove()
 */
abstract class Piece {
    protected Color color;
    protected Position position;

    public Piece(Color color, Position position) {
        this.color = color;
        this.position = position;
    }

    public Color getColor() { return color; }
    public Position getPosition() { return position; }
    public void setPosition(Position p) { this.position = p; }

    /**
     * MÉTODO POLIMÓRFICO: cada pieza implementa sus propias reglas de movimiento.
     * @param to   posición destino
     * @param board tablero actual
     * @return true si el movimiento es válido
     */
    public abstract boolean isValidMove(Position to, Board board);

    /** Símbolo de la pieza para mostrar en el tablero */
    public abstract String symbol();

    /** Nombre de la pieza */
    public abstract String name();

    /** Comprueba si hay piezas bloqueando el camino en línea recta o diagonal */
    protected boolean isPathClear(Position to, Board board) {
        int rowDir = Integer.signum(to.row - position.row);
        int colDir = Integer.signum(to.col - position.col);
        int r = position.row + rowDir;
        int c = position.col + colDir;
        while (r != to.row || c != to.col) {
            if (board.getPiece(r, c) != null) return false;
            r += rowDir;
            c += colDir;
        }
        return true;
    }

    /** Comprueba si la casilla destino está libre o tiene pieza enemiga */
    protected boolean canLandOn(Position to, Board board) {
        Piece target = board.getPiece(to.row, to.col);
        return target == null || target.getColor() != this.color;
    }

    public String toString() {
        return color + " " + name() + " at " + position;
    }
}

// ==================== PEON (Pawn) ====================

/**
 * Peón: avanza 1 casilla hacia adelante (o 2 desde posición inicial),
 * captura en diagonal.
 */
class Pawn extends Piece {

    public Pawn(Color color, Position position) {
        super(color, position);
    }

    @Override
    public boolean isValidMove(Position to, Board board) {
        if (!to.isInsideBoard()) return false;

        int direction = (color == Color.WHITE) ? 1 : -1;
        int startRow  = (color == Color.WHITE) ? 1 : 6;

        int rowDiff = to.row - position.row;
        int colDiff = Math.abs(to.col - position.col);
        Piece target = board.getPiece(to.row, to.col);

        // Avance de 1 casilla recto (sin captura)
        if (colDiff == 0 && rowDiff == direction && target == null) {
            return true;
        }

        // Avance inicial de 2 casillas
        if (colDiff == 0 && rowDiff == 2 * direction
                && position.row == startRow && target == null
                && board.getPiece(position.row + direction, position.col) == null) {
            return true;
        }

        // Captura en diagonal
        if (colDiff == 1 && rowDiff == direction
                && target != null && target.getColor() != this.color) {
            return true;
        }

        return false;
    }

    @Override public String symbol() { return color == Color.WHITE ? "P" : "p"; }
    @Override public String name()   { return "Pawn"; }
}

// ==================== REY (King) ====================

/**
 * Rey: se mueve exactamente 1 casilla en cualquier dirección.
 */
class King extends Piece {

    public King(Color color, Position position) {
        super(color, position);
    }

    @Override
    public boolean isValidMove(Position to, Board board) {
        if (!to.isInsideBoard()) return false;
        int rowDiff = Math.abs(to.row - position.row);
        int colDiff = Math.abs(to.col - position.col);
        // Solo 1 paso en cualquier dirección
        return rowDiff <= 1 && colDiff <= 1
                && (rowDiff + colDiff > 0)
                && canLandOn(to, board);
    }

    @Override public String symbol() { return color == Color.WHITE ? "K" : "k"; }
    @Override public String name()   { return "King"; }
}

// ==================== CABALLO (Knight) ====================

/**
 * Caballo: se mueve en L (2+1 o 1+2), puede saltar otras piezas.
 */
class Knight extends Piece {

    public Knight(Color color, Position position) {
        super(color, position);
    }

    @Override
    public boolean isValidMove(Position to, Board board) {
        if (!to.isInsideBoard()) return false;
        int rowDiff = Math.abs(to.row - position.row);
        int colDiff = Math.abs(to.col - position.col);
        // Forma de L: (2,1) o (1,2)
        return ((rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2))
                && canLandOn(to, board);
    }

    @Override public String symbol() { return color == Color.WHITE ? "N" : "n"; }
    @Override public String name()   { return "Knight"; }
}

// ==================== TORRE (Rook) ====================

/**
 * Torre: se mueve en línea recta horizontal o vertical.
 */
class Rook extends Piece {

    public Rook(Color color, Position position) {
        super(color, position);
    }

    @Override
    public boolean isValidMove(Position to, Board board) {
        if (!to.isInsideBoard()) return false;
        int rowDiff = Math.abs(to.row - position.row);
        int colDiff = Math.abs(to.col - position.col);
        // Solo horizontal o vertical
        if (rowDiff != 0 && colDiff != 0) return false;
        return isPathClear(to, board) && canLandOn(to, board);
    }

    @Override public String symbol() { return color == Color.WHITE ? "R" : "r"; }
    @Override public String name()   { return "Rook"; }
}

// ==================== ALFIL (Bishop) ====================

/**
 * Alfil: se mueve en diagonal.
 */
class Bishop extends Piece {

    public Bishop(Color color, Position position) {
        super(color, position);
    }

    @Override
    public boolean isValidMove(Position to, Board board) {
        if (!to.isInsideBoard()) return false;
        int rowDiff = Math.abs(to.row - position.row);
        int colDiff = Math.abs(to.col - position.col);
        // Solo diagonal (misma distancia fila y columna)
        if (rowDiff != colDiff || rowDiff == 0) return false;
        return isPathClear(to, board) && canLandOn(to, board);
    }

    @Override public String symbol() { return color == Color.WHITE ? "B" : "b"; }
    @Override public String name()   { return "Bishop"; }
}

// ==================== REINA (Queen) ====================

/**
 * Reina: combina movimientos de Torre y Alfil.
 */
class Queen extends Piece {

    public Queen(Color color, Position position) {
        super(color, position);
    }

    @Override
    public boolean isValidMove(Position to, Board board) {
        if (!to.isInsideBoard()) return false;
        int rowDiff = Math.abs(to.row - position.row);
        int colDiff = Math.abs(to.col - position.col);
        // Línea recta o diagonal
        boolean straight  = (rowDiff == 0 || colDiff == 0) && (rowDiff + colDiff > 0);
        boolean diagonal  = rowDiff == colDiff && rowDiff > 0;
        if (!straight && !diagonal) return false;
        return isPathClear(to, board) && canLandOn(to, board);
    }

    @Override public String symbol() { return color == Color.WHITE ? "Q" : "q"; }
    @Override public String name()   { return "Queen"; }
}

// ==================== TABLERO (Board) ====================

/**
 * Clase que gestiona el tablero 8x8 y las piezas.
 */
class Board {
    private Piece[][] grid;

    public Board() {
        grid = new Piece[8][8];
        setupPieces();
    }

    private void setupPieces() {
        // Peones
        for (int col = 0; col < 8; col++) {
            grid[1][col] = new Pawn(Color.WHITE, new Position(1, col));
            grid[6][col] = new Pawn(Color.BLACK, new Position(6, col));
        }

        // Torres
        grid[0][0] = new Rook(Color.WHITE, new Position(0, 0));
        grid[0][7] = new Rook(Color.WHITE, new Position(0, 7));
        grid[7][0] = new Rook(Color.BLACK, new Position(7, 0));
        grid[7][7] = new Rook(Color.BLACK, new Position(7, 7));

        // Caballos
        grid[0][1] = new Knight(Color.WHITE, new Position(0, 1));
        grid[0][6] = new Knight(Color.WHITE, new Position(0, 6));
        grid[7][1] = new Knight(Color.BLACK, new Position(7, 1));
        grid[7][6] = new Knight(Color.BLACK, new Position(7, 6));

        // Alfiles
        grid[0][2] = new Bishop(Color.WHITE, new Position(0, 2));
        grid[0][5] = new Bishop(Color.WHITE, new Position(0, 5));
        grid[7][2] = new Bishop(Color.BLACK, new Position(7, 2));
        grid[7][5] = new Bishop(Color.BLACK, new Position(7, 5));

        // Reinas
        grid[0][3] = new Queen(Color.WHITE, new Position(0, 3));
        grid[7][3] = new Queen(Color.BLACK, new Position(7, 3));

        // Reyes
        grid[0][4] = new King(Color.WHITE, new Position(0, 4));
        grid[7][4] = new King(Color.BLACK, new Position(7, 4));
    }

    public Piece getPiece(int row, int col) {
        if (row < 0 || row > 7 || col < 0 || col > 7) return null;
        return grid[row][col];
    }

    public Piece getPiece(Position p) {
        return getPiece(p.row, p.col);
    }

    /**
     * Mueve una pieza de 'from' a 'to'.
     * @return la pieza capturada (null si no hay captura)
     */
    public Piece move(Position from, Position to) {
        Piece piece   = grid[from.row][from.col];
        Piece captured = grid[to.row][to.col];

        grid[to.row][to.col]     = piece;
        grid[from.row][from.col] = null;
        piece.setPosition(to);

        return captured;
    }

    public void print(Color perspective) {
        System.out.println();
        System.out.println("    a  b  c  d  e  f  g  h");
        System.out.println("   ─────────────────────────");

        for (int r = 7; r >= 0; r--) {
            System.out.print((r + 1) + " │ ");
            for (int c = 0; c < 8; c++) {
                Piece piece = grid[r][c];
                // Fondo alternado con espaciado
                String cell = (piece == null) ? "." : piece.symbol();
                System.out.print(cell + "  ");
            }
            System.out.println("│ " + (r + 1));
        }

        System.out.println("   ─────────────────────────");
        System.out.println("    a  b  c  d  e  f  g  h");
        System.out.println();
    }
}

// ==================== CLASE PRINCIPAL: CHESS ====================

/**
 * Clase principal que gestiona el juego de ajedrez.
 *
 * POLIMORFISMO en acción: cuando se llama a piece.isValidMove(),
 * Java ejecuta la versión correspondiente a cada tipo de pieza
 * (Pawn, King, Knight, Rook, Bishop, Queen) sin necesidad de
 * comprobaciones instanceof.
 */
public class Chess {
    private Board board;
    private Color currentTurn;
    private Scanner scanner;
    private boolean gameOver;

    public Chess() {
        board = new Board();
        currentTurn = Color.WHITE;
        scanner = new Scanner(System.in);
        gameOver = false;
    }

    public void play() {
        printWelcome();

        while (!gameOver) {
            board.print(currentTurn);
            System.out.println("Turn: " + currentTurn);
            System.out.println("Enter move (e.g. e2 e4) or 'quit': ");
            System.out.print("> ");

            String input = scanner.nextLine().trim().toLowerCase();

            if (input.equals("quit") || input.equals("exit")) {
                System.out.println("Game ended by player.");
                break;
            }

            processInput(input);
        }
    }

    private void processInput(String input) {
        String[] parts = input.split("\\s+");
        if (parts.length != 2) {
            System.out.println("Invalid input. Use format: e2 e4\n");
            return;
        }

        Position from = Position.fromString(parts[0]);
        Position to   = Position.fromString(parts[1]);

        if (from == null || to == null) {
            System.out.println("Invalid positions. Columns a-h, rows 1-8.\n");
            return;
        }

        Piece piece = board.getPiece(from);

        if (piece == null) {
            System.out.println("No piece at " + parts[0] + "\n");
            return;
        }

        if (piece.getColor() != currentTurn) {
            System.out.println("That is not your piece. It's " + currentTurn + "'s turn.\n");
            return;
        }

        // POLIMORFISMO: se llama al isValidMove() específico de cada pieza
        if (!piece.isValidMove(to, board)) {
            System.out.println("Invalid move for " + piece.name() + ".\n");
            return;
        }

        // Ejecutar movimiento
        Piece captured = board.move(from, to);

        System.out.println(currentTurn + " moves " + piece.name()
                + " from " + from + " to " + to);

        if (captured != null) {
            System.out.println("  >> Captured: " + captured.name() + " (" + captured.getColor() + ")");
            // Verificar si se capturó al Rey → victoria
            if (captured instanceof King) {
                board.print(currentTurn);
                System.out.println("╔════════════════════════════════════╗");
                System.out.println("║  " + currentTurn + " wins by capturing the King!  ║");
                System.out.println("╚════════════════════════════════════╝");
                gameOver = true;
                return;
            }
        }

        System.out.println();
        currentTurn = currentTurn.opposite();
    }

    private void printWelcome() {
        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║        CHESS - OOP Polymorphism          ║");
        System.out.println("╚══════════════════════════════════════════╝");
        System.out.println();
        System.out.println("PIECES (White uppercase / Black lowercase):");
        System.out.println("  K/k = King   Q/q = Queen  R/r = Rook");
        System.out.println("  B/b = Bishop N/n = Knight P/p = Pawn");
        System.out.println("  .   = empty square");
        System.out.println();
        System.out.println("MOVEMENTS:");
        System.out.println("  Pawn   : forward 1 (or 2 from start), captures diagonally");
        System.out.println("  King   : 1 square any direction");
        System.out.println("  Knight : L-shape (2+1), jumps over pieces");
        System.out.println("  Rook   : straight lines (horizontal/vertical)");
        System.out.println("  Bishop : diagonal lines");
        System.out.println("  Queen  : straight + diagonal lines");
        System.out.println();
        System.out.println("WIN: capture the opponent's King");
        System.out.println("─────────────────────────────────────────────");
    }

    // ==================== MÉTODO MAIN ====================

    public static void main(String[] args) {
        Chess chess = new Chess();
        chess.play();
    }
}

