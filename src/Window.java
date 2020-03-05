import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class Window extends JFrame {
    
    private static final long serialVersionUID = 2460214854866562898L;

    private Game game;

    public Window(Game game, String title, int width, int height) {
        super(title);
        this.game = game;
        setResizable(false);
        getContentPane().setPreferredSize(new Dimension(width, height));
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        addWindowListener(new Listener());
    }

    class Listener extends WindowAdapter {
        @Override
        public void windowClosing(WindowEvent e) {
            game.close();
        }
    }
    
}