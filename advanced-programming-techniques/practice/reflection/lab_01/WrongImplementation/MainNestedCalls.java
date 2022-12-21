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

class MyNestedCalls extends NestedCalls implements NestedCallsI {
    public int a() {
        System.out.println(new Throwable().getStackTrace()[0]);
        return super.a();
    }

    public int b(int a) {
        System.out.println(new Throwable().getStackTrace()[0]);
        return super.b(a);
    }

    public int c(int a) {
        System.out.println(new Throwable().getStackTrace()[0]);
        return super.c(a);
    }
}

class MyProxy implements InvocationHandler {
    Object target;
    public MyProxy(Object target) { this.target = target; }

    public Object invoke(Object proxed, Method method, Object[] args) {
        try {
            // Object[] args2;
            // if (args != null) {
            //     var x = Arrays.asList(args);
            //     x.add(new Throwable().getStackTrace());
            //     args2 = x.toArray();
            // } else {
            //     args2 = new Object[]{new Throwable().getStackTrace()};
            // }
            
            var r = method.invoke(target, args);
            //var r = target.getClass().getMethod(method.getName(), Stream.of(args2).map(i -> i.getClass()).toArray(Class<?>[]::new)).invoke(target, args2);
            return r;
        } catch (Exception e) { e.printStackTrace(); return e; }
    }
}

public class MainNestedCalls {
    public static void main(String[] args) throws Exception {
        NestedCallsI nc = new MyNestedCalls();
        NestedCallsI proxed = (NestedCallsI) Proxy.newProxyInstance(
            nc.getClass().getClassLoader(), 
            nc.getClass().getInterfaces(), 
            new MyProxy(nc));
        
        int a = proxed.a();
        System.out.println(a);
    }
}