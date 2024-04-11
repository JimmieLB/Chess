import java.util.ArrayList;

public abstract class IterationPiece extends Piece{
    Vector2[] iterators;

    public IterationPiece(String color, Vector2[] iterators) {
        this.iterators = iterators;
        this.color = color;
    }

    public Vector2[] getValidMoves(Board board, Vector2 location) {
        LocationArray output = new LocationArray();
        for(Vector2 direction : iterators) {
            Vector2 pointer = location.getStep(0,0);
            while(true) {
                pointer.step(direction);
                if (!board.validSpace(pointer)) {
                    break;
                }
                Piece piece = board.getPiece(pointer);
                if (piece.color.equals(this.color)) {
                    break;
                }
                if (piece.color.equals(this.oppositeColor())) {
                    output.add(pointer);
                    break;
                }
                output.add(pointer);
            }
        }

        return output.toArray();
    }
}
