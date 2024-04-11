import java.util.Scanner;

public class HumanPlayer extends Player {
    private final Scanner scanner = new Scanner(System.in);

    public HumanPlayer(String color) {
        super(color);
    }

    public Vector2 parseSelection(String input) {
        int x = inputMap.get(input.substring(0, 1).toLowerCase());
        int y = Integer.parseInt(input.substring(1, 2)) - 1;
        return new Vector2(x, y);
    }

    public Vector2 getSelection(String input) {
        if (validInput(input)) {
            return parseSelection(input);
        }
        return null;
    }

    public Move getMove(Board board, String input) {
        String first = input.substring(0, 2);
        String second = input.substring(3, 5);

        if (validInput(first) && validInput(second)) {
            Vector2 start = parseSelection(first);
            Piece piece = board.getPiece(start);
            if (piece.color.equals(this.color)) {
                return new Move(start, parseSelection(second));
            }
            return null;
        }
        return null;
    }

    public String getInput() {
        return scanner.nextLine();
    }
}
