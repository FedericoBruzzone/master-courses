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
    public NestedCallsI target;
    public Field f;
    public MyProxy() throws NoSuchFieldException, SecurityException {
        this.target = (NestedCallsI)Proxy.newProxyInstance(NestedCalls.class.getClassLoader(), NestedCalls.class.getInterfaces(), this);
        f = NestedCalls.class.getDeclaredField("i");
        f.setAccessible(true);
    }

    public Object myInvoke(String methodName, Object[] args) throws IllegalArgumentException, IllegalAccessException {
        Object r = null;
        if (methodName == "a") {
            System.out.println("a: " + f.get(this));
            r = super.a();
        }
        if (methodName == "b") {
            System.out.println("b: " + f.get(this));
            r = super.b((int)args[0]);
        }
        if (methodName == "c") {
            System.out.println("c: " + f.get(this));
            r = super.c((int)args[0]);
        }
        return r;
    }
    
    public Object invoke(Object proxed, Method method, Object[] args) {
        Object r = null;
        try {
            r = myInvoke(method.getName(), args);
        } catch (Exception e) { e.printStackTrace(); return null; }
        return r;
    }

    @Override public int a() { return target.a(); }
    @Override public int b(int a) { return target.b(a); }
    @Override public int c(int a) { return target.c(a); }
}

public class MainNestedCalls {
    public static void main(String[] args) throws Exception {
        MyProxy myNestedCalls = new MyProxy();    
        System.out.println("a() :- " + myNestedCalls.a());
    }

    
}
