import java.util.Arrays;

public class Board {
    private final Piece[][] board;
    private final String[][] highlight;
    private final String ANSI_DEFAULT = "\u001B[0m";

    public Board() {
                this(new String[][][] {
                        {{"R","W"},{"N","W"},{"B","W"},{"Q","W"},{"K","W"},{"B","W"},{"N","W"},{"R","W"}},
                        {{"P","W"},{"P","W"},{"P","W"},{"P","W"},{"P","W"},{"P","W"},{"P","W"},{"P","W"}},
                        {{" "," "},{" "," "},{" "," "},{" "," "},{" "," "},{" "," "},{" "," "},{" "," "}},
                        {{" "," "},{" "," "},{" "," "},{" "," "},{" "," "},{" "," "},{" "," "},{" "," "}},
                        {{" "," "},{" "," "},{" "," "},{" "," "},{" "," "},{" "," "},{" "," "},{" "," "}},
                        {{" "," "},{" "," "},{" "," "},{" "," "},{" "," "},{" "," "},{" "," "},{" "," "}},
                        {{"P","B"},{"P","B"},{"P","B"},{"P","B"},{"P","B"},{"P","B"},{"P","B"},{"P","B"}},
                        {{"R","B"},{"N","B"},{"B","B"},{"Q","B"},{"K","B"},{"B","B"},{"N","B"},{"R","B"}}
                });
    }
    public Board(boolean fill) {
        this(new String[][][] {
                {{" "," "},{" "," "},{" "," "},{" "," "},{" "," "},{" "," "},{" "," "},{" "," "}},
                {{" "," "},{" "," "},{" "," "},{" "," "},{" "," "},{" "," "},{" "," "},{" "," "}},
                {{" "," "},{" "," "},{" "," "},{" "," "},{" "," "},{" "," "},{" "," "},{" "," "}},
                {{" "," "},{" "," "},{" "," "},{" "," "},{" "," "},{" "," "},{" "," "},{" "," "}},
                {{" "," "},{" "," "},{" "," "},{" "," "},{" "," "},{" "," "},{" "," "},{" "," "}},
                {{" "," "},{" "," "},{" "," "},{" "," "},{" "," "},{" "," "},{" "," "},{" "," "}},
                {{" "," "},{" "," "},{" "," "},{" "," "},{" "," "},{" "," "},{" "," "},{" "," "}},
                {{" "," "},{" "," "},{" "," "},{" "," "},{" "," "},{" "," "},{" "," "},{" "," "}}
        });
    }

