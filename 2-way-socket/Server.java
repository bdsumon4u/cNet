import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public boolean isRunning = true;

    public static void main(String[] args) throws Exception {
        ServerSocket ss = new ServerSocket(6767);
        Socket socket = ss.accept();

        Sender sender = new Sender(socket);
        Receiver receiver = new Receiver(socket);

        sender.start();
        receiver.start();

        ss.close();
    }
}