import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Base64;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class MailSender {
    private static DataInputStream in;
    private static DataOutputStream out;
    private static BufferedReader br;

    public static void main(String[] args) throws Exception {
        System.out.println("MailSender is running...");

        String user = "s1910676103@ru.ac.bd";
        String pass = "JannatGarden";

        String username = Base64.getEncoder().encodeToString(user.getBytes());
        String password = Base64.getEncoder().encodeToString(pass.getBytes());

        String to = "bdsumon4u@gmail.com";
        String subject = "Test";
        String body = "Hello, World!";

        SSLSocket socket = (SSLSocket) SSLSocketFactory.getDefault().createSocket("smtp.gmail.com", 465);
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());
        br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        send("EHLO smtp.gmail.com\r\n");
        for (int i = 0; i < 9; i++) {
            System.out.println("SERVER: " + br.readLine());
        }

        send("AUTH LOGIN\r\n");
        System.out.println("SERVER: " + br.readLine());
        send(username + "\r\n");
        System.out.println("SERVER: " + br.readLine());
        send(password + "\r\n");
        System.out.println("SERVER: " + br.readLine());

        send("MAIL FROM: <" + user + ">\r\n");
        System.out.println("SERVER: " + br.readLine());
        send("RCPT TO: <" + to + ">\r\n");
        System.out.println("SERVER: " + br.readLine());

        send("DATA\r\n");
        System.out.println("SERVER: " + br.readLine());

        send("FROM: " + user + "\r\n");
        send("TO: " + to + "\r\n");
        send("SUBJECT: " + subject + "\r\n");
        send(body + "\r\n.\r\n");
        System.out.println("SERVER: " + br.readLine());

        send("QUIT\r\n");
        System.out.println("SERVER: " + br.readLine());
    }

    private static void send(String arg) {
        try {
            out.writeBytes(arg);
            Thread.sleep(1000);
            System.out.println("CLIENT: " + arg);
        } catch (Exception e) {
            //
        }
    }
}
