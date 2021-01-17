package study.reflection.classloader;

import sun.nio.ch.IOUtil;

import javax.sound.midi.Soundbank;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.TreeSet;

public class RefictionDemo {
    public static void main(String[] args) {
//        try {
//            Class<?> cl = Class.forName("study.reflection.classloader.Person");
//            Annotation[] annotations = cl.getAnnotations();
//            for (Annotation an:annotations) {
//
//            }
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }


        Person person1 = new Person("1", "1", "1");
        Person person2 = new Person("2", "2", "2");
        Person person3 = new Person("3", "3", "3");

        List<Person> l = new ArrayList<Person>();
        l.add(person1);
        l.add(person2);
        l.add(person3);
        ListIterator listIterator = l.listIterator();
        while (listIterator.hasNext()){
            Person p = (Person)listIterator.next();
            System.out.println(p);
            if(p.getAge().equals("3")){
                listIterator.remove();
                listIterator.add( new Person("4", "4", "4") );
            }
        }
        l.forEach(System.out::println);

    }
}
