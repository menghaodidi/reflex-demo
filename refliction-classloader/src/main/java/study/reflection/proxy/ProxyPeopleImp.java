package study.reflection.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyPeopleImp implements ProxyPeople{

    @Override
    public void say2() {
        System.out.println(2222);
    }

    public static void main(String[] args) {
        ProxyPeopleImp p = new ProxyPeopleImp();
        ProxyPeople proxyPeople = (ProxyPeople)Proxy.newProxyInstance(p.getClass().getClassLoader(),
                p.getClass().getInterfaces(),
                new PeopleInvo(p));
        proxyPeople.say2();
    }

}

class PeopleInvo implements InvocationHandler{

    public PeopleInvo(Object o){
        this.ob = o;
    }

    private Object ob;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(11111);
        method.invoke(ob);
        System.out.println(3333333);
        return null;
    }
}
