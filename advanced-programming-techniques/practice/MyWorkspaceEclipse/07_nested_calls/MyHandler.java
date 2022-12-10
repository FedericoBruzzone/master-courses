import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyHandler extends NestedCalls implements InvocationHandler {
	private NestedCallsI target;
	private int counter;

	public MyHandler() {
		this.target = (NestedCallsI) Proxy.newProxyInstance(NestedCalls.class.getClassLoader(),
				NestedCalls.class.getInterfaces(), this);
		this.counter = 0;
	}

	@Override
	public Object invoke(Object proxy, Method m, Object[] args) throws Throwable {
		Method superM = NestedCalls.class.getDeclaredMethod(m.getName(), m.getParameterTypes());
		counter++;
		System.out.println(repeat("#", counter) + " " + m.getName());
		MethodHandle newM = MethodHandles.lookup().unreflectSpecial(superM, getClass());
		Object res = newM.bindTo(this).invokeWithArguments(args);
		counter--;
		return res;
	}

	public String repeat(String str, int times) {
		StringBuilder sb = new StringBuilder(str.length() * times);
		for (int i = 0; i < times; i++)
			sb.append(str);
		return sb.toString();
	}

	@Override
	public int a() {
		return target.a();
	}

	@Override
	public int b(int a) {
		return target.b(a);
	}

	@Override
	public int c(int a) {
		return target.c(a);
	}

}
