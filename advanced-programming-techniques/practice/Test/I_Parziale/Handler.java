import java.lang.reflect.*;
import java.util.*;

public class Handler implements InvocationHandler {
    private final StudentI st;
    private final WorkerI wr;
    
    public Handler(StudentI st, WorkerI wr) {
        this.st = st;
        this.wr = wr;
    }
    
    private boolean methodEquals(Method m1, Method m2) {
        return m1.getName().equals(m2.getName()) && Arrays.equals(m1.getParameterTypes(), m2.getParameterTypes()) && m1.getReturnType().equals(m2.getReturnType());
    }
    
    private boolean isClassMethod(Class cls, Method m1) {
        return Arrays.stream(cls.getMethods()).anyMatch(m2 -> methodEquals(m1, m2));
    }
    
    public Object invoke(Object p, Method m, Object[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        if(isClassMethod(st.getClass(), m)) {
            return m.invoke(st, args);
        }
        if(isClassMethod(wr.getClass(), m)) {
            return m.invoke(wr, args);
        }
        throw new NoSuchMethodException("Il metodo non appartiene ne a Student ne a Worker");      
    }
}
