package hand.com;

/**
 * Hello world!
 *
 */
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 5555);
            OutputStream os = socket.getOutputStream();
            PrintWriter pw = new PrintWriter(os);
            pw.write("SampleChapter.pdf");
            pw.flush();
            socket.shutdownOutput();
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String a = "";
            String temp = "";
            while ((temp = in.readLine()) != null) {
                a += temp;
            }
            PrintWriter out = new PrintWriter(new OutputStreamWriter(new FileOutputStream("C:/Users/zhangchao/IdeaProjects/JavaTest2/Exam1/tmp/SampleChapter.pdf")));
            out.write(a);
            out.flush();
            out.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }
    }
}