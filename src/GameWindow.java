import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

public class GameWindow extends JPanel{
    private static final long serialVersionUID = 5446208496818554929L;
    
    private Game game;

    public GameWindow(Game game) {
        this.game = game;
        addMouseListener(new Input());
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2D = (Graphics2D) g;
        g2D.setStroke(new BasicStroke(10));

        for (int x = Game.FIELD_WIDTH; x <= Game.FIELD_WIDTH * 2; x += Game.FIELD_WIDTH) {
            g2D.drawLine(x, 0, x, Game.HEIGHT);
        }
        
        for (int y = Game.FIELD_HEIGHT; y <= Game.FIELD_HEIGHT * 2; y += Game.FIELD_HEIGHT) {
            g2D.drawLine(0, y, Game.WIDTH, y);
        }
        
        g.setFont(new Font("Arial", Font.BOLD, 50));

        for (int line = 0; line < 3; line ++) {
            for (int column = 0; column < 3; column ++) {
                int field = game.getFields()[line][column];
                if (field != Game.FREE){
                    
                    int x = line * Game.FIELD_WIDTH + 100;
                    int y = column * Game.FIELD_HEIGHT + 100;
                    
                    if (field == Game.PLAYER_ONE) {
                        g2D.setColor(Color.blue);
                    } else {
                        g2D.setColor(Color.red);
                    }

                    g.drawString(Game.SIMBOLS[field - 1], x, y);
                }
            }
        }

    }

    class Input extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            if (e.getButton() == MouseEvent.BUTTON1) {
                int x = e.getX() / Game.FIELD_WIDTH;
                int y = e.getY() / Game.FIELD_HEIGHT;

                if (x == 3) {
                    x --;
                }

                if (y == 3) {
                    y --;
                }

                game.inputReceived(x, y);
            }
        }
    }
    
}