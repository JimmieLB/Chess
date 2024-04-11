import java.util.Arrays;

public class Queen extends IterationPiece{
    public final String WHITESYMBOL = "♛";
    public final String BLACKSYMBOL = "♕";
    public static Vector2[] iterators = ArrayUtils.add(Rook.iterators, Bishop.iterators);
    public Queen(String color) {
        super(color, Queen.iterators);
        this.symbol = this.color.equals("W") ? WHITESYMBOL : BLACKSYMBOL;
    }
}

