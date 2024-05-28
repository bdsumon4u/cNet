import java.io.DataInputStream;
import java.io.InputStream;
import java.net.Socket;

public class Receiver extends Thread {
    private Socket socket;

    public Receiver(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        System.out.println("Receiver is running...");
        try {
            InputStream in = socket.getInputStream();
            DataInputStream data = new DataInputStream(in);
            while (socket.isConnected()) {
                String message = data.readUTF();
                System.out.println("Received: " + message);
                if (message.equals("exit")) {
                    break;
                }
            }
            socket.close();
            data.close();
            in.close();
        } catch (Exception e) {
            //
        }
    }
}
