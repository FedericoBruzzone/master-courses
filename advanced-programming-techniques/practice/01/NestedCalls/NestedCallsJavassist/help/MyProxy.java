package help;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyProxy extends NestedCalls implements InvocationHandler {
    public NestedCallsI target = (NestedCallsI)Proxy.newProxyInstance(NestedCalls.class.getClassLoader(), NestedCalls.class.getInterfaces(), this);

    @Override public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(method.getName());
        Method m = NestedCalls.class.getDeclaredMethod(method.getName(), method.getParameterTypes());
        MethodHandle mh = MethodHandles.lookup().unreflectSpecial(m, getClass());
        Object res = mh.invokeWithArguments(args);
        return res;
    }
    
    @Override public int a() { return target.a(); }
    @Override public int b(int a) { return target.b(a); }
    @Override public int c(int a) { return target.c(a); }
}
