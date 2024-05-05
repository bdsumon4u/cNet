import java.net.Socket;

public class Client {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost", 6767);

        Sender sender = new Sender(socket);
        Receiver receiver = new Receiver(socket);

        sender.start();
        receiver.start();
    }
}
