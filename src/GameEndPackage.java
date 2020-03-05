import java.io.Serializable;

/**
 * GameEndPackage
 */
public class GameEndPackage implements Serializable {

    private static final long serialVersionUID = 1L;

    private int winner;

    public GameEndPackage(int winner) {
        this.winner = winner;
    }

    public int getWinner() {
        return winner;
    }

    
}