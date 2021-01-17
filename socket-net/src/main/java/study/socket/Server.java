package study.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(9999);
            Socket socket = server.accept();
            //打印客户端地址
            System.out.println(socket.getInetAddress());
            //获取客户端输入
            InputStream in = socket.getInputStream();
            OutputStream out = socket.getOutputStream();

            Scanner scanner = new Scanner(in);

            while (scanner.hasNextLine()){
                String mes = scanner.nextLine();
                System.out.println("服务端收到"+mes);
                StringBuilder sb = new StringBuilder(mes);
                sb.reverse();
                System.out.println("服务端写出"+sb);
                out.write((sb+"\n").getBytes());
            }

            scanner.close();
            in.close();
            out.close();
            socket.close();
            server.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
