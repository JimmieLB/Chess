import java.util.ArrayList;

public class Pawn extends Piece{
    public final String WHITESYMBOL = "♟";
    public final String BLACKSYMBOL = "♙";
    public Pawn(String color) {

        this.color = color;
        this.symbol = this.color.equals("W") ? WHITESYMBOL : BLACKSYMBOL;
    }


    public Vector2[] getValidMoves(Board board, Vector2 pos) {
        LocationArray output = new LocationArray();
        Piece piece = board.getPiece(pos);
        String color = piece.color;
        String oppositeColor = piece.oppositeColor();

        int yStep = -1;
        int startPos = 6;
        if (this.color.equals("W")) {
            yStep = 1;
            startPos = 1;
        }


        if(board.validSpace(pos.x,pos.y+yStep) && board.getPiece(pos.getStep(0,yStep)).color.equals(" ")) {
            output.add(pos.getStep(0,yStep));
        }
        if(board.validSpace(pos.x,pos.y+(yStep*2)) && board.getPiece(pos.getStep(0,2*yStep)).color.equals(" ") && pos.y == startPos) {
            output.add(pos.getStep(0,(yStep*2)));
        } else {
            System.out.println(startPos);
        }
        if(board.validSpace(pos.getStep(-1,yStep))) {
            String leftColor = board.getPiece(pos.getStep(-1,yStep)).color;
            if(leftColor.equals(oppositeColor)) {
                output.add(pos.getStep(-1,yStep));
            }
        }

        if(board.validSpace(pos.getStep(1,yStep))) {
            String rightColor = board.getPiece(pos.getStep(1,yStep)).color;
            if(rightColor.equals(oppositeColor)) {
                output.add(pos.getStep(1,yStep));
            }
        }

        return output.toArray();
    }
}
