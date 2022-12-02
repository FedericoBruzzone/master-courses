package help;
import java.lang.reflect.*;

import MyProxy;
import NestedCalls;

public class TestClass {
    public static void main(String[] args) throws NoSuchFieldException, SecurityException {
        NestedCalls pnc = (NestedCalls)Proxy.newProxyInstance(NestedCalls.class.getClassLoader(), NestedCalls.class.getInterfaces(), new MyProxy());
        System.out.println("NestedCalls a() :- " + pnc.a());
        System.out.println("NestedCalls b() :- " + pnc.b(pnc.a()));
        System.out.println("NestedCalls c() :- " + pnc.c(pnc.b(pnc.a())));
    }
}
