package hand.com;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HServerApp implements Runnable {
    private int port;

    private HServerApp(int port) {
        this.port = port;
    }
    public void run() {
        try {
            ServerSocket server = new ServerSocket(port);
            while (true) {

                System.out.println("waiting...");
                Socket socket = server.accept();

                DataInputStream in = new DataInputStream(socket.getInputStream());
                String string = in.readUTF();
                System.out.println("client:" + string);

                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                out.writeUTF("hi,i am hserver!i say:" + string);
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        HServerApp serverApp = new HServerApp(9050);
        serverApp.run();
    }
}
