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

class MyNestedCalls extends NestedCalls {
    public NestedCallsI proxy;

    public MyNestedCalls(NestedCallsI proxy) {
        this.proxy = proxy;
    }    

    @Override public int a() { return proxy.a(); }
    @Override public int b(int a) { return proxy.b(a); }
    @Override public int c(int a) { return proxy.c(a); }
}

class MyProxy implements InvocationHandler {
    public NestedCallsI target;
    
    public MyProxy(NestedCallsI target) {
        this.target = target;
    }

    public Object invoke(Object proxed, Method method, Object[] args) {
        Object r = null;
        try {
            // System.out.println(method.getName());
            Method superMethod = MyNestedCalls.class.getDeclaredMethod(method.getName(), method.getParameterTypes()); 
            r = superMethod.invoke(target, args);
            
        } catch (Exception e) { e.printStackTrace(); return null; }
        return (NestedCalls)r;
    }
}

public class MainNestedCalls {
    public static void main(String[] args) throws Exception {
        NestedCallsI proxy = (NestedCallsI)Proxy.newProxyInstance(
            NestedCalls.class.getClassLoader(), 
            NestedCalls.class.getInterfaces(), 
            new MyProxy(new NestedCalls()));

        MyNestedCalls myNestedCalls = new MyNestedCalls(proxy);    
        System.out.println("a() :- " + myNestedCalls.a());
        //System.out.println("b() :- " + proxyNestedCalls.b(proxyNestedCalls.a()));
        //System.out.println("c() :- " + proxyNestedCalls.c(proxyNestedCalls.b(proxyNestedCalls.a())));

    }

    
}
