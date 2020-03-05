import java.io.Serializable;

/**
 * ClientPlayPackage
 */
public class ClientPlayPackage implements Serializable {
    private static final long serialVersionUID = -4275109205544826339L;
    private int x;
    private int y;

    public ClientPlayPackage(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}