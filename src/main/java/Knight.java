import java.util.ArrayList;

public class Knight extends Piece{
    public final String WHITESYMBOL = "♞";
    public final String BLACKSYMBOL = "♘";
    public Knight(String color) {

        this.color = color;
        this.symbol = this.color.equals("W") ? WHITESYMBOL : BLACKSYMBOL;
    }


    public Vector2[] getValidMoves(Board board, Vector2 pos) {
        LocationArray output = new LocationArray();
        Piece piece = board.getPiece(pos);
        String color = piece.color;
        String oppositeColor = piece.oppositeColor();


        Vector2[] possibilities = new Vector2[] {
                pos.getStep(2,1),
                pos.getStep(1,2),
                pos.getStep(2,-1),
                pos.getStep(-1,2),
                pos.getStep(-1,-2),
                pos.getStep(-2,-1),
                pos.getStep(-2,1),
                pos.getStep(1,-2)
        };

        for(Vector2 possibility : possibilities) {
            if(board.validSpace(possibility) && !board.getPiece(possibility).color.equals(piece.color)) {
                output.add(possibility);
            }
        }

        return output.toArray();
    }
}
