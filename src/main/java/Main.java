import java.net.MalformedURLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        playOnlineGame();
    }

    public static void playGame(){
        GameState game = new GameState();

        HumanPlayer player = new HumanPlayer("W");
        game.board.display();
        while (true) {
            game.turn();
        }
    }

    public static void playOnlineGame() {
        OnlineGameState game = new OnlineGameState();

        game.board.display();
        while(true) {
            game.turn();
        }
    }
}