import org.junit.Test;
import org.junit.Assert;

public class ConflictTest {
    @Test
    public void movesThatCheckShouldFail() {
        Board board = new Board(false);
        board.addPiece(new King("W"),3,3);
        board.addPiece(new King("B"),7,7);
        board.addPiece(new Bishop("W"),4,3);
        board.addPiece(new Rook("B"),5,3);
        board.addPiece(new Rook("W"),6,6);

        Move move1 = new Move(new Vector2(4,3), new Vector2(6,3));
        Move move2 = new Move(new Vector2(6,6), new Vector2(6,3));
        board.display();
        Assert.assertTrue(board.moveCreatesConflict(move1));
        Assert.assertFalse(board.moveCreatesConflict(move2));
    }
}
