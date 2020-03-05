import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

public class Connection implements Runnable {
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;
    private boolean running;

    private Game game;

    public Connection(Game game, Socket socket) {
        this.game = game;
        try {
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            inputStream = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        new Thread(this).start();
    }

    @Override
    public void run() {
        running = true;

        while (running) {
            try {
                Object object = inputStream.readObject();
                game.packageReceived(object);
            } catch (EOFException | SocketException e){
                running = false;
            } catch (ClassNotFoundException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendPackage(Object object) {
        try{
            outputStream.reset();
            outputStream.writeObject(object);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void close() {
        running = false;
        try {
            inputStream.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}