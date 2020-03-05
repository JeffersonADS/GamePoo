import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerGame extends Game {

    private ServerSocket serverSocket;
    private Socket socket;

    private Connection connection;

    public ServerGame() {
        super(Game.PLAYER_ONE);
        try {
            serverSocket = new ServerSocket(Game.PORT);
            socket = serverSocket.accept();
            connection = new Connection(this, socket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void inputReceived(int x, int y) {
        if (isMyTurn()) {
            updateField(x, y);
        }
    }

    @Override
    public void packageReceived(Object object) {
        if (object instanceof ClientPlayPackage) {
            ClientPlayPackage pack = (ClientPlayPackage) object;

            updateField(pack.getX(), pack.getY());
        }

        gameWindow.repaint();
    }
    
    @Override
    public void close() {
        try {
            connection.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateField(int x, int y) {
        if (fields[x][y] == Game.FREE) {
            fields[x][y] = currentPlayer;
            if (currentPlayer == Game.PLAYER_ONE) {
                currentPlayer = Game.PLAYER_TWO;
            } else if (currentPlayer == Game.PLAYER_TWO) {
                currentPlayer = Game.PLAYER_ONE;
            }

            gameWindow.repaint();

            connection.sendPackage(new UpdatePackage(fields, currentPlayer));

            int winner = checkWin();

            if (winner != Game.FREE) {
                endGame(winner);
            } else {
                int counter = 0;
                for (int i = 0; i < 3; i ++){
                    for (int j = 0; j < 3; j ++){
                        if (fields[i][j] != Game.FREE) {
                            counter ++;
                        }
                    }
                }
                if (counter == 9) {
                    endGame(winner);
                }
            }
        }
    }

    private void endGame(int winner) {
        showWinner(winner);
        connection.sendPackage(new GameEndPackage(winner));
    }

    private int checkWin() {
        int counter;
        for (int player = Game.PLAYER_ONE; player <= Game.PLAYER_TWO; player ++) {

            for (int y = 0; y < 3; y ++) {
                counter = 0;

                for (int x = 0; x < 3; x ++) {
                    if (fields[x][y] == player) {
                        counter ++;
                    }
                }

                if (counter == 3) {
                    return player;
                }
            }

            for (int x = 0; x < 3; x ++) {
                counter = 0;

                for (int y = 0; y < 3; y ++) {
                    if (fields[x][y] == player) {
                        counter ++;
                    }
                }

                if (counter == 3) {
                    return player;
                }
            }

            counter = 0;
            for (int coordinate = 0; coordinate < 3; coordinate ++) {
                if (fields[coordinate][coordinate] == player) {
                    counter ++;
                }
            }

            if (counter == 3) {
                return player;
            }

            counter = 0;

            for (int coordinate = 0; coordinate < 3; coordinate ++) {
                if (fields[2 - coordinate][coordinate] == player){
                    counter ++;
                }
            }

            if (counter == 3) {
                return player;
            }
        }

        return Game.FREE;
 
    }
}