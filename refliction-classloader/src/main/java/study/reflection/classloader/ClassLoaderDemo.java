package study.reflection.classloader;

import java.io.InputStream;
import java.util.Properties;

public class ClassLoaderDemo {
    public static void main(String[] args) {
        //1.获取一个系统类加载器
        ClassLoader classloader = ClassLoader.getSystemClassLoader();
        System.out.println(classloader);
        //2.获取系统类加载器的父类加载器，即扩展类加载器
        classloader = classloader.getParent();
        System.out.println(classloader);
        //3.获取扩展类加载器的父类加载器，即引导类加载器
        classloader = classloader.getParent();
        System.out.println(classloader);
        //4.测试当前类由哪个类加载器进行加载
        try {
            classloader =
                    Class.forName("study.reflection.classloader.Person").getClassLoader();
            System.out.println(classloader);


            //5.测试JDK提供的Object类由哪个类加载器加载
            classloader =
                    Class.forName("java.lang.Object").getClassLoader();
            System.out.println(classloader);
            //*6.关于类加载器的一个主要方法：getResourceAsStream(String str):获取类路径下的指定文件的输入流
            InputStream in = null;
            in = ClassLoaderDemo.class.getClassLoader().getResourceAsStream("笔记.properties");
            System.out.println(in);
            Properties pt = new Properties();
            pt.load(in);
            System.out.println(pt);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
