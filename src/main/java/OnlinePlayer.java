import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;


public class OnlinePlayer extends HumanPlayer{
    public URL connectionUrl;
    public String lastGet;

    public OnlinePlayer(String color, String url) throws MalformedURLException {
        super(color);
        this.connectionUrl = new URL(url);
        new URL(url);
    }

    @Override
    public String getInput() {
        String m = OnlineGameState.lastMove;
        while (m.equals(OnlineGameState.lastMove)) {
            try {
                JSONObject response = API.fetchJSON(this.connectionUrl);
                m = response.getString("move");
                TimeUnit.SECONDS.sleep(5);
            } catch (IOException e) {
                System.out.println(e);
            } catch (InterruptedException e) {
                System.out.println("Sleep interruption");
            }
        }

        return m;
    }
}
