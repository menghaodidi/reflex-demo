package study.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.*;

public class ServerMul {
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(9999);
            Set<Socket> sockets = new HashSet<>();
            while (true){
                Socket socket = server.accept();
                sockets.add(socket);
                //打印客户端地址
                System.out.println("客户端"+socket.getInetAddress()+"已连接");
                //获取线程连接池
                ThreadPoolExecutor poolExecutor = (ThreadPoolExecutor)Executors.newFixedThreadPool(10);
                Future<String> result = poolExecutor.submit(new ClientHandler(socket));

                //如果使用返回值，会造成阻塞
                //System.out.println(socket+result.get());
                //直接创建线程
                //new Thread(new FutureTask<String>(new ClientHandler(socket))).start();

                //判断是否同一个socket
                System.out.println("使用的socket数量："+sockets.size());
            }

           // server.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


class ClientHandler implements Callable<String>{
    private  Socket socket;

    ClientHandler(Socket socket){
        this.socket = socket;
    }
    @Override
    public String call() throws Exception {
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
        return "客户端已断开";
    }
}