import javax.swing.JOptionPane;

public abstract class Game {
    private Window window;
    protected GameWindow gameWindow;

    public static final int  PORT = 5007;
    public static final String HOST = "localhost";
    public static final int WIDTH = 600, HEIGHT = 600;
    public static final int FIELD_WIDTH = WIDTH / 3, FIELD_HEIGHT = HEIGHT / 3;

    public static final int FREE = 0, PLAYER_ONE = 1, PLAYER_TWO = 2;
    public static final String[] SIMBOLS = {"X", "O"};
    protected int[][] fields; 

    protected int currentPlayer;

    protected int thisPlayer;

    public Game(int thisPlayer) {
        this.thisPlayer = thisPlayer;
        window = new Window(this, "TicTacToe", WIDTH, HEIGHT);
        gameWindow = new GameWindow(this);
        fields = new int[3][3];
        window.add(gameWindow);
        window.setVisible(true);

        currentPlayer = PLAYER_ONE;
    }

    protected void showWinner(int winner) {
        if (winner == Game.FREE) {
            JOptionPane.showMessageDialog(null, "TIE!");
        } else {
            JOptionPane.showMessageDialog(null, "Winner: Player" + winner);
        }
        resetGame();
        
    }

    protected boolean isMyTurn() {
        return thisPlayer == currentPlayer;
    }

    private void resetGame() {
        for (int i = 0; i < 3; i ++) {
            for (int j = 0; j < 3; j ++) {
                fields[i][j] = Game.FREE;
            }
        }
        gameWindow.repaint();
    }

    public abstract void inputReceived(int x, int y);
    public abstract void close();
    public abstract void packageReceived(Object object);


    public int[][] getFields() {
        return fields;
    }

    
    
}