    public Board(String[][][] board) {
        this.board = new Piece[8][8];
        this.highlight = new String[8][8];
        for (int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length; j++) {
                String name = board[i][j][0];
                String color = board[i][j][1];
                Piece piece = switch (name) {
                    case "P" -> new Pawn(color);
                    case "N" -> new Knight(color);
                    case "B" -> new Bishop(color);
                    case "R" -> new Rook(color);
                    case "Q" -> new Queen(color);
                    case "K" -> new King(color);
                    default -> new NoPiece();
                };
                this.board[i][j] = piece;
                this.highlight[i][j] = "\u001B[0m";
            }
        }
    }

    public Board(Piece[][] board) {
        this.board = board;
        this.highlight = new String[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                this.highlight[j][i] = "";
            }
        }
    }



    public Piece getPiece(int[] location) {
        return  this.board[location[1]][location[0]];
    }
    public Piece getPiece(Vector2 location) {
        return this.board[location.y][location.x];
    }

    public boolean validSpace(int x, int y) {
        return y < board.length && y >= 0 && x < board[0].length && x >= 0;
    }
    public boolean validSpace(int[] location) {
        return this.validSpace(location[0], location[1]);
    }
    public boolean validSpace(Vector2 location) {
        return this.validSpace(location.x, location.y);
    }

    public void setHighlight(Vector2 location, Colors c) {
        this.highlight[location.y][location.x] = c.get();
    }


    public void resetHighlight() {
        for (int i = 0; i < this.highlight.length; i++) {
            for (int j = 0; j < this.highlight.length; j++) {
                this.highlight[i][j] = this.ANSI_DEFAULT;
            }
        }
    }
    private String rowToString(Piece[] row, String[] highlight, int rowNumber, boolean reverse) {
        String[] str = new String[row.length];
        StringBuilder output = new StringBuilder("  " + rowNumber + " ");
        output.append("║");
        for (int i = 0; i < str.length; i++) {
            String symbol = " ";
            if (row[i] != null) {
                symbol = row[i].toString();
            }
            if (reverse) {
                output.insert(4," ");
                output.insert(4, highlight[i] + symbol + " " + ANSI_DEFAULT);
                output.insert(4, " ");
                output.insert(4, "║");
            } else {
                output.append(" ");
                output.append(highlight[i]).append(symbol).append(" ").append(ANSI_DEFAULT);
                output.append(" ");
                output.append("║");
            }
        }
        return output.toString();
//        return String.format("║ %s ║ %s ║ %s ║ %s ║ %s ║ %s ║ %s ║ %s ║",str[0],str[1],str[2],str[3],str[4],str[5],str[6],str[7]);
    }
    private String rowToString(Piece[] row, String[] highlight, int rowNumber) {
        return rowToString(row, highlight, rowNumber, false);
    }
    private String spacer() {
        String p = "════";
        return String.format("    ╬"+p+"╬"+p+"╬"+p+"╬"+p+"╬"+p+"╬"+p+"╬"+p+"╬"+p+"╣");
    }
    public void display(String color) {
        String top = "════╦════╦════╦════╦════╦════╦════╦════╦════╗";
        String bottom = "    ║ A    B    C    D    E    F    G    H  ║";

        if (color.equals("B")) {
            top = StringUtils.reverse(top);
            bottom = StringUtils.reverse(bottom);
        }

        System.out.println("════╦════╦════╦════╦════╦════╦════╦════╦════╗");
        for (int i = 0; i < 8; i++) {
            int j = i;
            if (color.equals("W")) {
                j = 7 - i;
                System.out.println(rowToString(this.board[j],this.highlight[j],j+1)); // Row 1
                System.out.println(this.spacer());
            } else {
                System.out.println(rowToString(this.board[j],this.highlight[j],j+1, true )); // Row 1
                System.out.println(this.spacer());
            }
        }
        if (color.equals("W")) {
            System.out.println("    ║ A    B    C    D    E    F    G    H  ║");
        } else {
            System.out.println("    ║ H    G    F    E    D    C    B    A  ║");
        }
        this.resetHighlight();
    }

    public void display() {
        this.display("W");
    }

    public void movePiece(Move move) {
        this.board[move.end.y][move.end.x] = this.board[move.start.y][move.start.x];
        this.board[move.start.y][move.start.x] = new NoPiece();
        this.board[move.end.y][move.end.x].moved = true;
    }

    public Board virtualMove(Move move) {
        Piece[][] pieces = new Piece[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece piece = board[j][i];
                pieces[j][i] = piece;
            }
        }
        Board newBoard = new Board(pieces);
        newBoard.movePiece(move);

        return newBoard;
    }

    public boolean hasConflict(String color) {
        Vector2 blackKingLoc = null;
        Vector2 whiteKingLoc = null;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece piece = board[j][i];
                if (piece == null) {
                    continue;
                }
                if (piece.symbol.equals("♚")) {
                    whiteKingLoc = new Vector2(i, j);
                }
                if (piece.symbol.equals("♔")) {
                    blackKingLoc = new Vector2(i, j);
                }
            }
        }
        if (blackKingLoc == null || whiteKingLoc == null) {
            return true;
        }
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece piece = board[j][i];
                if (piece.oppositeColor().equals(color)) {
                    Vector2[] locs = piece.getValidMoves(this, new Vector2(i,j));
                    for (Vector2 loc : locs) {
                        if (color.equals("B") && loc.equals(blackKingLoc)) {
                            return true;
                        }
                        if (color.equals("W") && loc.equals(whiteKingLoc)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean moveCreatesConflict(Move move) {
        Board newBoard = this.virtualMove(move);
        String color = this.getPiece(move.start).color;
        return newBoard.hasConflict(color);
    }

    public boolean moveCreatesConflict(Vector2 start, Vector2 end) {
        Move move = new Move(start, end);
        return this.moveCreatesConflict(move);
    }

    public void addPiece(Piece piece, Vector2 loc) {
        this.board[loc.y][loc.x] = piece;
    }
    public void addPiece(Piece piece, int x, int y) {
        this.board[y][x] = piece;
    }
}
