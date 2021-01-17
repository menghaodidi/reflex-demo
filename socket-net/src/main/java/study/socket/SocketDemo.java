package study.socket;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.UUID;

public class SocketDemo {
    public static void main(String[] args) throws UnknownHostException, InterruptedException {
        InetAddress local =
                InetAddress.getLocalHost();
        //local = InetAddress.getByName()
        System.out.println(local.getHostName());
        System.out.println(local.getHostAddress());
        System.out.println(local.getCanonicalHostName());


        ArrayList<String> i = new ArrayList<>();
        System.out.println(UUID.randomUUID().toString().substring(8));
        for(int j=0;j<30;j++){
            new Thread(()->{
                i.add(UUID.randomUUID().toString().substring(8));
                System.out.println(i);
            }).start();
        }
        //Thread.sleep(3000);
        i.forEach(System.out::println);

    }
}
