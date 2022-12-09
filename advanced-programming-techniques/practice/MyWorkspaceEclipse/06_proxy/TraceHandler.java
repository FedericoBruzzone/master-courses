import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class TraceHandler implements InvocationHandler {
	Object target;
	
	public TraceHandler(Object target) {
		this.target = target;
	}
		
	@Override
	public Object invoke(Object proxy, Method m, Object[] args) throws Exception {
		try {
			System.out.println("Before: " + target.getClass().getMethod("message").invoke(target));
			Object out = m.invoke(target, args);
			System.out.println("After: " + target.getClass().getMethod("message").invoke(target));
			return out;
		} catch (Exception e) { e.printStackTrace(); return e; }
	}
	
}
