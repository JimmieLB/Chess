import java.util.Map;

public abstract class Player {
    String color;

    public static final Map<String, Integer> inputMap = Map.of("a", 0, "b", 1, "c", 2, "d", 3, "e", 4, "f", 5, "g", 6, "h", 7);
    Player(String color) {
        this.color = color;
    }

    public boolean validInput(String input) {
        if (input.length() != 2) {
            return false;
        }
        String i1 = input.substring(0, 1);
        String i2 = input.substring(1, 2);

        if (!inputMap.containsKey(i1.toLowerCase())) {
            return false;
        }
        int y;
        try {
            y = Integer.parseInt(i2);
        } catch (NumberFormatException e) {
            return false;
        }
        return y >= 1 && y <= 8;
    }
    public abstract Vector2 getSelection(String input);
    public abstract Move getMove(Board board, String input);
    public abstract String getInput();
}
