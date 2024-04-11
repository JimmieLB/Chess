public class Bishop extends IterationPiece{
    public final String WHITESYMBOL = "♝";
    public final String BLACKSYMBOL = "♗";
    public static Vector2[] iterators = new Vector2[] {new Vector2(1,1), new Vector2(-1,-1), new Vector2(-1,1), new Vector2(1,-1)};
    public Bishop(String color) {
        super(color, Bishop.iterators);
        this.symbol = this.color.equals("W") ? WHITESYMBOL : BLACKSYMBOL;
    }
}
