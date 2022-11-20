import java.lang.reflect.*;
import java.lang.reflect.Proxy;

interface NestedCallsI {
    public int a();
    public int b(int a);
    public int c(int a);
}

class NestedCalls implements NestedCallsI {
    private int i = 0;

    public int a() {
        return b(i++);
    }

    public int b(int a) {
        return (i < 42) ? c(b(a())) : 1;
    }

    public int c(int a) {
        return --a;
    }
}

class MyProxy extends NestedCalls implements InvocationHandler {
    private final NestedCallsI proxy; 

    public MyProxy() {
        proxy = (NestedCallsI) Proxy.newProxyInstance(
            NestedCalls.class.getClassLoader(), 
            NestedCalls.class.getInterfaces(), 
            this);
    }
    
    public Object invoke(Object proxed, Method method, Object[] args) {
        Object r = null;
        try {
            System.out.println(method.getName());
            //Method superMethod = NestedCalls.class.getDeclaredMethod(method.getName(), method.getParameterTypes()); 
            
        } catch (Exception e) { e.printStackTrace(); return null; }
        return r;
    }

    @Override public int a() {
        return proxy.a();
    }

    @Override public int b(int a) {
        return proxy.b(a);
    }

    @Override public int c(int a) {
        return proxy.c(a);
    }
}

public class MainNestedCalls {
    public static void main(String[] args) throws Exception {
        NestedCalls proxyNestedCalls = new MyProxy();
        System.out.println("a() :- " + proxyNestedCalls.a());
        //System.out.println("b() :- " + proxyNestedCalls.b(proxyNestedCalls.a()));
        //System.out.println("c() :- " + proxyNestedCalls.c(proxyNestedCalls.b(proxyNestedCalls.a())));

    }

    
}
