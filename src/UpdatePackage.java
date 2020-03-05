import java.io.Serializable;

public class UpdatePackage implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int[][] fields;
    private int currentPlayer;

    public UpdatePackage(int[][] fields, int currentPlayer) {
        this.fields = fields;
        this.currentPlayer = currentPlayer;
    }

    /**
     * @return the fields
     */
    public int[][] getFields() {
        return fields;
    }

    /**
     * @return the currentPlayer
     */
    public int getCurrentPlayer() {
        return currentPlayer;
    }
    
}