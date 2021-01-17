package study.socket;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 9999);
            OutputStream out = socket.getOutputStream();
            PrintStream printStream = new PrintStream(out);

            InputStream in = socket.getInputStream();
            BufferedReader buin = new BufferedReader(new InputStreamReader(in));
            Scanner scanner = new Scanner(System.in);
            while (true){
                String mes = scanner.nextLine();
                if("bye".equals(mes)) break;
                printStream.println(mes);

                System.out.println("客户端写出"+mes);
                String line = buin.readLine();
                System.out.println("客户端读到"+line);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
