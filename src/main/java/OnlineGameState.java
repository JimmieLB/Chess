import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.LinkPermission;
import java.util.ArrayList;

public class OnlineGameState {
    Board board;
    public Player player1;
    public Player player2;
    public Player currentPlayer;
    public static String lastMove = "";
    public static String localColor = "";

    public static final String urlString = "http://localhost:3000/";

    OnlineGameState() {
        this.board = new Board();

        try {
            JSONObject output = API.fetchJSON(new URL(urlString + "players"));
            int count = output.getInt("count");
            if (count == 0) {
                player1 = new HumanPlayer("W");
                player2 = new OnlinePlayer("B", urlString);
                localColor = "W";
            } else if (count == 1){
                player1 = new OnlinePlayer("W", urlString);
                player2 = new HumanPlayer("B");
                localColor = "B";
            } else {
                System.out.println("Game is Full");
                System.exit(0);
            }
        } catch (MalformedURLException e) {
            System.out.println("Malformed URL");
        } catch (IOException e) {
            System.out.println("IO error");
        }
        currentPlayer = player1;
    }

    public void turn() {
        while (true) {
            String input = currentPlayer.getInput();
            lastMove = input;
            Piece piece;
            if (input.length() == 2 && currentPlayer.getSelection(input) != null) {
                Vector2 start = currentPlayer.getSelection(input);
                piece = board.getPiece(start);
                Vector2[] valids = piece.getValidMoves(board, start);
                ArrayList<Vector2> unValidsList = new ArrayList<Vector2>();
                for (Vector2 valid : valids) {
                    if (board.moveCreatesConflict(start, valid)) {
                        unValidsList.add(valid);
                    }
                }
                for (Vector2 valid : valids) {
                    board.setHighlight(valid, Colors.GREEN_UNDERLINED);
                }
                for (Vector2 unValid : unValidsList) {
                    board.setHighlight(unValid, Colors.RED_UNDERLINED);
                }
                board.setHighlight(start, Colors.BLUE_UNDERLINED);
            } else if (input.length() == 5 && currentPlayer.getMove(board, input) != null) {
                Move move = currentPlayer.getMove(board, input);
                piece = board.getPiece(move.start);
                Vector2[] valids = piece.getValidMoves(board, move.start);
                ArrayList<Vector2> unValids = new ArrayList<Vector2>();
                for (Vector2 valid : valids) {
                    if (board.moveCreatesConflict(move.start, valid)) {
                        unValids.add(valid);
                    }
                }
                Vector2[] unValidList = ArrayUtils.VectorListToArray(unValids);
                if (ArrayUtils.includes(valids, move.end) && !ArrayUtils.includes(unValidList, move.end)) {
                    try {
                        API.postJSON(new URL(urlString + "move"), input);
                    }catch (MalformedURLException e) {
                        System.out.println("Malformed URL");
                    }catch (IOException e) {
                        System.out.println("IO Error");
                    }
                    board.movePiece(move);
                    board.setHighlight(move.start, Colors.BLUE_UNDERLINED);
                    board.setHighlight(move.end, Colors.BLUE_UNDERLINED);
                    break;
                }
                System.out.println("Invalid Move");
            }
            board.display(localColor);
        }
        board.display(localColor);
    }

    public void switchPlayer() {
        currentPlayer = currentPlayer == player1 ? player2 : player1;
    }
}
