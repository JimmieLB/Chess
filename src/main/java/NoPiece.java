public class NoPiece extends Piece{

    NoPiece() {
        this.color = " ";
        this.symbol = " ";
    }
    public Vector2[] getValidMoves(Board board, Vector2 location) {
        return new Vector2[0];
    }

    public String toString() {
        return " ";
    }
}
