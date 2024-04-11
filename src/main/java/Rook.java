public class Rook extends IterationPiece{
    public final String WHITESYMBOL = "♜";
    public final String BLACKSYMBOL = "♖";
    public static Vector2[] iterators = new Vector2[] {new Vector2(1,0), new Vector2(-1,0), new Vector2(0,1), new Vector2(0,-1)};
    public Rook(String color) {
        super(color, Rook.iterators);
        this.symbol = this.color.equals("W") ? WHITESYMBOL : BLACKSYMBOL;
    }
}

