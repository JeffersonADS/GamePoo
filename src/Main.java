import javax.swing.JOptionPane;

public class Main {
    public static void main(String[] args) {
        int option = Integer.parseInt(JOptionPane.showInputDialog("1 for server or 2 for client"));
        if (option == 1) {
            new ServerGame();
        } else if (option == 2) {
            new ClientGame();
        }
    }
    
}