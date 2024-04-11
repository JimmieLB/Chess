import java.util.Map;
import java.util.Scanner;

public abstract class Piece {

    public String symbol;
    public String color;
    public boolean moved = false;

    public static Map<String, String> blackSymbolMap = Map.of(
            " ", " ",
            "K", "♔",
            "Q", "♕",
            "R", "♖",
            "B", "♗",
            "N", "♘",
            "P", "♙");
    public static Map<String, String> whiteSymbolMap = Map.of(
            " ", " ",
            "K", "♚",
            "Q", "♛",
            "R", "♜",
            "B", "♝",
            "N", "♞",
            "P", "♟");


    public String toString() {
        return this.symbol;
    }

    public String oppositeColor() {
        return this.color.equals("W") ? "B" : this.color.equals("B") ? "W" : " ";
    }

    public boolean isEnemy(Piece piece) {
        return piece.color.equals(this.oppositeColor());
    }

    public abstract Vector2[] getValidMoves(Board board, Vector2 location);

    public int[] Move() {
        int x = 0;
        int y = 0;
        String raw = "";
        do {
            Scanner scanner = new Scanner(System.in);
            raw = scanner.next();
        } while (raw.isEmpty());
        return new int[] {x, y};
    }
}
