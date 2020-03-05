import java.io.IOException;
import java.net.Socket;

public class ClientGame extends Game {

    private Socket socket;

    private Connection connection;

    public ClientGame() {
        super(Game.PLAYER_TWO);
        try {
            socket = new Socket(Game.HOST, Game.PORT);
            connection = new Connection(this, socket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void inputReceived(int x, int y) {
        if (isMyTurn()) {
            connection.sendPackage(new ClientPlayPackage(x, y));
        }

    }


    @Override
    public void packageReceived(Object object) {
        if (object instanceof UpdatePackage) {
            UpdatePackage pack = (UpdatePackage) object;
            fields = pack.getFields();
            currentPlayer = pack.getCurrentPlayer();
        } else if (object instanceof GameEndPackage) {
            GameEndPackage pack = (GameEndPackage) object;
            showWinner(pack.getWinner());
        }

        gameWindow.repaint();
    }


    @Override
    public void close() {
        try {
            connection.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}