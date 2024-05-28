import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Base64;

public class Mailer {
    private static DataOutputStream out;

    public static void main(String[] args) throws Exception {
        System.out.println("MailSender is running...");

        String user = "s1910676103@ru.ac.bd";
        String pass = "JannatGarden";

        String username = Base64.getEncoder().encodeToString(user.getBytes());
        String password = Base64.getEncoder().encodeToString(pass.getBytes());

        String to = "bdsumon4u@gmail.com";
        String subject = "Test";
        String body = "Hello, World!";

        Socket socket = new Socket("smtp.gmail.com", 465);
        out = new DataOutputStream(socket.getOutputStream());

        send("EHLO smtp.gmail.com");

        send("AUTH LOGIN");
        send(username);
        send(password);

        send("MAIL FROM: <" + user + ">");
        send("RCPT TO: <" + to + ">");

        send("DATA");
        send("FROM: " + user);
        send("TO: " + to);
        send("SUBJECT: " + subject);
        send(body + "\r\n.");
        send("QUIT");

        socket.close();
    }

    private static void send(String arg) {
        try {
            out.writeBytes(arg+"\r\n");
            Thread.sleep(1000);
            System.out.println("CLIENT: " + arg);
        } catch (Exception e) {
            //
        }
    }
}
