import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Sender extends Thread {
    private Socket socket;

    public Sender(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        System.out.println("Sender is running...");
        try {
            OutputStream out = socket.getOutputStream();
            DataOutputStream data = new DataOutputStream(out);
            Scanner scanner = new Scanner(System.in);
            while (socket.isConnected()) {
                String message = scanner.nextLine();
                data.writeUTF(message);
                if (message.equals("exit")) {
                    break;
                }
            }
            scanner.close();
            data.close();
            out.close();
        } catch (Exception e) {
            //
        }
    }
}
