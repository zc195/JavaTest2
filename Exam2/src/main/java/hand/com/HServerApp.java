package hand.com;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
public class HServerApp {
    static ServerSocket serverSocket;
    private static boolean result;
    private static String filepath;

    public static void main(String[] args) {
        try {
            serverSocket = new ServerSocket(5555);
            System.out.println("***服务器即将启动，等待客户端的连接***");
            Socket socket = serverSocket.accept();
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String file = br.readLine();
            findFile("src", file);
            if (result) {
                System.out.println("已找到" + file);
                File f = new File(file);
                BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(filepath + file)));
                System.out.println(filepath + file);
                String a = "";
                String temp = "";
                while ((temp = in.readLine()) != null) {
                    a += temp;
                }
                PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
                out.write(a);
                out.flush();
                socket.shutdownOutput();
            } else {
                System.out.println("未找到文件");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void findFile(String src, String file) {
    }
}
